package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutController extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		
		// 로그인되어 있는 상태 여야 로그아웃이 가능함 -> session을 만들 때 최초의 요청이 아니여야함 
		if(session.isNew()){ //session이 new(최초요청) 일때 -> 오류
			resp.sendError(400); 
			return;
		}
		
		// 정상적인 요청이라면
		// authmember 없애기
		// session 완료 시키기 
		// 두개를 만족시키는 것 -> invalidate
		session.invalidate();
		String viewName = "redirect:/";
		
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length()); //redirct 길이만큼 잘라낸다
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
		
	}
	

}
