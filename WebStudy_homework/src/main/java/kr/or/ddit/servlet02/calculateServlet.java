package kr.or.ddit.servlet02;
// 내가 해본 동기 비동기 
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Calculate")
public class calculateServlet extends HttpServlet {
	
	/*
	 
	 	1. JSP에서 데이터를 받아올 것. ( 왼쪽 숫자, 오른쪽 숫자, 연산 기호 )
	 	2. 데이터 검증
	 	3. 형변환
	 	4. 연산
	 	5. 비동기, 동기 둘 다의 방법으로 JSP로 보내주기
	 
	 
	 */

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String leftop = req.getParameter("leftOp");
		String oper = req.getParameter("operator");
		String rightop = req.getParameter("rightOp");
		
		if(leftop != null && leftop.matches("\\d{1,4}")&& 
			 rightop != null && rightop.matches("\\d{1,4}")) {
			
			int lop = Integer.parseInt(leftop);
			int rop = Integer.parseInt(rightop);
			
			int res = 0;
			
			if(oper.equals("PLUS")) {
				res = lop + rop;
			}else if(oper.equals("MINUS")) {
				res = lop - rop;
			}else if(oper.equals("MULTIPLY")) {
				res = lop * rop;
			}else if(oper.equals("DIVIDE")) {
				res = lop / rop;
			}
						
			req.setAttribute("res", res);
			
			String json = String.format("{\"res\" : %d}",res);
			req.setAttribute("json", json);
			
		    
			
		}else if(leftop != null && !leftop.matches("\\d{1,4}")&& 
				 rightop != null && !rightop.matches("\\d{1,4}")) {
			
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
			
		}
		
		String accept = req.getHeader("Accept");
		
		if(accept.contains("json")) {
			
			resp.setContentType("application/json;charset=UTF-8");
			try(
		      PrintWriter out = resp.getWriter();  
		  ){
				out.print(req.getAttribute("json"));
			}
		}else {
			
			//req.getRequestDispatcher("/02/calculateForm.jsp").forward(req, resp);
		}
		
	} 
	
	
	          

}
