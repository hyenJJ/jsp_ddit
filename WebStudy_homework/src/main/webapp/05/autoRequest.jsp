<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">     <!--주기 -->
<!-- <meta http-equiv="Refresh" content="1"> -->
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<h4>현재 서버의 시간 : <span id="timeArea"></span></h4>
<pre>
    HTTP 특성
      - ConnectLess : request vs response(1:1) 현재 요청에 대한 응답이 전송된 후 연결 종료.
      - StateLess : 연결이 종료되면서 양 피어(peer)에 저장된 정보들이 삭제됨. <!--  ex) 두개의 책을 하나의 장바구니에 넣을 수가 없음 -->
      
      
      주기적인 자동 요청
      1. server side : Refresh 헤더 
      2. client side
          1) HTML meta : http-equiv(Refresh)
          2) JavaScript : Scheduling 함수
                          timeout : 지연시간
                          interval(*) : 주기적 갱신 
                                
      
      <%--
         response.setIntHeader("ReFresh", 1); // -> 1초마다 새로고침
                /* refresh : 서버의 데이타 갱신 주기(value값)에 맞게 새로고침  */
      --%>
</pre>
<script>  
    let timeArea = $("#timeArea");
                   //파라미터
    setInterval(function(){
    	timeArea.html("");
//    	location.reload();

     $.ajax({

		url : "<%=request.getContextPath()%>/getServerTime.html", //어디로
		method : "get", //어떻게

		dataType : "html",
		success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함
           timeArea.html(resp);
		},
		error : function() {

			console.log(errorResp.status);
		}

	});
		
	}, 1000);// = 1초
                    
</script>
</body>
</html>