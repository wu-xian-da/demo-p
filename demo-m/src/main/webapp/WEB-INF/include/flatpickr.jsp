<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% include file="/WEB-INF/include/taglib.jsp" %>

<script src="${baseStatic }/js/flatpickr/flatpickr.min.js" type="text/javascript"></script>
<script>
if($(".flatpickr").length > 0){
	Flatpickr.l10ns.default.weekdays = {
		shorthand: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
		longhand: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
	}
	Flatpickr.l10ns.default.months = {
		shorthand: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
		longhand: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
	}
	flatpickr(".flatpickr");
}
</script>