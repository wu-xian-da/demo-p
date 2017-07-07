<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
		
		    <%@ include file="/WEB-INF/include/message.jsp" %>
		     
			<h3>条件检索</h3>
            <form method="post">
			<div class="from-gy-controls">
				<div class="form-inline">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
						    <label>信息名称：</label>
						    <input type="text" name="title" value="${page.entity.title }" class="form-control" placeholder="信息名称">
						  </div>
						</div>

						<div class="col-md-4">
							<div class="form-group">
						    <label>所属栏目：</label>
						    <select name="navBase.id" class="form-control">
								  <option value="">全部(单选)</option>
								  <c:forEach items="${navList }" var="nav">
								  	  <option value="${nav.id }"
								  	  	  <c:if test="${page.entity.navBase.id eq nav.id}">
								  	  	      selected="selected"
								  	  	  </c:if>
								  	  >${nav.navName }</option>
								  </c:forEach>
							</select>
						  </div>
						</div>

						<div class="col-md-4">
							<div class="form-group">
						    <label>信息状态：</label>
						    <select name="status" class="form-control">
								  <option value="">全部(单选)</option>
								  <c:forEach items="${infoStatuss }" var="s">
								  	 <option value="${s }"
								  	 	  <c:if test="${s eq page.entity.status }">
								  	  	      selected="selected"
								  	  	  </c:if>
								  	 >${s.name }</option>
								  </c:forEach>
								</select>
						  </div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
						    <label>信息类型：</label>
						    <select name="type" class="form-control">
							  <option value="">全部(单选)</option>
							  <c:forEach items="${infoTypes }" var="type">
							  	<option value="${type }"
							  		<c:if test="${type eq page.entity.type}">
						  	  	       selected="selected"
						  	  	    </c:if>
							  	>${type.name }</option>
							  </c:forEach>
							</select>
						  </div>
						</div>

						<div class="col-md-4">
							<div class="form-group">
								<div class="operation-box operation-head-box">
									<button type="submit" class="btn btn-gy btn-recovery"><span class="glyphicon glyphicon-search"></span>查询</button>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			</form>

			<div class="operation-box">
				<shiro:hasPermission name="info:navinfo:xk">
				<button id="infoXK" type="button" class="btn btn-gy btn-new"><i></i>下刊</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="info:navinfo:hfsk">
				<button id="infoHFSK" type="button" class="btn btn-gy btn-recovery"><i></i>恢复上刊</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="info:navinfo:shtg">
				<button id="infoSHTG" type="button" class="btn btn-gy btn-adpot"><span class="glyphicon glyphicon-ok"></span>审核通过</button>
				</shiro:hasPermission>
				<shiro:hasPermission name="info:navinfo:add">
				<a href="${base }/sys/info/navinfo/create" class="btn btn-gy btn-add"><span class="glyphicon glyphicon-plus-sign"></span>新增</a>
				</shiro:hasPermission>
			</div>

			<div class="box-table">
				<table class="table">
					<thead>
						<tr>
							<th><input type="checkbox" id="checkAll"></th>
							<th>信息名称</th>
							<th>所属栏目</th>
							<th>信息类型</th>
							<th>创建时间</th>
							<th>信息状态</th>
							<th>管理</th>
						</tr>
					</thead>
					<tbody>
					    <c:forEach items="${page.data }" var="navInfo">
							<tr>
								<td><input type="checkbox" name="infoCheck" data-id="${navInfo.id }"></td>
								<td>
									${navInfo.title }
								</td>
								<td>${navInfo.navBase.navName }</td>
								<td>${navInfo.type.name}</td>
								<td><fmt:formatDate value="${navInfo.createTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
								<td>${navInfo.status.name}</td>
								<td>
									<shiro:hasPermission name="info:navinfo:update">
									<a href="${base }/sys/info/navinfo/update/${navInfo.id}" class="edit">
										<i></i>
										编辑
									</a> 
									</shiro:hasPermission>
									<shiro:hasPermission name="	info:navinfo:delete">
									<a href="${base }/sys/info/navinfo/delete/${navInfo.id}" onclick="javascript:return confirmDel();" class="delete">
										<i></i>
										删除
									</a>
									</shiro:hasPermission>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

                <%@ include file="/WEB-INF/include/page.jsp" %>				

			</div>
			
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$("#checkAll").on("click", function(){
		if($(this).is(":checked")){
			$("[name=infoCheck]").prop("checked", true);
		}
		else{
			$("[name=infoCheck]").prop("checked", false);
		}
	});
	
	//下刊
	$('#infoXK').on("click", function(){
		var checkArr = $('input[name=infoCheck]:checked');
		if(checkArr.length == 0){
			alert("请勾选需要操作的数据");
			return;
		}
		var infos = [];
		checkArr.each(function(i){
			infos.push("infos[" + i + "].id=" + $(this).data("id"));
		});
		window.location.href = "${base}/sys/info/navinfo/check/yxk?" + infos.join('&');
	});
	
	// 上刊/恢复上刊
	$('#infoHFSK').on("click", function(){
		var checkArr = $('input[name=infoCheck]:checked');
		if(checkArr.length == 0){
			alert("请勾选需要操作的数据");
			return;
		}
		var infos = [];
		checkArr.each(function(i){
			infos.push("infos[" + i + "].id=" + $(this).data("id"));
		});
		window.location.href = "${base}/sys/info/navinfo/check/hfsk?" + infos.join('&');
	});
	
	// 审核通过
	$('#infoSHTG').on("click", function(){
		var checkArr = $('input[name=infoCheck]:checked');
		if(checkArr.length == 0){
			alert("请勾选需要操作的数据");
			return;
		}
		var infos = [];
		checkArr.each(function(i){
			infos.push("infos[" + i + "].id=" + $(this).data("id"));
		});
		window.location.href = "${base}/sys/info/navinfo/check/shtg?" + infos.join('&');
		
	});
});
</script>