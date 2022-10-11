package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public interface ProdDAO {
	public int insertProd(ProdVO prod);
	
	/**
	 * 
	 * @param prodId
	 * @return 실패 null
	 */
    public ProdVO selectProd(@Param("prodId")String prodId); 
                                //파라미터 키
    /**
     * 
     * @return size를 통해서 null 값을 확인
     */
    public List<ProdVO>	selectProdList(PagingVO<ProdVO> pagingVO);
    
    /**
     * 
     * @param prod
     * @return
     */
    public int updateProd(ProdVO prod);
    
    
    public int selectTotalRecord(PagingVO<ProdVO> pagingVO);
    
    
//    public int	deleteProd(String prodId);

}
