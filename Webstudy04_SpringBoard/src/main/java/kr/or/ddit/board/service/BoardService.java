package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;

/**
 * 게시판 관리용 Business Logic Layer
 * 
 *
 */

public interface BoardService {
	
//	createBoard
	public BoardVO retrieveBoard(int boNo);
    
	public List<BoardVO> retrieveBordList(PagingVO<BoardVO> pagingVO);
    
    public int retrieveBoardCount(PagingVO<BoardVO> pagingVO);
    
  
    
//	modifyBoarde 
//	removeBoard
	//pagingVO webstudy01꺼 사용

}
