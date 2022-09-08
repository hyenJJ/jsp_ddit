package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculateVO;

@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int statusCode = HttpServletResponse.SC_OK;
		String bodyContentType = req.getContentType(); 
		if(!bodyContentType.contains("json")) { // body contentType 에 json이 있는지 없는지
			statusCode = HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
			
			
		}
		
		String accept = req.getHeader("Accept");  
		if(statusCode==200 && !accept.contains("json")) {
			statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE;
		}
		
		if(statusCode == HttpServletResponse.SC_OK) {
			
			resp.setContentType("application/json;charset=UTF-8");
			
			try(
					InputStream is = req.getInputStream();
					PrintWriter out = resp.getWriter();
					){
				ObjectMapper mapper = new ObjectMapper();
				CalculateVO vo = mapper.readValue(is, CalculateVO.class);
				
				mapper.writeValue(out, vo);
			}
			
		}else {
			resp.sendError(statusCode);
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String left = req.getParameter("leftOp");
		String right = req.getParameter("rightOp");
		
 		String opParam = req.getParameter("operator");
 		
 		int statusCode = HttpServletResponse.SC_OK;
 		OperatorType operatorType = null;
 		try {
 			operatorType = OperatorType.valueOf(opParam);
 		}catch (Exception e) {
 			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		if(
			left==null || left.isEmpty() || !left.matches("\\d+")
			|| right==null || right.isEmpty() || !right.matches("\\d+")	
		) {
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
		}
		
		
		if(statusCode==HttpServletResponse.SC_OK) {
			int leftOp = Integer.parseInt(left);
			int rightOp = Integer.parseInt(right);
			
 			String expression = operatorType.getExpression(leftOp, rightOp);
			
 			try(
 				PrintWriter out = resp.getWriter();	
 			){
 				out.println(expression);
 			}
 			
		}else {
			resp.sendError(statusCode);
		}
	}
}


















