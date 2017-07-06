<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
var queryString = '${queryString}';
if(queryString){
	try{
		if(window.localStorage){
			localStorage.removeItem("device_params");
			localStorage.setItem("device_params",queryString);
		}else{
			document.cookie="device_params="+queryString+"; path=/";
		}
	}catch(e){
		console.log("set device_params exception...");
	}
}

if(window.top === window.self){ 
    window.location.href = "/web/home";
}
</script>
</head>
</html>