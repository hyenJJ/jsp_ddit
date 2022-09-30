package kr.or.ddit.emp.dao;

import java.util.List;

import kr.or.ddit.vo.EmpVO;

public interface EmpDAO {

	
	
	/**
	 * 
	 * @param empVO
	 * @return 성공 : 1 , 실패 : 0
	 */
	public int insertEmp(EmpVO empVO);
	
	/**
	 * 
	 * @return
	 */

	public List<EmpVO> selectEmpList();
	
	
}
