<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import="java.util.*,model.address.*" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 


<!DOCTYPE html> 
<html> 
<head> 
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>  
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css"/>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"> </script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
a{
color: white;
}
.a {
color: black;
}
</style>
<script type="text/javascript">
function read(no) {
	var url = "read.do";
	url += "?no=" +no;
	url += "&col=${col}";
	url += "&word=${word}";
	url += "&nowPage=${nowPage}";
	location.href = url;
}
function update(no) {
	var url = "updateForm.do";
	url += "?no=" +no;
	url += "&col=${col}";
	url += "&word=${word}";
	url += "&nowPage=${nowPage}";
	location.href = url;
}
function del(no) {
	if(confirm("삭제 하시겠습니까?")){
		var url = "deleteProc.do"
		url += "?no=" +no;
		url += "&col=${col}";
		url += "&word=${word}";
		url += "&nowPage=${nowPage}";
		location.href=url;
	}
		
}
</script>

</head> 
<body> 


<div class="w3-container mainPosition" id="services">
<h2 class="iconPosition"><span class="glyphicon glyphicon-list-alt"></span>_주소록</h2>
<hr class="w3-round border-position">
</div>

<div class="container">
<div class="search" style="margin-bottom: 5px;">
<form action="./list.do" method="post">

<select name="col">

	<option value="name"
	<c:if test="${col=='name'}">selected='selected'</c:if>
	>이름</option>
	
	
	<option value="phone"
	<c:if test="${col=='phone'}">selected='selected'</c:if>
	>핸드폰</option>
	
	<option value="tot">전체출력</option>
	
</select>

<input type="text" name="word" value="${word}">
<input type="submit" class="btn btn-default" value="검색">
<input type="button" class="btn btn-default" value="등록" onclick="location.href='./createForm.do'">
</form>
</div>
<table class="table table-hover">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>핸드폰</th>
		<th>우편번호</th>
		<th>주소</th>
		<th>상세주소</th>
		<th>수정/삭제</th>
	</tr>
<c:choose>
<c:when test="${fn:length(list)==0}">
<tr>
	<td colspan="6" align="center">등록된 정보가 없습니다</td>
</tr>
</c:when>
<c:otherwise>
<c:forEach items="${list}" var="dto">
<tr>
		<td>${dto.no}</td>
		<td><a class="a"href="javascript:read('${dto.no}')">${dto.name}</a></td>
		<td>${dto.phone}</td>
		<td>${dto.zipcode}</td>
		<td>${dto.address1}</td>
		<td>${dto.address2}</td>
		<td>
		<a href="javascript:update('${dto.no}')">수정</a>
		<a href="javascript:del('${dto.no}')">삭제</a>
		</td>
	</tr>
</c:forEach>
</c:otherwise>
</c:choose>
</table>
</div>
<div>
${paging}
</div>

</body> 
</html>

 