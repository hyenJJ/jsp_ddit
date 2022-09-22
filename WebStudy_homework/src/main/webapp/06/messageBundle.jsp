<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<input type="radio" name="language" value="ko" checked/>한글
<input type="radio" name="language" value="en"/>영문
<button class="generate">버튼 생성</button>
<button type="button" data-data-type="json"  class="btn" >JSON</button>
<button type="button" data-data-type="xml" class="btn" >XML</button>
<span id="resultArea"></span>
<script>

    $(".generate").on("click", function(){
    	let newBtn = $("<button>").text("HTML").data("dataType","html")
    	               //버튼태그를 추가하겠다
    	
      
    	$("button:last").after(newBtn);  //마지막 버튼 뒤에 추가한다               
  //  	  datatype =($(this).data('dataType'));            
    	
    
    });
    
    

	
/* 	 1. request 전송 (front-end)
	       1) 버튼(generate 버튼 제외, 동적 버튼 포함) click 이벤트 처리
	       2) 비동기 요청 발생
	            - 클릭이 발생한 버튼으로부터data( dataType ) 속성 확보 dataType
	            - language 라디오 버튼의 값으로 파라미터 형성. */
	            
	let resultArea = $('#resultArea');            
	let successFunctions = {
	      json : function(resp){
	    	  resultArea.html(resp.hello);
	      },
	      xml : function(resp){
	    	  let text = $(resp).find("hello").text();
	    	  resultArea.html(text);
	    	  //해당 돔객체를 j쿼리객체로 바꿔줌
	    	  console.log(resp);
	      },
	      html : function(resp){
	    	  resultArea.html(resp);
	      }
	 }            
    $(document).on('click',"button:not(.generate)",function() {
    
     
     resultArea.html("")//클리어 시키기  =resultArea.empty()	 	
     let dataType = $(this).data('dataType');	
     let settings = {

    			url : "<%=request.getContextPath()%>/getMessage", //어디로

    			success : function(resp) { //콜백 함수=>응답데이터를 갖고놀아야함
    	    		resp.hello
    			},
    			error : function() {

    				console.log(errorResp.status);
    			}

    		}
     settings['dataType'] = dataType ? dataType : "json"; //데이터 타입이 있으면 dataType 없으면 json
     settings.success = successFunctions[settings.dataType]  // =[settings['dataType']]
     settings.data = { 
    		 language : $("[name='language']:checked").val()
    		 };
     $.ajax(settings);  

    	  
 });
	//응답헤더에 content-type과 요청헤더에 Accept가 같아야함
/* 	   2. response 생성 (back-end) 
	        1) 메시지 번들 로딩(ResourceBundle)
	        2) "hello"코드 메시지 확보
	        3) 확보한 메시지를 컨텐츠화(Accept 헤더에 따라 Content-Type 결정).
	        
	   3. response 수신 후 처리 (front-end) : success function     
	        
	
	-----------------------------------  
	   
	   3.서블릿에서 resource bundle을 읽는다.
	   4.해당 bundle에서 hello찾는다.
	   5.map을 만든다.hello라는 키로 entry 만들기
	   6.mapper객체를 이용해서 마샬링
	   
	   7.돌아오는 resp안에 hello라는 */
	   

</script>
</body>
</html>