<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
		
		    <%@ include file="/WEB-INF/include/message.jsp" %>
		     
			<h3>条件检索</h3>
            <form method="post" action="${base }/sys/info/ninfo">
			<div class="from-gy-controls">
				<div class="form-inline">
						<div class="form-group">
						    <label>文章名称：</label>
						    <input type="text" name="title" value="${page.entity.title }" class="form-control" placeholder="文章名称">
					    </div>

						<div class="form-group">
						    <label>所属栏目：</label>
						    <select id="navId" name="navBase.id" class="form-control">
						    	<option value="">全部(单选)</option>
							  	<c:forEach items="${treeData }" var="tree">
							  	  <option value="${tree.value }" data-level="1"
							  	  	  <c:if test="${(empty tree.childs) and (page.entity.navBase.id eq tree.value)}">
							  	  	      selected="selected"
							  	  	  </c:if>
							  	  >${tree.label }</option>
							  	  <c:forEach items="${tree.childs }" var="child">
								  	  	<option value="${child.value }" data-level="2"
								  	  	   <c:if test="${page.entity.navBase.id eq child.value}">
								  	  	      selected="selected"
								  	  	   </c:if>
								  	    >${child.label }</option>
							  	  </c:forEach>
							  	</c:forEach>
							</select>
						</div>

						<div class="form-group">
						    <label>文章状态：</label>
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
						
						<div class="form-group">
						    <label>发布时间：</label>
						    <div class="date-time-box">
						    	<input type="text" name="beginCheckTime" value="${page.entity.beginCheckTime }" class="form-control flatpickr">
						    	<i class="date-time-icon"></i>
						    </div>
						          至
						   	<div class="date-time-box">
						   		<input type="text" name="endCheckTime" value="${page.entity.endCheckTime }" class="form-control flatpickr">
						   		<i class="date-time-icon"></i>
						   	</div>
					    </div>
						  
					    <div class="form-group">
							<div class="operation-box operation-head-box">
								<button type="submit" class="btn btn-gy btn-recovery"><span class="glyphicon glyphicon-search"></span>查询</button>
							</div>
					   </div>
				</div>
			</div>
			</form>

			<div class="operation-box">
			    <shiro:hasPermission name="info:navinfo:xk">
					<button id="infoXK" type="button" class="btn btn-gy btn-new">
						<i></i>
						下刊
					</button>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="info:navinfo:hfsk">
					<button id="infoHFSK" type="button" class="btn btn-gy btn-recovery">
						<i></i>
						恢复上刊
					</button>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="info:navinfo:shtg">
					<button id="infoSHTG" type="button" class="btn btn-gy btn-adpot">
						<span class="glyphicon glyphicon-ok"></span>
						审核通过
					</button>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="info:navinfo:add">
					<a href="${base }/sys/info/ninfo/create" class="btn btn-gy btn-add">
						<span class="glyphicon glyphicon-plus-sign"></span>
						新增
					</a>
				</shiro:hasPermission>
			</div>

			<div class="box-table">
				<table class="table">
					<thead>
						<tr>
							<th><input type="checkbox" id="checkAll"></th>
							<th>文章名称</th>
							<th>所属栏目</th>
							<!-- 2017-04-20 17:25 注释，原因：目前不需要此字段
							<th>信息类型</th>
							-->
							<th>发布时间</th>
							<th>文章状态</th>
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
								<!-- 2017-04-20 17:25 注释，原因：目前不需要此字段
								<td>${navInfo.type.name}</td>
								-->
								<td><fmt:formatDate value="${navInfo.checkTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
								<td>${navInfo.status.name}</td>
								<td>
								    <shiro:hasPermission name="info:navinfo:update">
										<a href="${base }/sys/info/ninfo/update/${navInfo.id}" class="edit">
											<i></i>
											编辑
										</a>
									</shiro:hasPermission>
									
									<shiro:hasPermission name="	info:navinfo:delete">
										<a href="${base }/sys/info/ninfo/delete/${navInfo.id}" onclick="javascript:return confirmDel();" class="delete">
											<i></i>
											删除
										</a>
									</shiro:hasPermission>
									<shiro:hasPermission name="	info:navinfo:detail">
									<a href="${base }/sys/info/ninfo/detail/${navInfo.id}">&nbsp;<i class="glyphicon glyphicon-search"></i>&nbsp;查看</a>
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
		window.location.href = "${base}/sys/info/ninfo/check/yxk?" + infos.join('&');
	});
	
	//恢复上刊
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
		window.location.href = "${base}/sys/info/ninfo/check/hfsk?" + infos.join('&');
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
		window.location.href = "${base}/sys/info/ninfo/check/shtg?" + infos.join('&');
	});
});

$(document).ready(function(){
	$("#navId").smartselect({
	    multiple: false,
	    style: {
	        select: 'form-control'
	    },
	    text: {
	        selectLabel: '---请选择---'
	    },
	    toolbar: false
	});
});
</script>