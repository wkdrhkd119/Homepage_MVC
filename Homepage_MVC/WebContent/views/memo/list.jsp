<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fnc" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html> 
<html> 
<head> 
<meta http-equiv="content-type" content="text/html" charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">

<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="Stylesheet">


<title></title> 

<script type="text/javascript">
function read(memono) {
 	/* alert(memono); */
	var url = "read.do";
	url+= "?memono=" +memono;
	url+= "&col=${col}"; 
	url+= "&word=${word}";
	url+= "&nowPage=${nowPage}";
	location.href=url;
}
</script>


 
</head> 
<body> 


<div class="w3-container mainPosition" id="services">
<h2 class="iconPosition"><span class="glyphicon glyphicon-pencil"></span>_메모</h2>
<hr class="w3-round border-position">
</div>

<div class="container">
<div class="search serchPosition">
<form action="./list.do"  method="post">
<div class="col-sm-2"></div>
<select name="col"><%-- 검색할 컬럼 --%>
	
	<option value="title"
	<c:if test="${col=='title'}">selected='selected'</c:if>
	>제목</option>
		
	<option value="content"
	<c:if test="${col=='content'}">selected='selected'</c:if>
	>내용</option>
	
	<option value="tot">전체출력</option>
</select>

<input type="text" name="word" value="${word}"><%-- 검색어 --%>
<input type="submit" class="btn btn-default" value="검색">
<input type='button' class="btn btn-default" value='등록' onclick="location.href='./createForm.do'">
</form>
</div>

<div class="row">
<div class="col-sm-2"></div>
<div class="col-sm-8">
<table class="table table-hover">
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>날짜</th>
		<th>조회수</th>
	</tr>
<c:choose>
<c:when test="${fnc:length(list)==0}">
<tr>
	<td colspan="4">등록된 메모가 없습니다.</td>
</tr>
</c:when>
<c:otherwise>
<c:forEach items="${list}" var="dto">
<tr>
	<td>${dto.memono}</td>
	<td><a href="javascript:read('${dto.memono}')">${dto.title}</a></td>
	<td>${dto.wdate}</td>
	<td>${dto.viewcnt}</td>
</tr>
</c:forEach>
</c:otherwise>
</c:choose>
</table>
</div>
</div>
</div>


<div class="row">
<div class="col-sm-4"></div>
${paging}
</div>

</body> 
</html> 

