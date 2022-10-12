package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.board.vo.SearchVO;
//동기처리 
@Controller
@RequestMapping("/boardSync")
public class BoardSynListController {

	@Inject         // 다른 동급의 하위컨트롤러 주입 
	private BoardRetrieveController otherController;
	

	
	// 동기 처리 -> model을 가져가야함
	@RequestMapping("boardList.do")
	public String list(
			@RequestParam(name="page", required=false , defaultValue="1" ) int currentPage
			, @ModelAttribute("simpleCondition") SearchVO simpleCondition 
			, Model model   	
			
		) {
		
		PagingVO<BoardVO> pagingVO =  otherController.list(currentPage, simpleCondition);
		model.addAttribute("pagingVO",pagingVO);
		
		return "board/boardListSync";
		
	}
	
	
}
