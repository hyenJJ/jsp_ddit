<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> JspWritter 를 이용한 Buffer 관리</h4>
<pre>
    buffer size : <%=out.getBufferSize() %>
    remaining size : <%=out.getRemaining() %>
    
    <%
       for(int i=1; i<=100; i++){
    	   out.print(String.format("%d 번째 반복,", i));
    	   out.println(String.format("남은 버퍼의 용량 : %d",out.getRemaining()));
           if(i==30)
        	   out.flush();
    	   if(i==40)
 //       	   throw new RuntimeException("강제 발생 예외");
           request.getRequestDispatcher("/08/implicit.jsp").forward(request, response);
           
       }
    %>
</pre>
</body>
</html>