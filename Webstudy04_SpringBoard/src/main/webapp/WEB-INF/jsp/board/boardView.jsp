
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

	<!-- 	필수파라미터가 빠졌으면 400에러
	존재하지않는 데이터를 조회하면 runtimeexception -->
	
	
<table class="table table-bordered">
   <tr>
      <th><spring:message code="board.boNo"/></th>
      <td>${board.boNo }</td>
   </tr>
   <tr>
      <th><spring:message code="board.boTitle"/></th>
      <td>${board.boTitle }</td>
   </tr>
   <tr>
      <th><spring:message code="board.boWriter"/></th>
      <td>${board.boWriter }</td>
   </tr>
   <tr>
      <th><spring:message code="board.boIp"/></th>
      <td>${board.boIp }</td>
   </tr>
   <tr>
      <th><spring:message code="board.boMail"/></th>
      <td>${board.boMail }</td>
   </tr>
   <tr>
      <th><spring:message code="board.boDate"/></th>
      <td>${board.boDate }</td>
   </tr>
   <tr>
      <th><spring:message code="board.boHit"/></th>
      <td>${board.boHit }</td>
   </tr>
   <tr>
      <th><spring:message code="board.boRec"/></th>
      <td>
      		<span id="recArea">${board.boRec }</span>
      		<span class="btn btn-success" id="recBtn" data-target="#recArea">추천</span>
      </td>
   </tr>
   <tr>
      <th><spring:message code="board.boParent"/></th>
      <td>${board.boParent }</td>
   </tr>
   <tr>
      <th><spring:message code="board.boFiles"/></th>
      <td>
         <c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
            ${attatch.attFilename }(${attatch.attFancysize })
            ${not vs.last?"|":"" }
         </c:forEach>
      </td>
   </tr>
   <tr>
      <th><spring:message code="board.boContent"/></th>
      <td>${board.boContent }</td>
   </tr>
		<tr>
			<td colspan="2">
				<c:url value="/board/boardUpdate.do" var="updateURL">
					<c:param name="what" value="${board.boNo }" />
				</c:url>
				<a href="${updateURL }" class="btn btn-danger" >글 수정</a>
				<a id = "deleteBtn" class="btn btn-danger" >글 삭제</a>
			</td>
			
		</tr>
	</table>
	
<!-- TestDrivenDevelopment vs EventDrivenDevelopment -->
<c:set var="cPath" value="${pageContext.request.contextPath }" scope="application" />
<form name="deleteForm" method="post" action="${cPath}/board/boardDelete.do">
	<input type="hidden" name="boNo" value="${board.boNo }"/>
	<input type="hidden" name="boPass"  />
</form>
<script>
	$("#deleteBtn").on("click",function(event){
		
		event.preventDefaulte;   // a태그를 통해서 가면 get 방식이므로 post로 가기 위해 a 태크의 이벤트를 중단 
		let password = prompt("비밀번호"); 
		if(password){
			document.deleteForm.boPass.value=password;
			$(document.deleteForm).submit();
			document.deleteForm.boPass.value=""; // 뒤로 가기 했을 때 입력했던 비번이 남아있음 안되므로 리셋
												 // document.deleteForm.boPass.reset();
		}
		return false;
		
	});
	
	$("#recBtn").on("click",function(event){
		
		//원래 span tag는 click event의 대상이 아니다 
		// 그래서 고유의 action이 없다 -> 멈출 필요없이 ajax 바로 쓸 수 있음
		let selector = $(this).data("target");
		
		
		$.ajax({

			url : "${cPath}/board/boardRecommend.do",   //어디로
			data : {
				what:${board.boNo}
			},
			dataType : "json",
			success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함
				if(resp.success) {		 //success라는 property가 있어야함
					$(selector).html(resp.boRec);
				}     

			},
			error : function(errorResp) {

				console.log(errorResp.status);
			}

		});
	})

</script>