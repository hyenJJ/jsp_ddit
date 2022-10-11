package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.controller.ProdListController;
import kr.or.ddit.prod.controller.ProdViewController;

//유일한 서블릿 
public class Dispatcherservlet extends HttpServlet {
	
	@Override 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getServletPath();
		AbstractCommandHandler controller = null;
		if("/prod/prodList.do".equals(uri)) {
			 controller = new ProdListController();
		}else if("/prod/prodView.do".equals(uri)) {
			 controller = new ProdViewController();
		}else if("/prod/getLprodList.do".equals(uri)) {
			
		}
		
		String logicalViewName = controller.process(req, resp);
		viewResolve(logicalViewName, req, resp);
	}
	
	
	private void viewResolve(
			String logicalViewName, 
			HttpServletRequest req, 
			HttpServletResponse resp
	) throws ServletException, IOException{
		if(logicalViewName.startsWith("redirect:")) {
			logicalViewName = logicalViewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + logicalViewName);
		}else if(logicalViewName.startsWith("forward:")) {
			logicalViewName = logicalViewName.substring("forward:".length());
			req.getRequestDispatcher(logicalViewName).forward(req, resp);
		}else {
			String viewName = "/"+logicalViewName+".tiles";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
	
}
