<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
					<div class="col-md-12">
						<div class="box information-management-edit sysmanage-role-add">
							<h2>${empty role.id ? "新增" : "编辑" }角色</h2>
							
							<form method="post">
							<input type="hidden" name="id" value="${role.id }">
							<input type="hidden" name="oldName" value="${role.name }">
							
							<div class="row">
								<div class="col-md-12">
									<hr>
									<div class="row row-list">
										<div class="col-md-1">
											<label>角色名称：</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="name" value="${role.name }" class="form-control" placeholder="角色名称">
										</div>
									</div>

									<div class="row row-list">
										<div class="col-md-1">
											<label>授权信息：</label>
										</div>
										<div class="col-md-8">
											<c:set var="menuIndex" value="0" />
											<c:forEach items="${menus }" var="p">
												<div class="row">
													<div class="col-md-12">
														<label><input type="checkbox"  name="menus[${menuIndex }].id" value="${p.id }"
															<c:if test="${functions:in(role.menus, p)}">checked</c:if>>${p.name }</label>
														<c:set var="menuIndex" value="${menuIndex + 1 }" />
													</div>
												</div>
												<c:if test="${fn:length(p.childs) > 0 }">
													<div class="row sysmanage-role-child">
														<div class="col-md-12">
															<c:forEach items="${p.childs }" var="c">
																<label><input type="checkbox" name="menus[${menuIndex }].id" value="${c.id }"
																	<c:if test="${functions:in(role.menus, c)}">checked</c:if>>${c.name }</label>
																<c:set var="menuIndex" value="${menuIndex + 1 }" />
															</c:forEach>
														</div>
													</div>
												</c:if>
											</c:forEach>
										</div>
									</div>
									<hr>
							  	<div class="row row-list">
							  		<div class="col-md-1">
							  		</div>
							  		<div class="col-md-8">
							  			<div class="operation-box">
											<button type="submit" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-ok-sign"></span>提交</button>
											<button type="button" onclick="javascript:history.back();" class="btn btn-gy btn-sort"><span class="glyphicon glyphicon-arrow-left"></span>返回</button>
										</div>
							  		</div>
							  	</div>
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>