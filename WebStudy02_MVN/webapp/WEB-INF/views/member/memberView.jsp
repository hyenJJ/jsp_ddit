    <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<table class="table table-bordered">
	<tr>
		<th>회원아이디</th>
		<td>${member['memId'] }</td>
	</tr>
	<tr>
		<th>MEM_PASS</th>
		<td>${member['memPass'] }</td>
	</tr>
	<tr>
		<th>MEM_NAME</th>
		<td>${member['memName'] }</td>
	</tr>
	<tr>
		<th>MEM_REGNO1</th>
		<td>${member['memRegno1'] }</td>
	</tr>
	<tr>
		<th>회원 이미지</th>
		<td>
			<img src="data:image/*;base64,${member.base64Img }" />
			<!-- http -> 프로토콜 = 스키마 
			- 데이터 스키마 
			data:[<media type>][;base64],<data>
			[base64] -> 이진 데이터를 문자열로 바꿔주기위한 encoding 방식
					 -> 대괄호라 생략 가능 
			-->
		</td>
	</tr>
	<tr>
		<th>MEM_REGNO2</th>
		<td>${member['memRegno2'] }</td>
	</tr>
	<tr>
		<th>MEM_BIR</th>
		<td>${member['memBir'] }</td>
	</tr>
	<tr>
		<th>MEM_ZIP</th>
		<td>${member['memZip'] }</td>
	</tr>
	<tr>
		<th>MEM_ADD1</th>
		<td>${member['memAdd1'] }</td>
	</tr>
	<tr>
		<th>MEM_ADD2</th>
		<td>${member['memAdd2'] }</td>
	</tr>
	<tr>
		<th>MEM_HOMETEL</th>
		<td>${member['memHometel'] }</td>
	</tr>
	<tr>
		<th>MEM_COMTEL</th>
		<td>${member['memComtel'] }</td>
	</tr>
	<tr>
		<th>MEM_HP</th>
		<td>${member['memHp'] }</td>
	</tr>
	<tr>
		<th>MEM_MAIL</th>
		<td>${member['memMail'] }</td>
	</tr>
	<tr>
		<th>MEM_JOB</th>
		<td>${member['memJob'] }</td>
	</tr>
	<tr>
		<th>MEM_LIKE</th>
		<td>${member['memLike'] }</td>
	</tr>
	<tr>
		<th>MEM_MEMORIAL</th>
		<td>${member['memMemorial'] }</td>
	</tr>
	<tr>
		<th>MEM_MEMORIALDAY</th>
		<td>${member['memMemorialday'] }</td>
	</tr>
	<tr>
		<th>MEM_MILEAGE</th>
		<td>${member['memMileage'] }</td>
	</tr>
	<tr>
		<th>MEM_DELETE</th>
		<td>${member['memDelete'] }</td>
	</tr>
	<tr>
		<td colspan="2">
			<a class="btn btn-secondary" href="#" onclick="history.back();">뒤로가기</a>
		</td>
	</tr>
	<tr>
		<th>구매기록</th>
		<td>
			<table class="table table-bordered">
				<c:set value="${member.prodList }" var="prodList" />
				<c:choose>
					<c:when test="${not empty prodList }">
						<c:forEach items="${prodList }" var="prod">
							<c:url value="/prod/prodView.do" var="prodViewURL">
								<c:param name="what" value="${prod.prodId }" />
							</c:url>
							<tr>
								<td><a href="${prodViewURL }">${prod.prodName }</a></td>
								<td>${prod.lprodNm }</td>
								<td>${prod.buyer.buyerName }</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="4">구매기록 없음.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</td>
	</tr>
</table>
















    
