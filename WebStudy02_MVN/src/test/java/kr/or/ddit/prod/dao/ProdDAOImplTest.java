package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProdDAOImplTest {
   
   ProdDAO dao = new ProdDAOImpl();

   @Test
   public void testInsertProd() {
      fail("Not yet implemented");
   }

   @Test
   public void testSelectProd() {
      ProdVO prod = dao.selectProd("sdfasdasdf");
      assertNull(prod);
      prod = dao.selectProd("P101000001");
      assertNotNull(prod);
      log.info("prod: {}", prod);
   }

   @Test
   public void testSelectProdList() {
      List<ProdVO> prodList = dao.selectProdList();
      log.info("prodList: {}", prodList);
   }

   @Test
   public void testUpdateProd() {
      fail("Not yet implemented");
   }

}










