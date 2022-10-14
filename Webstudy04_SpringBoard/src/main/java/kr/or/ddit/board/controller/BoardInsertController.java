package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.board.event.NewBoardEvent;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.InsertGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board/boardInsert.do")
public class BoardInsertController {
	
	private final BoardService service;
	
	@Inject
	private WebApplicationContext context; // 컨테이너 
	
	// get,post 핸들러 실행되기전에 한번 실행됨 
	@ModelAttribute("board")
	public BoardVO board() {
		log.info("@ModelAttribute 메소드 실행.");
		return new BoardVO();
	}

	
	
	@GetMapping          
	public String boardForm() {
		log.info("Get 메소드 핸들러 boardForm 실행");
		return "board/boardForm";
	}
	
	
	//새글 쓰기가 완성되면 해당글의 상세페이지로 보내야함 
	@PostMapping
	public String boardInsert(   //boNo를 뺀 5개 검증
			@Validated(InsertGroup.class) @ModelAttribute("board") BoardVO board
			, Errors errors // request에 자동으로 담겨있음 / 반드시 errors는  검증의 대상이 되는 terget 다음에 놓아야함
			, Model model
			
		) {
		
		
		log.info("Post 메소드 핸들러 boardInsert 실행");
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.createBoard(board);
			
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
				NewBoardEvent event =  new NewBoardEvent(board);
				context.publishEvent(event);
			}else {
				String message = "등록 실패";
				model.addAttribute("message", message); 
				viewName = "board/boardForm";
			}
		}else {
			viewName = "board/boardForm";
			
		}
		
		return viewName;
	}
}
