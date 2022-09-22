package kr.or.ddit.props.conroller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.service.PropertyServiceImpl;
import kr.or.ddit.props.vo.PropertyVO;

@WebServlet({"/properties","/property"})
public class PropertiesControllerServlet extends HttpServlet {
	
	private PropertyService service = new PropertyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");// 모든 컨트롤러에서 젤 첫번째로 들어가야 함 
		
		String servletPath = req.getServletPath();
		Object model = null;
		int statusCode = 200;
		
		if("/properties".equals(servletPath)) {
			model = service.readProperties();
			
		}else {
			
			String name = req.getParameter("name");
			
			if(name == null || name.isEmpty()) 
				statusCode = HttpServletResponse.SC_BAD_REQUEST;
			else
		        model = service.readProperty(name); //if문의 model
			    String message = (String)req.getSession().getAttribute("message");
			                                             //attribute 타입 - object
			    req.getSession().removeAttribute("message"); 
			    //꺼내고 나서 바로 삭제 - flash attribute
			    //      1.필요없는 데이타는 남겨 줄수 없다
			    //      2.부화를 줄이기 위해서 
			    System.out.println(message);
			
		}
		if(statusCode==200) {
			
			req.setAttribute("model", model); // setatrribute - 모든형태 가능
			String viewName = "/jsonView.do"; //언제나 모든 컨으롤러에서 view로 가는 코드가 있어야함
		    req.getRequestDispatcher(viewName).forward(req, resp);
		}else {
			resp.sendError(statusCode);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		PropertyVO newProp = new PropertyVO();
		//newProp.setPropertyName(req.getParameter("propertyName"));
			
		try {
			BeanUtils.populate(newProp, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
		  
			throw new ServletException(e);
		}
		
		boolean valid = validate(newProp);
		
		if(valid) {
			service.createProperty(newProp);
			
			String message = "성공";
			req.getSession().setAttribute("message", message);
			String viewName = "/property?name=" + newProp.getPropertyName();
			resp.sendRedirect(req.getContextPath() + viewName);
			//redirection
		}else {
			resp.sendError(400);
			
		}
		
		
		
	}

	private boolean validate(PropertyVO newProp) {
		boolean valid = true;
		if(newProp.getPropertyName() == null) {
			valid = false;
			
		}
		if(newProp.getPropertyValue() == null) {
			valid= false;
		}
		return valid;
	}

}
