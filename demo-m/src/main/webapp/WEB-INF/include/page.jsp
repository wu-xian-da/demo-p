<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div class="pagination">
	<div class="pagination-inner">
		<span>共${page.totalRecord }条数据</span> <span><i>|</i>每页显示<input
			type="text" value="${page.viewPageNumber }">条</span>
		<c:if test="${!page.first }">
			<a href="?pn=1${page.urlParams}" class="first-page">首页</a>
			<a href="?pn=${page.pageNo - 1 }${page.urlParams}" class="prev-page">上一页</a>
		</c:if>

		<c:forEach begin="${page.viewStartPage }" end="${page.viewEndPage }"
			step="1" var="pageIndex">
			<a href="?pn=${pageIndex }${page.urlParams}">${pageIndex }</a>
		</c:forEach>

		<c:if test="${!page.last }">
			<a href="?pn=${page.pageNo + 1 }${page.urlParams}" class="next-page">下一页</a>
			<a href="?pn=${page.totalPage }${page.urlParams}" class="last-page">尾页</a>
		</c:if>
	</div>
</div>
