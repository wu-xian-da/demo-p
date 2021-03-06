<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
			<h3>条件检索</h3>
			<form id="searchForm" method="post" action="${base }/sys/info/notice">
				<div class="from-gy-controls">
					<div class="form-inline">
					  <div class="form-group">
					    <label>公告名称：</label>
					    <input name="title" value="${page.entity.title }" type="text" class="form-control" placeholder="公告名称">
					  </div>
					  <div class="form-group">
					    <label>公告状态：</label>
					    <select name="status" class="form-control">
							  <option value="">全部(单选)</option>
							  	<c:forEach items="${infoStatuss }" var="status">
								  	  <option value="${status }"
								  	  	  <c:if test="${page.entity.status eq status}">
								  	  	      selected="selected"
								  	  	  </c:if>
								  	  >${status.name }</option>
								 </c:forEach>
							</select>
					  </div>
	
					  <div class="form-group">
					    <label>发布时间：</label>
					    <div class="date-time-box">
					    	<input name="beginCheckTime" type="text" class="form-control flatpickr">
					    	<i class="date-time-icon"></i>
					    </div>
					    至
					   	<div class="date-time-box">
					   		<input name="endCheckTime" type="text" class="form-control flatpickr">
					   		<i class="date-time-icon"></i>
					   	</div>
					  </div>
						<div class="form-group">
							<div class="operation-box operation-head-box">
								<script type="text/javascript">
									function search(){
										$("#searchForm").submit();
									}
								</script>
								<button type="button" onclick="search();" class="btn btn-gy btn-recovery" id="onclick"><span class="glyphicon glyphicon-search"></span>查询</button>
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="operation-box">
				<shiro:hasPermission name="info:notice:xk">
				<button type="button" class="btn btn-gy btn-new" id="noticeXK"><i></i>下刊</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="info:notice:hfsk">
				<button type="button" class="btn btn-gy btn-recovery" id="noticeHFSK"><i></i>恢复上刊</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="info:notice:shtg">
				<button type="button" class="btn btn-gy btn-adpot" id="noticeSHTG"><span class="glyphicon glyphicon-ok"></span>上刊</button>
				</shiro:hasPermission>
				<button id="noticePush" type="button" class="btn btn-gy btn-push"><i></i>信息推送</button>
				<shiro:hasPermission name="info:notice:add">
				<a href="${base}/sys/info/notice/create" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
				</shiro:hasPermission>
			</div>

			<div class="box-table">
				<table class="table">

					<thead>
						<tr>
							<th><input type="checkbox" id="checkAll"></th>
							<th>紧急公告名称</th>
							<th>发布时间</th>
							<th>公告状态</th>
							<th>管理</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${page.data }" var="notice">
							<tr>
								<td><input type="checkbox" name="noticeCheck" data-id="${notice.id}"></td>
								<td>
									${notice.title}
									<c:if test="${notice.pushStatus eq 'YTS'}"><span class="glyphicon glyphicon-open"></span></c:if>
								</td>
								<td><fmt:formatDate value="${notice.checkTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
								<td>
									<span
										<c:choose>
									    	<c:when test="${notice.status eq 'DSH'}">
									    	    class="info-status info-status-red"
									    	</c:when>
									    	<c:when test="${notice.status eq 'YSK'}">
									    		class="info-status info-status-blue"
									    	</c:when>
									    	<c:when test="${notice.status eq 'YXK'}">
									    		class="info-status info-status-gray"
									    	</c:when>
									    </c:choose>
									>
										${notice.status.name}
									</span>
							    </td>
								<td>
									<shiro:hasPermission name="info:notice:update">
									<a href="${base}/sys/info/notice/update/${notice.id}" class="edit"><i></i>编辑</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="info:notice:delete">
									<a href="${base}/sys/info/notice/delete/${notice.id}" onclick="javascript:return confirmDel();" class="delete"><i></i>删除</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="info:notice:detail">
									<a href="${webBaseUrl }/web/notice/toDetail/${notice.id}" target="_blank">&nbsp;<i class="glyphicon glyphicon-search"></i>&nbsp;预览</a>
									</shiro:hasPermission>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<%@ include file="/WEB-INF/include/page.jsp" %>
			</div>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/include/flatpickr.jsp" %>
<script type="text/javascript">
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

		$("input[name='beginCheckTime']").flatpickr({
			"defaultDate": '${page.entity.beginCheckTime }'
		});
		
		$("input[name='endCheckTime']").flatpickr({
			"defaultDate": '${page.entity.endCheckTime }'
		});

	}
	$(function(){
		$("#checkAll").on("click", function(){
			if($(this).is(":checked")){
				$("[name=noticeCheck]").prop("checked", true);
			}
			else{
				$("[name=noticeCheck]").prop("checked", false);
			}
		});
		
		//下刊
		$('#noticeXK').on("click", function(){
			var checkArr = $('input[name=noticeCheck]:checked');
			if(checkArr.length == 0){
				alert("请勾选需要操作的数据");
				return;
			}
			var notices = [];
			checkArr.each(function(i){
				notices.push("notices[" + i + "].id=" + $(this).data("id"));
			});
			window.location.href = "${base}/sys/info/notice/check/yxk?" + notices.join('&');
		});
		
		//恢复上刊
		$('#noticeHFSK').on("click", function(){
			var checkArr = $('input[name=noticeCheck]:checked');
			if(checkArr.length == 0){
				alert("请勾选需要操作的数据");
				return;
			}
			var notices = [];
			checkArr.each(function(i){
				notices.push("notices[" + i + "].id=" + $(this).data("id"));
			});
			window.location.href = "${base}/sys/info/notice/check/hfsk?" + notices.join('&');
		});
		
		// 审核通过
		$('#noticeSHTG').on("click", function(){
			var checkArr = $('input[name=noticeCheck]:checked');
			if(checkArr.length == 0){
				alert("请勾选需要操作的数据");
				return;
			}
			var notices = [];
			checkArr.each(function(i){
				notices.push("notices[" + i + "].id=" + $(this).data("id"));
			});
			window.location.href = "${base}/sys/info/notice/check/shtg?" + notices.join('&');
		});
		
		// 批量推送
		$('#noticePush').on("click", function(){
			var checkArr = $('input[name=noticeCheck]:checked');
			if(checkArr.length == 0){
				alert("请勾选需要操作的数据");
				return;
			}
			var notices = [];
			checkArr.each(function(i){
				notices.push("notices[" + i + "].id=" + $(this).data("id"));
			});
			window.location.href = "${base}/sys/info/notice/push?" + notices.join('&');
		});
		
	});
</script>