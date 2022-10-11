    package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.file.MultipartFile;
import kr.or.ddit.file.filter.StandardMultipartHttpServletRequest;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberUpdate.do")
@MultipartConfig
public class MemberUpdateServlet extends HttpServlet{
	
	private MemberService service = new MemberServiceImpl();
	
	private void viewResolve(
			String logicalViewName, 
			HttpServletRequest req, 
			HttpServletResponse resp
	) throws ServletException, IOException{
		if(logicalViewName.startsWith("redirect:")) {
			logicalViewName = logicalViewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + logicalViewName);
		}else {
			String viewName = "/"+logicalViewName+".tiles";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
	
	@Override
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
		
		String logicalViewName = "member/memberForm";
		viewResolve(logicalViewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
//		member.setMemId(req.getParameter("memId"));
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		if(req instanceof StandardMultipartHttpServletRequest) {
			MultipartFile memImage =  ((StandardMultipartHttpServletRequest) req).getFile("memImage");
			member.setMemImage(memImage);
		}
		
		Map<String, String> errors = new ValidateUtils<MemberVO>().validate(member, UpdateGroup.class);
		req.setAttribute("errors", errors);
		
		String logicalViewName = null;
		if(errors.isEmpty()) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case OK:
				logicalViewName = "redirect:/member/memberList.do";
				break;

			default:
				req.setAttribute("message", "서버 오류, 쫌따 다시 하셈.");
				logicalViewName = "member/memberForm";
				break;
			}
		}else {
			logicalViewName = "member/memberForm";
		}
		viewResolve(logicalViewName, req, resp);
	}
}






















    
