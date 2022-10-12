package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;

/**
 * 게시판 관리(CRUD)용 Persistence Layer
 * 
 * 작업에 대한 정의만 되어있다.(Impl X)
 * 
 * 
 */
@Mapper
public interface BoardDAO {

// 	insertBoard	
	
	public BoardVO selectBoard(@Param("boNo") int boNo); // 값만 전달된 경우는 이름이 없어서 @param으로 설정해줌
	public int incrementBoHit(@Param("boNo")int boNo);
	
    public List<BoardVO> selectBoardList(PagingVO<BoardVO> pagingVO );
    
    public int selectTotalRecord(PagingVO<BoardVO> pagingVO);
    
//	updateBoard
//	deleteBoard
}
