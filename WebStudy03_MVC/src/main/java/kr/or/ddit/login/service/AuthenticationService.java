package kr.or.ddit.login.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

public interface AuthenticationService {
	
	/**
	 * 아이디와 비밀번호 기반의 인증 서비스
	 * @param inputData
	 * @return {@link UserNotFoundException}, {@link BadCredentialException}
	 * 			인증에 성공한 경우, (이름, 휴대폰, 이메일, 아이디, 비밀번호)를 가진 {link@ MemberVO} 반환
	 */
	public MemberVO authenticate(MemberVO inputData);//아이디랑 패스워드를 받음
	
}
