package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public int insertMember(MemberVO member) {
		try(
		    SqlSession sqlSession = sqlSessionFactory.openSession();
		) {
		  int rowcnt =  sqlSession.insert("kr.or.ddit.member.dao.MemberDAO.insertMember",member);
		  sqlSession.commit();
		  return rowcnt;
		}
   	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
				   SqlSession sqlSession = sqlSessionFactory.openSession();		
		   ) {
				  return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember",memId); 
		  } 
	}

	@Override
	public List<MemberVO> selectMemberList() {
		try(
		   SqlSession sqlSession = sqlSessionFactory.openSession();		
		) {
		  return sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");	
		} 
		
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
