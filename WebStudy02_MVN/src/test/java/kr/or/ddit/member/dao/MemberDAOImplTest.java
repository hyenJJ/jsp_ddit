package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	MemberDAO dao = new MemberDAOImpl();

    MemberVO member = new MemberVO();
	@Before
	public void setUp() {
		member = new MemberVO();
	       member.setMemId("b002"); 
	      member.setMemPass( "java" );
	      member.setMemName( "신규" );
	      member.setMemZip( "000" );
	      member.setMemAdd1( "대전" );
	      member.setMemAdd2( "오류" );
	      member.setMemMail( "aa@naver.com" );
	}
	@Test
	public void testInsertMember() {


		int rowcnt = dao.insertMember(member);
		System.out.println(rowcnt);
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember(null);
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		System.out.println(memberList);
	}

	@Test
	public void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
		fail("Not yet implemented");
	}

}
