<%@page import="java.util.Collections"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
	<%
		String[] array = new String[]{"value1", "value2"};
		List<String> list = Arrays.asList(array);
		Set<String> set = new HashSet(list);
		Map<String,Object> map = Collections.singletonMap("key-1", "value1");
		pageContext.setAttribute("elArray", array);
		pageContext.setAttribute("elList", list);
		pageContext.setAttribute("elSet", set);
		pageContext.setAttribute("elMap", map);
		
	%>
	${elArray[1] }, <%=array[1] %> , <%=pageContext.getAttribute("elArray") %>
	${elArray[4] },<%--  <%=array[4] %> --%>
	${elList[1] }, <%=list.get(1) %> ->EL 언어는 메소드를 쓰지않기 때문에 () 안됨
	${elList[4] }, <%-- <%=list.get(4) %> --%> ->예외가 발생하지 않고 공백으로 바뀌기 때문에 오류를 잡을 때 한계가 있음
	${elSet }, <%=set %>
	${elMap.key1 }, ${elMap['key1']} ,<%=map.get("key1") %>
	${elMap.key-1 }, ${elMap['key-1'] } -> [' '] 여기는 문자열로 보기 때문에 키값이 나옴 이구조로 쓰는게 훨씬 안전
</pre>
</body>
</html>