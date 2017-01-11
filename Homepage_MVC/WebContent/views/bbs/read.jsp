<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="Stylesheet">
<script type="text/javascript">
function blist() {
	var url = "list.do";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	location.href = url;
}	

function bupdate() {
	var url = "updateForm.do";
	url += "?bbsno=${dto.bbsno}";
	url += "&col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	location.href=url;
}

function bdelete(bbsno) {
	var url = "deleteForm.do";
	url += "?bbsno=${dto.bbsno}";
	url += "&col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	url += "&oldfile=${dto.filename}";
	location.href=url;
}

function breply(bbsno) {
	var url = "replyForm.do";
	url += "?bbsno=${dto.bbsno}";
	url += "&col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	location.href=url;
}

function down(filename) {
	var url = "${pageContext.request.contextPath}/download";
	url += "?dir=/bbs/storage";
	url += "&filename="+filename;
	location.href = url
}

</script>


</head> 

<body>


<div class="w3-container mainPosition" id="services">
<h2 class="iconPosition"><span class="glyphicon glyphicon-th-list"></span>_게시판</h2>
<hr class="w3-round border-position">
</div> 


 
<div class="container">
<div class="row">
<div class="col-sm-3"></div>
<div class="col-sm-6">
  <TABLE class="table table-hover">
    <TR>
      <TH>제목</TH>
      <TD>${dto.title}</TD>
    </TR>
     
     <TR>
      <TH>내용</TH>
      <TD>${dto.content}</TD>
     </TR> 
      
     <TR> 
      <TH>성명</TH>
      <TD>${dto.wname}</TD>
     </TR> 
      
     <TR> 
      <TH>조회수</TH>
      <TD>${dto.wdate}</TD>
     </TR> 
      
     <TR> 
      <TH>등록</TH>
      <TD>${fn:substring(dto.wdate,0,10)}</TD>
    </TR>
    
    <TR> 
      <TH>파일명</TH>
      <TD>
      <c:choose>
      <c:when test="${empty dto.filename}">파일없음</c:when>
      <c:otherwise>
       ${dto.filename}(${dto.filesize div 1024}KB) 
      <a href="javascript:down('${dto.filename}')">
      <span class="glyphicon glyphicon-save"></span>
      </a>
      </c:otherwise>
      </c:choose>
      </TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='button' class="btn btn-default" value='등록' onclick="location.href='./createForm.do'">
    <input type='button' class="btn btn-default" value='목록' onclick="blist()">
    <input type='button' class="btn btn-default" value='수정' onclick="bupdate()">
    <input type='button' class="btn btn-default" value='삭제' onclick="bdelete()">
    <input type='button' class="btn btn-default" value='답변' onclick="breply()">
  </DIV>
 </div>
 </div>
 </div>
</body>

</html> 
