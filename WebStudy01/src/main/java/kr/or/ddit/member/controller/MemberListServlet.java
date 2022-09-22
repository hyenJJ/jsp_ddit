package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

/**
 *  RESTful URI
 *  /member(GET)
 *  /member/a001 (GET)
 *  /member/a001 (PUT)
 *  /member/a001 (DELETE)
 *  
 *  non-RESTful URI
 *  /member/memberList.do(GET)   회원 전체 조회
 *  /member/memberList.do?who=a001(GET)   한명의 회원  조회
 *  /member/memberList.do(GET)   
 *  /member/memberList.do(POST)
 *  /member/memberUpdate.do?who=a001  (GET)   
 *  /member/memberUpdate.do?who=a001  (POST)  
 *  /member/memberDelete.do?who=a001  (POST)  
 * 
 * @author PC-14
 *
 */
@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet {
	
	private MemberService service = new MemberServiceImpl(); //service가 결합력을 발생시킴
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<MemberVO> memberList = service.retrieveMemberList();
		
		req.setAttribute("memberList", memberList);
		String commandPage = "/WEB-INF/views/member/memberList.jsp"; 
		req.setAttribute("commandPage", commandPage);
		String viewName = "/WEB-INF/views/template.jsp"; 
		req.getRequestDispatcher(viewName).forward(req, resp);
	}

}
