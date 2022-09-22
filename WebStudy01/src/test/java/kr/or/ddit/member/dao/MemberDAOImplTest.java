package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOImplTest {
	
	MemberDAO dao = new MemberDAOImpl();

	@Test
	public void testInsertMember() {
		   MemberVO member = new MemberVO();
		      member.setMemId( "a002" );
		      member.setMemPass( "java" );
		      member.setMemName( "신규" );
		      member.setMemZip( "000" );
		      member.setMemAdd1( "대전" );
		      member.setMemAdd2( "오류" );
		      member.setMemMail( "aa@naver.com" );
		      int rowcnt = dao.insertMember( member );
		      assertEquals( 1 , rowcnt );
	}

	@Test
	public void testSelectMember() {
		MemberVO member = dao.selectMember("a001");
		assertNotNull(member);
	}
	
	@Test
	public void testSelectMemberNotExist() {
		MemberVO member = dao.selectMember("asdaqw");
		assertNull(member); 
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memList = dao.selectMemberList();
		assertNotNull(memList);
		assertNotEquals(0, memList.size());
		assertNotNull(memList.get(0).getMemName()); //null이여서는 안되는 것들
	}

	@Test
	public void testUpdateMember() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteMember() {
	
	}

}
