/**
  *project demo-m
  *@author changchun.wu
  *2017年6月27日下午5:04:45
  */
package com.jianfei.d.common.utils;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jianfei.d.common.config.FileConfig;
import com.jianfei.d.common.vo.FileMeta;

public class FileUtils {
    
    private static final char separator = '/';
    
    public static final String CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    //新目录
    public static String newDic(){
        StringBuilder path = new StringBuilder();
        path.append(separator)
            .append(JodaUtil.getYear()).append(separator)
            .append(JodaUtil.getMonth()).append(separator)
            .append(JodaUtil.getDay()).append(separator);
        return  path.toString();
    }

    public static String newFileName(String ext){
        return RandomStringUtils.random(3, CHARS) + ext;
    }
    
    public static String randomString(int id){
        return JodaUtil.formatDate(new Date(), "yyyyMMdd") + "-" + String.format("%04d", id);
    }
    
    public static String newFileName(String oldName, String ext){
        String newFileName = null;
        int lastIndex = oldName.lastIndexOf(".");
        if(lastIndex == -1){
            newFileName = oldName;
        }
        else{
            newFileName = oldName.substring(0, lastIndex) + "_" + RandomStringUtils.random(3, CHARS) + ext;
        }
        return newFileName;
    }
    
    public static String getFileExt(String fileName){
        if(fileName.lastIndexOf(".") == -1){
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    }
    
    public static void copyFile(MultipartFile file, FileConfig config, FileMeta fileMeta){
        if(file.isEmpty()){
            return ;
        }
        String ext = getFileExt(file.getOriginalFilename());
        String newDic = newDic();
        String newFileName = newFileName(file.getOriginalFilename(), ext);
        
        File dest = new File(config.getBasePath() + newDic, newFileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        fileMeta.setUrl(config.getMapping() + newDic + newFileName);
        fileMeta.setNewName(newFileName);
    }
    
    public static String byteCountToDisplaySize(long size){
        return org.apache.commons.io.FileUtils.byteCountToDisplaySize(size);
    }
    
    
    
}

