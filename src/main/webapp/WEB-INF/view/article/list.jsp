<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/resource/jquery/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	

function del(id){
	alert(id)
	if(!confirm("您确认删除么？"))
		return;
	$.post('/user/deletearticle',{id:id},
			function(data){
				if(data==true){
					alert("刪除成功")
					//location.href="#"
					$("#workcontent").load("/user/articles");
				}else{
					alert("刪除失敗")
				}
	},"json"				
	)
}
function gopage(page){
	$("#workcontent").load("/user/articles?page="+page);
}


function update(id) {
	$("#workcontent").load("/user/updateArticle?id="+id);
}
</script>
<title>Insert title here</title>
</head>
<body>
<table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">标题</th>
      <th scope="col">栏目</th>
      <th scope="col">分类</th>
      <th scope="col">发布时间</th>
      <th scope="col">状态</th>
      <th scope="col">操作</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${articlePage.list}" var="article">

        		<tr>
					<th scope="row">${article.id}</th>
        			<td>${article.title}</td>
        			<td>${article.channel.name}</td>
        			<td>${article.category.name}</td>
        			<td><fmt:formatDate value="${article.created}" pattern="yyyy年MM月dd日"/></td>
        			<td>
        				<c:choose>
        					<c:when test="${article.status==0}"> 待审核</c:when>
        					<c:when test="${article.status==1}"> 审核通过</c:when>
        					<c:when test="${article.status==2}"> 审核被拒</c:when>
        					<c:otherwise>未知</c:otherwise>
        				</c:choose>
        			</td>
        			<td width="200px">
        				<input type="button" value="删除"  class="btn btn-danger" onclick="del(${article.id})">
        				<input type="button" value="修改"  class="btn btn-warning" onclick="update(${article.id})" >
        			</td>
        		</tr>
        	</c:forEach>
  </tbody>
</table>
</body>
</html>