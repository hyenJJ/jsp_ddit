package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Servlet implementation class MessageBundleReadServlet
 */
@WebServlet("/getMessage")
public class MessageBundleReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    1) 메시지 번들 로딩(ResourceBundle)
//    2) "hello"코드 메시지 확보
//    3) 확보한 메시지를 컨텐츠화(Accept 헤더에 따라 Content-Type 결정).
		
		String baseName = "egovframework/message/com/message-common";
		Locale locale = request.getLocale();
		request.setCharacterEncoding("UTF-8"); 
		String languageTag = request.getParameter("language");
		if(languageTag != null && !languageTag.isEmpty()) {
			locale = Locale.forLanguageTag(languageTag);
		}
		
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale); //클라이언트 locale을 따라가야함
		String value = bundle.getString("hello");
		
//		Map<String, Object> model = Collections.singletonMap("hello", value);
		String accept = request.getHeader("Accept");
//		String contentType = null;  //view역할
		
		request.setAttribute("hello", value);
		String viewName = null;
		if(accept.contains("json")) {
			
			viewName = "/jsonView.do";	
			
			//모델 1			
//			contentType = "application/json;charset=UTF-8";
//			response.setContentType(contentType);
//			try(
//			   PrintWriter out = response.getWriter();		
//			){
//				new ObjectMapper().writeValue(out, model);
//			}
			
		}else if(accept.contains("xml")) {
			
			viewName = "/xmlView.do";	
			//모델 1
//			contentType = "application/json;charset=UTF-8";
//			response.setContentType(contentType);
//			
//			try(
//			   PrintWriter out = response.getWriter();		
//			){
//				new XmlMapper().writeValue(out, model);
//			}
			
			//모델 2
		}else {
			// contentType = "text/html;charset=UTF-8"; //jsp에서  utf-8을해줌
			
		     viewName = "/WEB-INF/views/messageView.jsp"; //web-inf 는 클라이언트가 요청할 수 없음 ->모델1방식 사용 x
		    
		}
		request.getRequestDispatcher(viewName).forward(request, response);
	}

}
