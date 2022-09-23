package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.Member;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.validator.ValidateWith;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
public class MemberUpdateServlet02 extends HttpServlet {

	private MemberService service = new MemberServiceImpl(); // 컨트롤러와 서비스 사이에 결합력 발생

	   private void viewResolve(
		         String commandPage, 
		         HttpServletRequest req, 
		         HttpServletResponse resp
		   ) throws ServletException, IOException{
		      if(commandPage.startsWith("redirect:")) {
		         commandPage = commandPage.substring("redirect:".length());
		         resp.sendRedirect(req.getContextPath() + commandPage);
		      }else {
		         req.setAttribute("commandPage", commandPage);
		         String viewName = "/WEB-INF/views/template.jsp";
		         req.getRequestDispatcher(viewName).forward(req, resp);
		      }
		   }
		   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		      req.setCharacterEncoding("UTF-8");
		      
		      String memId = req.getParameter("who");
		      
		      if(StringUtils.isBlank(memId)) {
		         resp.sendError(400);
		         return;
		      }
		      
		      MemberVO member = service.retrieveMember(memId);

		      req.setAttribute("member", member);
		      req.setAttribute("command", "UPDATE");
		      
		      String commandPage = "/WEB-INF/views/member/memberForm.jsp";
		      viewResolve(commandPage, req, resp);
		   }


		   @Override
		   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		      req.setCharacterEncoding("UTF-8");
		      
		      MemberVO member = new MemberVO();
		      req.setAttribute("member", member);


		/*
		 * memVo.setMemId(req.getParameter( "id" ) );
		 * memVo.setMemPass(req.getParameter("pass"));
		 * memVo.setMemName(req.getParameter("name"));
		 * memVo.setMemZip(req.getParameter("zip"));
		 * memVo.setMemAdd1(req.getParameter("add1"));
		 * memVo.setMemAdd2(req.getParameter("add2"));
		 * memVo.setMemMail(req.getParameter("email"));
		 * 
		 * 이걸 BeanUtilse 로 간단하고 편리하게 쓸수있다
		 */

		try {
			BeanUtils.populate(member, req.getParameterMap());

		} catch (Exception e) {
			throw new RuntimeException(e);

		}

		Map<String, String> errors = new HashMap<>();
	      req.setAttribute("errors", errors);
	      
	      //검증 분리
	boolean valid = validate(member, errors);
	      
	      String commandPage = null;
	      if(valid) {
	         ServiceResult result = service.modifyMember(member);
	         switch (result) {
	         case OK:
	            commandPage = "redirect:/member/memberList.do";
	            break;

	         default:

				req.setAttribute("message", "서버 오류, 조금 이따 다시 하세요.");
				commandPage = "/WEB-INF/views/member/memberForm.jsp";
				break;
			}

		} else {
			commandPage = "/WEB-INF/views/member/memberForm.jsp";
		}

		viewResolve(commandPage, req, resp);

	}

	// Hibernate validator
	private boolean validate(MemberVO memVo, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(memVo.getMemId())) { // isBlank -> 스페이스 공백까지 null로 처리해줌
			errors.put("memId", "아이디 누락");
			valid = false;
		}
		if (memVo.getMemPass() == null) {
			errors.put("memPass", "비밀번호 누락");
			valid = false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");

		if (StringUtils.isNotBlank(memVo.getMemBir())) {
			try {
				sdf.parse(memVo.getMemBir());
			} catch (ParseException e) {
				errors.put("memBir", "날짜 형식 확인");
				valid = false;
			}
		}

		return valid;
	}
}
