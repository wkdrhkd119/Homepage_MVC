<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="util" uri="/ELFunctions" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="Stylesheet">

<script type="text/javascript">
function read(no){
var url= "read.do";
url+= "?no="+no;
url+= "&col=${col}";
url+= "&word=${word}";
url+= "&nowPage=${nowPage}";

location.href=url;
}

function idown(fname) {
	var url = "${pageContext.request.contextPath}/download";
		url += "?dir=/views/images/storage";
		url += "&filename=" + fname;
		location.href = url
	}
</script>
<style type="text/css">

.imagestext{
	padding-top: 35px;
}

</style>

</head>

<body>


	<div class="w3-container mainPosition" id="services">
		<h2 class="iconPosition">
			<span class="glyphicon glyphicon-picture"></span>_썸네일
		</h2>
		<hr class="w3-round border-position">
	</div>


	<div class="container">
		<div class="search serchPosition">
			<div class="col-sm-2"></div>
			<FORM method='POST' action='./list.do'>
				
				
				<select name="col">
					<option value="wname"
					<c:if test="${col==wname}">selected='selected'</c:if>	
					>성명</option>
					
					<option value="title"
					<c:if test="${col==title}">selected='selected'</c:if>
					>제목</option>
					
					<option value="content"
					<c:if test="${col==content}">selected='selected'</c:if>
					>내용</option>
					
					<option value="total">전체</option>
					
				</select> <input type="text" name="word" value="${word}"> 
				<input type='submit' class="btn btn-default" value='검색'> 
				<input type='button' class="btn btn-default" value='등록' onclick="location.href='./createForm.do'">

			</FORM>
		</div>
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<TABLE class="table table-hover">
					<TR>
						<TH>번호</TH>
						<TH>사진</TH>
						<TH>제목</TH>
						<TH>성명</TH>
						<TH>조회수</TH>
						<TH>등록일</TH>
						<TH>파일명</TH>
					</TR>
<c:choose>
    	<c:when test="${fn:length(list)==0} ">
 		<tr>
    	<td colspan="7">등록된 글이 없습니다</td>
   		</tr>
		</c:when> 
		<c:otherwise>
		<c:forEach items="${list}" var="dto">

    <tr>
    	<Td class="imagestext">${dto.no}</Td>
    	<td><img class="images-img" src="${pageContext.request.contextPath}/views/images/storage/${dto.fname}"></td>
    	
    	<Td class="imagestext">
    	<c:forEach begin="1" end="${dto.indent}">
    	&nbsp;&nbsp;
    	</c:forEach>
    	
    	<c:if test="${dto.indent>0}"> 
    	<img src="${pageContext.request.contextPath}/views/images/storage/re.png"> 
    	</c:if>	
    	
    	<a href="javascript:read('${dto.no}')">${dto.title}</a>
    	
    	<c:if test="${util:newImg(fn:substring(dto.wdate,0,10))}">
    	<img src="${pageContext.request.contextPath}/views/images/storage/new.gif">
    	</c:if>
       	</Td>
    	
    	<Td class="imagestext">${dto.wname}</Td>
    	<Td class="imagestext">${dto.viewcnt}</Td>
        <Td class="imagestext">${fn:substring(dto.wdate,0,10)}</Td>    
        <Td class="imagestext">
        <c:choose>
        	<c:when test="${empty dto.fname}">파일없음</c:when>
        	<c:otherwise>
        	<a href="javascript:idown('${dto.fname}')">
        	<span class='glyphicon glyphicon-download-alt'></span>
        	</a>
        	</c:otherwise>
        </c:choose>
        </Td> 
    </tr>

    </c:forEach>
   	</c:otherwise>
 	</c:choose>
				</TABLE>
			</div>
		</div>
	</div>
	
	<div class="row">
	<div class="col-sm-4">
	</div>  
		${paging}
	</DIV>

</body>

</html>