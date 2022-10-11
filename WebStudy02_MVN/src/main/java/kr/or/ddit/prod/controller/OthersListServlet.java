package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

@WebServlet({"/prod/getLprodList.do", "/prod/getBuyerList.do"})
public class OthersListServlet extends HttpServlet{
   private OthersDAO othersDAO = new OthersDAOImpl();
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     
      
      // 들어온 서블릿경로 둘중에 무엇인지 확인 
      String servletPath = req.getServletPath();
      if("/prod/getLprodList.do".equals(servletPath)) {
         processLprodList(req, resp); 
      }else if("/prod/getBuyerList.do".equals(servletPath)) {
         processBuyerList(req, resp);
      }
      
   }
   
   private void processLprodList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
      req.setAttribute("model", lprodList);
      String viewName = "/jsonView.do";
      req.getRequestDispatcher(viewName).forward(req, resp);
   }

   private void processBuyerList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String lprodGu = req.getParameter("lprodGu"); //lprodGU파라미터를 통해서 선택한 분류를 받음
      List<BuyerVO> buyerList = othersDAO.selectBuyerList(lprodGu); 
      req.setAttribute("model", buyerList);
      String viewName = "/jsonView.do";
      req.getRequestDispatcher(viewName).forward(req, resp);
   }

}












