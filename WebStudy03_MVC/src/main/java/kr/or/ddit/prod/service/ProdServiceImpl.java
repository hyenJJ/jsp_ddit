package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.commons.exception.UserNotFoundException;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdServiceImpl implements ProdService {
	//dao 객체 생성해 주기
	
	private ProdDAO dao = new ProdDAOImpl();

	@Override
	public ServiceResult createProd(ProdVO prod) {
		
		
		
	   ServiceResult result = null;
		
	   // 검색을 통해 prod id 가 존재 하는 지 확인
		try {  
			
			retrieveProd(prod.getProdId());
			result = ServiceResult.PKDUPLICATED;
			
		} catch (UserNotFoundException e) {
			
			int rowcnt = dao.insertProd(prod);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		
		}
		return result;
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		
	    ProdVO prod = dao.selectProd(prodId);
		
		if(prod==null)
			throw new UserNotFoundException(prodId);
	
		return prod;
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO pagingVO) {
		
		return dao.selectProdList(pagingVO);
	}

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		
		retrieveProd(prod.getProdId());
		int rowcnt = dao.updateProd(prod);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public int retrieveProdCount(PagingVO pagingVO) {
		
		
		return dao.selectTotalRecord(pagingVO);
	}


}
