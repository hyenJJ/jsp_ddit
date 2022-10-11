package kr.or.ddit.login.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.login.service.AuthenticationService;
import kr.or.ddit.login.service.AuthenticationServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;


@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet {
	
   AuthenticationService service = new AuthenticationServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// encoding
		// 파라미터 획득
		// 검증 
		// 통과 -> 인증여부 확인
		// 인증 성공 -> welcome 페이지로 이동 (redirect) - session scope을 통해 "authMember" 를 유지.
		// 인증 실패 -> loginForm 으로 이동 (redirect : 잘못된 사용자 정보를 남겨선 안됨 ) - 메시지 전달 
		// 불통 - > loginForm으로 이동 (redirect) - 메시지 전달 
		// 인증 시스템에서는 forward방식이 존재하지않는다
		// 인증과 관련된 모든 방식은 get , redirect
		
		req.setCharacterEncoding("UTF-8");
		
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
		
		HttpSession session = req.getSession();
	
		String viewName = null;
		String message = null;
		
		if(StringUtils.isBlank(memId) || StringUtils.isBlank(memPass)) {
			message = "아이디나 비밀번호 누락";
			viewName = "redirect:/login/loginForm.jsp";
		}else {
			MemberVO inputData = new MemberVO();
			inputData.setMemId(memId);
			inputData.setMemPass(memPass);
			
			try {
				MemberVO authMember = service.authenticate(inputData);
				session.setAttribute("authMember", authMember);
				//request를 건들이지 않고도 request에 princiaple을 만들수있다 ->request에서도 wrapper가 존재
				
				viewName = "redirect:/";
			
				
				
			
			service.authenticate(inputData);
			}catch (UserNotFoundException | BadCredentialException e) {
				
			if(e instanceof UserNotFoundException) {
				message = "해당 회원 없음.";
			}else {
				message = "비밀번호 오류임.";
			}
			viewName = "redirect:/login/loginForm.jsp";
		}
			
		session.setAttribute("message", message);
			
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length()); //redirct 길이만큼 잘라낸다
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}

        		
		
		

	}
	
		
		
		

		

		
		
	}
	

}
