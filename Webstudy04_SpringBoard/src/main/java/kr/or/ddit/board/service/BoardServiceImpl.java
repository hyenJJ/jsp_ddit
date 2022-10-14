package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDAO boardDAO;
	
	private final AttatchDAO attatchDAO;

	private File saveFolder;
	
	@PostConstruct
	public void  init() throws IOException {
		saveFolder = attatchFolder.getFile();

	}
//	@Inject   //mapper 프록시 boardDAOImpl을 삭제해줘도 가짜 구현체가 존재함
//	public BoardServiceImpl(BoardDAO boardDAO) { //mybatis scanner가 가져다준 가짜 proxy 객체
//		super();
//		this.boardDAO = boardDAO;
//		log.info("주입된 객체 : {}", boardDAO);
//	}

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
	
	@Value("#{appInfo.attatchFolder}") //appInfo에있는 attatchFolder의 값 
	private Resource attatchFolder ;
	
	private int processAttatchList(BoardVO board) {
		
		int rowcnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList != null && !attatchList.isEmpty()) {
			
			// 메타데이터 저장 buffer에 저장되기 때문에 rollback이 가능해서 먼저 해주는게 좋다 
			rowcnt = attatchDAO.insertAttatches(board);
			
			// 2진 데이터 저장
			for(AttatchVO attatch : attatchList) {
				try {
					attatch.saveTo(attatchFolder.getFile());
				} catch (IOException e) {
					throw new RuntimeException(e);
					
				}
			}
		}

		return rowcnt;
	}
	
	@Override
	public ServiceResult createBoard(BoardVO board) {
		
		int rowcnt =boardDAO.insertBoard(board);
		rowcnt += processAttatchList(board); // 부모가 board이기 때문에  insert 한다음에 첨부를 한다
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	private boolean boardAuthenticate(BoardVO board) {
		BoardVO saved = retrieveBoard(board.getBoNo());
		String inputPass = board.getBoPass();
		String savedPass = saved.getBoPass();
		return savedPass.equals(inputPass);
	}
	
	@Override
	public ServiceResult modifyBoarde(BoardVO board) {
		ServiceResult result = null;
		
		if(boardAuthenticate(board)) {
			int rowcnt =boardDAO.insertBoard(board);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
	

		return result;
	}

	// 게시물에 있는 모든 첨부파일을 지워야함 (복수)
	private int processDeleteAttatches(BoardVO board) {
		
		//이 메소드의 board안에는 attatch가 없기 때문에 정보를 불러옴
		List<AttatchVO> attatchList = boardDAO.selectBoard(board.getBoNo()).getAttatchList();
		
		if(attatchList==null || attatchList.isEmpty()) return 0;
		
		List<String> saveNames = attatchList.stream()
					.map(attatch->{
						return attatch.getAttSavename();
						
					}).collect(Collectors.toList()) ; //저장 이름을 배열로 꺼내옴

		int rowcnt = 0;	
		
		// 메타데이터 (테이블 삭제)  롤백이 가능하니까 먼저 실행
		rowcnt = attatchDAO.deleteAttatch(board.getBoNo());	
		
		// 2진 데이터 삭제 (file 하나하나)
		
		if(!saveNames.isEmpty()) {
			for(String attSavename : saveNames) {
				File deleteFile = new File(saveFolder, attSavename);
				FileUtils.deleteQuietly(deleteFile);
			}
		}
		
		return rowcnt;
	}
	
	@Override
	public ServiceResult removeBoard(BoardVO board) {
		
		ServiceResult result = null;
		
		if(boardAuthenticate(board)) { 
			processDeleteAttatches(board);
			int rowcnt =boardDAO.deleteBoard(board);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
	

		return result;
	
	}

	@Override
	public int recommend(int boNo) {
		

		boardDAO.incrementRec(boNo); 

		return boardDAO.selectBoard(boNo).getBoRec();
	}
}
