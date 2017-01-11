<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import="model.bbs.*, java.util.*, utility.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="util" uri="/ELFunctions" %>

<!DOCTYPE html> 
<html> 
<head> 
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="Stylesheet">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
function read(bbsno) {
	var url = "read.do"
	url += "?bbsno=" +bbsno;
	url += "&col=${col}";
	url += "&word=${word}";
	url += "&nowPage=${nowPage}";
	location.href = url
}

function down(filename) {
	var url = "${pageContext.request.contextPath}/download";
	url += "?dir=/views/bbs/storage";
	url += "&filename="+filename;
	location.href = url;
}

</script>

</head> 

<body>

<div class="w3-container mainPosition" id="services">
<h2 class="iconPosition"><span class="glyphicon glyphicon-th-list"></span>_게시판</h2>
<hr class="w3-round border-position">
</div> 
  
<div class="container">
<div class="search serchPosition">
<form action="./list.do"  method="post">

<select name="col"><%-- 검색할 컬럼 --%>

	<c:if test="${col==wname}">selected='selected'</c:if>	

	<option value="wname" 
	<c:if test="${col==wname}">selected='selected'</c:if>	
	>성명</option>
	
	<option value="title"
	<c:if test="${col==title}">selected='selected'</c:if>	
	>제목</option>
		
	<option value="content"
	<c:if test="${col==content}">selected='selected'</c:if>	
	>내용</option>
	
	<option value="tot">전체출력</option>
</select>

<input type="text" name="word" value="${word}"><%-- 검색어 --%>
<input type="submit" class="btn btn-default" value="검색">
<input type='button' class="btn btn-default" value='등록' onclick="location.href='./createForm.do'">
</form>
</div>

  <TABLE class="table table-hover">
    <TR>
  
      <TH>번호</TH>
      <TH>제목</TH>
      <TH>성명</TH>
      <TH>조회수</TH>
      <TH>등록일</TH>
      <TH>파일명</TH>
    
    </TR>
   <c:choose>

<c:when test="${fn:length(list)==0}">    
	<tr>
	<td colspan="8">등록된 글이 없습니다.</td>
	</tr>
</c:when>

<c:otherwise>
<c:forEach items="${list}" var="dto">

	 <TR>
      <TD>${dto.bbsno}</TD>
      
      <TD align="left">
      <c:forEach begin="1" end="${dto.indent}"> 
	  <c:out value="&nbsp;" escapeXml="false"></c:out>
	  </c:forEach>

	  <c:if test="${dto.indent>0}">ㄴ</c:if>
	   
 	 <!-- <img src="./img/p.jpg" width="20px" height="20px"> --> 
      <a href="javascript:read('${dto.bbsno}')" >${dto.title}</a>
	
	  <c:if test="${util:newImg(fn:substring(dto.wdate,0,10))}">     
      <img src="${pageContext.request.contextPath}/images/new.gif">
      </c:if>
      </TD>
      
      <TD>${dto.wname}</TD>
	  <TD>${dto.viewcnt}</TD>
      <TD>${fn:substring(dto.wdate,0, 10)}</TD>
     
      <TD>
      <c:choose>
      <c:when test="${empty dto.filename}">파일없음</c:when>
      <c:otherwise>
      <a href="javascript:down('${dto.filename}')">
      <span class='glyphicon glyphicon-save-file'></span>	
      </a>
      </c:otherwise>
      </c:choose>
     </TD>
     
    </TR>
 
</c:forEach> 
</c:otherwise> 
</c:choose>    
    
  </TABLE>
  
</div>   
<div class="row">
<div class="col-sm-4"></div>  
${paging}
</div>

</body>

</html> 
