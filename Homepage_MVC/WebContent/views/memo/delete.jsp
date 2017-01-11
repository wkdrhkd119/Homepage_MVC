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

<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<body> 
<div class="w3-container mainPosition" id="services">
<h2 class="iconPosition"><span class="glyphicon glyphicon-pencil"></span>_메모</h2>
<hr class="w3-round border-position">
</div>
<form method="post" action="deleteProc.do">
<input type="hidden" name="memono" value="${param.memono}">
<input type="hidden" name="word" value="${param.word}"><%-- 바로 받아서 사용하기 때문에 표현식으로 --%>
<input type="hidden" name="col" value="${param.col}">
<input type="hidden" name="nowPage" value="${param.nowPage}">
<div class="w3-row">
<div class="w3-col w3-center modalPosition">
<div class="container">
   <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">클릭해 주세요.</button>
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">글 삭제</h4>
        </div>
        <div class="modal-body">
          <p>메모를 삭제 하시면 복구 될 수 없습니다. 그래도 삭제 하시겠습니까?</p>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-default">삭제</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='./list.do'">목록</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="history.back()">취소</button>
        </div>
      </div>
     </div>
   </div>
</div>
</div>
</div>
</form>

</body> 
</html> 