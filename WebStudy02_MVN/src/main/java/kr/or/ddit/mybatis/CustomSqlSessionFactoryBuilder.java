package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomSqlSessionFactoryBuilder {
            // SqlSessionFactory은 sql 전체를 통틀어 singleton
   private static SqlSessionFactory sqlSessionFactory;
   //static 코드블럭이라 딱 한번만 실행됨
   static {
      String resource ="kr/or/ddit/mybatis/Configration.xml";
      
      try(
         Reader reader = Resources.getResourceAsReader(resource);
      ){
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      }catch (IOException e) {
         throw new RuntimeException(e);
      }
   }
   
   //외부에서 가져갈 수 있도록 getter 설정. 
   public static SqlSessionFactory getSqlSessionFactory() {
      return sqlSessionFactory;
   }
}