<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<%@ include file="/WEB-INF/include/message.jsp"%>
<script>
	document.addEventListener("DOMContentLoaded", function(){
       var appMain = document.getElementById("app-main");
       appMainHeight = window.innerHeight - document.getElementById("top").offsetHeight;
       appMain.style.background = "url(${baseStatic}/img/gy-dashboard-bg.jpg) no-repeat center center";
       appMain.style.backgroundSize = "cover";
   });
</script>