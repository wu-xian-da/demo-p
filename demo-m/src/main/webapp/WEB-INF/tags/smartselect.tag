<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp" %>
<%@ attribute name="moduleId" type="java.lang.String" required="true"%>
<%@ attribute name="multiple" type="java.lang.Boolean" required="false"%>

<div id="${moduleId }" style="display: inline-block;"></div>

<c:set var="level" value="1" scope="request" />
<script type="text/javascript">
$(function(){
	$("#${moduleId}").smartselect({
		multiple:${multiple ? multiple : false},
		style: {select: 'dropdown-toggle btn gy-select'},
		text: {
			selectLabel: '请选择认证方式',
			labelTemplate: '# 选中项'
		},
		toolbar: false,
		data: [<c:import url="/WEB-INF/tags/tree.jsp"/>],
		callback: {
	        onOptionSelected: function($target, event){
	        	alert($target.attr('data-value'));
	        },
	        onOptionDeselected: function(){}
	    }
	}).getsmartselect();
});
</script>