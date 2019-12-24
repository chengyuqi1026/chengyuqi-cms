<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/resource/bootstrap-4.3.1/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/jquery/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/resource/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jquery validate/jquery.validate.js"></script>
<link href="/resource/bootstrap-4.3.1/css/bootstrap-reboot.css" rel="stylesheet">


<link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
	<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
    
<script type="text/javascript">


KindEditor.ready(function(K) {
	window.editor1 = K.create();
	prettyPrint();
});

function showWork(obj,url){
	$(".mymenuselected li").removeClass("menuselected");
	obj.parent().addClass("menuselected")
	$("#workcontent").load(url);
}

</script>
<style type="text/css">

.aaaa{
	background-color: black;
}

.bigSmall{
	width: 200px;
	height: 200px;
	background-color: blue;
}

</style>
<title>个人中心</title>
</head>
<body style="background-image: url('/resource/images/5c8a3be694273.jpg');">

<nav class="navbar navbar-light bg-light  shadow-lg p-3 mb-5 bg-white rounded ">
  <a class="navbar-brand" href="/user/home" >
    <img src="/resource/images/u=3129717166,974967207&fm=26&gp=0.jpg" width="30" height="30" class="d-inline-block align-top" alt="">
    	个人中心
  </a>
  
	  <nav class="justify-content-center ">
	  <form class="form-inline">
	    <input class="form-control mr-sm-2 col-sm-8" type="search" placeholder="Search" aria-label="Search">
	    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
	  </form>
	</nav>
</nav>

<div  >


<div class="row " >	

	<div class="col-md-2" style="margin-top:20px ; "> 
		<!-- 左侧的菜单 -->

		<ul class="nav flex-column mymenuselected">
	
		  <li class="nav-item ">
		 	 <div >
			<p class="card-text"></p>
		    <a class=" btn btn-primary rounded-pill  border border-secondary text-light bg-dark" href="#" onclick="showWork($(this),'/user/articles')" >我的文章</a>
		 	</div>
		  </li>
	
		  <li class="nav-item">
			<p class="card-text"></p>
		    <a class="btn btn-primary rounded-pill  border border-success text-light bg-dark" href="#" onclick="showWork($(this),'/user/postArticle')">发表文章</a>
		  </li>
	
		  <li class="nav-item">
			<p class="card-text"></p>
			<a class="btn btn-primary rounded-pill   border border-warning text-light bg-dark" href="#" onclick="showWork($(this),'/user/comments')" >我的评论</a>
		 </li>
	
		  <li class="nav-item">
			<p class="card-text"></p>
		    <a class="btn btn-primary disabled rounded-pill border border-info text-light bg-dark" href="#" tabindex="-1" aria-disabled="true">个人设置</a>
		  </li>
		  
		</ul>
		
</div>

<div id="workcontent" class="col-md-8 " class="aaaa" style="margin-top: 35px"> </div>
</div>
</div>
<nav class="nav fixed-bottom justify-content-center  text-white-50 bg-dark "  " height="100px"> 
	       在遭遇人生低谷的时候，不要灰心，至少曾经有人被你的魅力所吸引，曾经是，以后也会是。
</nav>
</body>
</html>