package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  /buyer/buyerList.do(GET)
 *  /buyer/buyerView.do?what=P10101(GET)
 *  /buyer/buyerInsert.do(GET)
 *  /buyer/buyerInsert.do(POST)
 *  /buyer/buyerUpdate.do?wht=P10101(POST)
 *  /buyer/buyerUpdate.do?wht=P10101(POST)
 *  
 *  " 전자제품" 카테고리의 "삼성전자" 를 검색. detail condition
 *  
 * 
 *
 */

@WebServlet("/buyer/buyerList.do")
public class BuyerListServlet extends HttpServlet {

	
	
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		
		
	}
	
	
	
}
