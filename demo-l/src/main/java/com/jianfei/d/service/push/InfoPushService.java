/**
  *project demo-l
  *@author changchun.wu
  *2017年7月10日下午4:46:43
  */
package com.jianfei.d.service.push;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.jianfei.d.base.service.CrudService;
import com.jianfei.d.common.utils.WeChatMsgSendUtils;
import com.jianfei.d.dao.push.InfoPushDao;
import com.jianfei.d.entity.common.InfoPushStatus;
import com.jianfei.d.entity.common.InfoPushType;
import com.jianfei.d.entity.push.InfoPush;

@Service
public class InfoPushService extends CrudService<InfoPushDao, InfoPush> {
	/***
	 * 更改推送状态
	 * @param id
	 * @param pushStatus
	 * @param pushTime
	 * @param updateTime
	 * @return
	 */
	public int updatePushStatusById(Long id,String pushRange,InfoPushStatus pushStatus,Date pushTime,Date updateTime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pushRange", pushRange);
		map.put("pushStatus", pushStatus);
	    map.put("pushTime", pushTime);
	    map.put("updateTime", updateTime);
	    return this.dao.updatePushStatusById(map);
	}
	
	/******
	 * 微信具体推送
	 * @param pushId
	 * @param pushRange
	 * @return(10000:推送成功;10001:获取微信授权失败;10002:上传封面图片失败;10003:上传富文本中图片失败;10004:上传图文消息素材失败;10005:群发消息失败)
	 */
	public int push(Long pushId, String pushRange, String baseURL){
		InfoPush infoPush = this.dao.get(pushId);
		String thumbMediaId = "";
		//是否展示封面照
		int showCoverPic = 1;
		String imgUrl = "";
		String newsMediaId = "";
		boolean flag = false;
		
		String accessToken = WeChatMsgSendUtils.getAccessToken();
		if(StringUtils.isBlank(accessToken)){
			return 10001;
		}else{
			if(InfoPushType.TPXW.equals(infoPush.getInfoType())){
				//封面图片
				thumbMediaId = WeChatMsgSendUtils.mediaUpload(accessToken, baseURL+infoPush.getInfoImg() , "thumb");
					if(StringUtils.isBlank(thumbMediaId)){
						return 10002;
					}
				}else{
					//封面图片无
					showCoverPic = 0;
					thumbMediaId = WeChatMsgSendUtils.mediaUpload(accessToken, baseURL+"/static/img/no_pic.png" , "thumb");
					if(StringUtils.isBlank(thumbMediaId)){
						return 10002;
					}
				}
			}
			
			//替换富文本中的图片路径 begin
			String htmlCodes = infoPush.getInfoContent();
			List<String> srcs = WeChatMsgSendUtils.getImageSrc(htmlCodes);
			for (String src : srcs) {
				imgUrl = WeChatMsgSendUtils.uploadImg(accessToken, baseURL + src);
				if(StringUtils.isBlank(imgUrl)){
					return 10003;
				}else{
					htmlCodes = htmlCodes.replaceAll(src, imgUrl); 
				}
			}
			
			newsMediaId = WeChatMsgSendUtils.uploadNews(accessToken, infoPush.getInfoName(), thumbMediaId, showCoverPic, "", "", htmlCodes, "");
			if(StringUtils.isBlank(newsMediaId)){
				return 10004;
			}
			
			flag = WeChatMsgSendUtils.msgMassSendAll(accessToken, newsMediaId);
			if(!flag){
				//发送失败，休眠1s，再发一次
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
				}
				
				flag = WeChatMsgSendUtils.msgMassSendAll(accessToken, newsMediaId);
			}
		}
		
		if(!flag){
			return 10005;
		}else{
			this.updatePushStatusById(pushId, pushRange, InfoPushStatus.YTS, new Date(), new Date());
			return 10000;
		}
	}
}
