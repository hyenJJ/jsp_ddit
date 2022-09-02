<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script> 
                                     <!-- 주소를 통해서 body를 받아오겠다 -->     
</head>
<body>
<form id="factorialForm">
   <input type="number" name="operand"  value="${number }"/>
</form>                                                 <!-- submit 이벤트를 발생 시키지 않는다 -->
<pre>
1. 반복문 : scriptlet
2. 재귀 호출 : declaration(선언부)
3. 피연산자 선택 UI
4. Model1 -> Model2
5. 비동기.


<%-- ${number }! = ${result} --%> <!-- jsp 주석은 server side 방식 -->

<span id="resultArea"></span>


</pre>
<script type="text/javascript">
	const PATTERN = "%O! = %R";
	// : 모든 입력 태그 중에 name 갖고있는 태그들
	$(":input[name]").on("change", function(){
		$(this).parents("form:first").submit();
		//$(this) => jquery 객체화 
		//parents("form:first")
	});
 /* = $("#factorialForm").on("submit",function(){})  */
    $(document).on("submit","#factorialForm",function(event){ 
   //document 안에서 #factorialForm일때만 실행을 하게 함
   event.preventDefault();
   let url = this.action; // this = #factorialForm
   let method = this.method;
   let data = $(this).serialize();  //query string
   
   $.ajax({

	url : url, //어디로
	method : method, //어떻게
	data : data, //무엇을
	dataType : "json",
	success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함
/*        resp.operand;
	   resp.result; */
	   $("#resultArea")
	         .html(
	        		 PATTERN.replace("%O", resp.operand)
	                         .replace("%R", resp.result)
	               );
	},
	error : function() {

		console.log(errorResp.status);
	}

});
   
   return false;
    	
    });
</script>
</body>
</html>