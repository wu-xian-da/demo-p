/**
  *project demo-l
  *@author changchun.wu
  *2017年7月11日下午2:26:39
  */
package com.jianfei.d.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
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
	private static final Logger logger = LoggerFactory
			.getLogger(HttpClientUtils.class);
	
	
	//重试次数
	private static final int retryTime = 3;
	
	// 设置连接超时时间
	private static int REQUEST_TIMEOUT = 5 * 1000; // 设置请求超时5秒钟
	private static int SO_TIMEOUT = 3 * 1000; // 设置等待数据超时时间3秒钟
	private static Long CONN_MANAGER_TIMEOUT = 500L; // 连接不够用的时候等待超时时间
			
	//线程安全DefaultHttpClient
	private static DefaultHttpClient httpClient;
	

	static {
		// 设置组件参数, HTTP协议的版本,1.1/1.0/0.9
		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
		HttpProtocolParams.setUseExpectContinue(params, true);
		
		params.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				REQUEST_TIMEOUT);
		params.setParameter(CoreConnectionPNames.SO_TIMEOUT, SO_TIMEOUT);
		params.setParameter(ClientPNames.CONN_MANAGER_TIMEOUT,
				CONN_MANAGER_TIMEOUT);

		// 设置访问协议
		SchemeRegistry schreg = new SchemeRegistry();
		schreg.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		schreg.register(new Scheme("https", 443, SSLSocketFactory
				.getSocketFactory()));

		// 多连接的线程安全的管理器
		PoolingClientConnectionManager pccm = new PoolingClientConnectionManager(
				schreg);
		pccm.setDefaultMaxPerRoute(50); // 每个主机的最大并行链接数
		pccm.setMaxTotal(200); // 客户端总并行链接最大数

		httpClient = new DefaultHttpClient(pccm, params);

		
		// 可以自动重连
		HttpRequestRetryHandler requestRetryHandler2 = new HttpRequestRetryHandler() {
			// 自定义的恢复策略
			public synchronized boolean retryRequest(IOException exception,
					int executionCount, HttpContext context) {
				// 设置恢复策略，在发生异常时候将自动重试3次
				if (executionCount > retryTime) {
					// 超过最大次数则不需要重试
					return false;
				} 
				if (exception instanceof NoHttpResponseException) {
					// 服务停掉则重新尝试连接
					return true;
				}
				if (exception instanceof SSLHandshakeException) {
					// SSL异常不需要重试
					return false;
				}
				
				HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST);
				boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
				if (!idempotent) {
					// 请求内容相同则重试
					return true;
				}
				return false;
			}
		};
		
		
		httpClient.setHttpRequestRetryHandler(requestRetryHandler2);
	}

	// Post
	public static String post(String url, List<NameValuePair> params) {
		String result = null;
		HttpPost post = new HttpPost(url);
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
		HttpEntity entityRos = null;
		try {
			StringEntity s = new StringEntity(jsonStr);  
            s.setContentEncoding("UTF-8");  
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
	
	public static String testGY(String url,  String jsonStr) {
		String result = null;
		HttpPost post = new HttpPost(url);
		
		HttpEntity entityRos = null;
		try {
			StringEntity s = new StringEntity(jsonStr);  
            s.setContentEncoding("UTF-8");  
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

