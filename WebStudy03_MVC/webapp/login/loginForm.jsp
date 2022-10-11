<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>
</head>
<body><!-- pageContext.request.contextPath cPath -->
<form action="${pageContext.request.contextPath}/login/loginProcess.do" method="post">
	<ul>
		<li>
			<input type="text"  name="memId" placeholder="아이디"/>
		</li>
		<li>
			<input type="password"  name="memPass" placeholder="비밀번호"/>
			<input type="submit"  value="로그인"/>
		</li>
	</ul>
</form>
</body>
</html>