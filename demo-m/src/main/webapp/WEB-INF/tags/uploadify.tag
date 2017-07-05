<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp" %>

<%@ attribute name="moduleName" type="java.lang.String" required="true"%>
<%@ attribute name="hiddenName" type="java.lang.String" required="true"%>
<%@ attribute name="hiddenValue" type="java.lang.String" required="true"%>
<%@ attribute name="buttonText" type="java.lang.String" required="false"%>
<%@ attribute name="imgWidth" type="java.lang.String" required="false"%>
<%@ attribute name="imgHeight" type="java.lang.String" required="false"%>

<input type="file" id="${moduleName }_input">
<div id="${moduleName }_box">
  	<c:if test="${not empty hiddenValue}">
  		<img style="width:${empty imgWidth ? 90 : imgWidth}px;height:${empty imgHeight ? 60 : imgHeight}px;border:1px solid #ccc; margin-top:10px;" 
  		src="${hiddenValue }" />
  		<input type="hidden" name="${hiddenName }" value="${hiddenValue }">
  	</c:if>
 </div>
										  
<script type="text/javascript">
$(function(){
	$("#${moduleName }_input").uploadify($.extend(uploadifyPro, {
		'buttonText' : '${empty buttonText ? "上传" : buttonText}',
		'fileTypeExts' : '*.gif; *.jpg; *.png; *.bmp; *.ico',
		'onUploadSuccess' : function(file, data, response) {
			var dataObj = $.parseJSON(data);
			if (!dataObj.status) {
				alert(dataObj.message);
				return;
			}
			
			var item = '<img style="width:${empty imgWidth ? 90 : imgWidth}px;height:${empty imgHeight ? 60 : imgHeight}px;border:1px solid #ccc; margin-top:10px;" src="' + dataObj.url +'" />' +
						'<input type="hidden" name="${hiddenName}" value="'+dataObj.url+'">';
			$('#${moduleName }_box').html(item);
			window._resize('.infomation-management-edit');
		}
	}));
});
</script>