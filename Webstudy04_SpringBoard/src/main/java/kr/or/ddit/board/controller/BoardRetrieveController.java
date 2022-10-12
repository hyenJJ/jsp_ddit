package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.board.vo.SearchVO;



/**
 * 
 * HandlerAdapter 사용 방법(컨트롤러 구현 방법)
 * 1. @Controller : 컨테이너에 빈으로 등록되며, HanlerMapping 에 의해 수집되는 컨트롤러 객체임.
 * 2. @RequestMapping (method-level) : 컨트롤러가 처리 가능한 요청을 제한(mapping)함.
 * 		- value : request URI
 * 		- method : http method
 * 		- params : request parameter
 * 		- headers : request header
 * 		- consume : request Content-Type
 * 		- produce : request Accept / response Content-Type 
 * 3. 요청과 관련된 모든 데이터는 handlerAdapter 로 부터 받되, 핸들러 메소드 파라미터 형태로.
 * 		1) @RequestXXX : RequestParam, RequestHeader, RequestPart
 * 		2) @ModelAttribute : 요청 파라미터를 VO 객체로 받을 때 , 특정(보통 request 를 사용) scope 을 통해 모델 속성을 공유하게 됨.
 * 		3) @RequestBody : request body(message, content) 를 Command Object(ex Calculate VO) 를 이용해 받을때,
 * 				@RequestMapping(consumes, unmarshalling)
 * 		4) @ResponseBody : 핸들러 메소드의 리턴값으로 response body(message, content) 를 구성할 때.
 * 				@RequestMapping(produces, marshalling)
 */	
@Controller
@RequestMapping("/board") // 앞에 /board 가 공통이기 때문에 따로 설정해줌 
public class BoardRetrieveController {

	@Inject
	private BoardService service;
	
	
	
	@RequestMapping(value="boardList.do", method=RequestMethod.GET)
	public String listUI() {
		return "board/boardList";
	}
	
	@ResponseBody
	@RequestMapping(value="boardList.do", method=RequestMethod.GET ,  produces= MediaType.APPLICATION_JSON_UTF8_VALUE )
	public PagingVO<BoardVO> list(
			
//                name = "page"  요청파라미터 이름  /  required=ture ->꼭 필요해  / -> 자료형이 int이므로 default로  null이 올수가 없어서 1로 정해줌
	@RequestParam(name="page", required=false , defaultValue="1" ) int currentPage
	
//				, @RequestParam(required=false) String searchType
//				, @RequestParam(required=false) String searchWord
	
				, @ModelAttribute("simpleCondition") SearchVO simpleCondition 
//				, Model model      simpleCondition 이름으로 model 데이터를 공유
	) { 
		//handler adapter에게 필요
		//handler mappging 의 파라미터와 요청파라미터가 같으면 생략가능
			
		
		PagingVO<BoardVO> pagingVO = new PagingVO<>(10,3);
		pagingVO.setCurrentPage(currentPage);
		
		pagingVO.setSimpleCondition(simpleCondition);
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO > boardList = service.retrieveBordList(pagingVO);
		pagingVO.setDataList(boardList);
		
//		model.addAttribute("pagingVO", pagingVO);
		
		return pagingVO; //WEB-INF/jsp/boardList.jsp
	}
	

	
	
	// 슬러시 자동생성됨
	@RequestMapping("boardView.do") //  = /board/boardView.do
	public ModelAndView boardView(@RequestParam(name="what", required = true) int boNo) {
		BoardVO board = service.retrieveBoard(boNo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("board" , board); // = add attribute
		mav.setViewName("board/boardView"); //logical viewName
		return mav;// 리턴 타입이 model과 view
		
	}
	

			
	
}
