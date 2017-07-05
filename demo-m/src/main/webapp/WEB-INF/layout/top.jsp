<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div class="navbar navbar-static-top bs-docs-nav header" id="top" role="banner">
	<div class="container-fluid">
		<div class="navbar-header logo-header">
			<div class="logo">
				<img src="${baseStatic }/img/logo.gif" alt="">
			</div>
			<div class="logo-text">
				<h1>贵阳机场</h1>
				<p>Guiyang Airport</p>
			</div>
		</div>
		<div class="logo-title">智慧管理平台</div>
		<nav class="navbar-collapse bs-navbar-collapse">
			<ul class="navbar-nav navbar-navigation">
				<c:forEach items="${userMenus }" var="menu">
					<li <c:if test="${fn:startsWith(currentUrl, menu.href)}">class="active"</c:if>>
						<a href="${base }${menu.href}">
							<img src="${menu.icon }" />${menu.name }
						</a>
					</li>
				</c:forEach>
			</ul>
			
			<ul class="navbar-nav navbar-right nav-signout">
				<li id="change-password" data-toggle="modal" data-target="#change-password-modal">
				<i class="glyphicon glyphicon-user"></i><a href="javascript:void(0);">修改密码</a></li>
				<li><i class="glyphicon glyphicon-off"></i><a href="${base }/logout">安全退出</a></li>
			</ul>
			
			<jsp:useBean id="now" class="java.util.Date" />   
			<div class="navbar-nav navbar-right nav-week-tips">今天是<fmt:formatDate value="${now }" pattern="yyyy年MM月dd日" type="date" /></div>
		</nav>
	</div>
</div>


<!-- dialog -->
<div class="modal fade from-gy-controls change-controls" id="change-password-modal" tabindex="-1" role="dialog" aria-labelledby="change-password-modal">
    	<div class="modal-dialog" role="document">
    		<div class="modal-content">
    			<div class="modal-header">
    				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    				<h4 class="modal-title">修改密码</h4>
    			</div>
    			<form id="pass-modify-form" method="post" action="${base }/sys/system/user/modifyPassword">
	    			<div class="modal-body">
	    					<div class="form-group form-inline">
	    						<label class="control-label">旧密码:</label>
	    						<input type="password" name="password" class="form-control {required:true,minlength:6,maxlength:30}">
	    					</div>
	
	    					<div class="form-group form-inline">
	    						<label class="control-label">新密码:</label>
	    						<input type="password" id="rePassword" name="rePassword" class="form-control {required:true,minlength:6,maxlength:30}">
	    					</div>
	
	    					<div class="form-group form-inline">
	    						<label class="control-label">重复一次:</label>
	    						<input type="password" name="reTwoPassword" class="form-control {required:true,minlength:6,maxlength:30,equalTo:'#rePassword'}">
	    					</div>
	    			</div>
	    			<div class="modal-footer">
	    				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	    				<button type="submit" class="btn btn-primary">确认</button>
	    			</div>
    			</form>
    		</div>
    	</div>
    </div>
    
    <script type="text/javascript">
	$("#pass-modify-form").validate({
		errorPlacement: function (error, element){
			error.appendTo(element.parent());  
		}
	});
</script>