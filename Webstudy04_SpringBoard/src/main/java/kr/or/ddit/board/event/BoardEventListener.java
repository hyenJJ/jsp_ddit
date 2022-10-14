package kr.or.ddit.board.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import kr.or.ddit.board.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class BoardEventListener {
	
	@EventListener(NewBoardEvent.class)
	public void newBoardEventListener(NewBoardEvent event) {
		BoardVO newBoard =  event.getSource();
		log.info("{} 가 작성한 새글, {} 제목으로 올라왔음.", newBoard.getBoWriter(), newBoard.getBoTitle());
	}
}
