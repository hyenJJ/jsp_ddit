    package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.AbstractCommandHandler;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

/**
 * /prod/prodList.do(GET)
 * /prod/prodView.do?what=P101000001(GET)
 * /prod/prodInsert.do(GET)
 * /prod/prodInsert.do(POST)
 * /prod/prodUpdate.do?what=P101000001(GET)
 * /prod/prodUpdate.do(POST)
 *
 */
//@WebServlet("/prod/prodList.do")
public class ProdListController extends AbstractCommandHandler{
	private ProdService service = new ProdServiceImpl();
	

	
	//@Override X -> 지우는 순간부터 제약이 없어짐 -> 마음대로 해도 됨 
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String accept = req.getHeader("Accept");
		
		if(accept.contains("json")) {
			return processJsonData(req, resp);
		}else {
			return processHTML(req, resp);
		}
	}
	
	private String processHTML(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String logicalViewName = "prod/prodList";
		return logicalViewName;
	}
	
	private String processJsonData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String prodLgu = req.getParameter("prodLgu");
		ProdVO detailCondition = new ProdVO();
//		detailCondition.setProdLgu(prodLgu);
		try {
			BeanUtils.populate(detailCondition, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<ProdVO> pagingVO = new PagingVO<>(3, 3);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setDetailCondition(detailCondition);
		
		int totalRecord = service.retrieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList);
		
//		req.setAttribute("prodList", prodList);
		req.setAttribute("pagingVO", pagingVO);
		
		return "forward:/jsonView.do";
	}
}
























    
