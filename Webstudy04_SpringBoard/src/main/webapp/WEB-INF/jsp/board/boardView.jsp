<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 	필수파라미터가 빠졌으면 400에러
	존재하지않는 데이터를 조회하면 runtimeexception -->
	
	
	<table>
		<tr>
			<th>글번호</th>
			<td>${board.boNo }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.boTitle }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.boWriter }</td>
		</tr>
		<tr>
			<th>아이피</th>
			<td>${board.boIp }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${board.boMail }</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>${board.boPass }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.boContent }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.boDate }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.boHit }</td>
		</tr>
		<tr>
			<th>추천수</th>
			<td>${board.boRec }</td>
		</tr>
		<tr>
			<th>부모글</th>
			<td>${board.boParent }</td>
		</tr>
	</table>

</body>
</html>