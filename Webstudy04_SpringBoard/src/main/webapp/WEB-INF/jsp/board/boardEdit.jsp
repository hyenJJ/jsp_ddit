<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script src="${pageContext.request.contextPath}/resources/js/ckeditor/ckeditor.js"></script>

<form:form id="boardForm" method="post" modelAttribute="board" enctype="multipart/form-data"><!-- controller에서 결정해준 이름 -->
	<form:hidden path="boNo"/>                                <!-- 이제부터 전송되는 타입은 part 타입 -->
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
			<th>기존 첨부파일</th>
			<td>
				<c:forEach items="${board.attatchList }" var="attatch">
					<span class="fileArea">	
						${attatch.attFilename }
						<span class="btn btn-danger delBtn" data-attNo-no="${attatch.attNo }" >삭제</span><!-- 몇번 파일을 삭제 할 것인지 남겨야함  -->
					</span>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>신규 첨부파일</th>
			<td>
				<input type="file" name="boFiles"/>
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
<script>
   CKEDITOR.replace('boContent');
   let boardForm = $("#boardForm");
   $(".delBtn").on("click", function(event){
      let attNo = $(this).data("attNo");
      let newInput = $("<input>").attr({
         type:"text"
         , name:"delAttNos"
      }).val(attNo);
      boardForm.append(newInput);
      $(this).parents("span.fileArea").hide();
   });
</script>