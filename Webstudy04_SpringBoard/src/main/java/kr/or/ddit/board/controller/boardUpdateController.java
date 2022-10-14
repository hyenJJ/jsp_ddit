package kr.or.ddit.board.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.validate.UpdateGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/board/boardUpdate.do")
public class boardUpdateController {

	
	private final BoardService service;

	
	
	
	@GetMapping
	public String updateForm(
			@RequestParam(name="what" ,required=true) int boNo
			,Model model
			
	) {
		
		
		BoardVO board = service.retrieveBoard(boNo);
		model.addAttribute("board" , board);
		return "board/boardEdit";// 리턴 타입이 model과 view
		
	
	}
	
	@PostMapping
	public String boardUpdate(
			//insertGroup을 제외한 4개 검증 
			@Validated(UpdateGroup.class) @ModelAttribute("board") BoardVO board 
			,BindingResult errors // BindingResult 는 Errors에게서 상속 받음 
		    , Model model
	) {
		
		String viewName = null;
		
		if(!errors.hasErrors()) {
			log.info("boardUpdateController의 62번째 행");
			ServiceResult result = service.modifyBoarde(board);
			String message = null;
			
			switch(result) {
			case INVALIDPASSWORD:
				log.info("boardUpdateController의 68번째 행");
				
				message = "비밀번호 오류";
				viewName = "board/boardEdit";
				break;
			case OK:
				
				log.info("boardUpdateController의 75번째 행");
				viewName = "redirect:/board/boardView.do?what="+board.getBoNo();
				break;
			default:
				log.info("boardUpdateController의 79번째 행");
				
				message = "서버오류";
				viewName = "board/boardEdit";
				break;
			}
			
			model.addAttribute("message", message);
		}else{
			
			viewName = "board/boardEdit"; //error -> 기존의 입력 데이터와 검증 결과 데이터 가져가야함 
		}
		
		return viewName;
	}
	
	
/*	public String updateForm(
	 @RequestParam(name="wath", required=true)		
	) {
		
		
	}
	*/
}
