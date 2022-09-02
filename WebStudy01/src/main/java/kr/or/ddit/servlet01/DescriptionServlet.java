package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;*/


//(Alt + shift + j)
/**  
 * 서블릿
 *  : 웹상의 요청을 받고, 그에 대한 응답한 생성할 수 있는 객체가 가져야하는 조건들에 대한 명세서(Spec) 
 *    => HttpServlet
 *       
 *   개발 단계
 *   1. HttpServlet 스펙 상속
 *   2. Compile => classpath 에 배치
 *   3. 서블릿 컨테이너에 등록
 *       1) servlet 2.x : web.xml 에 servlet 엘리먼트로 등록
 *       2) servlet 3.x : @WebServlet 으로 등록.
 *   4. 서블릿 매핑 등록
 *       1) servlet 2.x : web.xml 에 servlet-mapping 으로 등록
 *       2) servlet 3.x : @WebServlet(urlPatterns) 등록.
 *   5. Container 재구동.
 *   
 *   Servlet Container ? 서블릿의 생명주기 관리자 (생명주기 내에서 호출되는 callback 을 정의함)
 *        callback : 특정 이벤트가 발생했을 때 시스템 내부적으로 자동 호출되는 구조.
 *        생성 : init, 특별한 설정(load-on-startup)이 없는 한 해당 서블릿을 대상으로 최초의 요청이 발생하면 객체가 생성됨.
 *                    생성 시점에 parameter(String) 넘길 수 있는 구조를 가짐.
 *                    서블릿을 싱글턴의 형태로 관리함.
 *                    서블릿을 싱글턴(singleton)의 형태로 관리함.
 *        요청 : 
 *            - service : 현재 요청의 http method를 판단하고, 해당 method callback 을 호출함.
 *            - doXXX : service 에 의해 호출되는 hook 메소드로, 특정 http method 하나를 처리하는 메소드.
 *        소면 : destroy
 *        
 *   서블릿 스펙에서 제공되는 객체
 *    1. HttpServlet : 서블릿 스펙의 코드화.
 *    2. HttpServletRequest : 하나의 요청에 대한 정보를 캡슐화한 객체.. Stateless 방식으로 동작.
 *    3. HttpServlet Response : 응답으로 전송될 정볼르 캡슐화한 객체.
 *    4. HttpSession : 한 클라이언트의 하나의 브라우저를 이용한 경우 생성되는 세션에 대한 정보를 캡슐화한 객체.
 *    5. ServletContext : 서버(WAS, Servlet Container)와 특정 context 의 정보를 캡슐화한 객체.
 *                        하나의 context(application) 당 싱글턴 객체의 형태로 관리됨.
 *    6. ServletConfig : 하나의 서블릿 객체당 하나씩 운영되는 객체로, 해당 서블릿이 등록될때 설정 정보를 가진 객체.
 *    
 *   
 *   $("#btn").on("change", function(){})
 *   
 */
@SuppressWarnings("serial")
public class DescriptionServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
    	
    	super.init(config);
    	String paramValue = config.getInitParameter("param1");
    	System.out.printf("%s 객체 생성 및 초기화, 전달 파라미터 : % \n" 
    			           , this.getClass().getName(), paramValue);
    }
    
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("super.service 호출 전 ");
		super.service(req, resp);
		System.out.println("super.service 호출 후 ");
	}
    
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet 호출");
		
	}
    
    @Override
    	public void destroy() {
    		
    		super.destroy();
    		System.out.printf("%s 객체 소멸 \n" , this.getClass().getName());	
    }
}
