<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
		<div class="col-md-12">
			<div class="box information-management-box">
				<%@ include file="/WEB-INF/include/message.jsp"%>
				
				<h3>条件检索</h3>
				<form method="post" action="${base }/sys/system/department">
				<div class="from-gy-controls">
					<div class="form-inline">
					  <div class="form-group">
					    <label>部门名称：</label>
					    <input type="text" name="name" value="${page.entity.name }" 
					    class="form-control" placeholder="部门名称">
					  </div>
					  <div class="form-group">
					    <label>归属部门：</label>
					    <sys:smartselect hiddenName="departments" message="请选择所属部门" moduleId="departm-list" 
							treeData="${departmentTree }" hiddenValue="${page.entity.departments }" multiple="true"/>
					  </div>
					  <div class="form-group">
					  	<div class="operation-box operation-head-box">
							<button type="submit" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-search"></span>查询</button>
						</div>
					  </div>
					</div>
				</div>

				<div class="operation-box">
					<shiro:hasPermission name="department:create">
					<a href="${base }/sys/system/department/create" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
					</shiro:hasPermission>
				</div>
				</form>

				<div class="box-table">
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>部门名称</th>
								<th>上级部门</th>
								<th>显示序号</th>
								<th>管理</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.data }" var="d" varStatus="stat">
								<tr>
									<td>${page.beginIndex + stat.count}</td>
									<td>${d.name }</td>
									<td>${d.parent.name }</td>
									<td>${d.sort }</td>
									<td>
									<shiro:hasPermission name="department:update">
									<a href="${base }/sys/system/department/update/${d.id}" class="edit"><i></i>编辑</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="department:delete">
									<a href="${base }/sys/system/department/delete/${d.id}" onclick="javascript:return confirmDel();"
										class="delete"><i></i>删除</a>
									</shiro:hasPermission></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<%@ include file="/WEB-INF/include/page.jsp" %>
				</div>
			</div>
		</div>
	</div>