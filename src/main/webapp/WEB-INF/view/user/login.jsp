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


<script type="text/javascript">
	function zhuce(){
		location="/user/rejister";
	}
</script>

<title>登录页面</title>
</head>
<body>


<div class="jumbotron jumbotron-fluid  text-warning text-white-50 bg-dark ">
  <div class="container">
    <p class=" display-4">CMS客户管理系统---登录</p>
    <p class="lead">厉害的人，是敢于在逆境中生存，
    				敢于在艰难中坚持，敢于在黎明到来前忍受黑暗，
					在成功辉煌之际，保持颗平常心。</p>
  </div>
</div>

<form action="login" method="post">
${err}
  <div class="form-group" style="margin-left: 500px;margin-top: 10px;">
  	<div class="form-inline">
	    <label for="exampleInputEmail1">Username</label>
	    <input name="username" class="form-control" remote="/user/checkname"/>
  	</div>
  	<small id="emailHelp" class="form-text text-muted">请在上栏中输入您要登录的账号</small>
  </div>
  
    <div class="form-group" style="margin-left: 500px;margin-top: 10px;">
  	<div class="form-inline">
	    <label for="exampleInputEmail1">Password</label>
	    <input type="password" name="password" class="form-control" />
  	</div>
  	<small id="emailHelp" class="form-text text-muted">请在上栏中输入您的密码</small>
  </div>
  <button type="submit" class="btn btn-primary" style="margin-left: 500px;margin-top: 10px;">登录</button>
  <input class="btn btn-primary" type="button" value="注册" onclick="zhuce()" style="margin-top: 10px"/>
</form>

</body>
 	</html>