package kr.or.ddit.member.dao;



import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberDAOImplTest {
//	private static final Logger log = LoggerFactory.getLogger(MemberDAOImplTest.class);
	                                                         // = kr.or.ddit
	MemberDAO dao = new MemberDAOImpl();

    MemberVO member ;
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
		log.info("rowcnt : {}", rowcnt );
	 	             //message argument 
	}

	@Test
	public void testSelectMember() {
        MemberVO member = dao.selectMember("a001");
		
		log.info("memId : {}", member.getMemId());
		log.info("memBir : {}" , member.getMemBir());
		log.info("prodList : {}" , member.getProdList());

		
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList(pagingVO);
		
		log.info("memberList : {}" , memberList);
	}
	   @Test
	   public void testUpdateMember() {
	      dao.updateMember(member);
	   }

	   @Test
	   public void testDeleteMember() {
	      dao.deleteMember(member.getMemId());
	   }

	}
