package kr.or.ddit.servlet04;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.vo.FormDataVO;

/**
 * 
 * 문자열의 형태로 전송되는 파라미터 핸들링.
 * 
 * 1. Request Line(GET)
 *         - 서버의 설정으로 특수문자 처리
 *             ex) server.xml -> connector -> URIEncoding, useBodyEncodingForURI
 * 2. Request Body (POST, PUT/Patch(
 *         - request.setCharacterEncoding(encoding)
 *         
 * ** 특수문자가 포함된 경우. - 해당 데이터 접근하기 전에 charset 설정 필요.
 * 
 *    String getParameter(name)
 *    String[] getParameterValues(name)
 *    Map<String,String[]> getParmeterMap()
 *    Enumeration<String> getparameterName()
 *
 */


@WebServlet("/formDataProcess")
public class FormDataProcessServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		paramIpt1 : value/                       
//		String paramIpt1 = req.getParameter("paramIpt1");
		req.setCharacterEncoding("UTF-8");
		
		Map<String, String[]> parameterMap = req.getParameterMap();
		
		for(Entry<String,String[]> entry : parameterMap.entrySet()){
			String paramName = entry.getKey();
			String[] paramValues = entry.getValue();
			System.out.printf("%s : %s \n", paramName, Arrays.toString(paramValues));
			
		}
		
		FormDataVO vo = new FormDataVO();
//		vo.setParamIpt1(req.getParameter("paramIpt1"));
//		vo.setParamIpt2(req.getParameter("paramIpt2"));
		
		try {
			BeanUtils.populate(vo, parameterMap);
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		req.setAttribute("vo", vo);

		String view = "/WEB-INF/views/formDataView.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	

}
