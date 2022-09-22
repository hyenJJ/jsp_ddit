<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
       <%
   
   		
   		StringBuffer gugudan = new StringBuffer();
   		StringBuffer options = new StringBuffer();
   	
   		
   		String strMin = request.getParameter("minDan");
   		String strMax = request.getParameter("maxDan");
   		
   		if(strMin==null || strMin.isEmpty()) {
   			strMin = "2";
   		}
   		if(strMax == null || strMax.isEmpty()) {
   			strMax = "9";
   		}
   		
   		//숫자로 구성되어있는게 맞는지
   		if(!strMin.matches("[2-9]") || !strMax.matches("[2-9]")) {
   			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "2,단부터 9단까지 범위내 파라미터만 가능.");
   			return;
   		}
   		
   		
   		
   		int min = Integer.parseInt(strMin);
   		int max = Integer.parseInt(strMax);
   		
   		for(int i=0;i<10;i++) {
   			options.append(String.format("<option valu='%d'>%d</options>",i,i));
   		}
   		
   		
   		for(int i=min; i<= max; i++) {
   		   gugudan.append("<tr>");
   		  
   			for(int k=1; k<=9; k++) {
   				gugudan.append(String.format("<td> %d * %d = %d </td>", i,k,i*k)); 
   				
   			}
   			gugudan.append("</tr>") ;
   			
   		}
   		
   		
//   		X-Requested-With: XMLHttpRwquest, AJAX (Async Javascript And XMLHttpRequest)		
   		
   		String header = request.getHeader("X-Requested-With");
   		
   		if("XMLHttpRwquest".equals(header)) {

   			    out.println(gugudan);
   			    return;

   	}
    %>
<!DOCTYPE html>
<html>
   <head>
      <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
   </head>
   <body>
      <h4>구구단 (8*9)</h4>
      2단 부터 9단 까지의 구구단 랜더링.
      출력되는 구구단 text 형식 : "2 * 2 = 4"
      <form name="gugudanForm">
         최소단 : <input type="number" name="minDan" min="2" max="9" required value="<%=min %>"/>
         최대단 : 
         <select name="maxDan" required>   
            <option value>최대단</option>
            <%=options %>
         </select>
         <input type="submit" value="전송">
      </form>
      <table id="gugudanTable">
    <%	
       for(int i=min; i<= max; i++) {
    	   out.println("<tr>");
       
      
       
       
        for(int k=1; k<=9; k++) {
           out.println(
        		   String.format("<td> %d * %d = %d </td>", i,k,(i*k))  
        		   
        		   );    
        
         }
        out.println("<tr>");
      
       }      
       %>
          <!-- #gugudan# --> 
      </table>
      <script type="text/javascript">
//          document.gugudanForm.maxDan.value="#maxDan#";
         $("[name='maxDan']").val("#maxDan#");
         $("form1:first").on("submit", function(event){
            event.preventDefault();
//             console.log(this.name);
//             console.log(event.target);
//             console.log($(this).attr("name"));
            let url = this.action;
            let method = this.method;
            let data = $(this).serialize(); // query string 생성
            
            $.ajax({
               url : url,
               method : method,
               data : data,
               dataType : "html",
               success : function(resp) {
                  $("#gugudanTable").html(resp);
               },
               error : function(errorResp) {
                  console.log(errorResp.status);
               }
            });
            return false;
         });
      </script>
   </body>
</html>











