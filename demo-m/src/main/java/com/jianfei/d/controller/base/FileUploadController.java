/**
  *project demo-m
  *@author changchun.wu
  *2017年6月29日上午11:41:45
  */
package com.jianfei.d.controller.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jianfei.d.common.config.FileConfig;
import com.jianfei.d.common.utils.FileUtils;
import com.jianfei.d.common.vo.FileMeta;

/**
 * 文件上传
* @author ZhangBo   
* @date 2017年4月7日 下午2:46:47
 */
@Controller
@RequestMapping("/sys/file/upload")
public class FileUploadController{
    
    @Autowired
    private FileConfig fileConfig;
    
    /**
     * Uploadify附件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadify", method = RequestMethod.POST)
    @ResponseBody
    public FileMeta single(@RequestParam("file") CommonsMultipartFile file){

        FileMeta fileMeta = new FileMeta();
        if(!file.isEmpty()){
            //文件大小校验
            fileMeta.setSize(FileUtils.byteCountToDisplaySize(file.getSize()));
            //文件类型
            fileMeta.setType(file.getContentType());
            //旧文件名
            //fileMeta.setOldName(file.getOriginalFilename());
            //新文件名、URL
            FileUtils.copyFile(file, fileConfig, fileMeta);
            
            return fileMeta;
        }
        else{
            fileMeta.setStatus(false);
            fileMeta.setMessage("请选择文件。");
            return fileMeta;
        }
    }
    
    /**
     * Ckeditor 文件上传
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/ckeditor", method = RequestMethod.POST)
    public void ckeditor(
            @RequestParam("upload") CommonsMultipartFile file,
            HttpServletRequest request, HttpServletResponse response) {
        
        String callBack = request.getParameter("CKEditorFuncNum"); 
        PrintWriter out = null;
        try {
            if(!file.isEmpty()){
                out = response.getWriter();
                
                FileMeta fileMeta = new FileMeta();
                FileUtils.copyFile(file, fileConfig, fileMeta);
                
                this.setCkeditorSuccess(out, callBack, fileMeta.getUrl());
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
            this.setCkeditorError(out, callBack, "文件上传失败");
        }
        finally{
            if(out != null){
                out.close();
            }
        }
    }
    
    private void setCkeditorSuccess(PrintWriter out, String callBack, String fileUrl){
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callBack  
                + ",'" + fileUrl + "',''" + ")");
        out.println("</script>");
        out.flush();
    }
    
    private void setCkeditorError(PrintWriter out, String callBack, String message){
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callBack
                + ",''," + "'"+message+"');");
        out.println("</script>");
        out.flush();
    }
    
}

