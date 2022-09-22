<%@page import="java.util.Date"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.fasterxml.jackson.databind.PropertyName"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- Model1 구조를 이용하여,
'a001' 사용자의 이름을 조회하라. -->



<%
     
        String name = null;


		String sql = "SELECT MEM_NAME FROM MEMBER WHERE MEM_ID = ?";
       
       long currTime = System.currentTimeMillis();
       long endTime = 0;
       double runTime = 0;
     //  for(int i =1 ;i<=100; i++){
	   try(
		  Connection oracleConn = ConnectionFactory.getConnection();
	      PreparedStatement oracleStmt = oracleConn.prepareStatement(sql);
	   ) {
		   
		  oracleStmt.setString(1, "a001");
		  ResultSet rs = oracleStmt.executeQuery();
		  if(rs.next()){
			   name = rs.getString( "MEM_NAME" );
			
		
		     }
          } 
	   //       }  for end
	   endTime = System.currentTimeMillis();
	   runTime = endTime - currTime;

%>
<h4><%=name %></h4>
<h4><%=runTime %>ms</h4>

<h4>전체 소요 시간(response time) 확인</h4>
<h4>한번 연결 수립하고, 한번 쿼리 실행, 한번 출력 : 8 ms </h4>
<h4>백번 연결 수립하고, 백번 쿼리 실행, 백번 출력 : 700 ms</h4>
<h4>한번 연결 수립하고, 백번 쿼리 실행, 백번 출력 : 11 ms</h4>

<!-- Listener refused the connection with the following error: 
     데이터 감당을 못하고 과부하 걸림 
     이전에 실행하던게 닫기도 전에 또 실행을 반복했기 때문에 발생 
     
     
     해결해야될 문제
     아무리 많이 반복해도 일정시간에 맞춰서 실행해야함
     전체 소요시간을 줄여야함
     
    
     -->
     
<hr/>
<h4> connection pooling 이후</h4>
<h4>한번 연결 수립하고, 한번 쿼리 실행, 한번 출력 : ? ms </h4>
<h4>백번 연결 수립하고, 백번 쿼리 실행, 백번 출력 : 9 ms</h4>
<h4>한번 연결 수립하고, 백번 쿼리 실행, 백번 출력 : 5 ms</h4>    
 <!--  cnnection이 끊어지면 pool을 반납 -> 재사용  -->
</body>
</html>