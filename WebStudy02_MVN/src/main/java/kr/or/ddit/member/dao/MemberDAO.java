package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 
 * 회원 관리(Persistencfe Layer), CRUD
 *
 */
public interface MemberDAO {

	/**
	 * 회원 정보 등록 
	 * @param member
	 * @return 성공 : 1  실패 : 0
	 */
	public int insertMember(MemberVO member);
	
	
	
	/**
	 *  페이징 처리를 위한 totalRecord 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO pagingVO);
	/**
	 * 회원 상세 조회
	 * @param memId 조회할 회원의 아이디
	 * @return 존재하지 않는다면, null 반환
	 */
	public MemberVO selectMember(String memId);
   
	/**
	 * 회원 목록 조회
	 * @param pagingVO TODO
	 * @return size == 0 -> 테이블 empty
	 */
	public List<MemberVO> selectMemberList(PagingVO pagingVO);
    
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 성공 : 1 실패 : 0
	 */
	public int updateMember(MemberVO member);
	
	/**
	 * 회원 정보 삭제
	 * @param memId
	 * @return 성공 : 1 실패 : 0
	 */
	public int deleteMember(@Param("memId") String memId);
	
	
}
