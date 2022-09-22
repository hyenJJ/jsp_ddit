<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<form action="<%=request.getContextPath() %>/bts/getContent">
	<select name="member" onchange="$(this.form).submit();">  <!-- this.form.submit(); - submit 이벤트 발생 x
	                                                               -submit 이벤트 발생 O
	                                                                $(this.form).submit(); 
	                                                                this.form.requestsubmit();
	                                                             //https://developer.mozilla.org/en-US/docs/Web/API/HTMLFormElement/submit
	                                                           -->

	     <%                        
	        Map<String, String[]> btsDB = (Map) application.getAttribute("btsDB");
	        StringBuffer options = new StringBuffer();
	        btsDB.forEach((k,v) ->{ 
	        	options.append(
	               String.format("<option value='%s'>%s</option>\n", k, v[0])
	           );
	        	
	        });
	        
	        out.print(options);
	        
	        
	     %>

	</select>

</form>

<script>
     let area = $('#area');
     $(document).on('submit','form:first',function(){
    	       event.preventDefault();   
    	       let form = this;  // = form 
    	       let url = this.action;
    	       let method = this.method;
    	       let data = $(this).serialize();  
    	      //=ajax form 
    	      
			 $.ajax({
			
				url : url, //어디로
				method : method, //어떻게
				data : data, //무엇을
				dataType : "html",
				success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함
			         $(form).after(resp);
				    
				}
	
			});
    	 return false;
    	 
     })
</script>
</body>
</html>
<!-- 코드로 전송역할 만들기  -->
