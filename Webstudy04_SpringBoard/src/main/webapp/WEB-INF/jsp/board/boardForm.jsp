<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="${pageContext.request.contextPath}/resources/js/ckeditor/ckeditor.js"></script>
<%-- <form method="post" /> --%>
<form:form method="post" modelAttribute="board" enctype="multipart/form-data"><!-- controller에서 결정해준 이름 -->
  <table class="table table-bordered">
		<tr>
			<th><spring:message code="board.boTitle"/></th>
			<td>                    
				<%--				domain property명  
				 <input type="text" name="boTitle" class="form-control"
				 value="${board['boTitle'] }" />  -> 실패 했을 때 이전에 값을 초기화 시키기 위해서  value값을 정함  --%>
				 
				 <form:input path="boTitle" required="true" class="form-control"/>
				 <!-- path="board.boTitle" -->
				 
			   <%--  <span class="error">${errors['boTitle'] }</span> --%>
				<form:errors path="boTitle" element="span" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<th><spring:message code="board.boWriter"/></th>
			<td>
				<form:input path="boWriter" class="form-control"/>
				<form:errors path="boWriter" element="span" cssClass="error" ></form:errors>
			</td>
		</tr>
		<tr>
			<th><spring:message code="board.boMail"/></th>
			<td>
				<form:input path="boMail" type="email" class="form-control"/>
				<form:errors path="boMail" element="span" cssClass="error" ></form:errors>
			</td>
		</tr>
		<tr>
			<th><spring:message code="board.boIp"/></th>
			<td>
				<form:input path="boIp" readonly="true" class="form-control"
					value="${pageContext.request.remoteAddr }"
				/>
				<form:errors path="boIp" element="span" cssClass="error" ></form:errors>
			</td>
		</tr>
	
		<tr>
			<th><spring:message code="board.boPass"/></th>
			<td>
				<form:password path="boPass" class="form-control"/>
				<form:errors path="boPass" element="span" cssClass="error" ></form:errors>
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<input type="file" name="boFiles" />
				<input type="file" name="boFiles" />
				<input type="file" name="boFiles" />
			</td>
		</tr>
		<tr>
			<th><spring:message code="board.boContent"/></th>
			<td>
				<form:textarea path="boContent" class="form-control"/>
				<form:errors path="boContent" element="span" cssClass="error" ></form:errors>
			</td>
		</tr>

		<tr>
			<td colspan="2">
			     <!-- <button type="submit">전송</button> -->
				<form:button type="submit" class="btn btn-success">전송</form:button>
				<form:button type="reset" class="btn btn-danger">취소</form:button>
			</td>
		</tr>
		
  </table>
</form:form>

<script>
   CKEDITOR.replace('boContent', {
      filebrowserImageUploadUrl:"${cPath}/board/imageUpload.do?type=image"
   });
</script>
    