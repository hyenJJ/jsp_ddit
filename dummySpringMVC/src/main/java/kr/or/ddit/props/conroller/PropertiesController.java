package kr.or.ddit.props.conroller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.props.service.PropertyService;
import kr.or.ddit.props.service.PropertyServiceImpl;
import kr.or.ddit.props.vo.PropertyVO;

//@WebServlet({"/properties","/property"})
@Controller //mapping해주는 handler가 있기 때문에 무조건 이것만 써줘야함
public class PropertiesController  {
	
	@Inject
	private PropertyService service;
	
	
	@RequestMapping("/properties")
	@ResponseBody //json이 생략되어있어도 마샬링 해줌
	public List<PropertyVO> properties() {
		return service.readProperties();
	}
	
	//spring에서는 하나의 메소드가 servlet이기 때문에 메소드를 두개 써주면 됨 
	@GetMapping("/property")
	@ResponseBody 
	protected PropertyVO doGet(@RequestParam(required=true) String name ) throws ServletException, IOException {
		
		
		Object model = null;
		int statusCode = 200;
		
	return service.readProperty(name);
			
		
		
		       
//			    String message = (String)req.getSession().getAttribute("message");
//			                                             //attribute 타입 - object
//			    session.removeAttribute("message"); 
//			
//			    System.out.println(message);

	}
	
	@PostMapping("/property") //spring은 vlidate를 이용해서 알아서 검증을 해줌
	public String doPost(@Valid PropertyVO newProp, Errors errors, RedirectAttributes redirectAttributes)
			throws ServletException, IOException {
	
		
		//검증에 통과 했다  = errors.inEmpty
		if(!errors.hasErrors()) { 
			service.createProperty(newProp);
			
			String message = "성공";
			
//			req.getSession().setAttribute("message", message);
			
			redirectAttributes.addFlashAttribute("message", message);
			String viewName = "redirect:/property?name=" + newProp.getPropertyName();
			return viewName;
			
//			resp.sendRedirect(req.getContextPath() + viewName);
			//redirection
		}else {
			return "property/form"; //여기로 forwarding을 해라 -> errors를 알아서 전달해줌
			
		}
		
	}

//	private boolean validate(PropertyVO newProp) {
//		boolean valid = true;
//		if(newProp.getPropertyName() == null) {
//			valid = false;
//			
//		}
//		if(newProp.getPropertyValue() == null) {
//			valid= false;
//		}
//		return valid;
//	}

}
