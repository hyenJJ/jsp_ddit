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


import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertServlet extends HttpServlet {

	private MemberService service = new MemberServiceImpl(); // 컨트롤러와 서비스 사이에 결합력 발생

	private void viewResolve(String logicalViewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (logicalViewName.startsWith("redirect:")) {
			logicalViewName = logicalViewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + logicalViewName);

		} else {

//			req.setAttribute("commandPage", commandPage);

			String viewName = "/"+logicalViewName+".tiles";
			req.getRequestDispatcher(viewName).forward(req, resp);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("command", "INSERT");
		String logicalViewName = "member/memberForm";
		viewResolve(logicalViewName, req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		MemberService service = new MemberServiceImpl();

		MemberVO memVo = new MemberVO();

		req.setAttribute("prod", memVo);

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
			BeanUtils.populate(memVo, req.getParameterMap());

		} catch (Exception e) {
			throw new RuntimeException(e);

		}

//		Map<String, String> errors = new HashMap<>();
		
		Map<String, String> errors = new ValidateUtils<MemberVO>().validate(memVo, InsertGroup.class);
		req.setAttribute("errors", errors);


		String logicalViewName = null;
			
		if (errors.isEmpty()) { // = 에러가 없다
			ServiceResult result = service.createMember(memVo);
			switch (result) {
			case PKDUPLICATED:

				req.setAttribute("message", "아이디 중복");
				logicalViewName = "member/memberForm";

				break;
			case OK:
				logicalViewName = "redirect:/member/memberList.do";

				break;

			default:
				req.setAttribute("message", "서버 오류, 조금 이따 다시 하세요.");
				logicalViewName = "member/memberForm";
				break;
			}

		} else {
			logicalViewName = "member/memberForm";
		}

		viewResolve(logicalViewName, req, resp);

	}

	// Hibernate validator
	/*private boolean validate(MemberVO memVo, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(memVo.getMemId())) { // isBlank -> 스페이스 공백까지 null로 처리해줌
			errors.put("memId", "아이디 누락");
			valid = false;
		}
		if (memVo.getMemPass() == null) {
			errors.put("memPass", "비밀번호 누락");
			valid = false;
		}
		if (memVo.getMemName() == null) {
			errors.put("memName", "회원명 누락");
			valid = false;
		}
		if (memVo.getMemRegno1() == null) {
			errors.put("memRegno1", "주민번호1 누락");
			valid = false;
		}
		if (memVo.getMemRegno1() == null) {
			errors.put("memPass2", "주민번호2 누락");
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
	}*/
}
