package kr.or.ddit;



import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// bean으로 등록
// handler Mapping에 의해서 수집이 되어야함
// 하위 컨트롤러에 등록해야함 
// -> @Controller
@Controller
public class IndexController {

	@RequestMapping("/index.do") //*.do로 설정해줬기 때문에 
	public String index(Model model ) {
		
		Date now = new Date();
		model.addAttribute("now", now); // =request.setAttribute
		return "index"; //view resolve forwarding해줌
		//logical view name
		// /WEB-INF/jsp/index.jsp
	}
}
