<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html> 
<html> 
<head> 

<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.js"></script>  
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css"/>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/js/bootstrapValidator.min.js"> </script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
function blist() {
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
<h2 class="iconPosition"><span class="glyphicon glyphicon-list-alt"></span>_주소록</h2>
<hr class="w3-round border-position">
</div>


<div class="w3-row">
<div class="w3-col w3-center modalPosition">

<c:choose>
<c:when test="${flag}">
<div class="container">
	    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">결과 확인하기</button>

	  <!-- Modal -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog modal-sm">
	      <div class="modal-content">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">결과</h4>
	        </div>
	        <div class="modal-body">
	          <p>수정을 성공했습니다.</p>
	        </div>
	        <div class="modal-footer modal-button">
	          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="blist()">목록</button>
	          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='./createForm.do'">등록</button>
	          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</c:when>
<c:otherwise>
<div class="container">
   <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">결과 확인하기</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">결과</h4>
        </div>
        <div class="modal-body" style="padding-right: 40px;">
          <p>수정을 실패했습니다. 다시 시도해 주세요.</p>
        </div>
        <div class="modal-footer" style="padding-right: 40px;">
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='./list.do'">목록</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="history.back()">다시시도</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>
  </div>
</c:otherwise>
</c:choose>
</div>
</div>
</body>
</html> 

