<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%!
   public static String tmp = "TEMP";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4> 흐름 제어(페이지 이동 구조)</h4>
<pre>
    1. Request Dispatch : 이동 과정에서 최초의 요청을 가지고 분기함.
          1) Forward : 요청 -> A 페이지 -> B 페이지 -> 응답 , Model2 Architecture
          2) Include : 요청 -> A 페이지 -> B 페이지 -> A 페이지로 복귀(B 데이터 일부) -> 응답, 페이지 모듈화 
    <%
        String path = "/02/standard.jsp";   //서버 사이드 방식 
//        request.getRequestDispatcher(path).forward(request,response);
//        request.getRequestDispatcher(path).include(request,response);
      
    %>      
         
    2. Redirect : 이동 과정에서 최초의 요청에 대한 응답이 먼저 전송됨.
          최초의 요청(명령)에 대한 처리가 완료되어 더이상 저장할 필요가 없음.
          요청1 -> 응답1(Line(302), Header(Location)) -> 클라이언트
                                    <!--location 주소 :  클라이언트 side -->
          -> 요청2(Location) ->  응답2(Body Contents)
     <%
          response.sendRedirect(request.getContextPath() + path);
     
     %>
</pre>
</body>
</html>