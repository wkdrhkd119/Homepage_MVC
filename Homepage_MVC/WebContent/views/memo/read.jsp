<%@ page contentType="text/html; charset=UTF-8" %> 

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

<script type="text/javascript">
function mcreate() {
	var url = "createForm.do";
	location.href=url;
}

function mupdate(memono) {
	var url = "updateForm.do";
	url += "?memono=" +memono; 
	url += "&col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	location.href=url;
}
function mdelete(memono) {
	var url = "deleteForm.do";
	url += "?memono="+memono; 
	url += "&col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	location.href=url;
}
function mlist() {
	var url = "list.do";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	location.href=url;
}
</script>
</head> 
<body> 

<div class="w3-container mainPosition" id="services">
<h2 class="iconPosition"><span class="glyphicon glyphicon-pencil"></span>_메모</h2>
<hr class="w3-round border-position" >
</div>

<div class="container">
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-5">
<table class="table table-hover">
	<tr>
		<th>제목</th>
		<td>${dto.title}</td>
	</tr>
	
	<tr>
		<th>내용</th>
		<td>${dto.content}</td>
	</tr>
	
	<tr>
		<th>조회수</th>
		<td>${dto.viewcnt}</td>
	</tr>
	
	<tr>
		<th>등록일</th>
		<td>${dto.wdate}</td>
	</tr>

</table>

<div>
<input type="button" class="btn btn-default" value="등록" onclick="mcreate()">
<input type="button" class="btn btn-default" value="수정" onclick="mupdate('${dto.memono}')">
<input type="button" class="btn btn-default" value="삭제" onclick="mdelete('${dto.memono}')">
<input type="button" class="btn btn-default" value="목록" onclick="mlist()">
</div>

</div>
</div>
</div>


</body> 
</html> 


