<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
			<h3>条件检索</h3>
			<form method="post">
				<div class="from-gy-controls">
					<div class="form-inline">
					  <div class="form-group">
					    <label>公告名称：</label>
					    <input name="title" value="${page.entity.title }" type="text" class="form-control" placeholder="公告名称">
					  </div>
					  <div class="form-group">
					    <label>信息状态：</label>
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
					    	<input id="beginCheckTime" name="beginCheckTime" value="${page.entity.beginCheckTime }" type="text" class="form-control flatpickr">
					    	<i class="date-time-icon"></i>
					    </div>
					    至
					   	<div class="date-time-box">
					   		<input id="endCheckTime" name="endCheckTime" value="${page.entity.endCheckTime }" type="text" class="form-control flatpickr">
					   		<i class="date-time-icon"></i>
					   	</div>
					  </div>
						<div class="form-group">
							<div class="operation-box operation-head-box">
								<button type="submit" class="btn btn-gy btn-recovery" id="onclick"><span class="glyphicon glyphicon-search"></span>查询</button>
							</div>
						</div>
					</div>
				</div>
			</form>
			<div class="operation-box">
				<button type="button" class="btn btn-gy btn-new" id="noticeXK"><i></i>下刊</button>
				<button type="button" class="btn btn-gy btn-recovery" id="noticeHFSK"><i></i>恢复上刊</button>
				<button type="button" class="btn btn-gy btn-adpot" id="noticeSHTG"><span class="glyphicon glyphicon-ok"></span>审核通过</button>
				<a href="${base}/sys/info/notice/create" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
			</div>

			<div class="box-table">
				<table class="table">

					<thead>
						<tr>
							<th><input type="checkbox" id="checkAll"></th>
							<th>紧急公告名称</th>
							<th>发布时间</th>
							<th>信息状态</th>
							<th>管理</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${page.data }" var="notice">
							<tr>
								<td><input type="checkbox" name="noticeCheck" data-id="${notice.id}"></td>
								<td>${notice.title}</td>
								<td><fmt:formatDate value="${notice.checkTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
								<td id="${notice.status}_${notice.id}"><span class="info-status">${notice.status.name}</span></td>
								<td>
									<a href="${base}/sys/info/notice/update/${notice.id}" class="edit"><i></i>编辑
									</a><input type="hidden" value="${notice.id}"/> 
									<a href="${base}/sys/info/notice/delete/${notice.id}" onclick="javascript:return confirmDel();" class="delete"><i></i>删除</a>
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
		
		//时间检索验证
		$('#onclick').on("click",function(){
			var beginChecktime = $("#beginCheckTime").val();
			var endChecktime = $("#endCheckTime").val();
			var d1 = new Date(beginChecktime.replace(/\-/g, "\/"));  
			var d2 = new Date(endChecktime.replace(/\-/g, "\/"));
			if(beginChecktime!=""&&endChecktime!=""&&d1 >=d2){
				alert("开始时间不能大于结束时间！");
				return false;
			}
		});
		
		//状态为已上刊时编辑按钮不显示或无用
		$('.edit').on("click",function(titl){
			var status = $(this).next(":hidden").val();
			var ysk =$("#YSK_"+status).val();
			if(ysk == ''){
				alert("公告已上刊,不能编辑!");
				return false;
			}
		});
	});
</script>