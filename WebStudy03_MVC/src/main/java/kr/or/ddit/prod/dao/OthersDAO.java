package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.or.ddit.vo.BuyerVO;

/**
 * 
 * 상품분류와 거래처 선택 UI 생성 정보 조회.
 *
 */
public interface OthersDAO {
	public List<Map<String, Object>> selectLprodList();
	public List<BuyerVO> selectBuyerList(@Param("LprodGu") String lprodGu);
	

}
