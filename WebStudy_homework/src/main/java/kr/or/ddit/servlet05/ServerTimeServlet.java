package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerTimeServlet
 */
@WebServlet("/getServerTime.html")
public class ServerTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    private int count = 1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		if(count++ > 5)
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
		
		
//		response.setIntHeader("Refresh", 1);
		response.setContentType("text/html;charset=UTF-8");  
	    response.setHeader("Cache-Control", "max-age=5");
		response.getWriter()
		        .append(new Date().toString())
		        .close();
	}

}
