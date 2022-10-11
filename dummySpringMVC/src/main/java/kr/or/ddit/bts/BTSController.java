package kr.or.ddit.bts;

import java.io.IOException;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

//최초요청이 들어요면 그때 instance 생성
//@WebServlet(value = "/bts/getContent" , loadOnStartup = 1) //서블릿 먼저 실행 시키기 위해
@Controller 
public class BTSController  {
	
	//spring에서 controller는 class가 아니다
	
	@Inject
	private WebApplicationContext context; //ApplicationContext 너무 최상위라서 Web을 앞에 붙여서 낮춰줌 
	
	//하나의 servletcontext를 공유
    private ServletContext application;



	@PostConstruct
    public void init() throws ServletException {
    
    	application = context.getServletContext();
    	
    	
    	Map<String, String[]> btsDB = new LinkedHashMap<>();
    	btsDB.put("B001",new String[] {"RM","bts/rm"});
    	btsDB.put("B002",new String[] {"JHOP","bts/jhop"});
    	btsDB.put("B003",new String[] {"JIMIN","bts/jimin"});
    	btsDB.put("B004",new String[] {"JIN","bts/jin"});
    	btsDB.put("B005",new String[] {"JUNGKUK","bts/jungkuk"});
    	btsDB.put("B006",new String[] {"SUGA","bts/suga"});
    	btsDB.put("B007",new String[] {"BUI","bts/bui"});
    	
    	application.setAttribute("btsDB", btsDB);
                                    
      }
      
    
	@RequestMapping("/bts/form")
	public String btsForm() {
		return "btsForm";
	}
	
      
	@RequestMapping("/bts/getContent")
    protected String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	  // 멤버 파라미터가 들어와야함 
    	  // 파라미터가 누락됐다면 상태코드 400
    	  // 존재하지 않은 파라미터 상태코드 
    	  // 이 모든걸 통과하면 검증
    	  
    	  Map<String, String[]> btsDB = (Map)application.getAttribute("btsDB");
    	  req.setCharacterEncoding("UTF-8");
    	  
    	  //System.out.println(mem);
    	  
    	 
    	  
    	  String member = req.getParameter("member");
    	  
    	  int status = 200;
          //parameter가 안넘어온 && 비어있는
       if(member==null && member.isEmpty()) {
          //정상처리 불가, 원인은 client에게 있음
          status = HttpServletResponse.SC_BAD_REQUEST;
          
       //넘어오긴 했다는 것, 가만 포함되지 않은 키(!B001~007)가 넘어온 경우
       }else if(!btsDB.containsKey(member) ) {
          status = HttpServletResponse.SC_NOT_FOUND;
       }
       
       
       //status에 따른 처리---------------------------------
       if(status==200) {
          String[] info = btsDB.get(member);
          String contentURL = info[1];
          return contentURL;
          //이제 이걸 가지고 이동. 이동에는 dispatch와 redirect 두가지가 있음
          //모든 contetn 페이지는 WEB-INF 안에 있음
//          req.getRequestDispatcher(contentURL).forward(req, resp);
       }else {
          resp.sendError(status);
          return null;
       }
       
    }

    	  
    	  
 /*   	  
    	  int status = 200;
    	  if(mem==null && mem.isEmpty()) {
    		  status = HttpServletResponse.SC_BAD_REQUEST;	
    	  }
    	  
    	  
    	  int statusCode = HttpServletResponse.SC_OK;
    	  Option option = null;
    	  
    	  
    	   
    	  
    	  try {
    		   
    		      option = Option.valueOf(mem);
    		  
	    		  if(!btsDB.containsKey(mem)) {
	        		  
	        		  statusCode = HttpServletResponse.SC_NOT_FOUND;
	        		  
	        	  }  
	        	  if(statusCode == 200) {
	        		  String[] info = btsDB.get(mem);
	        		  String contentURL = info[1];
	        		  req.getRequestDispatcher(contentURL).forward(req, resp);
	        	  }else {
	        		  resp.sendError(statusCode);
	        	  }
	    		  
		    } catch (Exception e) {
			   
		      statusCode = HttpServletResponse.SC_BAD_REQUEST;	
		    }
    	 
    	  
    	  
//    	  if(statusCode == 200) {
//    		  String[] info = btsDB.get(mem);
//    		  String contentURL = info[1];
//    		  req.getRequestDispatcher(contentURL).forward(req, resp);
//    	  }else {
//    		  resp.sendError(statusCode);
//    	  }
//    	
    	 
 */   	  
    	  
    	  
    	  
    	  
    	  
    	 
    }
