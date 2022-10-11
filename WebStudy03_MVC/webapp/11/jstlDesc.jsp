<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.yellow{
		background-color : yellow;
	}
	.red{
		background-color : red;
	}
	.blue{
		background-color: blue;
	}
</style>
</head>
<body>
<h4>JSTL(Jsp Standard Tag Library)</h4>
<pre>
	: 커스텀 태그 라이브러리 
	** taglib 지시자로 해당 커스텀 태그 로딩.
	el과 jstl은 상호 보완적 
	c:remove -> el변수(=속성)를 제거해줌
	c:out -> 속성을 출력 (문자 escape) 
	
	** Core(C) 태그
	1. EL 변수 지원 : set, remove 
		<c:set var="sample" scope="session" value = "세션샘플" /> -> back 코드
		       var -> 특정 scope에 속성하나를 만들어줌 (속성명)
		       ${sample }, <%=session.getAttribute("sample") %>
		       <c:remove var="sample" scope="session"/> -> 어느 scope인지 명시를 하지 않으면 모든 scope 에서 다 지워짐
		       --> ${sample }
		       
	2. 조건문 : if, choose~when~otherwise
		<c:if test="${not empty sessionScope.sample}">
			샘플 있음. -->${sessionScope.sample }
		</c:if>
		<c:if test="${empty sessionScope.sample}">
			샘플 없음.
		</c:if>
		<c:set var="sample" value="샘플" />
		<c:choose>
			<c:when test="${not empty pageScope.sample}">
			샘플 있음. -->${pageScope.sample }
			</c:when>
			<c:otherwise>
			샘플 없음.
			</c:otherwise>
		</c:choose>
		
	3. 반복문 : foreach, forTokens
		<!-- for(int i=1;i<=10;i++){반복문} -->
		<c:forEach var="i" begin="1" end="10" step="1" varStatus="vs">
			${i * 10}, ${i * vs.count }
		</c:forEach>
		
		향상된 for 문
		<!-- for( element : collection){} -->
		<c:set var="elList" value='<%=Arrays.asList("value1","value2") %>' /> ->"" 안에 "" 가 불가해서 밖에 '' 로 바꿔줌
		<c:forEach items="${elList}" var="element" varStatus="vs">
			${element }  ${not vs.last ? "," :"" }
		</c:forEach> -> items = collection
		
		<c:forTokens items="1,2,3" delims="," var="token">
			${token * 1000 }
		</c:forTokens> -> token 문장을 구성하는 최소한의 단위 
		
	4. URL 재작성 : url
		<c:url value ="/prod/prodView.do" var="prodViewURL" >
			<c:param name="what" value="P101000001"></c:param>
		</c:url>
		${prodViewURL }
		<a href="${prodViewURL }">P101000001번 상품 상세조회</a>
	     	<%-- <%=request.getContextPath() %>/prod/prodView.do?what=P101000001 --%>
	     	
	5. 기타 : redirect, out
		<%-- <c:redirect url="/" /> --%>
		<c:set var="crawlingData" value="<table>" />
		<c:out value="${crawlingData }" escapeXml="false" />
</pre>
<table border ="1">
<c:forEach var="i" begin="2" end="9" step="1" varStatus="vs1">
	<c:if test="${vs1.count eq 3 }">
		<c:set var="trClass" value="yellow" />
	</c:if>
	<c:if test="${vs1.count ne 3 }">
		<c:set var="trClass" value="normal" />
	</c:if>
		<tr class="${trClass}">
	<c:forEach var="j" begin="2" end="9" step="1" varStatus="vs2">
		<c:choose>
			<c:when test="${vs2.first}">
				<c:set var="tdClass" value="red"></c:set>
			</c:when>
			<c:when test="${vs2.last }" >
				<c:set var="tdClass" value="blue"></c:set>
			</c:when>
			<c:otherwise>
				<c:set var="tdClass" value="normal"></c:set>
			</c:otherwise>
		</c:choose>
		<td class="${tdClass }">
			${i}*${j} = ${i*j}
		</td>
	</c:forEach>
	</tr>
</c:forEach>
</table>
</body>
</html>