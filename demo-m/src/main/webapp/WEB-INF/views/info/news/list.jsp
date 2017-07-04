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
				    <label>公告名称：</label>
				    <input type="text" class="form-control" placeholder="公告名称">
				  </div>
				  
				  <div class="form-group">
				    <label>信息状态：</label>
				    <select class="form-control">
					   	<option>全部(单选)</option>
						  <option>待审核</option>
						  <option>已上刊</option>
						  <option>已下刊</option>
						</select>
				  </div>

				  <div class="form-group">
				    <label>发布时间：</label>
				    <div class="date-time-box">
				    	<input type="text" class="form-control flatpickr">
				    	<i class="date-time-icon"></i>
				    </div>
				          至
				   	<div class="date-time-box">
				   		<input type="text" class="form-control flatpickr">
				   		<i class="date-time-icon"></i>
				   	</div>
				  </div>

				  <div class="form-group">
					 <div class="operation-box operation-head-box">
						 <button type="button" class="btn btn-gy btn-recovery"><span class="glyphicon glyphicon-search"></span>查询</button>
					 </div>
				  </div>

				</div>
			</div>

			<div class="operation-box">
				<button type="button" class="btn btn-gy btn-new"><i></i>下刊</button>
				<button type="button" class="btn btn-gy btn-recovery"><i></i>恢复上刊</button>
				<button type="button" class="btn btn-gy btn-push"><i></i>信息推送</button>
				<a href="./information-management-photo-add.html" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
				<button type="button" class="btn btn-gy btn-adpot"><span class="glyphicon glyphicon-ok"></span>审核通过</button>
			</div>

			<div class="box-table">
				<table class="table table-photo-news">
					<thead>
						<tr>
							<th><input type="checkbox"></th>
							<th>新闻名称</th>
							<th>图片</th>
							<th>是否推送</th>
							<th>发布时间</th>
							<th>信息状态</th>
							<th>管理</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="checkbox"></td>
							<td><a href="#">关于2017年春运应急保障的紧急通知</a> <a href="#"><span class="glyphicon glyphicon-open"></span></a></td>
							<td><img src="./public/img/n-1.png" alt=""></td>
							<td>是</td>
							<td>2016/12/12</td>
							<td><span class="info-status">已上刊</span></td>
							<td><a href="./information-management-photo-edit.html" class="edit"><i></i>编辑</a> <a href="#" class="delete"><i></i>删除</a></td>
						</tr>
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
</script>