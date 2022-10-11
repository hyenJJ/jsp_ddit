package kr.or.ddit.commons;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class JsonViewServlet
 */
@WebServlet("/jsonView.do")
public class JsonViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    @Override
	protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

    	Map<String, Object> model = new HashMap<>();
    	Enumeration<String> attributeNames = req.getAttributeNames();
    	while (attributeNames.hasMoreElements()) {
    		String name = ( String ) attributeNames.nextElement();
            Object value = req.getAttribute( name );
            model.put( name, value );
    	}
    	
    	
		String contentType = "application/json;charset=UTF-8";
		response.setContentType(contentType);
		try(
		   PrintWriter out = response.getWriter();		
     	){
		new ObjectMapper().writeValue(out, model);
		}
	}

}
