package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.vo.CalculateVO;


public class CalculateController{
 
    
    @RequestMapping(value="/calculate", method=RequestMethod.POST
    		, headers="content-type:application/json"
    		, produces = "application/json") 
    @ResponseBody // 데이터를 json객체로 mashalling 해줌
    public CalculateVO doPostJson(@RequestBody CalculateVO vo) throws ServletException, IOException {
      // @RequestBody request body 안에 json으로 되어있어서 unmashalling 해줌
       
    	return vo;
    }
    
    @RequestMapping(value="/calculate")//get방식이든 post 방식이던 parameter가 넘어오면 알아서 넣어줌		
    @ResponseBody
    public String doPost(CalculateVO vo) throws ServletException, IOException {

    	return vo.getExpression();
    }

	
//	@RequestMapping("/calculate") //알아서 파싱해줌
//	@ResponseBody	// /WEB-INF/view/3+3=6  이렇게 logic주소랑 같이 넘어가지 않게 하기 위해서 써줌
//	public String doGet(int leftOp, int rightOp, OperatorType operator) throws ServletException, IOException {
//
// 			String expression = operator.getExpression(leftOp, rightOp);
//			return expression;
//			
//	}
    
}


















