<%@ page contentType="text/html; charset=UTF-8" %> 

<!DOCTYPE html> 
<html> 
<head> 

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>  
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css"/>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script type="text/javascript">
function mcreate() {
	var url = "createForm.do";
	location.href=url;
}

function mupdate(no) {
	var url = "updateForm.do";
	url += "?no=" +no;
	url += "&col=${col}";
	url += "&word=${word}";
	url += "&nowPage=${nowPage}";
	location.href=url;
}
function mdelete(no) {
	var url = "deleteForm.do";
	url += "?no=" +no;
	url += "&col=${col}";
	url += "&word=${word}";
	url += "&nowPage=${nowPage}";
	location.href=url;
}
function mlist(){
	var url= "list.do";
	url += "?col=${col}";
	url += "&word=${word}";
	url += "&nowPage=${nowPage}";
	location.href=url;
}
</script>
</head> 
<body> 

<div class="w3-container mainPosition">
<h2 class="iconPosition"><span class="glyphicon glyphicon-list-alt"></span>_주소록</h2>
<hr class="w3-round border-position">
</div>


<div class="container">
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-6">
<table class="table table-hover">
	<tr>
		<th>이름</th>
		<td>${dto.name}</td>
	</tr>
	
	<tr>
		<th>전화번호</th>
		<td>${dto.phone}</td>
	</tr>
	
	<tr>
		<th>우편번호</th>
		<td>${dto.zipcode}</td>
	</tr>
	
	<tr>
		<th>주소</th>
		<td>${dto.address1}<BR>
			${dto.address2}
		</td>
	</tr>
	
	<tr>
		<th>등록일</th>
		<td>${dto.wdate}</td>
	</tr>
</table>

 <div>
  <input type="button" class="btn btn-default" value="등록" onclick="mcreate()">
  <input type="button" class="btn btn-default" value="수정" onclick="mupdate('${dto.no}')">
  <input type="button" class="btn btn-default" value="삭제" onclick="mdelete('${dto.no}')">
  <input type="button" class="btn btn-default" value="목록" onclick="mlist()">
 </div>
</div>
</div>
</div>
</body> 
</html> 



