package kr.or.ddit.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

//setUpClass - setUp - testOracle - tearDown - setUp -  testMariaDB - tearDown - tearDownClass
public class DataBaseConnectionTest {

	private Connection oracleConn;
	private Connection mariaDBConn;
	private Statement oracleStmt;
	private Statement mariaDBStmt;

	@BeforeClass // 한번만 실행
	public static void setUpClass() { // vm에게 리모컨이 있다는걸 인식 시켜주는 단계
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	@Before // testcase가 두개면 두번 실행
	public void setUp() {
		String oracleURL = "jdbc:oracle:thin:@//localhost:1521/XE";
		String oracleUser = "ddit";
		String oraclePassword = "java";

		String mariaDBURL = "jdbc:mariadb://localhost:3305/python";
		String mariaDBUser = "root";
		String mariaDBPassword = "python";
		try {

			oracleConn = DriverManager.getConnection(oracleURL, oracleUser, oraclePassword);
			mariaDBConn = DriverManager.getConnection(mariaDBURL, mariaDBUser, mariaDBPassword);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testOracle() {
		String sql = "SELECT MEM_NAME FROM MEMBER";
		try {

			oracleStmt = oracleConn.createStatement();
			ResultSet rs = oracleStmt.executeQuery(sql);
			// 커서형태 - 포인터가 무조건 헤더에 위치
			// 돌아오는 형태가 몇개일지 모르기때문에 set(집합의 형태임)
			ResultSetMetaData rsmd = rs.getMetaData();
			// rsmd.getColumnName(0);

			while (rs.next()) {
				// 헤더에 있던 커서가 body로 내려옴

				System.out.println(rs.getString("MEM_NAME"));
			}
			System.out.println(oracleConn);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testMariaDB() {
		String sql = "SELECT E_NAME FROM EMP";

		try {
			mariaDBStmt = mariaDBConn.createStatement();
			ResultSet rs = mariaDBStmt.executeQuery(sql);

			while (rs.next()) {
				// 헤더에 있던 커서가 body로 내려옴

				System.out.println(rs.getString("E_NAME"));

			}

			System.out.println(mariaDBConn);

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@After
	public void tearDouwn() {

		try {
			if (oracleStmt != null)
				oracleStmt.close();
			if (oracleConn != null)
				oracleConn.close();
			mariaDBStmt.close();
			if (mariaDBConn != null)
				mariaDBConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterClass // 모든 텍스트가 다 끝나기 직전에 실행
	public static void tearDownClass() {
		System.out.println("테스트 종료");

	}

}
