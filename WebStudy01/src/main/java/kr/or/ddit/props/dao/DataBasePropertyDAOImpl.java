package kr.or.ddit.props.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.props.vo.PropertyVO;

public class DataBasePropertyDAOImpl implements PropertyDAO {

	@Override
	public PropertyVO selectProperty(String propertyName) {
		//어떤 연산자가 사용되는지 이 단계 부분에서 꼼꼼하게 체크해야함       
		// 검증해야할게 너무 많아서 prepared라는 구조를 사용 
	
		// 여기서 property를 선택하면 500에러 - 아직 porperty를 조회하는 query문이 완성이 안되어 있기 때문
		// 상세정보가 나오도록 하는것
		
                                                                             //table이 아니라 view ->얘를 대상으로 insertr가 불가능
		String sql = "SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES";
               sql += " WHERE PROPERTY_NAME = ? ";// 단순한 텍스트가 아니라 쿼리문의 일부분으로 사용
    // SQL 문 이어서 쓸땐 앞에 띄어쓰기 신경쓰기 !!     - 쿼리 파라미터
               
        PropertyVO propertyVO = null;       
               
		String[] headers = null;

		try (Connection oracleConn = ConnectionFactory.getConnection();
		     PreparedStatement oracleStmt = oracleConn.prepareStatement(sql);
				                                 //slelct에만 사용할수 있다 
			) {
			oracleStmt.setString(1, propertyName); // 단순한 값 
			ResultSet rs = oracleStmt.executeQuery();//prepared는 쿼리문을 동적으로 바꿀수 없다
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			headers = new String[count];
			for (int i = 1; i <= count; i++) {
				headers[i - 1] = rsmd.getColumnName(i);
			}
			if (rs.next()) {
				propertyVO  = new PropertyVO();
				
				propertyVO.setPropertyName(rs.getString("PROPERTY_NAME"));
				propertyVO.setPropertyValue(rs.getString("property_value"));
				propertyVO.setDescription(rs.getString("DESCRIPTION"));
			}
			return propertyVO;
		} catch (SQLException e) {
			e.printStackTrace();
			// 예외가 발생한 경우에, 여기선 지금 return이 없는데,
//         return null 이라고 하면, 발생한 예외 정보 사라지고, 어디서 null생긴지 모름

//         interface의 예외의 종류를 unchecker로 바꿔 주어야 함
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<PropertyVO> selectProperties() {

		String sql = "SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES";

		List<PropertyVO> dataList = new ArrayList<>();
		String[] headers = null;

		try (Connection oracleConn = ConnectionFactory.getConnection();
				Statement oracleStmt = oracleConn.createStatement();) {
			ResultSet rs = oracleStmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			headers = new String[count];
			for (int i = 1; i <= count; i++) {
				headers[i - 1] = rsmd.getColumnName(i);
			}
			while (rs.next()) {
				PropertyVO vo = new PropertyVO();
				dataList.add(vo);
				vo.setPropertyName(rs.getString("PROPERTY_NAME"));
				vo.setPropertyValue(rs.getString("property_value"));
				vo.setDescription(rs.getString("DESCRIPTION"));
			}
			return dataList;
		} catch (SQLException e) {
			e.printStackTrace();
			// 예외가 발생한 경우에, 여기선 지금 return이 없는데,
//         return null 이라고 하면, 발생한 예외 정보 사라지고, 어디서 null생긴지 모름

//         interface의 예외의 종류를 unchecker로 바꿔 주어야 함
			throw new RuntimeException(e);
		}

	}

	@Override
	public void insertProperty(PropertyVO propertyVo) {
	      if(1==1) //-> 의미없는 코드
	    	  throw new RuntimeException("해당 뷰는 insert 대상이 아님.");

	}

}