<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>
<%@ include file="/WEB-INF/include/uploadify.jsp" %>

<div id="app-main-container" class="white-bg">
					<div class="col-md-12">
						<div class="box information-management-edit">
							<h2>${empty menu.id ? "新增" : "编辑" }信息</h2>
							<form method="post">
							<input type="hidden" name="id" value="${menu.id }">
							
							<div class="row">
								<div class="col-md-12">
									<hr>
									<div class="row row-list">
										<div class="col-md-1">
											<label>权限名称：</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="name" value="${menu.name }" class="form-control" placeholder="权限名称">
										</div>
									</div>
									
									<div class="row row-list">
										<div class="col-md-1">
											<label>权限URL：</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="href" value="${menu.href }" class="form-control" placeholder="权限URL">
										</div>
									</div>
									
									<div class="row row-list">
										<div class="col-md-1">
											<label>权限标识符：</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="permission" value="${menu.permission }" class="form-control" placeholder="权限标识符">
										</div>
									</div>
									
									<div class="row row-list">
										<div class="col-md-1">
											<label>权限类型：</label>
										</div>
										<div class="col-md-8">
											<select class="form-control" name="type">
												<c:forEach items="${types }" var="t">
													<option value="${t }" <c:if test="${menu.type eq t }">selected="selected"</c:if>>${t.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="row row-list">
										<div class="col-md-1">
											<label>上级权限：</label>
										</div>
										<div class="col-md-8">
											<select class="form-control" name="parent.id">
												<option value="" >请选择</option>
												<c:forEach items="${parents }" var="d">
													<option value="${d.id }" <c:if test="${menu.parent.id eq d.id }">selected="selected"</c:if>>${d.name }</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="row row-list">
										<div class="col-md-1">
											<label>显示序号：</label>
										</div>
										<div class="col-md-8">
											<input type="text" name="sort" value="${menu.sort }" class="form-control" placeholder="显示序号">
										</div>
									</div>
									
									<div class="row row-list">
										<div class="col-md-1">
											<label>图标icon：</label>
										</div>
										<div class="col-md-1">
											<sys:uploadify moduleName="menu" hiddenName="icon" hiddenValue="${menu.icon }" />
										</div>
									</div>

							  	<div class="row row-list">
							  		<div class="col-md-1"></div>
							  		<div class="col-md-8">
							  			<div class="operation-box">
											<button type="submit" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-ok-sign"></span>保存</button>
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