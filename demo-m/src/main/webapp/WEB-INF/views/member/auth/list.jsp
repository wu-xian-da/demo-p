<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
					<div class="col-md-12">
						<div class="box information-management-box">
							<h3>条件检索</h3>
							
							<form method="post" action="${base }/sys/member/auth">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>用户名：</label>
								    <input type="text" name="code" value="${page.entity.code }" class="form-control" placeholder="证件号">
								  </div>
								
								  <div class="form-group">
								    <label>登录时间：</label>
								    <div class="date-time-box">
								    	<input type="text" name="start" value="${page.entity.start }" class="form-control flatpickr">
								    	<i class="date-time-icon"></i>
								    </div>
								    	至
								   	<div class="date-time-box">
								   		<input type="text" name="end" value="${page.entity.end }" class="form-control flatpickr">
								   		<i class="date-time-icon"></i>
								   	</div>
								  </div>

								  <div class="form-group">
								    <label>认证方式：</label>
								    <select name="methods" id="authentication-select" multiple="multiple">
										  <c:forEach items="${methods }" var="m">
										  	<option value="${m }" 
										  		<c:if test="${functions:in(page.entity.methods, m)}">selected="selected"</c:if>>${m.value }</option>
										  </c:forEach>
										</select>
								  </div>

									<div class="form-group">
										<div class="operation-box operation-head-box">
											<button type="submit" class="btn btn-gy btn-recovery">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询</button>
										</div>
									</div>
								</div>
							</div>
							</form>

							<div class="box-table">
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>登录ID</th>
											<th>用户名 </th>
											<th>登录时间</th>
											<th>会员等级</th>
											<th>认证方式</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${page.data }" var="a" varStatus="stat">
											<tr>
												<td>${stat.count }</td>
												<td>${a.access.requestId }</td>
												<td>${a.access.code }</td>
												<td>
													<fmt:formatDate value="${a.access.timestamp }" pattern="yyyy-MM-dd HH:mm" type="date" />
												</td>
												<td>普通会员</td>
												<td>${a.access.auth.value }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<%@ include file="/WEB-INF/include/page.jsp" %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
<%@ include file="/WEB-INF/include/flatpickr.jsp" %>
<script type="text/javascript">
		$(function(){
			$("select#authentication-select").smartselect({
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
				//console.log(e.getValues());
			}
		});

		$(function(){
			window._resize();
		});
</script>
