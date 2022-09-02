package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/factorial")
public class FactorialServlet extends HttpServlet {
    
	

	private long factorial(int number){
		
		// 검증
		if(number<=0){
			throw new IllegalArgumentException("연산은 양수만 처리함."); 
		}
		 if(number==1){		 
		 return 1;
		 
		 }else{
			 return number*factorial(number-1);
		 }
	}
	
	//Java -> JSON : marshalling
    //JSON -> Java : unmarshalling

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String operand = request.getParameter("operand");
		
		// 검증
		if(operand != null && operand.matches("\\d{1,3}")){  //1글자부터 3글자까지만
			
			int number = Integer.parseInt(operand);
			long result = factorial(number);
			
			request.setAttribute("result", result);
			request.setAttribute("number", number);
			
			String json = String.format("{ \"operand\" : %d, \"result\" : %d}",number,result);
                                       //{string : value , string : value}
			request.setAttribute("json", json);
			
			
		}else if(operand != null && !operand.matches("\\d{1,3}")){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		
		String accept = request.getHeader("Accept");
		
		if(accept.contains("json")) {
			response.setContentType("application/json;charset=UTF-8");
			try(
				PrintWriter out = response.getWriter();	
			){
				out.print(request.getAttribute("json"));
			}
		
			
		}else {
			String view = "/02/factorial.jsp";
			request.getRequestDispatcher(view).forward(request, response);
			
		}
		
		
		
	  }
		
	}
	
