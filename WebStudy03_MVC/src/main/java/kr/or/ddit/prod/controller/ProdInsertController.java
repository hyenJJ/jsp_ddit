    package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.MultipartFile;
import kr.or.ddit.file.filter.StandardMultipartHttpServletRequest;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

//@WebServlet("/prod/prodInsert.do")
@MultipartConfig
public class ProdInsertController {
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	String saveFolderURL = "/resources/prodImages";
	String saveFolderPath;
	File saveFolder;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		 saveFolderPath = getServletContext().getRealPath(saveFolderURL);
		 saveFolder = new File(saveFolderPath);
	}
	
	
	
	private void viewResolve(
			String logicalViewName, 
			HttpServletRequest req, 
			HttpServletResponse resp
	) throws ServletException, IOException{
		if(logicalViewName.startsWith("redirect:")) {
			logicalViewName = logicalViewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + logicalViewName);
		}else {
			String viewName = "/"+logicalViewName+".tiles";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//?????? ???????????? ???????????? ????????? service ????????? ?????? ??????
		
		// doget, dopost ?????? ???????????? ??????????????? sevice ??????????????? ?????? 
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(null);
		req.setAttribute("lprodList", lprodList);
		req.setAttribute("buyerList", buyerList);
		
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "prod/prodForm";
		viewResolve(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProdVO prod = new ProdVO(); // ????????? ?????? ??????
		req.setAttribute("prod", prod);
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		

		
		if(req instanceof StandardMultipartHttpServletRequest) {
			
			MultipartFile prodImage =  ((StandardMultipartHttpServletRequest) req).getFile("prodImage");
			
				prod.setProdImage(prodImage);
				prod.saveTo(saveFolder);

			
		}
		
//		req.getpart
//		prod.setProdImage(null);
		
		
		Map<String, String> errors = new ValidateUtils<ProdVO>().validate(prod, InsertGroup.class); ;
		req.setAttribute("errors", errors);
		
	//	boolean valid = validate(prod, errors);
		
		String logicalViewName = null;
		if(errors.isEmpty()) {  // ????????? ????????????(=??????) -> ?????? ?????? 
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
				logicalViewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
				break;

			default:
				req.setAttribute("message", "?????? ??????, ?????? ?????? ??????.");
				logicalViewName = "prod/prodForm";
				break;
			}
		}else {
			logicalViewName = "prod/prodForm";
		}
		viewResolve(logicalViewName, req, resp);
	}
	
	// Hibernate validator 
/*	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true;
//		if (StringUtils.isBlank(prod.getProdId())) {
//			errors.put("prodId", "??????????????????");
//			valid = false;
//		}
		if (StringUtils.isBlank(prod.getProdName())) {
			errors.put("prodName", "???????????????");
			valid = false;
		}
		if (StringUtils.isBlank(prod.getProdLgu())) {
			errors.put("prodLgu", "??????????????????");
			valid = false;
		}
		if (StringUtils.isBlank(prod.getProdBuyer())) {
			errors.put("prodBuyer", "?????????????????????");
			valid = false;
		}
		if (prod.getProdCost()<0) {
			errors.put("prodCost", "???????????????");
			valid = false;
		}
		if (prod.getProdPrice()<0) {
			errors.put("prodPrice", "???????????????");
			valid = false;
		}
		if (prod.getProdSale()<0) {
			errors.put("prodSale", "???????????????");
			valid = false;
		}
		if (StringUtils.isBlank(prod.getProdOutline())) {
			errors.put("prodOutline", "????????????");
			valid = false;
		}
		if (StringUtils.isBlank(prod.getProdImg())) {
			errors.put("prodImg", "?????????????????????");
			valid = false;
		}
		if (prod.getProdTotalstock()<0) {
			errors.put("prodTotalstock", "???????????????");
			valid = false;
		}
		if (prod.getProdProperstock()<0) {
			errors.put("prodProperstock", "??????????????????");
			valid = false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isNotBlank(prod.getProdInsdate())) {
			try {
				sdf.parse(prod.getProdInsdate());
			} catch (ParseException e) {
				errors.put("ProdInsdate", "?????? ?????? ??????");
				valid = false;
			}			
		}
		return valid;
		
	}*/
}













    
