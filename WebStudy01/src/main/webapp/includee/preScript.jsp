<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/js/bootstrap-5.1.3-dist/css/bootstrap.min.css">    
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>



<script type = "text/javascript">
<%
	String message =(String)request.getAttribute("message");
    
	if(message!=null)
		message = (String)session.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
   %>
   alert("<%=message %>");
   <%
    session.removeAttribute("message"); // flash attribute    
    
 }
 %>
//모든 페이지에선 template
</script>