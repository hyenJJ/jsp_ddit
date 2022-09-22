package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.do")
public class IndexServlet extends HttpServlet{
   // 데이터 공유를 위한 application scope 사용
   private ServletContext application;
   
   @Override
   public void init(ServletConfig config) throws ServletException {
      super.init(config);
      application = getServletContext();
      Map<String, String> commandDB = new LinkedHashMap<>();
      commandDB.put("CALENDAR", "/05/calendar.jsp");
      commandDB.put("FACTORIAL", "/02/factorial.jsp");
      commandDB.put("CALCULATOR", "/02/calculateForm.jsp");
      application.setAttribute("commandDB", commandDB);
   }
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Map<String, String> commandDB = (Map)application.getAttribute("commandDB");
      
      String command = req.getParameter("command");
      //index에서 사용하는 commandPage를 여기서 선언 해 줘야 함
      String commandPage = null;
      int status = 200;
      
      if(command==null || command.isEmpty()) {
         // 여기서는 commandPage가 index.jsp여야 하고 
         commandPage = "/WEB-INF/views/index.jsp";
      }else {
         // 여기서는 경우에따라 commandPage가 바뀌어야 함
         // 정해진 3개의 페이지, 상수의 집합인 enum 또는 Map을 사용할 수도 있음.
         if(!commandDB.containsKey(command)) {
            status = HttpServletResponse.SC_NOT_FOUND;
         }else {
            commandPage = commandDB.get(command);
         }
      }
      if(status==200) {
         //request, session, application scope 중 사용하면 되는데
         //서버에 부하를 주지 않도록 최소한의 영역을 주는 request scope 사용
         req.setAttribute("commandPage", commandPage);
         
         String viewName = "/WEB-INF/views/template.jsp";
         req.getRequestDispatcher(viewName).forward(req, resp);
      }else {
         resp.sendError(status);
      }
                   
      
      
   }
}