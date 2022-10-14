<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<form:form method="post" modelAttribute="board"><!-- controller에서 결정해준 이름 -->
	<form:hidden path="boNo"/>
  <table class="table table-bordered">
		<tr>
			<th><spring:message code="board.boTitle"/></th>
			<td>                    			 
				 <form:input path="boTitle" required="true" class="form-control"/>
				 <!-- path="board.boTitle" -->				 

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
				<form:input path="boMail" type="email" class="form-control" />
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
    