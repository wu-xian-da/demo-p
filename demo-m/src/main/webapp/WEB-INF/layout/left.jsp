<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div class="left-menu" id="left-menu">
	<ul>
		<!-- <li><a href="./information-management-photo.html"><i
				class="new-photo"></i>图片新闻</a></li>
		<li><a href="./information-management-notice.html"><i
				class="urgent-notice"></i>紧急公告</a></li>
		<li><a href="./information-management-article.html"><i
				class="menu-article"></i>栏目文章</a></li>
		<li class="active"><a
			href="./information-management-manage.html"><i
				class="menu-manage"></i>栏目管理</a></li> -->
		<c:forEach items="${userMenus }" var="parent">
			<c:if test="${fn:startsWith(currentUrl, parent.href) }">
				<c:forEach items="${parent.childs }" var="child">
					<li <c:if test="${fn:startsWith(currentUrl, child.href)}">class="active"</c:if>>
						<a href="${child.href }"><i class="new-photo"></i>${child.name }</a>
					</li>
				</c:forEach>
			</c:if>
		</c:forEach>
	</ul>
</div>