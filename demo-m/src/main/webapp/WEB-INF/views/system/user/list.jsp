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
								    <label>登录名：</label>
								    <input type="text" class="form-control" name="loginName" value="${page.entity.loginName }" placeholder="用户名">
								  </div>

								  <div class="form-group">
								    <label>归属角色：</label>
								    <select class="form-control" name="role.id">
										  <option value="">全部</option>
										  <c:forEach items="${roles }" var="r">
										  	<option value="${r.id }" <c:if test="${r.id eq page.entity.role.id }">selected="selected"</c:if>>${r.name }</option>
										  </c:forEach>
									</select>
								  </div>

								  <div class="form-group">
								    <label>归属部门：</label>
								    <select class="form-control" name="department.id">
										  <option value="">全部</option>
										  <c:forEach items="${departments }" var="d">
										  	<option value="${d.id }" <c:if test="${d.id eq page.entity.department.id }">selected="selected"</c:if>>${d.name }</option>
										  </c:forEach>
									</select>
								  </div>
								</div>
							</div>
							<div class="operation-box">
								<button type="submit" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-search"></span>查询</button>
								<button type="button" class="btn btn-gy btn-new" id="user_open"><i></i>启用</button>
								<button type="button" class="btn btn-gy btn-recovery" id="user_close"><i></i>禁用</button>
								<button type="button" class="btn btn-gy btn-push" id="user_init"><i></i>密码重置</button>
								<a href="${base }/sys/system/user/create" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
							</div>
							</form>

							<div class="box-table">
								<table class="table">
									<thead>
										<tr>
											<th><input type="checkbox" id="userCheckAll"></th>
											<th>登录名</th>
											<th>姓名</th>
											<th>归属角色</th>
											<th>所属部门</th>
											<th>状态</th>
											<th>创建时间</th>
											<th>管理</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${page.data }" var="d">
											<tr>
												<td><input type="checkbox" name="userCheck" data-id="${d.id }" data-name="${d.loginName }"></td>
												<td>${d.loginName }</td>
												<td>${d.name }</td>
												<td>${d.role.name }</td>
												<td>${d.department.name }</td>
												<td>${d.status.name }</td>
												<td><fmt:formatDate value="${d.createDate}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
												<td><a href="${base }/sys/system/user/update/${d.id}" class="edit"><i></i>编辑</a> 
												<a href="${base }/sys/system/user/delete/${d.id}" onclick="javascript:return confirmDel();"
													class="delete"><i></i>删除</a></td>
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
	$("#userCheckAll").on("click", function(){
		if($(this).is(":checked")){
			$("[name=userCheck]").prop("checked", true);
		}
		else{
			$("[name=userCheck]").prop("checked", false);
		}
	});
	
	$('#user_init').on("click", function(){
		var checkArr = $('input[name=userCheck]:checked');
		if(checkArr.length == 0){
			alert("请勾选需要操作的数据");
			return;
		}
		var users = [];
		checkArr.each(function(i){
			users.push("users[" + i + "].id=" + $(this).data("id") + "&users[" + i + "].loginName=" + $(this).data("name"));
		});
		window.location.href = "${base}/sys/system/user/init?" + users.join('&');
	});
	
	$('#user_open').on("click", function(){
		var checkArr = $('input[name=userCheck]:checked');
		if(checkArr.length == 0){
			alert("请勾选需要操作的数据");
			return;
		}
		var users = [];
		checkArr.each(function(i){
			users.push("users[" + i + "].id=" + $(this).data("id"));
		});
		window.location.href = "${base}/sys/system/user/open?" + users.join('&');
	});
	
	$('#user_close').on("click", function(){
		var checkArr = $('input[name=userCheck]:checked');
		if(checkArr.length == 0){
			alert("请勾选需要操作的数据");
			return;
		}
		var users = [];
		checkArr.each(function(i){
			users.push("users[" + i + "].id=" + $(this).data("id"));
		});
		window.location.href = "${base}/sys/system/user/close?" + users.join('&');
	});
});
</script>
