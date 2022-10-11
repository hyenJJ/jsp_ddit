package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)//junit과 spring 연결할 때 등록하는 API
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml") //가상의 컨테이너가 필요 -> 모의환경 
public class BoardDAOTest {
	
	//컨테이너를 통해서 주입받은 객체를 test할 수 있어야한다 
	// -> Junit과 Spring의 연동작업 필요
	@Inject // 가상 container의 객체를 주입
	private BoardDAO dao;

	@Test
	public void testSelectBoard() {
		log.info("주입된 객체 : {}", dao);
	}

}
