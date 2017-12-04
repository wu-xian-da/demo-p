<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
					<div class="col-md-12">
						<div class="box information-management-box">
							<%@include file="/WEB-INF/include/message.jsp" %>
							
							<h3>条件检索</h3>
							<form method="post" action="${base }/sys/system/menu">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>权限名称：</label>
								    <input type="text" name="name" value="${page.entity.name }" class="form-control" placeholder="权限名称">
								  </div>
								  <div class="form-group">
								    <label>权限标识符：</label>
								    <input type="text" name="permission" value="${page.entity.permission }" class="form-control" placeholder="权限标识符">
								  </div>
								  <div class="form-group">
								  	<div class="operation-box operation-head-box">
										<button type="submit" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-search"></span>查询</button>
									</div>
								  </div>
								</div>
							</div>

							<div class="operation-box">
								<shiro:hasPermission name="menu:create">
								<a href="${base }/sys/system/menu/create" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
								</shiro:hasPermission>
							</div>
							</form>

							<div class="box-table">
								<table class="table">
									<thead>
										<tr>
											<th>序号</th>
											<th>权限名称</th>
											<th>权限URL</th>
											<th>权限标识符</th>
											<th>权限类型</th>
											<th>上级权限</th>
											<th>序号</th>
											<th>管理</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${menualldata}" var="p" varStatus="stat">
											<c:if test="${p.parent.id eq -1 }">
											<tr>
												<td>${p.id}</td>
												<td>|--${p.name }</td>
												<td>${p.href }</td>
												<td>${p.permission }</td>
												<td>${p.type.name }</td>
												<td>${p.parent.name }</td>
												<td>${p.sort }</td>
												<td>
													<shiro:hasPermission name="menu:update">
													<a href="${base }/sys/system/menu/update/${p.id}" class="edit"><i></i>编辑</a> 
													</shiro:hasPermission>
													<shiro:hasPermission name="menu:delete">
													<a href="${base }/sys/system/menu/delete/${p.id}" onclick="javascript:return confirmDel();"
														class="delete"><i></i>删除</a>
													</shiro:hasPermission>
												</td>
											</tr>
												<c:forEach items="${menualldata }" var="c">	
													<c:if test="${c.parent.id eq p.id }">
													<tr>
														<td>${c.id}</td>
														<td>||--${c.name }</td>
														<td>${c.href }</td>
														<td>${c.permission }</td>
														<td>${c.type.name }</td>
														<td>${c.parent.name }</td>
														<td>${c.sort }</td>
														<td>
															<shiro:hasPermission name="menu:update">
															<a href="${base }/sys/system/menu/update/${c.id}" class="edit"><i></i>编辑</a> 
															</shiro:hasPermission>
															<shiro:hasPermission name="menu:delete">
															<a href="${base }/sys/system/menu/delete/${c.id}" onclick="javascript:return confirmDel();"
																class="delete"><i></i>删除</a>
															</shiro:hasPermission>
														</td>
													</tr>
														<c:forEach items="${menualldata }" var="d">
															<c:if test="${d.parent.id eq c.id }">
															<tr>
																<td>${d.id}</td>
																<td>|||--${d.name }</td>
																<td>${d.href }</td>
																<td>${d.permission }</td>
																<td>${d.type.name }</td>
																<td>${d.parent.name }</td>
																<td>${d.sort }</td>
																<td>
																	<shiro:hasPermission name="menu:update">
																	<a href="${base }/sys/system/menu/update/${d.id}" class="edit"><i></i>编辑</a> 
																	</shiro:hasPermission>
																	<shiro:hasPermission name="menu:delete">
																	<a href="${base }/sys/system/menu/delete/${d.id}" onclick="javascript:return confirmDel();"
																		class="delete"><i></i>删除</a>
																	</shiro:hasPermission>
																</td>
															</tr>
															</c:if>
														</c:forEach>	
													</c:if>
												</c:forEach>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
								<%@ include file="/WEB-INF/include/page.jsp" %>
							</div>
						</div>
					</div>
				</div>