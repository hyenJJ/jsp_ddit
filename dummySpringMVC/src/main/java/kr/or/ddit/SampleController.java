package kr.or.ddit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SampleController {
	
	@RequestMapping("/01/sample.do")                                              //요청헤드중에 accept만 꺼내죠
	public String handler1(Model model,@RequestParam(required=true) int param1, @RequestHeader("Accept") String accept) {
		System.out.println("handler1 동작 완료");
		String infomation = "생성한 모델정보+param1 : " + param1 + ", accept header : " + accept;
		
		
		model.addAttribute("infomation", infomation);
		return "01/sampleView";
	}
}
