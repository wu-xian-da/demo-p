<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
		
			<%@ include file="/WEB-INF/include/message.jsp" %>
		
			<h3>条件检索</h3>

			<form action="${base }/sys/push/ing" method="post">
			<div class="from-gy-controls">
				<div class="form-inline">

				  <div class="form-group">
				    <label>信息名称：</label>
				    <input type="text" name="infoName" value="${page.entity.infoName }" class="form-control" placeholder="信息名称">
				  </div>

				  <div class="form-group">
				    <label>信息类型：</label>
				    <select name="infoType" class="form-control">
					  	<option value="">全部(单选)</option>
					    <c:forEach items="${pushTypes }" var="pushType">
					    	<option value="${pushType }"
					    		<c:if test="${pushType eq page.entity.infoType }">
					    			selected="selected"
						  	 	</c:if>
					    	>${pushType.name }</option>
					    </c:forEach>
					</select>
				  </div>


				  <div class="form-group">
				    <label>创建时间：</label>
				    <div class="date-time-box">
				    	<input type="text" name="beginCreateTime" value="${page.entity.beginCreateTime }" class="form-control flatpickr">
				    	<i class="date-time-icon"></i>
				    </div>
				          至
				   	<div class="date-time-box">
				   		<input type="text" name="endCreateTime" value="${page.entity.endCreateTime }" class="form-control flatpickr">
				   		<i class="date-time-icon"></i>
				   	</div>
				  </div>
				  <div class="form-group">
				  	<div class="operation-box operation-head-box">
				  		<button type="submit" class="btn btn-gy btn-recovery">
							<span class="glyphicon glyphicon-search"></span>
							查询
						</button>
				  	</div>
				  </div>
				</div>
			</div>

			<div class="operation-box operation-um-box">
				<shiro:hasPermission name="push:ing:add">
				<a class="btn btn-gy btn-add" href="${base }/sys/push/ing/create">
					<span class="glyphicon glyphicon-plus-sign"></span>
					新增
				</a>
				</shiro:hasPermission>
			</div>
			</form>

			<div class="box-table">
				<table class="table">
					<thead>
						<tr>
							<th>序号</th>
							<th>信息名称(待推送)</th>
							<th>信息类型</th>
							<th>创建时间</th>
							<th>管理</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${page.data }" var="d" varStatus="stat">
						<tr>
							<td>${page.beginIndex + stat.count}</td>
							<td>${d.infoName }</td>
							<td>${d.infoType.name }</td>
							<td><fmt:formatDate value="${d.createTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
							<td>
								<shiro:hasPermission name="push:ing:update">
								<a href="${base }/sys/push/ing/update/${d.id }" class="edit"><i></i>编辑</a>
								</shiro:hasPermission>
								<shiro:hasPermission name="push:ing:push">
								&nbsp;&nbsp;
								<a href="javascript:void(0);" onclick="pushFun(${d.id },'${d.infoName }');"><i class="glyphicon glyphicon-send"></i>&nbsp;&nbsp;推送</a> 
								</shiro:hasPermission>
								<shiro:hasPermission name="push:ing:delete">
								<a href="${base }/sys/push/ing/delete/${d.id }" class="delete"><i></i>删除</a>
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

<div id="pushBox" style="display: none; padding: 20px;">
    <div  class="row row-list">
        <!-- hidden -->
        <input type="hidden" id="pushId" />
        
        <div class="col-md-3">
			<label>信息名称：</label>
		</div>

		<div class="col-md-3">
			<label id="infoName" style="text-align:center;"></label>
		</div>
    </div>
    
    <div class="row row-list">
		<div class="col-md-3">
			<label>推送范围：</label>
		</div>

		<div class="col-md-3">
			<label class="checkbox-inline">
			  <input type="checkbox" name="pushRange" checked="checked" value="wechat"> 微信公众号
			</label>
		</div>
	</div>
	
	<div class="operation-box" style="padding: 20px;">
		<button id="confirmBtn" type="button" class="btn btn-gy btn-query">
			<span class="glyphicon glyphicon-ok-circle"></span>
			确认推送
		</button>
		<button id="cancelBtn" type="button" class="btn btn-gy btn-add">
			<span class="glyphicon glyphicon-remove-circle"></span>
			取消推送
	    </button>
	</div>
</div>


<%@ include file="/WEB-INF/include/flatpickr.jsp" %>
<script type="text/javascript" src="${baseStatic }/js/layer/layer.js"></script>
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
			
			$("input[name='beginCreateTime']").flatpickr({
				"defaultDate": '${page.entity.beginCreateTime }'
			});
			
			$("input[name='endCreateTime']").flatpickr({
				"defaultDate": '${page.entity.endCreateTime }'
			});
		}
</script>
<script type="text/javascript">
    //推送
	function pushFun(pushId, infoName){
		$("#pushId").val(pushId);
		$("#infoName").html(infoName);
		
		layer.open({
			title:"推送",
			type:1,
			area:['600px'],
			content: $("#pushBox")
		});
	}
	
	//确认推送
	$("#confirmBtn").on("click", function(){
		var pushId = $("#pushId").val();
		var checkedEles = $("#pushBox input[name='pushRange']:checkbox:checked");
		
		var checkedValues = "";
		for(var i = 0; i < checkedEles.length; i++){
			checkedValues += checkedEles[i].value + ","
		}
		
		if(!checkedValues){
			alert("请选择推送范围！");
			return;
		}else{
			location.href = "${base }/sys/push/ing/push?pushId=" + pushId + "&pushRange=" + checkedValues;
		}
	});
	
	//取消推送
	$("#cancelBtn").on("click", function(){
		layer.closeAll('page');
	});
</script>