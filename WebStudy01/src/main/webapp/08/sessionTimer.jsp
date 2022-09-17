<%@page import="java.util.Timer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   System.out.println(session.getLastAccessedTime());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/sessionTimer.jsp</title>
<jsp:include page="/includee/preScript.jsp"/>
<script src="<%=request.getContextPath()%>/resources/js/moment.min.js"></script>
<style type="text/css">
#mymodal{
   visibility: hidden;
}
</style>

</head>
<body>
<h4>application hashcode : <%=application.hashCode() %></h4>
<h4> 세션 타이머 </h4>

<div id="timeArea" ></div>   

<div id="msgArea"></div>


<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
         세션 만료 시간이 1분 남았음. 연장 할래?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary ctrlBtn">YES</button>
        <button type="button" class="btn btn-warning ctrlBtn">NO</button>
      </div>
    </div>
  </div>
</div>


<script src="<%=request.getContextPath()%>/resources/js/sessionTimer.js"></script>
<script>
   //상수 
   $("#timeArea").sessionTimer(120, "#exampleModal");
</script>
<script src="<%=request.getContextPath()%>/resources/js/moment.min.js"></script>
<jsp:include page="/includee/postScript.jsp"/>
<!--  

1. session timeout 초기값으로 갖는 타이머 출력.

2. 1초를 주기로 타이머를 디스카운트. interval() timeout()
     -> 타이머가 0이되면, 디스카운트 중단. 
     
3. "timeout - 60" 동안 대기한 후 메시지 창(modal) 디스플레이


4-1. NO 버튼 클릭, 메시지창 닫기

4-2. YES 버튼 클릭,
      1) 서버의 세션을 연장하기 위한 비동기 요청. : response body가 없도록 method 설정.
      2) 타이머 리셋
      3) 메세지 창 닫기.
      
-->

</body>
</html>


