<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
					<div class="col-md-12">
						<div class="box information-management-box">
							<form method="post">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>登录名：</label>
								    <input type="text" name="user.loginName" value="${page.entity.user.loginName }" class="form-control" placeholder="登录名">
								  </div>
								  <div class="form-group">
								    <label>IP：</label>
								    <input type="text" name="ip" value="${page.entity.ip }" class="form-control" placeholder="IP">
								  </div>
								</div>
							</div>

							<div class="operation-box">
								<button type="submit" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-search"></span>查询</button>
							</div>
							</form>

							<div class="box-table">
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>登录名</th>
											<th>请求地址</th>
											<!-- <th>请求参数</th> -->
											<th>IP</th>
											<th>时间</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${page.data }" var="d" varStatus="stat">
											<tr>
												<td>${page.beginIndex + stat.count}</td>
												<td>${d.user.loginName }</td>
												<td>${d.requestUrl }</td>
												<%-- <td>${d.params }</td> --%>
												<td>${d.ip }</td>
												<td><fmt:formatDate value="${d.date }" pattern="yyyy-MM-dd HH:m" type="date"/></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<%@ include file="/WEB-INF/include/page.jsp" %>
							</div>
						</div>
					</div>
				</div>