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
function readGo(no){
	var url = "./read.do";
	url = url +"?no="+no;
	
	location.href=url;
}
function idown(fname){
	var url = "${pageContext.request.contextPath}/download";
	url += "?dir=/views/images/storage";
	url += "&filename="+fname;	
	
	location.href = url;
	
}
function update(){
	var url= "updateForm.do";
	url+="?no=${param.no}";
	url+="&col=${param.col}";
	url+="&word=${param.word}";
	url+="&nowPage=${param.nowPage}";
	location.href=url;
}
function reply(){
	var url= "replyForm.do";
	url+="?no=${param.no}";
	url+="&col=${param.col}";
	url+="&word=${param.word}";
	url+="&nowPage=${param.nowPage}";
	location.href=url;		
}
function idelete(){
	var url= "deleteForm.do";
	url+="?no=${param.no}";
	url+="&col=${param.col}";
	url+="&word=${param.word}";
	url+="&nowPage=${param.nowPage}";
	url+="&oldfile=${dto.fname}";
	location.href=url;
}
</script>


</head> 

<body>

<div class="w3-container mainPosition" id="services">
<h2 class="iconPosition"><span class="glyphicon glyphicon-picture"></span>_썸네일</h2>
<hr class="w3-round border-position">
</div> 
<div class="w3-container" style="margin-left: 20px;">
	<div class="container">
	<div class="row">
  	<div class="col-sm-4"></div>
     <table>
    <tr>			
		<td colspan="2" id="ftd">
		<img class="simage" id="img" src='${pageContext.request.contextPath}/views/images/storage/${dto.fname}'>
	    </td>
    </tr>
	
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
      <TD>${dto.viewcnt}</TD>
    </TR>
     <TR>
      <TH>등록날짜</TH>
      <TD>${fn:substring(dto.wdate,0,10)}</TD>
    </TR>
     <TR>
      <TH>파일</TH>      
       <TD>
       <c:choose>
       		<c:when test="${empty dto.fname}">파일없음</c:when>
       		<c:otherwise>
       		<a href="javascript:down('${dto.fname}')">
		     <span class="glyphicon glyphicon-download-alt"></span>
		     </a> 
       		</c:otherwise>
       </c:choose>
      
      </TD>
    </TR>
  
  </TABLE>
  </div>
  <TABLE>
  	<TR>
  	<c:set var="files" value="${list.get(0)}"/>
  	<c:set var="noArr" value="${list.get(1)}"/>
  	<c:forEach var="i" begin="0" end="4" >
  	<c:choose>
  	<c:when test="${empty files[i] }">
  <td><img class="slistimage w3-row-padding w3-hover-opacity" src="${pageContext.request.contextPath}/views/images/storage/member.jpg"><td>
	</c:when>
<c:otherwise>
	<c:choose>
  	<c:when test="${noArr[i]==dto.no}">
  	<td class="w3-row-padding"><a href="javascript:readGo('${noArr[i] }')">
  <img class="slistimage w3-hover-opacity" src="${pageContext.request.contextPath}/views/images/storage/${files[i]  }">
  </a>
  </td>
  </c:when>
  	<c:otherwise>
  	  <td class="w3-row-padding"><a href="javascript:readGo('${noArr[i]}')">
  <img class="slistimage w3-hover-opacity" src="${pageContext.request.contextPath}/views/images/storage/${files[i] }">
  </a>
  </td>
  	</c:otherwise>
  	
  	  	</c:choose>
  
</c:otherwise>  	
  	 </c:choose>
   </c:forEach> 
    </TR>
    </TABLE>
    </div>
   
  
<br>  


<div class="container">
 <div class="row">
 
  <div class="col-md-4 iconp"> </div>
    <input type='button' value='목록' class="btn btn-default" onclick="location.href='./list.do'">
    <input type='button' value='수정' class="btn btn-default" onclick ="update()">
    <input type='button' value='답변' class="btn btn-default" onclick="reply()">
    <input type='button' value='삭제' class="btn btn-default" onclick="idelete()">
 </div>
 </div>
 </div>
</body>

</html> 