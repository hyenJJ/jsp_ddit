<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<h4>Welcome Page~</h4>
<c:if test="${not empty sessionScope.authMember }">
   <a href="<c:url value='/mypage.do'/>">${authMember.memName }[${authMember.memRole}]</a>님 로그인 상태.
   <pre>
   Principal : <%=request.getUserPrincipal().getName() %>
   		${pageContext.request.userPrincipal.realUser.memRole }
   Principal type : <%=request.getUserPrincipal().getClass() %>
   </pre>
</c:if>
request type : <%=request.getClass()%>