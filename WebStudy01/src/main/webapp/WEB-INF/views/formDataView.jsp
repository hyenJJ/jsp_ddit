<%@page import="kr.or.ddit.vo.FormDataVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   FormDataVO vo = (FormDataVO)request.getAttribute("vo");
%>
<table>
    <tr>
       <th>paramInt1</th> 
       <td><%=vo.getParamIpt1() %></td>
    </tr>
</table>
</body>
</html>