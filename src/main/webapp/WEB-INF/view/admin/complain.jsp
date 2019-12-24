<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- <div class="container-fluid"> -->
	<table class="table">
	  <thead>
          <tr>
            <th>id</th>
            <th width="150px">被举报文章</th>
            <th>投诉类型</th>
            <th width="150px" height="100px">涉及内容</th>
            <th>投诉人邮箱</th>
            <th>投诉人电话</th>
            <th>投诉时间</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach items="${pg.list}" var="c">
        		<tr>
        			<td>${c.id}</td>
        			<td>${c.article.title}</td>
        			<td>
        				<c:if test="${c.complainType==1}">政治敏感</c:if>
        				<c:if test="${c.complainType==2}">反社会</c:if>
        				<c:if test="${c.complainType==3}">涉黄</c:if>
        				<c:if test="${c.complainType==4}">涉毒</c:if>
        			</td>
        			<td>${c.content}</td>
        			<td>${c.email}</td>
        			<td>${c.mobile}</td>
        			<td><fmt:formatDate value="${c.created}" pattern="yyyy年MM月dd日"/></td>
        			<td width="200px">
        				<input type="button" value="删除"  class="btn btn-danger" onclick="del(${article.id})">
        				<input type="button" value="修改"  class="btn btn-warning" onclick="check(${article.id})" >
        			</td>
        		</tr>
        	</c:forEach>
        </tbody>
      </table>
      
      <div class="modal fade"   id="articleContent" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document" style="margin-left:100px;">
    <div class="modal-content" style="width:1200px;" >
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">添加地址</h5>
      </div>
      <div>
      	<form>
        	<table class="table table-dark">
	        	  <tbody>
				    <tr>
				      <th scope="col">网址名称</th>
				      <td><input type="text" name="name"></td>
				    </tr>
				    <tr>
				      <th scope="row">网址地址</th>
				      <td><input type="text" name="url"></td>
				    </tr>
				  </tbody>
			</table>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="add()">添加</button>
      </div>
    </div>
  </div>
</div>
    
      <nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		    <li class="page-item disabled">
		      <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
		    </li>
		   	<c:forEach begin="1" end="${pg.pages}" varStatus="i">
		   		<li class="page-item"><a class="page-link" href="javascript:void()" onclick="gopage(${i.index})">${i.index}</a></li>
		   	</c:forEach>
		    
		   
		    <li class="page-item">
		      <a class="page-link" href="#">Next</a>
		    </li>
		  </ul>
		</nav>
		

	
<!-- </div>     -->
<script>
	var global_article_id;
	
	$('#articleContent').on('hidden.bs.modal', function (e) {
		refreshPage()
		})
	
	
	/**
	* 翻页
	*/
	function gopage(page){
		$("#workcontent").load("/admin/link?page="+page);
	}
	
	function refreshPage(){
		$("#workcontent").load("/admin/link?page=" + '${pg.pageNum}' );
	}
	
	//去往地址添加
	function toAdd(){
			$('#articleContent').modal('show')
	}
	//地址添加
	function add(){
		var param=$("form").serialize();
		alert(param)
		$.post("/admin/linkAdd",param,function(flag){
			if(flag){
				$('#articleContent').modal('hide');
			}else{
				alet("添加失败")
			}
		},"json")
	}
	
</script>