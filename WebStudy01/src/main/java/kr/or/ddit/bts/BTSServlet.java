package kr.or.ddit.bts;

import java.io.IOException;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//최초요청이 들어요면 그때 instance 생성
@WebServlet(value = "/bts/getContent" , loadOnStartup = 1) //서블릿 먼저 실행 시키기 위해
public class BTSServlet extends HttpServlet {
	
	//하나의 servletcontext를 공유
    private ServletContext application;



	@Override
    public void init(ServletConfig config) throws ServletException {
    	
    	  //여기서 만든 map을 doget, form에서 사용
    	  // 어떤 scope을 사용해야할까 - map을 jsp , servlet 모두 사용하기위해선 application scope 사용
    	  // web-inf로 갈수있는 이동방식 둘중에 하나를 정하기 
    	super.init(config);
    	application = getServletContext();
    	
    	
    	Map<String, String[]> btsDB = new LinkedHashMap<>();
    	btsDB.put("B001",new String[] {"RM","/WEB-INF/views/bts/rm.jsp"});
    	btsDB.put("B002",new String[] {"JHOP","/WEB-INF/views/bts/jhop.jsp"});
    	btsDB.put("B003",new String[] {"JIMIN","/WEB-INF/views/bts/jimin.jsp"});
    	btsDB.put("B004",new String[] {"JIN","/WEB-INF/views/bts/jin.jsp"});
    	btsDB.put("B005",new String[] {"JUNGKUK","/WEB-INF/views/bts/jungkuk.jsp"});
    	btsDB.put("B006",new String[] {"SUGA","/WEB-INF/views/bts/suga.jsp"});
    	btsDB.put("B007",new String[] {"BUI","/WEB-INF/views/bts/bui.jsp"});
    	
    	application.setAttribute("btsDB", btsDB);
                                    
      }
      
      
      
      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
          //이제 이걸 가지고 이동. 이동에는 dispatch와 redirect 두가지가 있음
          //모든 contetn 페이지는 WEB-INF 안에 있음
          req.getRequestDispatcher(contentURL).forward(req, resp);
       }else {
          resp.sendError(status);
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
