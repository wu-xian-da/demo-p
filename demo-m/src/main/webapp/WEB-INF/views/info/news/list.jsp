<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
		
		    <%@ include file="/WEB-INF/include/message.jsp" %>
		    
			<h3>条件检索</h3>
			<form id="searchForm" method="post" action="${base }/sys/info/news">
			<div class="from-gy-controls">
				<div class="form-inline">
				  
				  <div class="form-group">
				    <label>新闻名称：</label>
				    <input type="text" name="title" value="${page.entity.title }" class="form-control" placeholder="新闻名称">
				  </div>
				  
				  <div class="form-group">
				    <label>新闻状态：</label>
				    <select name="status" class="form-control">
					   	<option value="">全部(单选)</option>
						<c:forEach items="${infoStatus }" var="status">
							<option value="${status }"
								<c:if test="${status eq page.entity.status }">selected="selected"</c:if>
							>${status.name }</option>
						</c:forEach> 	
					</select>
				  </div>

				  <div class="form-group">
				    <label>发布时间：</label>
				    <div class="date-time-box">
				    	<input type="text" name="beginCheckTime" class="form-control flatpickr">
				    	<i class="date-time-icon"></i>
				    </div>
				          至
				   	<div class="date-time-box">
				   		<input type="text" name="endCheckTime" class="form-control flatpickr">
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
						<button type="button" onclick="search();" class="btn btn-gy btn-recovery"><span class="glyphicon glyphicon-search"></span>查询</button>
					 </div>
				  </div>

				</div>
			</div>
			</form>
			
			<div class="operation-box">
				<shiro:hasPermission name="info:news:xk">
				<button id="newsXK" type="button" class="btn btn-gy btn-new"><i></i>下刊</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="info:news:hfsk">
				<button id="newsHFSK" type="button" class="btn btn-gy btn-recovery"><i></i>恢复上刊</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="info:news:shtg">
				<button id="newSHTG" type="button" class="btn btn-gy btn-adpot"><span class="glyphicon glyphicon-ok"></span>上刊</button>
				</shiro:hasPermission>
				<button id="newsPush" type="button" class="btn btn-gy btn-push"><i></i>信息推送</button>
				<shiro:hasPermission name="info:news:add">
				<a href="${base }/sys/info/news/create" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
				</shiro:hasPermission>
			</div>

			<div class="box-table">
				<table class="table table-photo-news">
					<thead>
						<tr>
							<th><input type="checkbox" id="checkAll"></th>
							<th>新闻名称</th>
							<th>图片</th>
							<th>发布时间</th>
							<th>新闻状态</th>
							<th>显示序号</th>
							<th>管理</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.data }" var="news">
							<tr>
								<td><input type="checkbox" name="newsCheck" data-id="${news.id }"></td>
								<td>
									${news.title }
									<c:if test="${news.pushStatus eq 'YTS'}"><span class="glyphicon glyphicon-open"></span></c:if>
								</td>
								<td>
								    <c:choose>
								    	<c:when test="${not empty news.imgPath }">
								    		<img src="${news.imgPath }" alt="">
								    	</c:when>
								    	<c:otherwise>
								    		<img src="${baseStatic }/img/no_pic.png" alt="">
								    	</c:otherwise>
								    </c:choose>
								</td>
								<td><fmt:formatDate value="${news.checkTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
								<td>
									<span
										<c:choose>
									    	<c:when test="${news.status eq 'DSH'}">
									    	    class="info-status info-status-red"
									    	</c:when>
									    	<c:when test="${news.status eq 'YSK'}">
									    		class="info-status info-status-blue"
									    	</c:when>
									    	<c:when test="${news.status eq 'YXK'}">
									    		class="info-status info-status-gray"
									    	</c:when>
									    </c:choose>
									>
										${news.status.name }
									</span>
								</td>
								<td>${news.orderNum }</td>
								<td>
									<shiro:hasPermission name="info:news:update">
									<a href="${base }/sys/info/news/update/${news.id}" class="edit">
										<i></i>
										编辑
									</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="info:news:delete">
									<a href="${base }/sys/info/news/delete/${news.id}" onclick="javascript:return confirmDel();" class="delete">
										<i></i>
										删除
									</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="info:news:detail">
									<a href="${webBaseUrl }/web/news/toDetail/${news.id}" target="_blank">&nbsp;<i class="glyphicon glyphicon-search"></i>&nbsp;预览</a>
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
			"defaultDate":'${page.entity.beginCheckTime }'
		});
		
		$("input[name='endCheckTime']").flatpickr({
			"defaultDate": '${page.entity.endCheckTime }'
		});
	}
	
	$(function(){
		$("#checkAll").on("click", function(){
			if($(this).is(":checked")){
				$("[name=newsCheck]").prop("checked", true);
			}
			else{
				$("[name=newsCheck]").prop("checked", false);
			}
		});
		
		//下刊
		$('#newsXK').on("click", function(){
			var checkArr = $('input[name=newsCheck]:checked');
			if(checkArr.length == 0){
				alert("请勾选需要操作的数据");
				return;
			}
			var newss = [];
			checkArr.each(function(i){
				newss.push("imgNewss[" + i + "].id=" + $(this).data("id"));
			});
			window.location.href = "${base}/sys/info/news/check/yxk?" + newss.join('&');
		});
		
		//恢复上刊
		$('#newsHFSK').on("click", function(){
			var checkArr = $('input[name=newsCheck]:checked');
			if(checkArr.length == 0){
				alert("请勾选需要操作的数据");
				return;
			}
			var newss = [];
			checkArr.each(function(i){
				newss.push("imgNewss[" + i + "].id=" + $(this).data("id"));
			});
			window.location.href = "${base}/sys/info/news/check/hfsk?" + newss.join('&');
		});
		
		// 审核通过
		$('#newsSHTG').on("click", function(){
			var checkArr = $('input[name=newsCheck]:checked');
			if(checkArr.length == 0){
				alert("请勾选需要操作的数据");
				return;
			}
			var newss = [];
			checkArr.each(function(i){
				newss.push("imgNewss[" + i + "].id=" + $(this).data("id"));
			});
			window.location.href = "${base}/sys/info/news/check/shtg?" + newss.join('&');
		});
		
		// 批量推送
		$('#newsPush').on("click", function(){
			var checkArr = $('input[name=newsCheck]:checked');
			if(checkArr.length == 0){
				alert("请勾选需要操作的数据");
				return;
			}
			var newss = [];
			checkArr.each(function(i){
				newss.push("imgNewss[" + i + "].id=" + $(this).data("id"));
			});
			window.location.href = "${base}/sys/info/news/push?" + newss.join('&');
		});
	});
</script>