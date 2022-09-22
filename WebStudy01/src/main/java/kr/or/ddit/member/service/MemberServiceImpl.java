package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO dao = new MemberDAOImpl();
	
	

	@Override
	public ServiceResult createMember(MemberVO member) {
		
		
        ServiceResult result = null;
		
		try {
			retrieveMember(member.getMemId());
			result = ServiceResult.PKDUPLICATED;
			
		} catch (UserNotFoundException e) {
			
			int rowcnt = dao.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		
		}
		return result;
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = dao.selectMember(memId);
		
		if(member==null)
			throw new UserNotFoundException(memId);
	
		return member;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {

		return dao.selectMemberList();
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		retrieveMember(member.getMemId());
		int rowcnt = dao.deleteMember(member.getMemId());
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}
