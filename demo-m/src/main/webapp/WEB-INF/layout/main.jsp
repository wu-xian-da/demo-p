<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<c:set var="currentUrl" value="${pageContext.request.servletPath }"/>
<c:set var="_version" value="?20170517"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<script src="${baseStatic }/js/jquery.min.js" type="text/javascript"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="${baseStatic }/img/logo.gif">
<%@ include file="/WEB-INF/include/css.jsp" %>
<title>贵阳机场智慧管理平台</title>
<sitemesh:head/>

<script>
var context_path_ = '${base}';
var sid_s = '${cookie.sid.value}';
</script>
</head>
<body>
	<%@ include file="top.jsp" %>
	
	<div class="main container-fluid" id="app-main">
		<div class="row" id="row-container">
			<c:if test="${currentUrl ne '/sys/index' }">
				<% include file="left.jsp" %>
			</c:if>
			<sitemesh:body/>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/include/js.jsp" %>
</body>
</html>