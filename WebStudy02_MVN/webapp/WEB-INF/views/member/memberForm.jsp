<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberVO member = (MemberVO) request.getAttribute("member");
if (member == null)
	member = new MemberVO();
Map<String, String> errors = (Map) request.getAttribute("errors");
if (errors == null)
	errors = new HashMap<>();
%>

<h4>가입양식</h4>
<form action="<%=request.getContextPath()%>/member/memberInsert.do"
	method="post">
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td>
			    <input type="text" name="memId" class="form-control"
				required value="${member.memId }" />
				<span class="error">${errors.memId}</span>
			</td>
		</tr>
		<tr>
			<th>MEM_PASS</th>
			<td><input type="text" name="memPass" class="form-control"
				required value="<%=member.getMemPass()%>" /><span class="error"><%=errors.get("memPass")%></span></td>
		</tr>
		<tr>
			<th>MEM_NAME</th>
			<td><input type="text" name="memName" class="form-control"
				required value="<%=member.getMemName()%>" /><span class="error"><%=errors.get("memName")%></span></td>
		</tr>
		<tr>
			<th>MEM_REGNO1</th>
			<td><input type="text" name="memRegno1" class="form-control"
				value="<%=member.getMemRegno1()%>" /><span class="error"><%=errors.get("memRegno1")%></span></td>
		</tr>
		<tr>
			<th>MEM_REGNO2</th>
			<td><input type="text" name="memRegno2" class="form-control"
				value="<%=member.getMemRegno2()%>" /><span class="error"><%=errors.get("memRegno2")%></span></td>
		</tr>
		<tr>
			<th>MEM_BIR</th>
			<td><input type="date" name="memBir" class="form-control"
				value="<%=member.getMemBir()%>" /><span class="error"><%=errors.get("memBir")%></span></td>
		</tr>
		<tr>
			<th>MEM_ZIP</th>
			<td><input type="text" name="memZip" class="form-control editable"
				required value="<%=member.getMemZip()%>" /><span class="error"><%=errors.get("memZip")%></span></td>
		</tr>
		<tr>
			<th>MEM_ADD1</th>
			<td><input type="text" name="memAdd1" class="form-control editable"
				required value="<%=member.getMemAdd1()%>" /><span class="error"><%=errors.get("memAdd1")%></span></td>
		</tr>
		<tr>
			<th>MEM_ADD2</th>
			<td><input type="text" name="memAdd2" class="form-control editable"
				required value="<%=member.getMemAdd2()%>" /><span class="error"><%=errors.get("memAdd2")%></span></td>
		</tr>
		<tr>
			<th>MEM_HOMETEL</th>
			<td><input type="text" name="memHometel" class="form-control editable"
				value="<%=member.getMemHometel()%>" /><span class="error"><%=errors.get("memHometel")%></span></td>
		</tr>
		<tr>
			<th>MEM_COMTEL</th>
			<td><input type="text" name="memComtel" class="form-control editable"
				value="<%=member.getMemComtel()%>" /><span class="error"><%=errors.get("memComtel")%></span></td>
		</tr>
		<tr>
			<th>MEM_HP</th>
			<td><input type="text" name="memHp" class="form-control editable"
				value="<%=member.getMemHp()%>" /><span class="error"><%=errors.get("memHp")%></span></td>
		</tr>
		<tr>
			<th>MEM_MAIL</th>
			<td><input type="text" name="memMail" class="form-control" editable
				required value="<%=member.getMemMail()%>" /><span class="error"><%=errors.get("memMail")%></span></td>
		</tr>
		<tr>
			<th>MEM_JOB</th>
			<td><input type="text" name="memJob" class="form-control editable"
				value="<%=member.getMemJob()%>" /><span class="error"><%=errors.get("memJob")%></span></td>
		</tr>
		<tr>
			<th>MEM_LIKE</th>
			<td><input type="text" name="memLike" class="form-control editable"
				value="<%=member.getMemLike()%>" /><span class="error"><%=errors.get("memLike")%></span></td>
		</tr>
		<tr>
			<th>MEM_MEMORIAL</th>
			<td><input type="text" name="memMemorial" class="form-control editable"
				value="<%=member.getMemMemorial()%>" /><span class="error"><%=errors.get("memMemorial")%></span></td>
		</tr>
		<tr>
			<th>MEM_MEMORIALDAY</th>
			<td><input type="date" name="memMemorialday editable"
				class="form-control" value="<%=member.getMemMemorialday()%>" /><span
				class="error"><%=errors.get("memMemorialday")%></span></td>
		</tr>
		<tr>
		    <td colspan = "2">
		      <input type="reset" value="취소" class="btn btn-warning"/>
		      <input type="submit" value="저장" class="btn btn-primary"/>
		    </td>
		</tr>
	</table>

</form>
<%
    if("UPDATE".equals(request.getAttribute("command"))){
    
    
%>
<script>
    //editable클래스가 아닌input 태그      prop는 속성 타입이 boolean이면 그대로 유지할수 있음
    $(":input:not(.editable)").prop("readyonly",true);

</script>
<%
    }
%>