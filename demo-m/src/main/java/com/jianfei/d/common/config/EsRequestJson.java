/**
  *project demo-m
  *@author changchun.wu
  *2017年7月11日下午4:57:31
  */
package com.jianfei.d.common.config;

public class EsRequestJson {
    
    public static final String DAY_HOUR = "{"
                                + "\"query\": {\"match\": {\"url\": {"
                                + "\"query\": \"/log/index\","
                                + "\"operator\": \"and\""
                            + "}"
                            + "}},"
                            + "\"aggs\": {"
                                + "\"entirys\": {"
                                    + "\"date_histogram\": {"
                                       + "\"field\": \"@timestamp\","
                                        + "\"interval\": \"2h\","
                                        + "\"format\": \"HH\""
                                        //+ "\"time_zone\": \"+08:00\","
                                        //+ "\"missing\": 0"
                                    + "}"
                                + "}"
                            + "}"
                            + "}";
    
    
    
    
    public static final String DEVICE_TYPE = "{"
                                            + "\"query\": {\"match\": {\"url\": {"
                                            + "\"query\": \"/log/index\","
                                            + "\"operator\": \"and\""
                                        + "}"
                                        + "}},"
                                        + "\"aggs\": {"
                                            + "\"entirys\": {"
                                                + "\"terms\": {"+ 
                                                    "\"field\": \"ua.device.keyword\","
                                                    + "\"size\": 12"
                                                    + "\"order\":{"
                                                    	+ "\"_count\": \"desc\""
                                                    + "}"
                                                + "}"
                                            + "}"
                                        + "}"
                                        + "}";
    
    
    /*public static final String AUTH_LIST = "{"
            + "\"query\": {"
            	+ "\"bool\": {"
                	+ "\"should\":["
                        + "{\"match\": {\"url\": {\"query\": \"/log/auth/sms\", \"operator\": \"and\"}}},"
                        + "{\"match\": {\"url\": {\"query\": \"/log/auth/wx\", \"operator\": \"and\"}}}"
                    + "]"
               + ", \"must\": {\"regexp\": {\"url\": {\"value\": \".*13855178829.*\"}}}"
        + "}}}";*/
    
    
    
    public static final String AUTH_METHODS = "{"
            + "\"aggs\": {"
            + "\"entirys\": {"
                + "\"adjacency_matrix\": {"
                    + "\"filters\": {"
                    + "\"微信\": "
                        + "{\"match\": {\"url\": {\"query\": \"/log/auth/wx\", \"operator\": \"and\"}}},"
                    + "\"短信\": "
                        + "{\"match\": {\"url\": {\"query\": \"/log/auth/sms\", \"operator\": \"and\"}}}"
                    + "}"
                + "}"
            + "}"
        + "}"
        + "}";
    
    
    public static final String DAY_RANGE = "{" + 
                                            "\"query\": {" +
                                            "\"match\": {"
                                                + "\"url\": {"
                                                    + "\"query\": \"/log/index\", "
                                                    + "\"operator\": \"and\""
                                                + "}"
                                            + "}"
                                      + "},"
                                     + "\"aggs\": {"
                                         + "\"entirys\": {"
                                             + "\"terms\": {"
                                                 + "\"field\": \"_index\","
                                                 + "\"size\": 15,"
                                                 + "\"order\": {"
                                                     + "\"_term\": \"desc\""
                                                 + "}"
                                                 + "}"
                                           + "}"
                                     + "}"
                                   + "}";
    
}

