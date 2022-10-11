<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="boardList" value="${pagingVO.dataList }" />

<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>추천수</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty boardList}">
				<c:forEach items="${boardList}" var="board">
					<c:url value="/board/boardView.do" var="viewURL">
						<c:param name="what" value="${board.boNo} " />
					</c:url>
					<tr>
						<td>${board.boNo }</td>
						<td><a href="${viewURL }">${board.boTitle }</a></td>
						<td>${board.boWriter }</td>
						<td>${board.boDate }</td>
						<td>${board.boHit }</td>
						<td>${board.boRec }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">조건에 맞는 글이 없음.</td>
				</tr>
			</c:otherwise>
		</c:choose>

	</tbody>
</table>
</body>
</html>