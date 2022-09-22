<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
    페이지 :  <%=pageContext.getAttribute("pageAttr") %>
    요청 : <%=request.getAttribute("requestAttr") %>
    세션 : <%=session.getAttribute("sessionAttr") %>
    어플리케이션 : <%=application.getAttribute("applicationAttr") %>
</pre>
</body>
</html>