package kr.or.ddit.login.service;

import java.util.Map;

import javax.validation.groups.Default;

import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.login.BadCredentialException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.validate.ValidateUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticationServiceImpl implements AuthenticationService {

	MemberDAO dao = new MemberDAOImpl();
	
	
	@Override
	public MemberVO authenticate(MemberVO inputData) {
		
//		{@link UserNotFoundException}, {@link BadCredentialException}
//		 * 			인증에 성공한 경우, (이름, 휴대폰, 이메일, 아이디, 비밀번호)를 가진 {link@ MemberVO} 반환	
		
	MemberVO member = null;
		

	
		
	member =  dao.selectMember(inputData.getMemId());
	
	if(member == null) {
		throw new UserNotFoundException(inputData.getMemId());		
    }
	String inputPass = inputData.getMemPass();
	String savedPass = member.getMemPass();
	if(savedPass.equals(inputPass)) {
		return member;
	}else{
		throw new BadCredentialException("비밀번호 오류");
		
	}
	
	}
}