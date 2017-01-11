<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html> 
<head> 
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
function mlist() {
	var url = "list.do";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	location.href=url;
}
</script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css"> 
</head> 
<body> 


<div class="w3-container mainPosition" id="services">
<h2 class="iconPosition"><span class="glyphicon glyphicon-pencil"></span>_메모</h2>
<hr class="w3-round border-position" >
</div>


<div class="w3-row">
<div class="w3-col w3-center modalPosition">
<div class="container">

<c:choose>
<c:when test="${flag}">
<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">삭제 성공! 클릭해 주세요.</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">삭제 성공!</h4>
        </div>
        <div class="modal-body">
          <p>글 삭제를 완료했습니다.</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='./createForm.do'">새로운 메모 등록</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='./list.do'">목록</button>
          <!-- <button type="button" class="btn btn-default" data-dismiss="modal" onclick="history.back()">취소</button> -->
        </div>
      </div>
     </div>
   </div>
</c:when>
<c:otherwise>
<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">삭제 실패.. 클릭해 주세요.</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">삭제 실패.....</h4>
        </div>
        <div class="modal-body">
          <p>메모삭제를 실패 했습니다...</p>
        </div>
        <div class="modal-footer">
          <!-- <button type="submit" class="btn btn-default">삭제</button> -->
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='./list.do'">목록</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="history.back()">다시시도</button>
        </div>
      </div>
     </div>
   </div>
</c:otherwise>
</c:choose>
</div>
</div>
</div>

</body> 
</html> 
