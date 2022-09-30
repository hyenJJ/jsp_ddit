package kr.or.ddit.prod.view;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.vo.MenuVO;

public class ProdViewPreparer implements ViewPreparer {

	@Override
	public void execute(Request req, AttributeContext context) {
		
		MenuVO menu1 = new MenuVO("/prod/prodList.do", "상품목록조회");
		MenuVO menu2 = new MenuVO("/prod/prodInsert.do", "신규상품등록");
		List<MenuVO> menuList = Arrays.asList(menu1,menu2);
		Map<String, Object> requestScope = req.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menuList", menuList);
	}

}
