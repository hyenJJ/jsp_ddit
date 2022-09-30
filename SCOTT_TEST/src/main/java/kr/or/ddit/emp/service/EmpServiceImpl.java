package kr.or.ddit.emp.service;

import java.util.List;

import kr.or.ddit.emp.dao.EmpDAO;
import kr.or.ddit.emp.dao.EmpDAOImpl;
import kr.or.ddit.vo.EmpVO;

public class EmpServiceImpl implements EmpService {

	
	private EmpDAO dao = new EmpDAOImpl();
	@Override
	public List<EmpVO> retrieveMemberList() {
		
		return dao.selectEmpList();
	}

}
