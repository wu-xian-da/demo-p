<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
		    <%@ include file="/WEB-INF/include/message.jsp" %>
		    
			<h3>条件检索</h3>
			<form method="post">
				<div class="from-gy-controls">
					<div class="form-inline">
					  <div class="form-group">
					    <label>栏目名称：</label>
					    <input type="text" name="navName" value="${page.entity.navName }" class="form-control" placeholder="公告名称...">
					  </div>
					  <div class="form-group">
					    <label>归属栏目：</label>
					    <select name="parent.id" class="form-control">
					    	<option value="">---请选择---</option>
					    	<c:forEach items="${parentNavList }" var="nav">
					    		<option value="${nav.id }"
					    			<c:if test="${nav.id eq page.entity.parent.id }">
					    				selected="selected"
					    			</c:if>
					    		>${nav.navName }</option>
					    	</c:forEach>
					    </select>
					  </div>
	
					</div>
				</div>
	
				<div class="operation-box">
					<button type="submit" class="btn btn-gy btn-query">
						<span class="glyphicon glyphicon-search"></span>
						查询
					</button>
					<shiro:hasPermission name="info:nav:show">
					<button id="navShow" type="button" class="btn btn-gy btn-push">
						<i></i>
						恢复展示
					</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="info:nav:hide">
					<button id="navHide" type="button" class="btn btn-gy btn-recovery">
						<i></i>
						取消展示
					</button>
					</shiro:hasPermission>
					<shiro:hasPermission name="info:nav:add">
					<a href="${base }/sys/info/nav/create" class="btn btn-gy btn-add">
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
							<th><input type="checkbox" id="checkAll"></th>
							<th class="dleft">栏目名称</th>
							<th>上级栏目</th>
							<th>栏目类型</th>
							<th>显示序号</th>
							<th>栏目状态</th>
							<th>管理</th>
						</tr>
					</thead>
					<tbody>
					    <c:forEach items="${page.data }" var="navBase">
							<tr>
								<td><input type="checkbox" name="navCheck" data-id="${navBase.id }"></td>
								<td class="dleft">${navBase.navName }</td>
								<td>${navBase.parent.navName }</td>
								<td>${navBase.navType.name }</td>
								<td>${navBase.navOrderNum }</td>
								<td>${navBase.navStatus.name }</td>
								<td>
									<shiro:hasPermission name="info:nav:update">
									<a href="${base }/sys/info/nav/update/${navBase.id}" class="edit"><i></i>编辑</a> 
									</shiro:hasPermission>
									<shiro:hasPermission name="info:nav:delete">
									<a href="${base }/sys/info/nav/delete/${navBase.id}" onclick="javascript:return confirmDel();" class="delete"><i></i>删除</a>
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

<script type="text/javascript">
$(function(){
	$("#checkAll").on("click", function(){
		if($(this).is(":checked")){
			$("[name=navCheck]").prop("checked", true);
		}
		else{
			$("[name=navCheck]").prop("checked", false);
		}
	});
	//恢复展示
	$('#navShow').on("click", function(){
		var checkArr = $('input[name=navCheck]:checked');
		if(checkArr.length == 0){
			alert("请勾选需要操作的数据");
			return;
		}
		var navBases = [];
		checkArr.each(function(i){
			navBases.push("navBases[" + i + "].id=" + $(this).data("id"));
		});
		window.location.href = "${base}/sys/info/nav/hfshow?" + navBases.join('&');
	});
	
	//取消展示
	$('#navHide').on("click", function(){
		var checkArr = $('input[name=navCheck]:checked');
		if(checkArr.length == 0){
			alert("请勾选需要操作的数据");
			return;
		}
		var navBases = [];
		checkArr.each(function(i){
			navBases.push("navBases[" + i + "].id=" + $(this).data("id"));
		});
		window.location.href = "${base}/sys/info/nav/hide?" + navBases.join('&');
	});
});
</script>