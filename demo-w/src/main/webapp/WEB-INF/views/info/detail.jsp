<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/WEB-INF/include/header.jsp"%>
	<%@ include file="/WEB-INF/include/css.jsp"%>
</head>	
<body>
    <div id="app">
        <style>
            #html,body{ background: #FFF;}
        </style>
        <div class="app-bannar">
            <span class="header-arrow" onclick="javascript:history.go(-1);"></span>
            <h2>
            	<c:if test="${not empty infoDetail }">
            		${infoDetail.title } - 详情页
            	 </c:if>
            	 
            	 <c:if test="${not empty infoList }">
            	    ${navContent.navName } - 文章列表
            	 </c:if>
            </h2>
        </div>

        <div class="app-container">
           <c:if test="${not empty infoDetail }">
           		<div class="app-container-news">
	              <h2 class="title">${infoDetail.title }</h2> 
	              <div class="news-souce"><fmt:formatDate value="${infoDetail.createTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></div>
	              <div class="app-container-new-content">
	                  ${infoDetail.content }
	              </div>
	            </div>
           </c:if>
           
           <c:if test="${not empty infoList }">
           		<c:forEach items="${infoList }" var="info">
               		<a href="${base }/web/info/toDetail/${navContent.navId }/${info.id }">
	               		<p>${info.title }</p>
	               		<span class="new-arrow"></span>
	                </a>
               </c:forEach>
           </c:if>
        </div>

        <%@ include file="/WEB-INF/include/footer.jsp"%>
    </div>
</body>
</html>