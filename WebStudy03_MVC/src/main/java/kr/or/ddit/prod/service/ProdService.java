package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

/**
 * 상품 관리 Business Login Layer
 * @author PC-14
 *
 */
public interface ProdService {
	
	
	
	/**
	 * 
	 * @param prod
	 * @return
	 */
	public ServiceResult createProd(ProdVO prod);
	
	/**
	 * 
	 * @param prodId
	 * @return 실패 null
	 */
    public ProdVO retrieveProd(String prodId);
    
    /**
     * 
     * @return size를 통해서 null 값을 확인
     */
    public List<ProdVO>	retrieveProdList(PagingVO<ProdVO> pagingVO);
    
    /**
     * 
     * @param prod
     * @return 예외 ok pk
     */
    public ServiceResult modifyProd(ProdVO prod);
    
    
    public int retrieveProdCount(PagingVO<ProdVO> pagingVO);

}
