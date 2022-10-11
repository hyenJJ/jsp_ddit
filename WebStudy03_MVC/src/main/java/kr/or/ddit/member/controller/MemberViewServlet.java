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

@WebServlet("/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	private MemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String who = req.getParameter("who");
	
        if(who==null || who.isEmpty()) {
        	resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        	return;
        }
		MemberVO member = service.retrieveMember(who); 
		//usernotfondexception 처리코드가 없어서 tomcat서버에게 넘어가서 500에러가 남
      
		req.setAttribute("member", member);

		String viewName = "/WEB-INF/views/member/memberView.jsp";

		//req.setAttribute("commandPage", commandPage);

		//String viewName = "/WEB-INF/views/template.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
