package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;

@WebServlet("/mypage.do")
public class MyPageServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String memId = req.getUserPrincipal().getName();
//		MemberVO member = service.retrieveMember(memId);
	
		MemberVOWrapper wrapper = (MemberVOWrapper) req.getUserPrincipal();
		
		MemberVO member = wrapper.getRealUser();
		
		
		req.setAttribute("member", member);
		
		String viewName = "member/mypage";
		
		viewResolve(viewName, req, resp);
	}



private void viewResolve(String logicalViewName, HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	if (logicalViewName.startsWith("redirect:")) {
		logicalViewName = logicalViewName.substring("redirect:".length());
		resp.sendRedirect(req.getContextPath() + logicalViewName);

	} else {

//		req.setAttribute("commandPage", commandPage);

		String viewName = "/"+logicalViewName+".tiles";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}

}

}