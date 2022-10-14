package kr.or.ddit.board.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;


// CRUD
@Mapper
public interface AttatchDAO {
	
	//insertAll : 한번에 여러개 레코드를 넣을 수 있다 -> attatchList가 필요함 
	/**
	 * 게시글의 첨부파일의 메타데이터를 한번에 insert.
	 * @param board
	 * @return
	 */
	public int insertAttatches(BoardVO board); //attatchList가 boardVO안에 있음
	
	// 게시글 상세조회시 첨부파일을 한번에 select -> board select 할때 join해야함
	
	/**
	 * 다운로드 처리를 위해 메타데이터 한건 조회.
	 * @param attNo
	 * @return
	 */
	public AttatchVO selectAttatch (int attNo);
	
	/**
	 * 게시글 수정시 한건 한건의 파일의 메타데이터 삭제용.
	 * @param attNo
	 * @return
	 */
	public int deleteAttatch(int attNo);
	
	/**
	 * 게시글에 첨부된 모든 파일의 메타데이터 삭제용.
	 * @param boNo
	 * @return
	 */
	public int deleteAttatches(int boNo);
}
