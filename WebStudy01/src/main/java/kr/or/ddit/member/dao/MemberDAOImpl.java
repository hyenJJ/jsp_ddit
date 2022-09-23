package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public int insertMember(MemberVO member) {
				StringBuffer sql = new StringBuffer();                             
			sql.append("	INSERT INTO MEMBER(  ");                                    

			sql.append(" 	    MEM_ID           ");
			sql.append(" 	    ,MEM_PASS        ");
			sql.append(" 	    ,MEM_NAME        ");
			sql.append(" 	    ,MEM_REGNO1      ");
			sql.append(" 	    ,MEM_REGNO2      ");
			sql.append(" 	    ,MEM_BIR         ");
			sql.append(" 	    ,MEM_ZIP         ");
			sql.append(" 	    ,MEM_ADD1        ");
			sql.append(" 	    ,MEM_ADD2        ");
			sql.append(" 	    ,MEM_HOMETEL     ");
			sql.append(" 	    ,MEM_COMTEL      ");
			sql.append(" 	    ,MEM_HP          ");
			sql.append(" 	    ,MEM_MAIL        ");
			sql.append(" 	    ,MEM_JOB         ");
			sql.append(" 	    ,MEM_LIKE        ");
			sql.append(" 	    ,MEM_MEMORIAL    ");
			sql.append(" 	    ,MEM_MEMORIALDAY ");	
			sql.append(" 	) VALUES (           ");
			sql.append(" 		     ?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,TO_DATE(? , 'YYYY-MM-DD')  ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,?           ");
			sql.append(" 		    ,TO_DATE(?, 'YYYY-MM-DD')  ");
			sql.append(" 	)                   ");
		     		
		
		try (
		   Connection conn = ConnectionFactory.getConnection();
		   PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		 
		){
			int index = 1;
			
			pstmt.setString( index++, member.getMemId());
			pstmt.setString( index++, member.getMemPass());
			pstmt.setString( index++, member.getMemName());
			pstmt.setString( index++, member.getMemRegno1());
			pstmt.setString( index++, member.getMemRegno2());
			pstmt.setString( index++, member.getMemBir());
			pstmt.setString( index++, member.getMemZip());
			pstmt.setString( index++, member.getMemAdd1());
			pstmt.setString( index++, member.getMemAdd2());
			pstmt.setString( index++, member.getMemHometel());
			pstmt.setString( index++, member.getMemComtel());
			pstmt.setString( index++, member.getMemHp());
			pstmt.setString( index++, member.getMemMail());
			pstmt.setString( index++, member.getMemJob());
			pstmt.setString( index++, member.getMemLike());
			pstmt.setString( index++, member.getMemMemorial());
			pstmt.setString( index++, member.getMemMemorialday());
		

			return pstmt.executeUpdate();
			
		
		
	
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		
		
	}

	
	@Override
	public MemberVO selectMember(String memId) {
	
		StringBuffer sql = new StringBuffer();
		
		// 반복되지 않는 파트 1
		sql.append("SELECT                                                 ");
		sql.append(" mem_id, mem_pass, mem_name,                            ");
		sql.append(" mem_regno1, mem_regno2,                                ");
		sql.append(" TO_CHAR(mem_bir, 'YYYY-MM-DD')mem_bir,                 ");
		sql.append(" mem_zip, mem_add1,mem_add2,                            ");
		sql.append(" mem_hometel, mem_comtel, mem_hp,                       ");
		sql.append(" mem_mail, mem_job, mem_like,                           ");
		sql.append(" mem_memorial,                                          ");
		sql.append(" TO_CHAR( mem_memorialday,'YYYY-MM-DD')mem_memorialday, ");
		sql.append(" mem_mileage, mem_delete                                ");
		sql.append(" FROM member                                            ");
		sql.append("WHERE MEM_ID = ?                                        ");
		
		// sql.append("SELECT * FROM MEMBER WHERE MEM_ID =? ");

		MemberVO member = null;
		// String[] headers = null;
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		) {
			pstmt.setString(1, memId);
			ResultSet rs = pstmt.executeQuery();
			/*
			 * ResultSetMetaData rsmd = rs.getMetaData(); int count = rsmd.getColumnCount();
			 * 
			 * headers = new String[count]; for(int i = 1; i<=count; i++) { headers[i-1] =
			 * rsmd.getColumnName(i); }
			 */
			if (rs.next()) {
				member = new MemberVO();

				member.setMemAdd1(rs.getString("MEM_ADD1"));
				member.setMemAdd2(rs.getString("MEM_ADD1"));
				member.setMemBir(rs.getString("MEM_BIR"));
				member.setMemComtel(rs.getString("MEM_COMTEL"));
				member.setMemDelete(rs.getBoolean("MEM_DELETE"));
				member.setMemHometel(rs.getString("MEM_HOMETEL"));
				member.setMemHp(rs.getString("MEM_HP"));
				member.setMemId(rs.getString("MEM_ID"));
				member.setMemJob(rs.getString("MEM_JOB"));
				member.setMemLike(rs.getString("MEM_LIKE"));
				member.setMemMail(rs.getString("MEM_MAIL"));
				member.setMemMemorial(rs.getString("MEM_MEMORIAL"));
				member.setMemMemorialday(rs.getString("MEM_MEMORIALDAY"));
				member.setMemMileage(rs.getInt("MEM_MILEAGE"));
				member.setMemName(rs.getString("MEM_NAME"));
				member.setMemPass(rs.getString("MEM_PASS"));
				member.setMemRegno1(rs.getString("MEM_REGNO1"));
				member.setMemRegno2(rs.getString("MEM_REGNO2"));
				member.setMemZip(rs.getString("MEM_ZIP"));

			}

			return member;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

	}

	@Override
	public List<MemberVO> selectMemberList() {
		// 쿼리문 작성
		// String sql = "SELECT * FROM MEMBER";

		StringBuffer sql = new StringBuffer();

		// 반복되지 않는 파트 1 - sqlmapper
		sql.append("SELECT                            ");
		sql.append("    MEM_ID, MEM_NAME, MEM_ADD1,   ");
		sql.append("    MEM_HP, MEM_MAIL, MEM_MILEAGE ");
		sql.append("FROM MEMBER                     ");
		sql.append("WHERE MEM_DELETE IS NULL                     ");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		) {
			ResultSet rs = pstmt.executeQuery();

			// 반복되지 않는 파트 2 - datamapper
			List<MemberVO> memList = new ArrayList<MemberVO>();

			while (rs.next()) {
				MemberVO memVo = new MemberVO();
				memList.add(memVo);

				// 결과 집합을 자바형태로 변환
				memVo.setMemId(rs.getString("MEM_ID"));
				memVo.setMemName(rs.getString("MEM_NAME"));
				memVo.setMemMail(rs.getString("MEM_MAIL"));
				memVo.setMemAdd1(rs.getString("MEM_ADD1"));
				memVo.setMemHp(rs.getString("MEM_HP"));
				memVo.setMemMileage(rs.getInt("MEM_MILEAGE"));
				// metadate를 사용하면 간단하게 할 수 있음
			}

			return memList;
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

	}

	@Override
	public int updateMember(MemberVO member) {
		
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("UPDATE MEMBER ");
		sql.append("SET  	                     ");

		sql.append(" 	    ,MEM_ZIP        = ?  ");
		sql.append(" 	    ,MEM_ADD1       = ?  ");
		sql.append(" 	    ,MEM_ADD2       = ?  ");
		sql.append(" 	    ,MEM_HOMETEL    = ?  ");
		sql.append(" 	    ,MEM_COMTEL     = ?  ");
		sql.append(" 	    ,MEM_HP         = ?  ");
		sql.append(" 	    ,MEM_MAIL       = ?  ");
		sql.append(" 	    ,MEM_JOB        = ?  ");
		sql.append(" 	    ,MEM_LIKE       = ?  ");
		sql.append(" 	    ,MEM_MEMORIAL   = ?  ");
		sql.append(" 	    ,MEM_MEMORIALDAY= TO DATE ?  ");	
		sql.append(" 	    WHERE MEM_ID = ?     = ?  ");	
		   
		
		
		try (
				
		   Connection conn = ConnectionFactory.getConnection();
		   PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		 
		){
		
			MemberVO memberVo = new MemberVO();
			int index = 1;
			
			

			pstmt.setString( index++, member.getMemZip());
			pstmt.setString( index++, member.getMemAdd1());
			pstmt.setString( index++, member.getMemAdd2());
			pstmt.setString( index++, member.getMemHometel());
			pstmt.setString( index++, member.getMemComtel());
			pstmt.setString( index++, member.getMemHp());
			pstmt.setString( index++, member.getMemMail());
			pstmt.setString( index++, member.getMemJob());
			pstmt.setString( index++, member.getMemLike());
			pstmt.setString( index++, member.getMemMemorial());
			pstmt.setString( index++, member.getMemMemorialday());
			pstmt.setBoolean( index++, member.getMemDelete());
			pstmt.setInt( index++, member.getMemMileage());
			pstmt.setString( index++, member.getMemId());
		
				
			return pstmt.executeUpdate();
			
			
             
	
		
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		
		
	
	}

	@Override
	public int deleteMember(String memId) {
	     
		
	    StringBuffer sql = new StringBuffer();
	    
	    
	    sql.append("UPDATE MEMBER          ");
	    sql.append("SET MEM_DELETE = '1'   ");
	    sql.append("WHERE MEM_ID =?       ");
		
		try (
		   Connection conn = ConnectionFactory.getConnection();
		   PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		 
		){
			
			//ResultSet rs 
			
//			return pstmt.executeUpdate();
			return 0;
			
			
			
		//	while(rs.next()) {}
		
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
		
	
	}

}
