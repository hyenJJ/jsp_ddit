package kr.or.ddit.vo;

import java.security.Principal;
//adapter 안에 ataptee로 memberVO를 넣어줌 

public class MemberVOWrapper implements Principal{
	
	private MemberVO realUser;
	
	//memverVO 없이는 adapter를 만들수 없는 구조
	public MemberVOWrapper(MemberVO realUser) { 
		super();
		this.realUser = realUser;
	}

	@Override
	public String getName() { //memberId
		
		return realUser.getMemId();
	}
	
	
	public MemberVO getRealUser() {
		return realUser;
	}
	
	
	
}
