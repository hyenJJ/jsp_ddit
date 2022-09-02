<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>request : HttpServletRequest</h4>
<pre>
   Http 에 의해 패키징되는 요청의 구조.
   
   1. Request Line : URL HttpMethod(Request Method) Protocol/version
                    
       Http Method : 요청의 목적(동사, 행위) 이면서 동시에 패키징 구조 표현.
       GET  (R) : 기본 METHOD    ex) src, href..
       POST (C)
       PUT/PATCH (U)
       DELETE (D)
       OPTIONS : preFlight 요청 으로 본 요청의 method 지원여부를 확인.
       HEADER : 응답의 body가 없는 상태로 요청할 때 
       TRACE : 서버를 디버깅 할 때 사용 (제한적으로 사용)   
       
       <%=request.getRequestURI() %>          
       <%=request.getMethod() %>          
       <%=request.getProtocol() %>          
            <!--  프로토콜 버전 있음 -->
  <!-- 편지를 보내는 목적 
       get : 서버에 있는 자원을 클라이언트가 get 하려는 목적
       post : 클라이언트가 서버에게 데이터를 보내려는 목적
   
       rest 아키텍처
       URI (명사) 와 method(동사) 로 나누어서 표현
		요청 URL: http://localhost/WebStudy01/04/requestDesc.jsp
		요청 메서드: GET
   -->
                                        
   2. Request Header : Metadata (name/value - String)
     <!-- 부가정보 = 있어도 되고 없어도 됨 
          발신자의 정보 
     -->
   3. Request Body(Content(=Message) Body , only POST) : 전송 데이터 자체 영역.
   
           parameter : 문자열로 전송 
           
           Part : 2진 데이터 전송 (multipart -> 동시에 여러 형태의 데이터 전송)
           
           <%
              if("POST".equals(request.getMethod().toUpperCase())){
            	  out.println(request.getInputStream().available());
            	                       // body에 올 데이터의 길이가 얼마나 되는지 
            	  out.println(request.getContentLength());
            	  out.println(request.getContentType());
            	                       
              }
           %>
           
        
                                  
</pre>
<form method="post" enctype="multipart/form-data">
    <input type="text" name="param" />
    <input type="file" name="filePart" />
    <input type="submit" value="전송" />
</form>

<h4>요청 헤더들</h4>

<table border = "1">
    <thead>
        <tr>
          <th>헤더이름</th>
          <th>헤더값</th>
        </tr>
    </thead>
    <tbody>
<!--    <tr>
           <td>Accept</td>
           <td>text/html</td>
        </tr>   -->
        <%
            StringBuffer trTag = new StringBuffer();
            String pattern = "<tr><td>%s</td><td>%s</td></tr>";
            
            
           // collection view            
            Enumeration<String> en = request.getHeaderNames();
        
        
            while(en.hasMoreElements()){
        	
        	String headerName = en.nextElement();
        	       	        	
        	String headerValue = request.getHeader(headerName);
        	
        	trTag.append(
        	   String.format(pattern, headerName, headerValue)		
        	);
        
         }
        	
         out.println(trTag);   
        %>

    </tbody>
</table>

</body>
</html>