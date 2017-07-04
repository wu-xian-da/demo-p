<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp" %>

<%@ attribute name="moduleName" type="java.lang.String" required="true"%>
<%@ attribute name="hiddenName" type="java.lang.String" required="true"%>
<%@ attribute name="hiddenValue" type="java.lang.String" required="true"%>

<input type="file" id="${moduleName }_input">
<div id="${moduleName }_box">
  	<c:if test="${not empty hiddenValue}">
  		<img style="width:90px;height:60px;" src="${hiddenValue }" />
  		<input type="hidden" name="${hiddenName }" value="${hiddenValue }">
  	</c:if>
 </div>
										  
<script type="text/javascript">
$(function(){
	$("#${moduleName }_input").uploadify($.extend(uploadifyPro, {
		'buttonText' : '上传',
		'fileTypeExts' : '*.gif; *.jpg; *.png',
		'onUploadSuccess' : function(file, data, response) {
			var dataObj = $.parseJSON(data);
			if (!dataObj.status) {
				alert(dataObj.message);
				return;
			}
			
			var item = '<img style="width:90px;height:60px;" src="' + dataObj.url +'" />' +
						'<input type="hidden" name="${hiddenName}" value="'+dataObj.url+'">';
			$('#${moduleName }_box').html(item);
			window._resize();
		}
	}));
});
</script>