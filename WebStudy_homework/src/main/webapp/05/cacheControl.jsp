<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/cacheControl.jsp</title>
</head>
<body>
<h4> 캐시 제어 </h4>
<pre>  

      Cache-Control : HTTP 1.1 , 캐시 정책 설정
           ex) max-age : 초단위 캐시 생존 시간  (얼마동안 만들어 놓겠다)
               public / private ====> 304 상태코드(response Body X)로 서버의 데이터가 갱신되지 않았음을 알림.
               no-store(X)/no-chche(O)
      Pragma : HTTP 1.0
      Expires : HTTP 1.* , 캐시 데이터 만료 시간 설정.
      <%
         response.setHeader("Cache-Control", "no-store");
         response.setHeader("Pragma", "no-store");
         response.setDateHeader("Expires", 0);
      %>
</pre>
</body>
</html>