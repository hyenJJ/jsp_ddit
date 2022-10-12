package kr.or.ddit.etc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.vo.CalculateVO;

@Controller
@RequestMapping("/calculate") //calculate안에 있는 모든 요청은 여기 안에서 받을 것
public class CalculateController {
	
	
	@RequestMapping("form") // = /calculate/form
	public String calForm() {
		
		return "etc/calculateForm";
		
	}
	
	// case1. JSON => JSON
	// response meassage = response content
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)  //@RequestMapping(mothod = RequestMethod.POST)
	@ResponseBody //logicalViewName 없을 때 
	public CalculateVO jsonToJson(@RequestBody CalculateVO vo) {
					// 어뎁터가 알아서 언마샬링 해줌
		
		 return vo;
		
	}
	
	
	
	// case2. JSON => HTML
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String jsonToHtml( @RequestBody CalculateVO vo, Model model) {
		                                          // json으로 받아서 언마샬링을 하기위해 @requestbody
		model.addAttribute("calculate",vo);
		return "/etc/calculateForm_html" ;
	}
	
	// case3. parameter => JSON
	// consumes(request content) 를 빼줌 -> parameter로 옴
	@PostMapping( produces = MediaType.APPLICATION_JSON_UTF8_VALUE)  //@RequestMapping(mothod = RequestMethod.POST)
	@ResponseBody //lgicalViewName 가 없을 때 
	public CalculateVO parameterToJson( CalculateVO vo) {
		// 어뎁터가 알아서 언마샬링 해줌
		
		return vo;
		
	}
	// case4. parmeter => HTML
	@PostMapping       //parameter로 받아서  언마샬링 필요 X(@requestbody X)
	public String parameterToHtml(@ModelAttribute("calculate") CalculateVO vo) {
		 											//이때 vo -> command Object 이면서 model
		return "/etc/calculateForm_html" ;
	}
    // 모두 post 방식 
}
