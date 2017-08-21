<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta name="renderer" content="webkit"></meta>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"></meta>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"></meta>
	<style type="text/css">
		html,body{  height: 100%; margin:0;}
		body{ background: url('${baseStatic}/img/gy-login-bg.jpg') no-repeat center top; background-attachment: fixed; font-family: SimHei, sans-serif; }
		.login-app{ width: 1000px; height: 562px; margin: 0 auto; position: relative; top: 50%; margin-top: -281px; }
		.login-title{ width: 620px; height: 77px; margin: 0 auto; }
		.login-title img{ float: left; width: 75px; height: 77px; }
		.login-title h1{ float: right; font-size: 53px; color: #FFF; margin: 0; line-height: 77px; height: 77px; }
		.login-box{ width: 545px; height: 410px; border: 1px solid #FFF; background: #def1f8; position: absolute; bottom: 0; right: 0; }
		.login-box-inner{ padding: 20px 48px; }
		.login-box-inner h3{ font-size: 26px; color: #0099cb; }
		.login-box-inner input{ width: 390px; height: 52px; line-height: 52px; border: 1px solid #0098ca; color: #0098ca; font-size: 18px; padding-left: 50px; }
		.login-box-inner input:focus{ outline: none; }
		.login-box-inner input.submit-btn{ background: #0098ca; color: #FFF; font-size: 20px; font-family: "Microsoft YaHei"; padding: 0; line-height: 50px; width: 445px; }
		.login-box-inner p a{ font-size: 18px; color: #0099cb; text-decoration: none;}
		.login-input-control{ position: relative; margin-bottom: 20px; }
		.login-input-control span{ width: 40px;height: 40px;display: block;position: absolute;top: 5px;left: 3px; }
		.user-icon{ background: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDoxNzhCMzVCN0ZCMzcxMUU2ODdEOEY0MkI4QzRCQzg0QSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDoxNzhCMzVCOEZCMzcxMUU2ODdEOEY0MkI4QzRCQzg0QSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjE3OEIzNUI1RkIzNzExRTY4N0Q4RjQyQjhDNEJDODRBIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjE3OEIzNUI2RkIzNzExRTY4N0Q4RjQyQjhDNEJDODRBIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+d2cEtQAAAaRJREFUeNpi/P//P8NgBkwMgxyMOnDUgaMOHOoOZKGGIYwzLzkCqUggtgBiTiD+AMTbgHjp/3S9WxSZTUlBDXQYD5BaD8QueJT1AB1ZSncHAh3HBqRuALEiEcpnAx2ZRu80uIpIx4FAKtBDcXRzINAyKyDlT6K2TnqGYDgZeiSAHvOklwNVyNRnQi8HcpCpj41eDnxFL33kOvAomfo20suBS4D4B6mOA5aFj+jiQKBFoKosggQtP4E4l64FNdCRoOjKJ0LpZyDWAap/TFcHAss0caClk6ANhP1YlHwB4nlANXxAfAeoXoxudTHQsg4gVQjEi4CWpyKJm4EKZCC+BnIUVEwUSB0GYlkgjoKGPG0cCLSMEUjtA2IHJGFQelwOxLOAll9Aaki4AXE6EPugGdMBVFdJKweCQsKGgLJ/RCSdeqAjm6jqQKDjpgGpTCo2lh2AjjxIlUwCdJw5lR1HdKFNbC6eS4PuBj/Q48UUOxBoiBqQ0qZRn6iSGiHoQ8NOmwA1HBhFQwcyU6PbyQqtrmgBCJrLODo+OOrAUQeOOnCEOxAgwABJrX+46vimlgAAAABJRU5ErkJggg=='); }
		.password-icon{ background: url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDoxNzhCMzVCQkZCMzcxMUU2ODdEOEY0MkI4QzRCQzg0QSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDoxNzhCMzVCQ0ZCMzcxMUU2ODdEOEY0MkI4QzRCQzg0QSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjE3OEIzNUI5RkIzNzExRTY4N0Q4RjQyQjhDNEJDODRBIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjE3OEIzNUJBRkIzNzExRTY4N0Q4RjQyQjhDNEJDODRBIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+IWKVsQAAAXlJREFUeNpi/P//P8NgBkwMgxyMOnDUgaMOJABYqGUQ48xLJkBKH4i5gfglEF/7n653mWJzKS0HgQ7zBVIVQGyFRXo7EJcAHXptQKIY6Lg0ILUJyXFfgfghkhJPIL4EVKdGdwcCLTUEUjOh3AdA7APEEsDQUgDSIAc1QuWYgXgd3aMY6MAFQCoeiD8BsRLQYW+xqGkFUlVQrgVQzUm6hCDQYpA+Wyh3KTbHgQBQvBpIfYRynekZxUKgUIOydxFQexdKs9PTgaxI7C9E6vlNTwe+BsUglP2DgNp/SDmctgU1MO0JQ6NKGElYASh+E5pbUZIglOaE0uJAdaJAmg2YNp9SPRcDDZ8ApDJBFkAtZ0RyyF88DmRCUwcKlMtAR+pROwTzkd2LxsZmDiMWPkydLi3S4DMqtgE+08KBA9J5GW0Pjjpw1IGjDhx14DByIAcV7WWkhQMZqehAHlq0B0H9Xz9oa/ofhaF3j24d99FMMurAUQeOOhA/AAgwAFSjXu5A66H0AAAAAElFTkSuQmCC'); }
	</style>
	<title>贵阳机场智慧管理平台</title>
	<shiro:user>
		<script type="text/javascript">
			window.location.href = "${base}/sys/index";
		</script>
	</shiro:user>
	
</head>
<body>
	

	<div class="login-app">
		<div class="login-title">
			<img src="${baseStatic }/img/login-logo.png" alt="" />
			<h1>贵阳机场智慧管理平台</h1>
		</div>
		<div class="login-box">
			<div class="login-box-inner">
				<h3>用户登录</h3>
				<form method="post">
					<div class="login-input-control">
						<input type="text" name="username" class="user-input" value="${empty param.username ? '用户名' : param.username }" /> <span class="user-icon"></span>
					</div>
	
					<div class="login-input-control">
						<input type="text" name="password" class="user-password" value="密码" /> <span class="password-icon"></span>
					</div>
					<c:if test="${not empty message }">
						<div class="login-input-control" style="text-align:center;">${message }</div>
					</c:if>
					<input class="submit-btn" type="submit" value="登录" />
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getElementsByClass(classnames){
		  var classobj = new Array();
		  var classint = 0;
		  var tags =document.getElementsByTagName("*");
		  for(var i in tags){
		   if(tags[i].nodeType == 1){
		     if(tags[i].getAttribute("class") == classnames){
		       classobj[classint] = tags[i];
		        classint++;
		      }
		    }
		  }
		  return classobj;
		}
	
		function addEvent(dom, event, callback){
			dom.addEventListener(event,callback);
		}
	
		var userInput = getElementsByClass("user-input")[0];
		var userInputDefaultValue = userInput.defaultValue;
	
		addEvent(userInput, "focus", function(){
			userInput.value = "";
		});
	
		addEvent(userInput, "blur", function(){
			var _this = userInput;
			if(_this.value == ""){
				_this.value = userInputDefaultValue;
			}
		});
	
		var userPassword = getElementsByClass("user-password")[0];
		userPasswordDefaultValue = userPassword.defaultValue;
		addEvent(userPassword, "focus", function(){
			userPassword.value = "";
			userPassword.type="password";
		});
	
		addEvent(userPassword, "blur", function(){
			if(userPassword.value == ""){
				userPassword.value = userPasswordDefaultValue;
				userPassword.type="text";
			}
		});
	</script>
</body>
</html>