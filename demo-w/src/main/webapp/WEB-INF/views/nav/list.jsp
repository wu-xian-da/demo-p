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
            <h2>${nav.navName }</h2>
        </div>
        <div class="header">
            <c:choose>
            	<c:when test="${not empty nav.navSecondMenu.menuHeadIcon }">
            	    <img src="${cdnDomain }${nav.navSecondMenu.menuHeadIcon }" width="100%" alt="">
            	</c:when>
            	<c:otherwise>
            		<img src="${cdnDomain }${nav.navSecondMenu.menuHeadIcon }" width="100%" alt="">
            	</c:otherwise>
            </c:choose>
        </div>

        <div class="app-container">
           <div class="app-container-news">
               <c:forEach items="${secNavList }" var="secNav">
                    <c:choose>
                    	<c:when test="${'URLWL' eq secNav.navType }">
                    		<a href="${secNav.navUrl.url }" target="${secNav.navUrl.target }">
			               		<img src="${secNav.navIcon}" alt="${secNav.navName}">
			               		<p>${secNav.navName}</p>
			               		<span class="new-arrow"></span>
			                </a>
                    	</c:when>
                    	<c:otherwise>
		               		<a href="${base }/web/info/toList/${nav.id}/${secNav.id}">
			               		<img src="${cdnDomain }${secNav.navIcon}" alt="${secNav.navName}">
			               		<p>${secNav.navName}</p>
			               		<span class="new-arrow"></span>
			                </a>
                    	</c:otherwise>
                    </c:choose>
               </c:forEach>
            </div>
        </div>

        <%@ include file="/WEB-INF/include/footer.jsp"%>
    </div>
</body>
</html>