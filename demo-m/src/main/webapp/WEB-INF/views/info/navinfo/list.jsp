<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
		
		    <%@ include file="/WEB-INF/include/message.jsp" %>
		     
			<h3>条件检索</h3>

			<div class="from-gy-controls">
				<div class="form-inline">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
						    <label>信息名称：</label>
						    <input type="text" class="form-control" placeholder="信息名称">
						  </div>
						</div>

						<div class="col-md-4">
							<div class="form-group">
						    <label>所属栏目：</label>
						    <select class="form-control">
								  <option>紧急通知</option>
								  <option>航班动态</option>
								  <option>机场向导</option>
								  <option>机场服务</option>
								  <option>机场交通</option>
								</select>
						  </div>
						</div>

						<div class="col-md-4">
							<div class="form-group">
						    <label>信息状态：</label>
						    <select class="form-control">
								  <option>全部(单选)</option>
								  <option>待审核</option>
								  <option>已上刊</option>
								  <option>已下刊</option>
								</select>
						  </div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
						    <label>信息类型：</label>
						    <select class="form-control">
								  <option>全部(单选)</option>
								  <option>文章类</option>
								  <option>交通类</option>
								</select>
						  </div>
						</div>

						<div class="col-md-4">
							<div class="form-group">
								<div class="operation-box operation-head-box">
									<button type="button" class="btn btn-gy btn-recovery"><span class="glyphicon glyphicon-search"></span>查询</button>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="operation-box">
				<button type="button" class="btn btn-gy btn-new"><i></i>下刊</button>
				<button type="button" class="btn btn-gy btn-recovery"><i></i>恢复上刊</button>
				<button type="button" class="btn btn-gy btn-push"><i></i>信息推送</button>
				<a href="./information-management-article-add.html" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
				<button type="button" class="btn btn-gy btn-adpot"><span class="glyphicon glyphicon-ok"></span>审核通过</button>
			</div>

			<div class="box-table">
				<table class="table">
					<thead>
						<tr>
							<th><input type="checkbox"></th>
							<th>信息名称</th>
							<th>所属栏目</th>
							<th>是否推送</th>
							<th>信息类型</th>
							<th>创建时间</th>
							<th>信息状态</th>
							<th>管理</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox"></td>
							<td><a href="#">关于2017年春运应急保障的紧急通知</a> <a href="#"><span class="glyphicon glyphicon-open"></span></a></td>
							<td>机场服务</td>
							<td>是</td>
							<td>文章类</td>
							<td>2016-07-19 11:23</td>
							<td>已上刊</td>
							<td><a href="./information-management-article-edit.html" class="edit"><i></i>编辑</a> <a href="#" class="delete"><i></i>删除</a></td>
						</tr>
					</tbody>
				</table>

                <%@ include file="/WEB-INF/include/page.jsp" %>				

			</div>
			
		</div>
	</div>
</div>