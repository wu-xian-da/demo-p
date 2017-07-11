<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
					<div class="col-md-12">
						<div class="box information-management-box">
							<h3>条件检索</h3>


							<div class="from-gy-controls">
								<div class="form-inline">
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
								    <label>认证方式：</label>
								    <select id="authentication-select" multiple="multiple">
										  <option value="全部">全部</option>
										  <option value="短信认证">短信认证</option>
										  <option value="微信认证">微信认证</option>
										  <option value="登机牌认证">登机牌认证</option>
										</select>
								  </div>

								  <div class="form-group">
								    <label>证件号：</label>
								    <input type="text" class="form-control" placeholder="证件号" required>
								  </div>


									<div class="form-group">
										<div class="operation-box operation-head-box">
											<button type="button" class="btn btn-gy btn-recovery"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询</button>
										</div>
									</div>

								</div>
							</div>

							<div class="box-table">
								<table class="table">

									<thead>
										<tr>
											<th>序号</th>
											<th>登录ID</th>
											<th>登录时间</th>
											<th>认证方式</th>
											<th>证件号</th>
											<th>查看</th>
										</tr>
									</thead>

									<tbody>
										<tr>
											<td>1</td>
											<td>234555</td>
											<td>2016-12-19 15:48:59</td>
											<td>短信认证</td>
											<td>19278993389</td>
											<td><a href="./user-center.html">查看其他</a></td>
										</tr>

										<tr>
											<td>2</td>
											<td>103499</td>
											<td>2016-12-19 12:12:29</td>
											<td>微信认证</td>
											<td>aiguiyang</td>
											<td><a href="./user-center.html">查看其他</a></td>
										</tr>


										<tr>
											<td>3</td>
											<td>103688</td>
											<td>2016-12-19 12:12:29</td>
											<td>登机牌认证</td>
											<td>342512196701010123</td>
											<td><a href="./user-center.html">查看其他</a></td>
										</tr>

										<tr>
											<td>4</td>
											<td>234555</td>
											<td>2016-12-19 15:48:59</td>
											<td>短信认证</td>
											<td>19278993389</td>
											<td><a href="./user-center.html">查看其他</a></td>
										</tr>

										<tr>
											<td>5</td>
											<td>103499</td>
											<td>2016-12-19 12:12:29</td>
											<td>微信认证</td>
											<td>aiguiyang</td>
											<td><a href="./user-center.html">查看其他</a></td>
										</tr>


										<tr>
											<td>6</td>
											<td>103688</td>
											<td>2016-12-19 12:12:29</td>
											<td>登机牌认证</td>
											<td>342512196701010123</td>
											<td><a href="./user-center.html">查看其他</a></td>
										</tr>
									</tbody>
								</table>

								<div class="pagination">
									<div class="pagination-inner">
										<span>共50条数据</span>
										<span><i>|</i>每页显示<input type="text" value="10">条</span>
										<a href="#" class="first-page">首页</a>
										<a href="#" class="prev-page">上一页</a>
										<a href="#">1</a>
										<a href="#">2</a>
										<a href="#">3</a>
										<a href="#">4</a>
										<a href="#">5</a>
										<a href="#" class="next-page">下一页</a>
										<a href="#" class="last-page">尾页</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<%@ include file="/WEB-INF/include/flatpickr.jsp" %>
<script type="text/javascript">
		$(function(){
			var $aa = $("select#authentication-select").smartselect({
				style: {
					select: 'dropdown-toggle btn gy-select'
				},
				text: {
					selectLabel: '请选择认证方式',
					labelTemplate: '# 选中项'
				},
				toolbar: false,
				callback: {
					onOptionSelected: function () {
						getValues(this);
					}
				}
			}).getsmartselect();

			function getValues(e) {
				console.log(e.getValues());
			}

			$("button").on('click', function () {
				console.log($aa.getValues());
			});
		});

		$(function(){
			window._resize()
		});
</script>
