<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp" %>

<%@ attribute name="moduleId" type="java.lang.String" required="true"%>
<%@ attribute name="treeData" type="java.lang.String" required="true"%>
<%@ attribute name="message" type="java.lang.String" required="true"%>
<%@ attribute name="hiddenName" type="java.lang.String" required="true"%>
<%@ attribute name="hiddenValue" type="java.lang.Object" required="true"%>

<%@ attribute name="multiple" type="java.lang.Boolean" required="false"%>

<div id="${moduleId }" style="display: inline-block;"></div>
<div id="selectValues_hidden_${moduleId }" style="display: none"></div>

<script type="text/javascript">
$(function(){
	var getSelects = function($target, event){
		var vs = this.getValues();
		if(this._isMultiple){
			$('#selectValues_hidden_${moduleId }').html('');
			for(var i=0 ; i < vs.length; i++){
				$('#selectValues_hidden_${moduleId }').append('<input type="hidden" name="${hiddenName}['+i+']" value="'+vs[i]+'">');
			}
		}
		else{
			$('#selectValues_hidden_${moduleId }').html('<input type="hidden" name="${hiddenName}" value="'+vs[0]+'">');
		}
		return true;
	};
	
	$("#${moduleId}").smartselect({
		multiple:${not empty multiple ? multiple : false},
		style: {select: 'dropdown-toggle btn gy-select'},
		text: {
			selectLabel: '${message}',
			labelTemplate: '#个选中项'
		},
		toolbar: false,
		data: ${treeData},
		<c:if test="${not empty hiddenValue && hiddenValue ne -1}">
		initialValues:[${multiple ? functions:listToString(hiddenValue) : hiddenValue}],
		</c:if>
		callback: {
	        onOptionSelected: [getSelects],
	        onOptionDeselected: [getSelects]
	    }
	}).getsmartselect();
});
</script>