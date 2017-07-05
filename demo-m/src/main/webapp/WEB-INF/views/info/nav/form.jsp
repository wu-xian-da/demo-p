<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-4 sysmanage-site-box-wrap">
		<div class="box sysmanage-site-box">
		
			<h2>${empty navBase.id ? "新增" : "编辑" }栏目</h2>
			
			<form id="navBase_form" method="post">
			<!-- hidden -->
			<input type="text" name="id" value="${navBase.id }" />
			
			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  <div class="form-group">
						    <label>栏目名称：</label>
						    <input type="text" name="navName" value="${navBase.navName }" class="form-control" placeholder="栏目名称">
						  </div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  
						  <div class="form-group">
						    <label>所属栏目：</label>
						    <select name="parentId" class="form-control">
						    	<option value="">---请选择---</option>
						    	<c:forEach items="${parentNavList }" var="nav">
						    		<option value="${nav.id }"
						    			<c:if test="${nav.id eq navBase.parentId }">
						    				selected="selected"
						    			</c:if>
						    		>${nav.navName }</option>
						    	</c:forEach>
						    </select>
						  </div>
						  
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  <div class="form-group">
						    <label>栏目图标：</label>
								<img src="./public/img/login-logo.png" alt="">
								<button type="button" class="btn btn-gy btn-upload">
									<span class="glyphicon glyphicon-upload"></span>
									上传图标
								</button>
						  </div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  <div class="form-group">
						    <label>栏目类型：</label>
						    <select id="navType" name="navType" onchange="showTypeContentDiv(this.id);" class="form-control">
						    	<option value="">---请选择---</option>
						    	<c:forEach items="${navTypes }" var="type">
						    		<option value="${type}"
						    			<c:if test="${type eq navType}">
						    			   selected="selected"
						    			</c:if>
						    		>
						    		${type.name }</option>
						    	</c:forEach>
						    </select>
						  </div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="column-type">
				<div id="xxejcd_div" class="column-type-list" 
				    <c:choose>
				    	<c:when test="${navType eq 'XXEJCD' }">style="display:block;"</c:when>
				    	<c:otherwise>style="display:none;"</c:otherwise>
				    </c:choose>
				>
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>栏目头标：</label>
								    <img src="./public/img/login-logo.png" alt="">
										<button type="button" class="btn btn-gy btn-upload">
											<span class="glyphicon glyphicon-upload"></span>
											上传图标
										</button>
								  </div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>列表模版：</label>
								    <select id="nav_menuListTemplateId" name="navSecondMenu.menuListTemplateId" class="form-control" onchange="showTemplates(this.id, 'list', 'show_template_l_list', 'show_template_l_content');">
								    	<option value="1111" data-src="/static/img/n-1.png">1111</option>
								    	<option value="2222" data-src="/static/img/n-1.png">2222</option>
								    </select>
								  </div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
									<div class="form-group">
										<label>内容模版：</label>
										<select id="nav_menuContentTemplateId" name="navSecondMenu.menuContentTemplateId" class="form-control" onchange="showTemplates(this.id, 'content', 'show_template_l_list', 'show_template_l_content');">
											<option value="1111" data-src="/static/img/n-1.png">1111</option>
								    		<option value="2222" data-src="/static/img/n-1.png">2222</option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div id="wejcd_div" class="column-type-list" 
				    <c:choose>
				    	<c:when test="${navType eq 'WEJCD' }">style="display:block;"</c:when>
				    	<c:otherwise>style="display:none;"</c:otherwise>
				    </c:choose>
				>
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>内容模版：</label>
								    <select id="nav_contentTemplateId" name="navContent.contentTemplateId" class="form-control" onchange="showTemplate(this.id, 'content', 'show_template_c_content');">
								    	<option value="1111" data-src="/static/img/n-1.png">1111</option>
								    	<option value="2222" data-src="/static/img/n-1.png">2222</option>
								    </select>
								  </div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div id="urlwl_div" class="column-type-list" 
				    <c:choose>
				    	<c:when test="${navType eq 'URLWL' }">style="display:block;"</c:when>
				    	<c:otherwise>style="display:none;"</c:otherwise>
				    </c:choose>
				>
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>URL：</label>
								    <input type="text" class="form-control" placeholder="URL">
								  </div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  <div class="form-group">
						    <label>显示序号：</label>
						    <input type="text" class="form-control" placeholder="显示序号">
						  </div>
						</div>
					</div>
				</div>
			</div>

			<hr>
			
			<div class="operation-box">
				<button type="submit" class="btn btn-gy btn-query">
					<span class="glyphicon glyphicon-ok-sign"></span>
					保存
				</button>
				<button type="button" onclick="javascript:history.back();" class="btn btn-gy btn-sort">
					<span class="glyphicon glyphicon-arrow-left"></span>
					返回
				</button>
			</div>
			</form>

		</div>
	</div>

	<div class="col-md-8 show-item-thumb-wrap">
		<div class="row">
		    <!-- 下辖二级菜单 模板显示区域  begin -->
			<div id="show_template_l_list" class="col-md-4" 
				<c:choose>
			    	<c:when test="${navType eq 'XXEJCD' }">style="display:block;"</c:when>
			    	<c:otherwise>style="display:none;"</c:otherwise>
			    </c:choose>
			>
				<h3>列表模板</h3>
				<div class="show-list-thumb-wrap">
					<img src="${base }${navSecondMenu.menuListTemplate.imgPath}" alt="${navSecondMenu.menuListTemplate.name}">
				</div>
			</div>

			<div id="show_template_l_content" class="col-md-4 col-md-offset-1"
				<c:choose>
			    	<c:when test="${navType eq 'XXEJCD' }">style="display:block;"</c:when>
			    	<c:otherwise>style="display:none;"</c:otherwise>
			    </c:choose>
			>
				<h3>内容模板</h3>
				<div class="show-content-thumb-wrap">
					<img src="${base }${navSecondMenu.menuContentTemplate.imgPath}" alt="${navSecondMenu.menuContentTemplate.name}">
				</div>
			</div>
			<!-- 下辖二级菜单 模板显示区域  end -->
			
			<!-- 无二级菜单 模板显示区域  begin -->
			<div id="show_template_c_content" class="col-md-4"
				<c:choose>
			    	<c:when test="${navType eq 'WEJCD' }">style="display:block;"</c:when>
			    	<c:otherwise>style="display:none;"</c:otherwise>
			    </c:choose>
			>
				<h3>内容模板</h3>
				<div class="show-content-thumb-wrap">
					<img src="${base }${navContent.contentTemplate.imgPath}" alt="${navContent.contentTemplate.name}">
				</div>
			</div>
			<!-- 无二级菜单 模板显示区域  end -->
		</div>
	</div>
</div>

<script type="text/javascript">
	function showTypeContentDiv(id){
		var selectVal = $("#"+id).val();
		if(selectVal){
			if("XXEJCD" === selectVal){
				$("#xxejcd_div").show();
				$("#wejcd_div").hide();
				$("#urlwl_div").hide();
				
				//模板显示区域
				$("#show_template_l_list").show();
				$("#show_template_l_content").show();
				$("#show_template_c_content").hide();
			}else if("WEJCD" === selectVal){
				$("#xxejcd_div").hide();
				$("#wejcd_div").show();
				$("#urlwl_div").hide();
				
				$("#show_template_l_list").hide();
				$("#show_template_l_content").hide();
				$("#show_template_c_content").show();
			}else if("URLWL" === selectVal){
				$("#xxejcd_div").hide();
				$("#wejcd_div").hide();
				$("#urlwl_div").show();
				
				$("#show_template_l_list").hide();
				$("#show_template_l_content").hide();
				$("#show_template_c_content").hide();
			}else{
				$("#xxejcd_div").hide();
				$("#wejcd_div").hide();
				$("#urlwl_div").hide();
				
				$("#show_template_l_list").hide();
				$("#show_template_l_content").hide();
				$("#show_template_c_content").hide();
			}
		}
	}
	
	var ctx = '${base }';
	
	//列表+内容 模板
	function showTemplates(eId, showType, showListDivId, showContentDivId){
		var imgSrc = $("#"+eId+" option:selected").attr("data-src");
		
		if("list" === showType && imgSrc){
			$("#"+showListDivId + " .show-list-thumb-wrap img").attr("src", ctx + imgSrc);
		}else if("content" === showType && imgSrc){
			$("#"+showContentDivId + " .show-content-thumb-wrap img").attr("src", ctx + imgSrc);
		}
	}
	
	//内容模板
	function showTemplate(eId, showType, showContentDivId){
		var imgSrc = $("#"+eId+" option:selected").attr("data-src");
		
		if("content" === showType && imgSrc){
			$("#"+showContentDivId + " .show-content-thumb-wrap img").attr("src", ctx + imgSrc);
		}
	}
</script>