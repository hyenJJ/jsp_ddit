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
<select id="propSel" size="10"></select>
<table>
	<thead>
		<tr>
			<th>프로퍼티명</th>
			<th>프로퍼티값</th>
			<th>비고</th>
		</tr>
	</thead>
	<tbody id="dataArea">
	
	</tbody>
</table>
<form id="propForm" action="<%=request.getContextPath() %>/property" method="post">
	<input type="text" name="propertyName" placeholder="포르퍼티명"/>
	<input type="text" name="propertyValue" placeholder="포르퍼티값"/>
	<input type="submit" value="새 프로퍼티 추가" />
</form>
<div id="errArea">
<script>
    $(function(){         
    	let errArea = $("#errArea"); 
    	$(document).ajaxError(function(event, jqXHR, thrownError){ //3개의 ajax error처리를 한번에
    		errArea.html(jqXHR.status + ", " + jqXHR.responseText);
    		               //상태코드                  //구체적인 에러 메세지
    	});
    	let propForm = $("#propForm").on("submit", function(event){
    		event.preventDefault();
    		let form = this;
    		$.ajax({

				url : form.action, //어디로
				method : form.method, //어떻게
				data : propForm.serialize(), //무엇을
				dataType : "json",
				success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함
					let newProp = resp.model;
				    let option = $("<option>").text(newProp.propertyName);
				    propSel.prepend(option);
				    form.reset();
				},


			});
    		return false;
    	});
    	let dataArea = $("#dataArea");
    	
    	let propSel = $('#propSel').on("change", function(){
    		let propertyName = $(this).val();
    		
    		$.ajax({

				url : "<%=request.getContextPath()%>/property", //어디로
			
/* 				data : {
					name : propertyName
				}, //무엇을 */
				dataType : "json",
				success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함
					let propertyVO = resp.model;
				    let trTag = $("<tr>").append(
								         $("<td>").html(propertyVO.propertyName)
								         , $("<td>").html(propertyVO.propertyValue)
								         , $("<td>").html(propertyVO.description)
					    ); //여러개일경우 append 한개일경우 html
				    
				    dataArea.html(trTag);
				         

				},

			
    	});
    });
    $.ajax({

		url : "<%=request.getContextPath()%>/properties" ,   //어디로

		success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함
				let dataList = resp.model;
			    let options = [];
				$.each(dataList, function(index, propertyVO){
					let option = $("<option>").text(propertyVO.propertyName);
					options.push(option);
					
		     });
			propSel.append(options);
	
			},

		});

	});
</script>
</div>
<script type="text/javascript">
// 	다음의 모든 요청은 비동기 처리를 기반으로 함.(rest방식으로 처리 -> ui가 포함되어있지 않아야함)
// 	1. 현재 페이지가 랜더링된 후 전체 프로퍼티 정보를 조회하여 select option 을 완성함. line : /properties (GET)
<%-- 
    $.ajax({

		url : "<%=request.getContextPath()%>/properties", //어디로
	
		dataType : "json",
		success : function(dataList) { //콜백 함수=>응답데이터를 갖고놀아야함
			let options = []
          $.each(dataList,function(i,p){
        	let option = $("<option>").text(p.propertyName);
        	options.push(option);
        	
          })
          $('#propSel').append(options)
		},
		error : function(errorResp) {

			console.log(errorResp.status);
		}

	}); --%>
// 	2. 선택 option이 변경될때마다 해당 프로퍼티의 정보를 조회하여 dataArea 에 랜더링함. line : /property?name=prop1 (GET)
// 	3. 하단 form 을 비동기로 전송하여 새 프로퍼티를 추가하고, 기존의 option들의 앞메 추가함. line : /property (POST)
// 	4. 모든 message(content)는 "json" 형식으로 교환됨.
// 	5. 요청 처리에 실패한 경우, 해당 상태코드와 응답 메시지를 errArea 에 랜더링함.
</script>
</body>
</html>