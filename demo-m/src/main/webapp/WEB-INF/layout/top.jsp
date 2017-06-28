<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div class="navbar navbar-static-top bs-docs-nav header" id="top" role="banner">
	<div class="container-fluid">
		<div class="navbar-header logo-header">
			<div class="logo">
				<img src="${baseStatic }/img/logo.gif" alt="">
			</div>
			<div class="logo-text">
				<h1>贵阳机场</h1>
				<p>Guiyang Airport</p>
			</div>
		</div>
		<div class="logo-title">智慧管理平台</div>
		<nav class="navbar-collapse bs-navbar-collapse">
			<ul class="navbar-nav navbar-navigation">
				<li class="navbar-navigation-usermanage"><a
					href="./user-management.html">会员管理</a></li>
				<li class="navbar-navigation-infomanage active"
					class="navbar-navigation-infomanage"><a
					href="./information-management-notice.html">信息管理</a></li>
				<li class="navbar-navigation-userpush"><a
					href="./information-push-pending.html">信息推送</a></li>
				<li class="navbar-navigation-coustomer"><a href="#">在线客服</a></li>
				<li class="navbar-navigation-sysmanage"><a
					href="./sysmanage-user.html">系统管理</a></li>
			</ul>

			<ul class="navbar-nav navbar-right nav-signout">
				<li><a href="${base }/logout">安全退出</a></li>
			</ul>
			<jsp:useBean id="now" class="java.util.Date" />   
			<div class="navbar-nav navbar-right nav-week-tips">今天是<fmt:formatDate value="${now }" pattern="yyyy年MM月dd日" type="date" /></div>
		</nav>
	</div>
</div>