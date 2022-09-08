<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
<!-- 이항 연산자로 4칙 연산 처리. -->
Send Data Type
<input type="radio" name="sendDataType" value="json" checked/>JSON
<input type="radio" name="sendDataType" value="parameter"/>Parameter
<hr/>
Receive Data Type
<input type="radio" name="receiveDataType" value="xml"/>XML
<input type="radio" name="receiveDataType" value="json" checked/>JSON

<form action="<%=request.getContextPath()%>/calculate" name="calForm" method="post">
	<input type="number" name="leftOp" />
	<select name="operator">
		<%
			for(OperatorType single :OperatorType.values()){
				%>
					<option value="<%=single.name()%>"><%=single.getSign() %></option>
				<%
			}
		%>
	</select>
	<input type="number" name="rightOp" />
	<input type="submit" value="=">
</form>

<script type="text/javascript">

		let makeSendData = function(settings){
			let inputs = calForm.find(":input[name]"); // 폼 태그 안에 자식 태그 중에 input 태그 중에서 name 있는 거만 찾는다
			let data = {};
			
			$.each(inputs, function(index, input){
				let name = this.name;
				let value = $(this).val();
				let type = this.type;
//				data.leftobj = 23;
//				data['leftObj']=23;
                if(type=='number'){
				data[name] = parseInt(value);
                	
                }else{
				data[name] = value;
                	
                }
			});
			console.log(data);
			let sendDataType = $("[name=sendDataType]:checked").val();
			
			if(sendDataType == 'json'){
				settings.data = JSON.stringify(data); // 마샬링
				settings.contentType = "application/json;charset=UTF-8";
				
			}else{
				settings.data = data;
			}
			
			
		}
		
		
		let makeReceiveDataType = function(settings){
			let receiveDataType = $("[name=receiveDataType]:checked").val();
			let dataType= receiveDataType;
			settings.dataType = dataType;
		}

    	let calForm = $(document.calForm).on("submit", function(event){
		event.preventDefault();
		
		
		
		let url = this.action;
		let method = this.method;
// 		let data = $(this).serialize(); // QueryString

		
		let settings ={
				
					url : url,
					method : method,

					//dataType : radioVal //"xml/json", // request (Accept) | response (Content-Type)
					success : function(resp) {
						console.log(resp);
						calForm.after(resp.expression);
						
					},
					error : function(errorResp) {
						alert(errorResp.status);
					}
				
				
			};	
		makeSendData(settings);
		
		makeReceiveDataType(settings);
		
		
		$.ajax(settings);
		return false;
	});
</script>
</body>
</html>


















