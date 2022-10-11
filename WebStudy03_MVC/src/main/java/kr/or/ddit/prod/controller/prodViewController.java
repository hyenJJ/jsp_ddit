package kr.or.ddit.prod.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.AbstractCommandHandler;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

// POJO(Plain Old Java Object)
@Slf4j
//@WebServlet("/prod/prodView.do")
public class ProdViewController extends AbstractCommandHandler {
	
	private ProdService service = new ProdServiceImpl();
	
	
	//@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		String prodId = req.getParameter("what");
		
		if(StringUtils.isBlank(prodId)) {
        	resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        	return null;
        }
		
		ProdVO prod = service.retrieveProd(prodId);	
		req.setAttribute("prod", prod);
		
		String logicalViewName = "prod/prodView";
		return logicalViewName; 
	
	}

}
