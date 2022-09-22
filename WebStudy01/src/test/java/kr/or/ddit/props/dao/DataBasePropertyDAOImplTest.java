package kr.or.ddit.props.dao;

import org.junit.Test;

import kr.or.ddit.props.vo.PropertyVO;

public class DataBasePropertyDAOImplTest {
	
	PropertyDAO dao = new DataBasePropertyDAOImpl();

	@Test
	public void testSelectProperty() {
		PropertyVO vo = dao.selectProperty("1' or '1'='1");
		System.out.println(vo);
//	    assertNull(vo);//null인지 추측
	}

}
