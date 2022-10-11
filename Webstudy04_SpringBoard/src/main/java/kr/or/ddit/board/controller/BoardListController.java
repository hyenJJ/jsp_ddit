package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;

@Controller
public class BoardListController {

	@Inject
	private BoardService service;
	
	@RequestMapping(value="/board/boardList.do", method=RequestMethod.GET)
	public String list(
			
	//name = "page"  요청파라미터 이름  /  required=ture ->꼭 필요해  / -> 자료형이 int이므로 default로  null이 올수가 없어서 1로 정해줌
	@RequestParam(name="page", required=false , defaultValue="1" ) int currentPage
				,Model model
	) { //handler adapter에게 필요
		//handler mappging 의 파라미터와 요청파라미터가 같으면 생략가능
		
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);;
		List<BoardVO > boardList = service.retrieveBordList(pagingVO);
		pagingVO.setDataList(boardList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "board/boardList"; //WEB-INF/jsp/boardList.jsp
	}
}
