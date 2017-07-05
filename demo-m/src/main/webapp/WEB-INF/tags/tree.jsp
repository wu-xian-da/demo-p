<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<c:forEach items="${tree }" var="parent" varStatus="stat">
	{value: '${parent.value }', label: '${parent.label }' <c:if test="${level > 1 }">,level:${level }</c:if>}
	
	<c:if test="${fn:length(parent.childs) > 0}">
		,
		<c:set var="level" value="${level + 1}" scope="request" />
    	<c:set var="tree" value="${parent.childs}" scope="request" />
    	<c:set var="isChild" value="true" scope="request" />
		<c:import url="/WEB-INF/tags/tree.jsp" />
	</c:if>
	
	<c:if test="${!stat.last}">,</c:if>
</c:forEach>
<c:set var="level" value="${level - 1}" scope="request" />
<c:set var="isChild" value="false" scope="request" />