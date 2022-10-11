<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap w-100 p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Company403</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <ul class="nav px-3 col">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="<%=request.getContextPath() %>/member/memberList.do">회원관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="<%=request.getContextPath()%>/prod/prodList.do">상품관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#">거래처관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#">게시판</a>
    </li>
  </ul>
  <ul class="nav px-3 col-2">
  	<c:if test="${empty sessionScope.authMember }" >
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/login/loginForm.jsp">Sign in</a>
    </li>
    </c:if>
  	<c:if test="${not empty sessionScope.authMember }" >
	    <form name="logoutForm" method="post" action="${pageContext.request.contextPath }/login/logout.do"></form>
	    <li class="nav-item text-nowrap">
	      <a class="nav-link" href="#" onclick="document.logoutForm.submit(); return false;">Sign out</a>
	    </li>
    </c:if>
  </ul>
</nav>