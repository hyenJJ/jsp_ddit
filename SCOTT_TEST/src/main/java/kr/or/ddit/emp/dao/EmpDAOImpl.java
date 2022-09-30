package kr.or.ddit.emp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.EmpVO;

public class EmpDAOImpl implements EmpDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); 
	
	@Override
	public int insertEmp(EmpVO empVO) {
		

		
		return 0;
	}

	
	
	@Override
	public List<EmpVO> selectEmpList() {
		
		
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			
			EmpDAO mapper = sqlSession.getMapper(EmpDAO.class);
			return mapper.selectEmpList();
		}	
	}

}
