<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
		    <%@ include file="/WEB-INF/include/message.jsp" %>
		    
			<h3>条件检索</h3>
			<div class="from-gy-controls">
				<div class="form-inline">
				  <div class="form-group">
				    <label>栏目名称：</label>
				    <input type="text" class="form-control" placeholder="公告名称">
				  </div>
				  <div class="form-group">
				    <label>归属栏目：</label>
				    <select class="form-control">
						  <option>全部</option>
						  <option>航班动态</option>
						  <option>机场向导</option>
						  <option>机场向导</option>
						  <option>机场交通</option>
						</select>
				  </div>

				</div>
			</div>

			<div class="operation-box">
				<button type="button" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-search"></span>查询</button>
				<button type="button" class="btn btn-gy btn-recovery"><i></i>取消展示</button>
				<button type="button" class="btn btn-gy btn-push"><i></i>恢复展示</button>
				<a href="./information-management-add.html" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
			</div>

			<div class="box-table">
				<table class="table">
					<thead>
						<tr>
							<th><input type="checkbox"></th>
							<th class="dleft">栏目名称</th>
							<th>上级栏目</th>
							<th>栏目类型</th>
							<th>显示序号</th>
							<th>栏目状态</th>
							<th>管理</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox"></td>
							<td class="dleft">航班动态</td>
							<td>贵阳机场</td>
							<td>URL外链</td>
							<td>2</td>
							<td>展示</td>
							<td><a href="./information-management-edit.html" class="edit"><i></i>编辑</a> <a href="#" class="delete"><i></i>删除</a></td>
						</tr>
					</tbody>
				</table>

				<%@ include file="/WEB-INF/include/page.jsp" %>
				
			</div>

		</div>
	</div>
</div>