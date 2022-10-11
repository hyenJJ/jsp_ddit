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

public class MemberDAOImpl_bak implements MemberDAO {

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
		//   Connection conn = ConnectionFactory.getConnection();
		//   PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		 
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
	
	/*
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

		} catch (SQLException e) {

			throw new RuntimeException(e);
		}

	//	return memId;
	}

	@Override
	public List<MemberVO> selectMemberList() {
	
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
  */
}
