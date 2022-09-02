<%@page import="kr.or.ddit.enumpkg.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/userAgent.jsp</title>
</head>
<body>
<h4>UserAgent</h4>
<pre>


<%
//enum : 상수만을 가지고 있는 객체

/*     String userBrowser = request.getHeader("User-Agent" );
    String agent = userBrowser.toUpperCase();
    
  
   BrowserType searched = BrowserType.OTHER;
    
    
  //BrowserType.OTHER.name(); 상수의 이름을 문자열로 받아올수있음
   for(BrowserType tmp :  BrowserType.values()){
		if(agent.indexOf(tmp.name())>-1){
			searched = tmp;
    		break;
	   
   } */
		
    String browserName = BrowserType.searchBrowserName(request);
    out.println(
         String.format("당신의 브라우저는'%s' 입니다.",browserName)
    		
    );
    
    
    
    
  
    
           //원래 map은 순서가 없지만 LinkedHashMap =>entry 간 순서를 유지
/*  
    Map<String, String> agentMap = new LinkedHashMap<>();
    agentMap.put("TRIDENT", "IE");
    agentMap.put("EDG", "Edg");
    agentMap.put("CHROME", "Chrome");
    agentMap.put("SAFARI", "Safari");
    agentMap.put("OTHER", "기타");
    
    String browserName = agentMap.get("other");
    // map 요소 = entry
    for(Entry<String,String> entry : agentMap.entrySet()){
    	
    	if(agent.indexOf(entry.getKey())>-1){
    		browserName = entry.getValue();
    		break;
    	}
    }	
    
    */
    
    

 

%>




</pre>
</body>
</html>