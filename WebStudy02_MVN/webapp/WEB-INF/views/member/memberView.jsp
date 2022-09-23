<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- //스키마를 활용하기 위해서 딕셔너리를 어떻게 사용하는지 -->
 

<%
   MemberVO member = (MemberVO)request.getAttribute("member");

%>
	   
<table class="table table-bordered">   
	<tr>
		<th>회원아이디</th>
		<td><%=member.getMemId()%></td>
	</tr>
	<tr>
		<th>MEM_PASS</th>
		<td><%=member.getMemPass()%></td>
	</tr>
	<tr>
		<th>MEM_NAME</th>
		<td><%=member.getMemName()%></td>
	</tr>
	<tr>
		<th>MEM_REGNO1</th>
		<td><%=member.getMemRegno1()%></td>
	</tr>
	<tr>
		<th>MEM_REGNO2</th>
		<td><%=member.getMemRegno2()%></td>
	</tr>
	<tr>
		<th>MEM_BIR</th>
		<td><%=member.getMemBir()%></td>
	</tr>
	<tr>
		<th>MEM_ZIP</th>
		<td><%=member.getMemZip()%></td>
	</tr>
	<tr>
		<th>MEM_ADD1</th>
		<td><%=member.getMemAdd1()%></td>
	</tr>
	<tr>
		<th>MEM_ADD2</th>
		<td><%=member.getMemAdd2()%></td>
	</tr>
	<tr>
		<th>MEM_HOMETEL</th>
		<td><%=member.getMemHometel()%></td>
	</tr>
	<tr>
		<th>MEM_COMTEL</th>
		<td><%=member.getMemComtel()%></td>
	</tr>
	<tr>
		<th>MEM_HP</th>
		<td><%=member.getMemHp()%></td>
	</tr>
	<tr>
		<th>MEM_MAIL</th>
		<td><%=member.getMemMail()%></td>
	</tr>
	<tr>
		<th>MEM_JOB</th>
		<td><%=member.getMemJob()%></td>
	</tr>
	<tr>
		<th>MEM_LIKE</th>
		<td><%=member.getMemLike()%></td>
	</tr>
	<tr>
		<th>MEM_MEMORIAL</th>
		<td><%=member.getMemMemorial()%></td>
	</tr>
	<tr>
		<th>MEM_MEMORIALDAY</th>
		<td><%=member.getMemMemorialday() %></td>
	</tr>
	<tr>
		<th>MEM_MILEAGE</th>
		<td><%=member.getMemMileage() %></td>
	</tr>
	<tr>
		<th>MEM_DELETE</th>
		<td><%=member.getMemDelete() %></td>
	</tr>
</table>