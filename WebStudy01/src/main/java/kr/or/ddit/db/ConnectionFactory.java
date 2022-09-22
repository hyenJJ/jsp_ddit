package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
// *주의* 테스트할땐 톰캣 서버가 돌지 않는데 tomcat서버로 import 해줘서 톰캣 사용할 땐 됐지만 사용안할 땐 오류가 남 

/**
 * 
 * Design Pattern
 * 1. 생성 패턴 : Singleton pattern, Factory Method pattern, Builder pattern
 * 2. 구조 패턴 : Adapter[wrapper] Pattern, Proxy Pattern
 * 3. 행위 패턴 : Template Method Pattern, Command Pattern, Strategy Pattern
 *
 */
public class ConnectionFactory {
	private static String oracleURL;
	private static String oracleUser;
	private static String oraclePassword;
	
	
	private static DataSource dataSource;
	         // javax.sql    javax -> 신형
	
	static {   // static 코드블럭 - 클래스가 로딩될때 한번만 실행
		
		// 여기서 칼국수면 100분을 준비
		Properties dbInfo = new Properties();
		
		
		try(  ///WebStudy01/src/main/resources -> calss path 
			 
			InputStream is = 
			ConnectionFactory.class.getResourceAsStream("/kr/or/ddit/db/DBInfo.properties");	
				                                         // class path 이후 절대경로
	    ) {
			dbInfo.load(is);
			oracleURL = dbInfo.getProperty("url");
			oracleUser = dbInfo.getProperty("user");
			oraclePassword = dbInfo.getProperty("password");
//			Class.forName(dbInfo.getProperty("driverClassName"));
			
			BasicDataSource bds = new BasicDataSource(); 
			/*
			 아무것도 설정하지 않으면 기본설정으로 인해서 10개의 connection을 만들어놓음
			 처음에는 소요시간이 좀 걸릴 수도 있지만 
			 다음부터는 미리 만들어놓은 connection을 재사용 하기 때문에 시간이 줄어듬
			 database의 session요소를 더 만들 필요가 없음
			*/
		
			bds.setDriverClassName(dbInfo.getProperty("driverClassName"));
			bds.setUrl(oracleURL); 
			bds.setUsername(oracleUser);
			bds.setPassword(oraclePassword); 
			//모든 정보가 라이브러리로 넘어감 
			
			
			bds.setInitialSize(Integer.parseInt(dbInfo.getProperty("initialSize"))); //(5) 미리 5개를 만들어놓음
			bds.setMaxWaitMillis(Long.parseLong(dbInfo.getProperty("maxWait"))); //(2000) 쓸 connection이 없을때 2초 대기 -> 2초 이내에 반납된 connection이 있음 사용
			
			//2초 지나서 반납된게 없으면?
			bds.setMaxTotal(Integer.parseInt(dbInfo.getProperty("maxTotal"))); // (10) 최대 10개까지는 새로 만들수 있게 설정
			
			// 10개 만들고 나서도 반납된게 없으면 dbexception 발생
			
			bds.setMaxIdle(Integer.parseInt(dbInfo.getProperty("maxIdle"))); //(5) 최대 5개connection이 놀수있음 ->10개반납됐을 때 5개는 사라짐 
			
			
			dataSource = bds; // bts로 초기화 
		  } catch (IOException e) {
			throw new RuntimeException(e);
			
		}
	}
	
	public static Connection getConnection() throws SQLException {
		
//		return DriverManager.getConnection(oracleURL, oracleUser, oraclePassword);
		          // connection 생성이 필요 할 때 마다 그 즉시 생성
		
		return dataSource.getConnection();
	}

}
