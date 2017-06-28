<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="functions" uri="/WEB-INF/tlds/functions.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags" %>

<c:set var="base" value="${pageContext.request.contextPath}"/>
<c:set var="baseStatic" value="${base}/static"/>