/**
  *project demo-m
  *@author changchun.wu
  *2017年7月11日下午4:58:17
  */
package com.jianfei.d.common.utils;

import java.io.IOException;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.jianfei.d.common.config.Constants;
import com.jianfei.d.common.config.EsConfig;

@Component
public class ElasticsearchRestService {
    
    private static final String GET = "GET";
    
    @Autowired
    private EsConfig config;
    
    private RestClient client;
    
    @PostConstruct
    public void init(){
        client = RestClient.builder(config.getHttpHosts())
                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    public Builder customizeRequestConfig(Builder requestConfigBuilder) {
                        return requestConfigBuilder.setConnectTimeout(config.getConnTimeout())
                                .setSocketTimeout(config.getSocketTimeout());
                    }
                })
                .setMaxRetryTimeoutMillis(config.getMaxRetryTimeout())
                .build();
    }
    
    public <T> T get(String url, String json, Class<T> c){
        try{
            HttpEntity entity = new NStringEntity(json, ContentType.APPLICATION_JSON);
            
            Response response = client.performRequest(
                    GET, 
                    url, 
                    Collections.<String, String>emptyMap(), 
                    entity);
            
            if(response.getStatusLine().getStatusCode() < 300){
                String responseJson = EntityUtils.toString(response.getEntity(), Constants.ENCODING);
                System.out.println(responseJson);
                return JSON.parseObject(responseJson, c);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    private void close() throws IOException {
        if (this.client != null) {
            this.client.close();
        }
    }
    
    @Override
    public void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}

