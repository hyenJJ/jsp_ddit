<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!doctype html>
<html>
   <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="description" content="">
      <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
      <meta name="generator" content="Hugo 0.88.1">
      <meta name="theme-color" content="#7952b3">

      <title>Dashboard Template · Bootstrap v5.1</title>
      
      <jsp:include page="/includee/preScript.jsp" />
      
      <style>
         .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
         }
         
         @media ( min-width : 768px) {
            .bd-placeholder-img-lg {
               font-size: 3.5rem;
            }
         }
      </style>
      
      <!-- Custom styles for this template -->
      <link href="<%=request.getContextPath()%>/resources/css/dashboard.css" rel="stylesheet">
   </head>
   <body class="d-flex flex-column vh-100">
      <header class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
         <jsp:include page="/includee/headerMenu.jsp" />
      </header>
   
      <div class="container-fluid">
         <div class="row">
         
            <jsp:include page="/includee/leftMenu.jsp" />
            
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
               <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
               <!-- Main Content Area start -->
                  <div class="border border-primary">
                                    <!-- scope안에서꺼내는 형태로 나와야 함
                                    쌍따옴표, 홀따옴표로 써 주기..그래야 에러 안 남 -->
<%--                      <%
                     String cp = (String)request.getAttribute("commandPage");
                     %>
                     <jsp:include page='<%=cp %>' /> --%>
                     INDEX
                  </div>
               <!-- Main Content Area end -->
               </div>
            </main>
         </div>
      </div>
   
      <footer class="footer mt-auto py-3 bg-dark col-md-9 ms-sm-auto col-lg-10">
         <jsp:include page="/includee/footer.jsp" />
      </footer>
      <jsp:include page="/includee/postScript.jsp" />
   </body>
</html>