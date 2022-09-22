<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>pageContext (pageContext)</h4>
<pre>
    : 가장 먼저 생성되는 기본 객체
    : 나머지 기본 객체 참조 소유.
    
    1. 흐름 제어 (flow Control, request dispatch)
    <%--
         pageContext.include("/08/sessionTimer.jsp");
//          request.getRequestDispatcher("/08/sessionTimer.jsp").include(request,response);
   --%>
    2.에러 데이터 확보(에러 처리 페이지)
    <%--
       if(1==1)
    	   throw new ArithmeticException("강제 0으로 나눴음. 예외 발생...");
    --%>
    
    3. 속성 데이터 관리
         <%--
            //request.setAttribute(arg0, arg1) 명확한 생명주기가 정해져 있음
            //session.setAttribute(arg0, arg1)
            //application.setAttribute(name, object)   모두 map의 형태 
            //pageContext.setAttribute(name, value)
            pageContext.setAttribute(name, value, PageContext.SESSION_SCOPE)
            
         --%>
    *** Scope : 해당 저장 영역을 관리하는 기본 객체와 생명주가귀가 동일한 저장 공간.
    attribut : scope 을 통해 공유되는 데이터.
    1. page scope
    2. request scope
    3. session scope
    4. application.scope
    setAttribut(name,vlaur), getArribute(name), removeAttribute
    =================================
    <%
      pageContext.setAttribute("pageAttr", "페이지 속성");
      request.setAttribute("requestAttr", "요청 속성");
      session.setAttribute("sessionAttr", "세션 속성");
      application.setAttribute("applicationAttr", "어플리케이션 속성");
      
//      pageContext.include("/09/attributeView.jsp");

        response.sendRedirect(request.getContextPath()+"/09/attributeView.jsp");
    

    %>
    <a href="<%=request.getContextPath() %>/09/attributeView.jsp">어트리뷰트 뷰로 이동</a> 
    <!-- attributeView.jsp  - 상대 경로  -->
</pre>
</body>
</html>