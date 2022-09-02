package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.OperatorType;
// 동기 -> 비동기
// jsp로 달력 구현
@WebServlet("/calculate")
public class CalculateServlet extends HttpServlet{
	
	
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	 String left = req.getParameter("leftOp");
    	 String right = req.getParameter("rightOp");
    	 
    	 String opParam = req.getParameter("operator");
    	 
    	 int statusCode = HttpServletResponse.SC_OK; 
    	 OperatorType operatorType = null;
    	 
    	 try {
    		 
    	  operatorType = OperatorType.valueOf(opParam); //연산기호 가져옴
    	 
    	 }catch(Exception e) {
    		 statusCode = HttpServletResponse.SC_BAD_REQUEST;
    		 
    	 }
    	 
    	 
    	                                     //  \\d+ : 한글자 이상의 숫자
    	 if(left==null || left.isEmpty() || !left.matches("\\d+")
    	    || right==null || right.isEmpty() || !right.matches("\\d+")) {
    		 
    		 statusCode = HttpServletResponse.SC_BAD_REQUEST;
    		 
    	 }
    	                                 //검증 통과
    	 if(statusCode==HttpServletResponse.SC_OK) {
    		 int leftOp = Integer.parseInt(left);
    		 int rightOp = Integer.parseInt(right);
    		 
    		 String expression = operatorType.getExpression(leftOp, rightOp);
    		 
    		 try(
    			PrintWriter out = resp.getWriter();	 //expression 이 문자열이므로 
    				                   
    		 ){
    			 out.println(expression);
    		 }
    		 
    	 }else { //검증 실패
    		 resp.sendError(statusCode);
    	 }
    			 
    			 
    }
}
