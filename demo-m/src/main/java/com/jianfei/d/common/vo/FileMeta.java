/**
  *project demo-m
  *@author changchun.wu
  *2017年6月27日下午5:06:10
  */
package com.jianfei.d.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileMeta{

    private String newName;
    
    //文件类型
    private String type;
    
    //文件大小
    private String size;
    
    //文件访问地址
    private String url;
    
    private Boolean status = Boolean.TRUE; //文件上传状态
    
    private String message; //返回消息

}
