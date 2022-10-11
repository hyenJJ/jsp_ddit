package kr.or.ddit.member.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Provider.Service;
import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리(Business Loginc Layer), CRUD
 * 
 * @author PC-14
 *
 */
public interface MemberService {
	
	
	/**
	 * 회원 신규 등록
	 * @param member
	 * @return {@link ServiceResult.PKDUPLICATED },{@link ServiceResult.OK }, {@link ServiceResult.FAIL } 
	 */
	public ServiceResult createMember(MemberVO member);
	
	
    /**
     * 회원 정보 상세조회
     * @param memId 조회할 회원 아이디
     * @return 존재하지 않는 경우, {@link UserNotFoundException} 발생
     */
    public MemberVO retrieveMember(String memId);
    
    /**
     * 전체 회원수 조회
     * @param pagingVO
     * @return
     */
    public int retrieveMemberCount(PagingVO pagingVO);
    
    /**
     * 회원 목록 조회, 차후 페이징과 검색 기능 추가함.
     * @param pagingVO TODO
     * @return
     */
    public List<MemberVO> retrieveMemberList(PagingVO pagingVO);
    
    
	/**
	 * 회원 정보 수정.
	 * @param member
	 * @return 존재하지 않는 경우, {@link UserNotFoundException} 발생.
	 *         {@link ServiceResult.INVALIDPASSWORD },
	 *         {@link ServiceResult.OK },
	 *         {@link ServiceResult.FAIL }
	 */
	public ServiceResult modifyMember(MemberVO member);
 	
	 
	/**
	 * 회원 정보 제거
	 * @param member
	 * @return 존재하지 않는 경우, {@link UserNotFoundException} 발생.
	 *         {@link ServiceResult.INVALIDPASSWORD },
	 *         {@link ServiceResult.OK },
	 *         {@link ServiceResult.FAIL }
	 */
	public ServiceResult removeMember(MemberVO member);

}
