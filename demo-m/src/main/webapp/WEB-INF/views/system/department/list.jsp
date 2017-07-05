<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
		<div class="col-md-12">
			<div class="box information-management-box">
				<%@ include file="/WEB-INF/include/message.jsp"%>
				<form method="post">
				<div class="from-gy-controls">
					<div class="form-inline">
					  <div class="form-group">
					    <label>部门名称：</label>
					    <input type="text" name="name" value="${page.entity.name }" 
					    class="form-control" placeholder="部门名称">
					  </div>
					  <div class="form-group">
					    <label>归属机构：</label>
					    <sys:smartselect hiddenName="departments" message="请选择所属机构" moduleId="departm-list" 
							treeData="${departmentTree }" hiddenValue="${page.entity.departments }" multiple="true"/>
					  </div>
					</div>
				</div>

				<div class="operation-box">
					<button type="submit" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-search"></span>查询</button>
					<a href="${base }/sys/system/department/create" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
				</div>
				</form>

				<div class="box-table">
					<table class="table">
						<thead>
							<tr>
								<th>序号</th>
								<th>机构名称</th>
								<th>上级机构</th>
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
									<a href="${base }/sys/system/department/update/${d.id}" class="edit"><i></i>编辑</a> 
									<a href="${base }/sys/system/department/delete/${d.id}" onclick="javascript:return confirmDel();"
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