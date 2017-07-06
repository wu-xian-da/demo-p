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
            <h2>${notice.title } - 详情页</h2>
        </div>

        <div class="app-container">
           <div class="app-container-news">
              <h2 class="title">${notice.title }</h2> 
              <div class="news-souce"><fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></div>
              <div class="app-container-new-content">
                  ${notice.content }
              </div>
            </div>
        </div>

        <%@ include file="/WEB-INF/include/footer.jsp"%>
    </div>
</body>
</html>