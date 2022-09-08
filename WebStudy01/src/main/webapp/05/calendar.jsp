<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="static java.util.Calendar.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Locale locale = request.getLocale(); //accept language
    String yearParam = request.getParameter("year");
    String monthParam = request.getParameter("month");
    String language = request.getParameter("language");
    
    Calendar calendar = getInstance();
    if(yearParam!=null && yearParam.matches("\\d{4}")// 년도가 정상적으로 들어왔다면
          && monthParam!=null && monthParam.matches("^1[0,1]$|^[0-9]$")){   // ^ : 문장의 시작  $ : 문장의 끝
        calendar.set(YEAR, Integer.parseInt(yearParam));
        calendar.set(MONTH, Integer.parseInt(monthParam));
    }
    
    if(language!=null && !language.isEmpty()){
    	locale = Locale.forLanguageTag(language);
    }
    	
    String title = String.format(locale, "%1$tY, %1$tB ", calendar); // %ty : 년도    %tB: 월
                                    // 1$ 한개의 argumnet 
    
    calendar.add(MONTH, -1);   
    int beforeYear = calendar.get(YEAR);
    int beforeMonth = calendar.get(MONTH);
    
    calendar.add(MONTH, 2);  // 1 : 현재 
    int nextYear = calendar.get(YEAR);
    int nextMonth = calendar.get(MONTH);
    
    
    calendar.add(MONTH, -1);   // 2 - 1 = 1  -> 현재로 다시 돌아오게 함 
    int year = calendar.get(YEAR);
    int month = calendar.get(MONTH);
    
    calendar.set(DAY_OF_MONTH,1);  // 해당 달의 1일 찾기
    int dayOfWeek1st = calendar.get(DAY_OF_WEEK); // 1일의 요일 찾기
    int offset = dayOfWeek1st -1;
    calendar.add(DAY_OF_MONTH, -offset); //offset만큼 전 
    //calendar.add(DAY_OF_MONTH, 1); 내일의 날짜 찾기 
    
   
    //localizable 
    DateFormatSymbols dfs = new DateFormatSymbols(locale);
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/jquery-3.6.1.min.js"></script>
</head>                <!--client   -->
<body>
<h4><!-- ? : 현재 주소에다가 쿼리를 쓰겠다!  -->
<a href="#" class="cahngeBtn" data-year="<%=beforeYear %>" data-month="<%=beforeMonth %>">&lt;&lt;&lt;</a>
<%=title %>
<a href="#" class="cahngeBtn" data-year="<%=nextYear %>" data-month="<%=nextMonth%>">&gt;&gt;&gt;</a>
</h4>
<form name="calForm" method="post">
    <input type="text" name="year" pattern="\d{4}"  placeholder="2022" value="<%=year%>"/>
                                    <!--\d : 한글자 {4} : 숫자 4글자  -->
    <select name="month">
         <%
            String[] months = dfs.getMonths();
            for(int idx=JANUARY; idx<=DECEMBER; idx++){
            	out.println(String.format("<option value='%d'>%s</option>", idx, months[idx]));
            }
         %>
    </select>  
    <select name="language">
         <%
             Locale[] locales = Locale.getAvailableLocales();
             for(Locale tmp : locales){
            	 String display = tmp.getDisplayLanguage(tmp);
            	 
            	 if(display.isEmpty()) continue;
            	 
            	 out.println(String.format("<option value='%s'>%s</option>",tmp.toLanguageTag(), display ) );
             }                                                               //kr=KR 얻을 수 있음
         
         %>    
         <option value="ko-KR">한국어</option>
             <!-- = locale 코드 or language코드  -->
         <option>영어</option>
    </select>     
    <select name="timeZoneId">
          <%
             /* Timez */
          %>
          <option value="Asia.Seoul" >아시아/서울</option>
    </select>           
</form>                             
<table>
    <thead>
       <tr>
        <%
		      String[] weekDays = dfs.getShortWeekdays();
		      for(int col=SUNDAY; col<=SATURDAY; col++){
		         out.println(String.format("<th>%s</th>", weekDays[col]));
		      }
         %>

       </tr>
    </thead>
    <tbody>
     <%
     // Calendar.getInstance(); <-  import="static java.util.Calendar.*"
      //  int count = 1;
        for(int row=1; row<=6; row++){
        	out.println("<tr>");
        	for(int col=SUNDAY; col<=SATURDAY; col++){
        		out.println(String.format("<td>%te</td>", calendar)); //'e' :  01 -> 1
        		calendar.add(DAY_OF_MONTH,1); // 하루하루 증가 시키기 
        		
        		
        	}
        	out.println("</tr>");
        }
     %>
    </tbody>
</table>
<script type="text/javascript">
    let yearTag = $("[name=year]").val("<%=year %>");    
    let monthTag = $("[name=month]").val("<%=month %>");
    let languageTag = $("[name=language]").val("<%=language %>");    // option 기본 상태 유지
    
    $(".cahngeBtn").on("click", function(){
    	//<h4> <a>
    	event.preventDefault();
    	let year = $(this).data("year");
    	let month = $(this).data("month");
    	yearTag.val(year);
    	monthTag.val(month);
    	calForm.submit(); // post방식의 요청 
    	return false;
    	
    	
    });
    
    let calForm = $(document.calForm).on("change",":input[name]",function(event){
    	// event.target == this
        // this.form.submit(); // submit 이벤트는 발생하지 않는다.
    	this.form.requestSubmit(); // submit 이벤트는 발tod
    });
</script>
</body>
</html>