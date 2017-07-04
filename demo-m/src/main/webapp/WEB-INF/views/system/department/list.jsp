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
					    <select class="form-control" name="parent.id">
					    	<option value="" >请选择</option>
						  	<c:forEach items="${parents }" var="d">
								<option value="${d.id }"
								<c:if test="${page.entity.parent.id eq d.id }">selected="selected"</c:if>>${d.name }</option>
							</c:forEach>
						</select>
					  </div>
					</div>
				</div>

				<div class="operation-box">
					<button type="submit" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-search"></span>查询</button>
					<a href="${base }/sys/system/department/create" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
					<button type="button" class="btn btn-gy btn-sort"><span class="glyphicon glyphicon-resize-vertical"></span>排序</button>
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