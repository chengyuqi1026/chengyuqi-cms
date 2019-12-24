<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cms-个人中心</title>
<link href="/resource/bootstrap-4.3.1/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/jquery/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jquery validate/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/jquery validate/localization/messages_zh.js"></script>

<style type="text/css">
	.menuselected {
		background:red;
	}
	.mymenuselected li:hover {
		background:blue;
	}
</style>
</head>
<body style="background-image: url('/resource/images/5c8a3be694273.jpg');">
<nav class="navbar navbar-light bg-light  shadow-lg p-3 mb-5 bg-white rounded ">
  <a class="navbar-brand" >
    <img src="/resource/images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">
    	管理中心
  </a>
  
	  <nav class="justify-content-center ">
	  <form class="form-inline">
	    <!-- <input class="form-control mr-sm-2 col-sm-8" type="search" placeholder="Search" aria-label="Search"> -->
	    <button class="btn btn-outline-success my-2 my-sm-0" type="">退出</button>
	  </form>
	</nav>
</nav>
<!--  头结束 -->	
<div class="container-fluid">
	<div class=" row">
		<div class="col-md-2" style="margin-top:20px ; border-right:solid 2px"> 
			<!-- 左侧的菜单 -->
			<ul class="nav flex-column mymenuselected">
				  <li class="nav-item ">
				    <a  class="nav-link active" href="#" onclick="showWork($(this),'/admin/article?status&page')" >文章管理</a>
				  </li>
				  
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/link')" >友情链接管理</a>
				  </li>
				  <!-- <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/userComplain')" >用户管理</a>
				  </li> -->
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/')" >用户管理</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/comment')" >评论管理</a>
				  </li>
				</ul>	
		</div>
		
		<div class="col-md-10" id="workcontent"> 
		
		    
		</div>	
	</div>
</div>
	
<!-- 尾开始 -->
<nav class="nav fixed-bottom justify-content-center  text-white-50 bg-dark"   height="70px"> 
	         CMS  系统后台管理系统  版权所有 违者必奖 
</nav>

<script type="text/javascript">	
	
	function showWork(obj,url){
		$(".mymenuselected li").removeClass("menuselected");
		obj.parent().addClass("menuselected")		
		$("#workcontent").load(url);
		
	}
</script>


</body>

</html>