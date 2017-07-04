<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<c:set var="currentUrl" value="${pageContext.request.servletPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/WEB-INF/include/css.jsp" %>
<title>贵阳机场智慧管理平台</title>
<sitemesh:head/>

<script src="${baseStatic }/js/jquery.min.js" type="text/javascript"></script>
<script>
var context_path_ = '${base}';
var sid_s = '${cookie.sid.value}';
</script>
</head>
<body>
	<%@ include file="top.jsp" %>
	
	<div class="main container-fluid" id="app-main">
		<div class="row" id="row-container">
			<%@ include file="left.jsp" %>
			<sitemesh:body/>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/include/js.jsp" %>
</body>
</html>