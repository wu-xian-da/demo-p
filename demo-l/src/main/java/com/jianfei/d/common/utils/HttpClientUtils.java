/**
  *project demo-l
  *@author changchun.wu
  *2017年7月11日下午2:26:39
  */
package com.jianfei.d.common.utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.List;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	
	
	//重试次数
	private static final int retryTime = 3;
	
	// 请求的超时设置
	private static final int connectionRequestTimeout = 3000;
	private static final int connectTimeout = 1000;
	private static final int socketTimeout = 2000;
			
	private static CloseableHttpClient httpClient;
	private static RequestConfig requestConfig;
	
	static {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainsf)
                .register("https", sslsf)
                .build();
        
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加到200
        cm.setMaxTotal(200);
        // 将每个路由基础的连接增加到20
        cm.setDefaultMaxPerRoute(20);
        
        //请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception,int executionCount, HttpContext context) {
                if (executionCount >= retryTime) {// 如果已经重试了3次，就放弃                    
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试                    
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常                    
                    return false;
                }                
                if (exception instanceof InterruptedIOException) {// 超时                    
                    return false;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达                    
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝                    
                    return false;
                }
                if (exception instanceof SSLException) {// ssl握手异常                    
                    return false;
                }
                
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {                    
                    return true;
                }
                return false;
            }
        };
        
        requestConfig = RequestConfig.custom()  
                .setConnectionRequestTimeout(connectionRequestTimeout)  
                .setConnectTimeout(connectTimeout)  
                .setSocketTimeout(socketTimeout)  
                .build();
        
       httpClient = HttpClients.custom()
                    .setConnectionManager(cm)
                    .setRetryHandler(httpRequestRetryHandler)
                    .build();
	}

	// Post
	public static String post(String url, List<NameValuePair> params) {
		String result = null;
		HttpPost post = new HttpPost(url);
		post.setConfig(requestConfig);
		HttpEntity entityRos = null;
		try {
			// 设置参数
			post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
			// 发送请求
			HttpResponse httpResponse = httpClient.execute(post);
			// 获取返回数据
			entityRos = httpResponse.getEntity();
			result = EntityUtils.toString(entityRos, Consts.UTF_8);
		} catch (UnsupportedEncodingException e) {
			logger.error("参数异常******", e);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocol异常******", e);
		} catch (IOException e) {
			logger.error("IO异常******", e);
		} catch (Exception e) {
			logger.error("异常******", e);
		} finally {
			EntityUtils.consumeQuietly(entityRos);
			post.releaseConnection();
		}
		return result;
	}

	// Get 请求
	public static String get(String url) {
		String result = null;
		HttpGet get = new HttpGet(url);
		get.setConfig(requestConfig);
		HttpEntity entityRos = null;
		try {
			// 发送请求
			HttpResponse httpResponse = httpClient.execute(get);
			// 获取返回数据
			entityRos = httpResponse.getEntity();
			result = EntityUtils.toString(entityRos, Consts.UTF_8);
		} catch (UnsupportedEncodingException e) {
			logger.error("参数异常******", e);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocol异常******", e);
		} catch (IOException e) {
			logger.error("IO异常******", e);
		} catch (Exception e) {
			logger.error("异常******", e);
		} finally {
			EntityUtils.consumeQuietly(entityRos);
			get.releaseConnection();
		}
		return result;
	}
	
	//Post
	public static String post(String url,  String jsonStr) {
		String result = null;
		HttpPost post = new HttpPost(url);
		post.setConfig(requestConfig);
		HttpEntity entityRos = null;
		try {
			StringEntity s = new StringEntity(jsonStr,"UTF-8");  
            s.setContentType("application/json");    
            post.setEntity(s);  
			// 发送请求
			HttpResponse httpResponse = httpClient.execute(post);
			// 获取返回数据
			entityRos = httpResponse.getEntity();
			result = EntityUtils.toString(entityRos, Consts.UTF_8);
		} catch (UnsupportedEncodingException e) {
			logger.error("参数异常******", e);
		} catch (ClientProtocolException e) {
			logger.error("ClientProtocol异常******", e);
		} catch (IOException e) {
			logger.error("IO异常******", e);
		} catch (Exception e) {
			logger.error("异常******", e);
		} finally {
			EntityUtils.consumeQuietly(entityRos);
			post.releaseConnection();
		}
		return result;
	}

//	public static void main(String[] args) {
//
//		//POST调用  begin
//		//添加参数  
//		List<NameValuePair> params = new ArrayList<NameValuePair>();  
//		params.add(new BasicNameValuePair("name", "aaaa"));
//		params.add(new BasicNameValuePair("sex", "男"));
//
//		String urlPost = "http://www.ath.com";
//		String resultPost = HttpClientUtil.post(urlPost, params);
//		//POST调用  end
//		
//		
//		//GET调用  begin
////		String urlGet = "";
////		String resultGet = HttpClientUtil.get(urlGet);
//		//GET调用  end
//	}
}

