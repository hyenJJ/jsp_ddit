package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	private BoardDAO boardDAO;

	@Inject   //mapper 프록시 boardDAOImpl을 삭제해줘도 가짜 구현체가 존재함
	public BoardServiceImpl(BoardDAO boardDAO) { //mybatis scanner가 가져다준 가짜 proxy 객체
		super();
		this.boardDAO = boardDAO;
		log.info("주입된 객체 : {}", boardDAO);
	}

	@Override
	public BoardVO retrieveBoard(int boNo) {
		
	
		 BoardVO board = boardDAO.selectBoard(boNo);
		 
		 if(board==null) {
			 throw new RuntimeException(String.format("%d 글번호의 글이 없음" , boNo)); 
		 }
		 boardDAO.incrementBoHit(boNo);
		 
		 return board;
	}

	@Override
	public List<BoardVO> retrieveBordList(PagingVO<BoardVO> pagingVO) {
		
		return boardDAO.selectBoardList(pagingVO) ;
	}

	@Override
	public int retrieveBoardCount(PagingVO<BoardVO> pagingVO) {
		
		return boardDAO.selectTotalRecord(pagingVO) ;
	}
	
	
}
