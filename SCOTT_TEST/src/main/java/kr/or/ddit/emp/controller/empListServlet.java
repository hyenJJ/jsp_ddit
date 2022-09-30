package kr.or.ddit.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.emp.service.EmpService;
import kr.or.ddit.emp.service.EmpServiceImpl;
import kr.or.ddit.vo.EmpVO;

@WebServlet("/emp/empList.do")
public class empListServlet extends HttpServlet {

	private EmpService service = new EmpServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		List<EmpVO> empList =  service.retrieveMemberList();
		
		req.setAttribute("empList", empList);
		
		String viewName = "/emp/empList.tiles";
		
		req.getRequestDispatcher(viewName).forward(req, resp);
		
		
	}
}
