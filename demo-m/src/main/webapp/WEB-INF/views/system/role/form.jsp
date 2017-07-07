<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
					<div class="col-md-12">
						<div class="box information-management-edit sysmanage-role-add">
						
						<%@ include file="/WEB-INF/include/message.jsp" %>
						
							<h2>${empty role.id ? "新增" : "编辑" }角色</h2>
							
							<form method="post" id="role_form">
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
											<input type="text" name="name" value="${role.name }" class="form-control {required:true,maxlength:20}" placeholder="角色名称">
										</div>
									</div>

									<div class="row row-list">
										<div class="col-md-1">
											<label>授权信息：</label>
										</div>
										<div class="col-md-8">
											<c:set var="menuIndex" value="0" />
											<c:forEach items="${menus }" var="p"><!--一级  -->
												<div class="row">
													<div class="col-md-12">
														<label>
															<input type="checkbox" name="menus[${menuIndex }].id" value="${p.id }"
															<c:if test="${functions:in(role.menus, p)}">checked</c:if>>${p.name }
														</label>
														<c:set var="menuIndex" value="${menuIndex + 1 }" />
													</div>
												</div>
												<c:if test="${fn:length(p.childs) > 0 }"><!-- 二级   -->
													<div class="row sysmanage-role-child">
														<div class="col-md-12">
															<c:forEach items="${p.childs }" var="c">
																<label>
																	<input type="checkbox" data-p="${p.id }" name="menus[${menuIndex }].id" value="${c.id }"
																	<c:if test="${functions:in(role.menus, c)}">checked</c:if>>${c.name }
																</label>
																<c:set var="menuIndex" value="${menuIndex + 1 }" />
																
																<c:if test="${fn:length(c.childs) > 0 }"><!-- 三级 -->
																	<div>
																		<c:forEach items="${c.childs }" var="d">
																			<label>
																				<input type="checkbox" data-p="${c.id }" name="menus[${menuIndex }].id" value="${d.id }"
																				<c:if test="${functions:in(role.menus, d)}">checked</c:if>>${d.name }
																			</label>
																			<c:set var="menuIndex" value="${menuIndex + 1 }" />
																		</c:forEach>
																	</div>
																</c:if>	
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
											<button type="button" onclick="roleSubmit();" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-ok-sign"></span>提交</button>
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
<script type="text/javascript">
	$("#role_form").validate({
		errorPlacement: function(error,element){
			error.appendTo(element.parent());
		}
	});
	function roleSubmit(){
		var checksLen = $('input[type="checkbox"]:checked').length;
		if(checksLen == 0){
			alert("请选择授权信息");
			return;
	 	}
		$("#role_form").submit();
	}
	$(function(){
		/* //父节点
		$('input[is-parent="true"]').on('click',function(){
			var parent_status = $(this).prop('checked');
			$('input[data-parent='+$(this).val()+']').each(function(){
				$(this).prop("checked",parent_status);
			});
		});
		
		//子节点
		$('input[is-child="true"]').on('click',function(){
			var parent_id = $(this).data("parent");
			if($(this).prop("checked")){
				$('input[value='+parent_id+']').each(function(){
					$(this).prop("checked",true);
				});
			}
		}); */
		$("#role_form").validate({
			errorPlacement: function(error,element){
				error.appendTo(element.parent());
			}
		});
		
		$('input[type="checkbox"]').on('click',function(){
			var _statusP = $(this).prop("checked");
			$('input[data-p='+$(this).val()+']').each(function(){
				var _statusC = $(this).prop("checked");
				if(_statusP){
					if(!_statusC){
						$(this).click();
					}
				}
				else{
					if(_statusC){
						$(this).click();
					}
				}
			});
	 	});
	});
</script>