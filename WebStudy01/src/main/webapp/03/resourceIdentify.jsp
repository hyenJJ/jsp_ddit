<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.net.URL"%>
<%@page import="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/resourceIdentify.jsp</title>
</head>
<body>
<h4>자원의 종류와 식별 방법</h4>
<pre>
   자원의 위치와 접근하기 위한 경로 표기 방법에 따른 분류
   1. File System Resource : 파일시스템의 특정 드라이브 아래. 파일시스템 절대 경로 형태(드라이 루트부터 시작)
        D:\contents\images\cat1.jpg  (논리주소)
        
   2. Class path Resource : classpath 아래. classpath이후의 qualified name 형태 접근.
      \kr\or\ddit\images\cat2.png  (논리주소)
      
      <%
          Date today = new Date();           //jsp 
//          ClassLoader classLoader = ClassLoader.getSystemClassLoader();
          ClassLoader classLoader = DescriptionServlet.class.getClassLoader();
//          URL cpResource = classLoader.getResource("kr/or/ddit/images/cat2.png");
          URL cpResource = DescriptionServlet.class.getResource("../images/cat2.png");
          String cpPath = cpResource.toURI().getPath();
      %>
      
      --> <%=cpPath %>
       
   3. Web Resource : web, URL 형태 접근.
   https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png
   
   \WebStudy01\resources\image\cat3.jpg
   <%
     URL webResource = application.getResource("/resources/images/cat3.png");
   %>
   
   --> <%=webResource %>  
 
  *** 웹상의 자원을 식별하기 위한 주소 체계
  URI : Unified Resource Identifier(공용 자원을 식별)
  URL : Unified Resource Locator(공용 자원의 *주소*를 통해서 식별) /uri의 방법중 하나/
  URN : Unified Resource Naming(공용 자원의 *명칭*을 통해서 식별) ex 책 이름으로 검색
  URC : Unified Resource Contents ex 책 저자나 출판사로 검색
  
  URL
  절대 경로 -> protocol://IP[domain]:port/contextName/depth..../resourceName
           <!-- / => 시작점 -->
  client side : <%=request.getContextPath()%>/WebStudy01/resources/images/cat3.png 
  server side : context path 이후의 경로 /resources/images/cat3.png
</pre>
<img src="/resources/images/cat3.png"/>
<img src="/WebStudy01/resources/images/cat3.png"/>
<img src="//localhost/WebStudy01/resources/images/cat3.png"/>
          <!-- protocol:"http:" (생략됨) 
              가지고 있는 정보는 생략이 가능하다.
          -->
<img src="/localhost/WebStudy01/resources/images/cat3.png"/>

<img src="../resources/images/cat3.png"/>
     <!--현재 위치 = 브라우저 주소 / WebStudy01 -->
</body>
</html>