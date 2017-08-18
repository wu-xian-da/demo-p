<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div class="pagination">
	<div class="pagination-inner">
		<span>共${page.totalRecord }条数据</span> <span><i>|</i>每页显示
		<select id="_page_ps_select">
			<c:forEach items="${page.defaultPageSizeSelect}" var="s">
				<option value="${s }" <c:if test="${page.pageSize == s}">selected="selected"</c:if>>${s }</option>
			</c:forEach>
		</select>条</span>
		
		<c:if test="${!page.first}">
			<a href="?pn=1&ps=${page.pageSize}${page.urlParams}" class="first-page">首页</a>
			<a href="?pn=${page.pageNo-1}&ps=${page.pageSize}${page.urlParams}" class="prev-page">上一页</a>
		</c:if>

		<c:forEach begin="${page.viewStartPage}" end="${page.viewEndPage}" step="1" var="pageIndex">
			<a <c:if test="${page.pageNo == pageIndex}">class="active"</c:if>
			href="?pn=${pageIndex}&ps=${page.pageSize}${page.urlParams}">${pageIndex}</a>
		</c:forEach>

		<c:if test="${!page.last}">
			<a href="?pn=${page.pageNo+1}&ps=${page.pageSize}${page.urlParams}" class="next-page">下一页</a>
			<a href="?pn=${page.totalPage}&ps=${page.pageSize}${page.urlParams}" class="last-page">尾页</a>
		</c:if>
	</div>
</div>
<script type="text/javascript">
$(function(){
	$("#_page_ps_select").change(function(){
		window.location.href = '?pn=${page.pageNo}${page.urlParams}&ps='+$(this).val();
	});
});
</script>
