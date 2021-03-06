<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<!-- 消息提醒  ${message.info}-->
<!-- 
<div class="alert alert-auto alert-warning alert-dismissible fade in" role="alert">警告</div>

<div class="alert alert-auto alert-info alert-dismissible fade in" role="alert">信息</div>

<div class="alert alert-auto alert-danger alert-dismissible fade in" role="alert">错误</div> 
-->

<c:if test="${not empty message }">
	<div class="alert alert-auto alert-${message.status.name } alert-dismissible fade in" role="alert">
		${message.info }
	</div>
</c:if>