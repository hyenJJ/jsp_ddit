package kr.or.ddit.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.do")
public class indexServlet extends HttpServlet {
	
	
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//단 한번 실행 됨
		super.init(config);
		application = getServletContext();
		Map<String, String> commandDB = new LinkedHashMap<>();
		commandDB.put("CALENDAR", "/05/calender.jsp");
		commandDB.put("FACTORIAL", "/02/factorial.jsp");
		commandDB.put("CALCULATOR", "/02/calculator.jsp");
		
		application.setAttribute("commandDB", commandDB);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> commandDB = (Map) application.getAttribute("commandDB");
		
		String command = req.getParameter("command");
		String commandPage = null;
		int status = 200;
		if(command == null || command.isEmpty()) { //웰컴 페이지 요청한 경우
			
			commandPage="/WEB-INF/views/index.jsp";
		
		}else {
			 // map사용 , enum
			if(!commandDB.containsKey(command)) {
				status = HttpServletResponse.SC_NOT_FOUND;
				
			}else {
				commandPage = commandDB.get(command);
			}
			
		}
		if(status == 200) {
			
			
			req.setAttribute("commandPage", commandPage);
			String viewName = "/WEB-INF/views/template.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}else {
			resp.sendError(status);
		}
	}

}

/*page request session application */