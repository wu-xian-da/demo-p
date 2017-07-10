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
			
			<div class="navbar-nav navbar-right nav-signout">
   				<div class="nav-signout-user">
   				欢迎 <span>${sessionUser.name }</span>
   				(${sessionUser.department.name }) 
   				</div>
   				<div>
   					<ul class="navbar-right">
   						<li id="change-password" data-toggle="modal" data-target="#change-password-modal">
   						<i class="glyphicon glyphicon-user"></i><a href="javascript:void(0);">修改密码</a></li>
   						<li><i class="glyphicon glyphicon-off"></i><a href="${base }/logout" onclick="javascript:return confirmDel('退出');">安全退出</a></li>
   					</ul>
   					<jsp:useBean id="now" class="java.util.Date" />   
   					<div class="navbar-right nav-week-tips">
   						今天是<fmt:formatDate value="${now }" pattern="yyyy年MM月dd日" type="date" />
   					</div>
   				</div>
    		</div>
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
    			<form id="pass-modify-form" method="post">
	    			<div class="modal-body">
	    					<div class="form-group form-inline">
	    						<label class="control-label">旧密码:</label>
	    						<input type="password" name="password" class="form-control">
	    					</div>
	
	    					<div class="form-group form-inline">
	    						<label class="control-label">新密码:</label>
	    						<input type="password" id="rePassword" name="rePassword" class="form-control">
	    					</div>
	
	    					<div class="form-group form-inline">
	    						<label class="control-label">重复一次:</label>
	    						<input type="password" name="reTwoPassword" class="form-control">
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
    $(function(){
		$("#pass-modify-form").validate({
			rules: {
				password: {required:true, maxlength:20},
				rePassword: {required:true, minlength:6, maxlength:20},
				reTwoPassword: {required:true, minlength:6, maxlength:20, equalTo:'#rePassword'}
			},
			messages: {
				password: {required: '请填写密码', maxlength: '不能超过20个字符'},
				rePassword: {required: '请填写新密码', minlength: '不能少于6个字符', maxlength: '不能超过20个字符'},
				reTwoPassword: {required: '请填写确认新密码', minlength: '不能少于6个字符', maxlength: '不能超过20个字符', equalTo: '两次密码不一致'}
			},
			submitHandler:function(form){
				var param = $("#pass-modify-form").serialize(); 
			    $.ajax({ 
					url: "${base }/sys/system/user/modifyPassword",
					type: "post", 
					dataType: "json",
					data: param, 
					success: function(data){
						alert(data.message);
						if(data.result){
							setTimeout(function(){
								window.location.reload();
							}, 1000);
						}
					}
			    });
			}
		});
		
	});
</script>