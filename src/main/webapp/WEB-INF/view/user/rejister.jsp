<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/resource/bootstrap-4.3.1/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/jquery/jquery-3.4.1.min.js" ></script>
<script type="text/javascript" src="/resource/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jquery validate/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/jquery validate/localization/messages_zh.js"></script>
<link href="/resource/bootstrap-4.3.1/css/bootstrap-reboot.css" rel="stylesheet">


<script type="text/javascript">
	function aa(username){
		
	}
</script>

<title>注册页面</title>
</head>
<body>

<div class="jumbotron jumbotron-fluid text-white-50 bg-dark " >
  <div class="container">
    <p class="text-danger display-4">CMS客户管理系统---注册</p>
    <p class="lead">厉害的人，是敢于在逆境中生存，
    				敢于在艰难中坚持，敢于在黎明到来前忍受黑暗，
					在成功辉煌之际，保持颗平常心。</p>
  </div>
</div >

<form:form modelAttribute="user" action="rejister" method="post">
  <div class="form-group " style="margin-left: 400px;margin-top: 10px;">
  	<div class="form-inline">
	    <label for="exampleInputEmail1">Username</label>
	    <form:input path="username" class="form-control" id="reg" remote="/user/checkname"/>
	 	<form:errors path="username"><div class="alert alert-danger col-sm-2" style="margin-left: 200px;margin-top: 10px;">用户名已经存在</div></form:errors>
  	</div>
  	<small id="emailHelp" class="form-text text-muted">请在上栏中输入您要注册的账号</small>
  </div>
  
  
    <div class="form-group" style="margin-left: 400px;margin-top: 10px;">
  	<div class="form-inline">
	    <label for="exampleInputEmail1">Password</label>
	    <form:input path="password" class="form-control" />
	 	<form:errors path="password"><div class="alert alert-danger col-sm-4" style="margin-left: 200px;margin-top: 10px;">密码不合法(不能位纯数字，不可超过16位)</div></form:errors>
  	</div>
  	<small id="emailHelp" class="form-text text-muted">请在上栏中输入您的密码</small>
  </div>
  <button type="submit" class="btn btn-primary" style="margin-left: 500px;margin-top: 10px;">Submit</button>
</form:form>
</body>
<script type="text/javascript">
	$("#reg").validate();
</script>
 </html>