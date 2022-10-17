package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.management.RuntimeErrorException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.board.dao.AttatchDAO;
import kr.or.ddit.board.dao.BoardDAO;
import kr.or.ddit.board.vo.AttatchVO;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.PagingVO;
import kr.or.ddit.enumpkg.ServiceResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//트랜젝션이 잘 관리하고 있는지 확인이 불가하다 
//즉, target은 어떤 advice에서도 접촉성과 결합력을 가져선 안된다
@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDAO boardDAO;
	
	private final AttatchDAO attatchDAO;

	private File saveFolder;
	
	
	@Value("#{appInfo.attatchFolder}") //appInfo에있는 attatchFolder의 값 
	private Resource attatchFolder ;
	
	
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
	
	
	
	
	
	
	
	private int processAttatchList(BoardVO board) {
		
		int rowcnt = 0;
		List<AttatchVO> attatchList = board.getAttatchList();
		if(attatchList != null && !attatchList.isEmpty()) {
			
//			if(1==1) throw new RuntimeException("트랜잭션 관리 여부 확인용 가제 예외 발생");
			
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
	
	@Transactional //선언적 프로그래밍 기법(AOP), 이 어노테이션을 갖고 있는것이 pointcut이 됨  =>결합력 발생 
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
	
	

	private int processDeleteAttatch(BoardVO board) {
		
		int rowcnt =0;
		int[] attdelList = board.getDelAttNos();
		
		if(attdelList == null || attdelList.length == 0)  return 0;
		
		Arrays.sort(attdelList);
		
		// 기존에 있었던 파일들을 가져옴
		List<AttatchVO> attatchList = boardDAO.selectBoard(board.getBoNo()).getAttatchList();
		
		List<String> saveNames = attatchList.stream()
				.filter(attatch->{
					return Arrays.binarySearch(attdelList, attatch.getAttNo()) >= 0;
					 //return true;  필터링을 안하는 것과 같음
					
				})
				.map(attatch->{
					return attatch.getAttSavename();
					
				}).collect(Collectors.toList()) ; //저장 이름을 배열로 꺼내옴

		
        rowcnt += attatchDAO.deleteAttatches(attdelList);
		
		
		
		if(!saveNames.isEmpty()) {
			for(String attSavename : saveNames) {
				File deleteFile = new File(saveFolder, attSavename);
				FileUtils.deleteQuietly(deleteFile);
				
			}
		}
			
		
		return rowcnt;
		 
		
		
	}
//------------modifyBoarde----------------------------------	
	@Transactional
	@Override
	public ServiceResult modifyBoarde(BoardVO board) {
		ServiceResult result = null;
		
		if(boardAuthenticate(board)) {
			
			int rowcnt =boardDAO.updateBoard(board);
				
			// 1. 신규파일등록 : 2진, 메타데이터 
			processAttatchList(board);  // 첫번째에 업데이트 했던것을 롤백 할 수 있기 때문에 두번째로 
		
			// 2. 기존 파일 삭제 : 2진, 메타데이타
			processDeleteAttatch(board);
			
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
	

		return result;
	}

	
//-------------------------processDeleteAttatches------------
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
		rowcnt = attatchDAO.deleteAttatchess(board.getBoNo());	
		
		// 2진 데이터 삭제 (file 하나하나)
		
		if(!saveNames.isEmpty()) {
			for(String attSavename : saveNames) {
				File deleteFile = new File(saveFolder, attSavename);
				FileUtils.deleteQuietly(deleteFile);
				
			}
		}
		
		return rowcnt;
	}

	
//----------------------------remove---------------------
	@Transactional
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

//------------------------------------	
	@Override
	public int recommend(int boNo) {
		

		boardDAO.incrementRec(boNo); 

		return boardDAO.selectBoard(boNo).getBoRec();
	}

	@Override
	public AttatchVO retrieveAttatch(int attNo) {
		
		AttatchVO attatch =  attatchDAO.selectAttatch(attNo);
		
		if(attatch == null)
			throw new RuntimeException("해당 파일 없음.");
		
		
		return attatch;
	}
}
