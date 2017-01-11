<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html> 
<html> 
<head> 

<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">

<script>
$(document).ready(function() {
    $('#contactForm').bootstrapValidator({
        container: '#messages',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            passwd: {
                validators: {
                    notEmpty: {
                        message: '비밀번호를 입력해주세요.'
                    },
                    stringLength: {
                        max: 15,
                        message: '최소 6자리 까지 입력해주세요.'
                    }
                }
            }      
        }
    });
});
</script>

</head> 
<body> 

<div class="w3-container mainPosition" id="services">
<h2 class="iconPosition"><span class="glyphicon glyphicon-picture"></span>_썸네일</h2>
<hr class="w3-round border-position">
</div> 

<c:choose>
<c:when test="${flag}">
<div class="w3-row">
<div class="w3-col w3-center modalPosition">	
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
          <p>답변이 있는 글이므로 삭제 불가!</p>
        </div>
        <div class="modal-footer modal-button">
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="location.href='./list.do'">목록</button>
          <button type="button" class="btn btn-default" data-dismiss="modal" onclick="history.back()">뒤로</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </div>
      </div>
    </div>
  </div>
</div>	
</div>	
</div>	
</c:when>
<c:otherwise>
<form method="post" 
	  action="./deleteProc.do" 
	  onsubmit="return incheck(this)"
	  id="contactForm"
	  class="form-horizontal">

<input type="hidden" name="no" value="${param.no}"> 
<input type="hidden" name="col" value="${param.col}">
<input type="hidden" name="word" value="${param.word}">
<input type="hidden" name="nowPage" value="${param.nowPage}">
<input type="hidden" name="oldfile"value="${param.oldfile}">

	<div class="container" style="margin-left: 270px;">

	  <div class="alert alert-danger alingCenter">
	  	<strong>경고!</strong>  삭제하시면 복구가 불가능 합니다. 그래도 삭제 하시겠습니까?
	  </div>
	</div>

	
	<div class="form-group" >
	   <label class="col-md-5 control-label">패스워드</label>
        <div class="col-md-2">
            <input type="password" class="form-control" name="passwd"/>        
        </div>
        <div class="col-md-3">
            <button type="submit" class="btn btn-default">삭제</button>
            <button type="button" class="btn btn-default" onclick="history.back()">취소</button>       
        </div>
    </div>
   
    
    <!-- #messages is where the messages are placed inside -->
    
    <div class="form-group">
        <div class="col-md-9 col-md-offset-3">
            <div id="messages"></div>
        </div>
    </div>
</form>
</c:otherwise>
</c:choose>
</html> 
