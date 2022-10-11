package kr.or.ddit.sample.view;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOImpl_MariaDB;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SampleView {
	
	//private SampleDAO dao = new SampleDAOImpl_MariaDB();	
	private SampleService service;
	
	public SampleView() {
		log.info("{} 객체 생성" , this);
	}
	
//	@Autowired
	@Required // 꼭 injection을 해야한다 
	@Inject
	public void setService(SampleService service) {
		this.service = service;
	}
	
	
	
	public void rendermessage(Integer teamNumber) {
		String content = String.format("조회한 모델데이터 : %s\n",service.retrieveTeam(teamNumber) ) ;
		System.out.println(content);
	}
	
	

	public void start() {         //this -> 현재 container에서 관리하고 있는 bean
		log.info("{} 초기화 완료.", this);
	}
	
	public void stop() {//container closing 이후에 객체가 소멸 됨 
		log.info("{}겍체 소멸.", this);
	}
	
	public static void main(String[] args) {
	
		// 컨테이너 생성
		ApplicationContext context = new ClassPathXmlApplicationContext("/kr/or/ddit/sample/conf/Sample-Context.xml");
		
		// 주입 받기
		// IoC container -> Dependency Infection container	
		SampleView view = context.getBean(SampleView.class);
		view.rendermessage(2);
	
	}
	

}
