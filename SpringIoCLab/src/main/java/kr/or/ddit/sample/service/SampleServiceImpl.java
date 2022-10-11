package kr.or.ddit.sample.service;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
//autowired -> spring에 종속되어있고 Resource는 종속되어 있지 않아서 Resource는 어디에서나 쓸수있다.
import org.springframework.stereotype.Service;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOFactory;
import kr.or.ddit.sample.dao.SampleDAOImpl_MariaDB;
//import kr.or.ddit.sample.dao.SampleDAOImpl_Oracle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleServiceImpl implements SampleService {
	
	// 1. 결합력이 최상으로 높은 구조 
	//private SampleDAO dao = new SampleDAOImpl_MariaDB();
	
	// 2. Factory object pattern 
	//private SampleDAO dao = new SampleDAOFactory().getsampleDAO(); // 결합력이 한단계 낮아졌지만 factory로 옮겨감
	
	// 3. Strategy Pattern : 전략 주입자 필요.  -> 주입 방식 : (setter, constructor)
	// 꼭 받아야하는 거면 constructor
	
	// 4. DI Container 사용
	
	//TARGET {TYPE, FIELD, METHOD}
	//@Resource(name="dao_oracle") //명시적으로 검색 -> 좀 더 안전하다 
	private SampleDAO dao;
	
	
	public SampleServiceImpl() {
		super();
		log.info("{} 객체 생성, 기본 생성자", this);
		
	}

//	@Autowired // getBean(Class) -> getBean("id") 타입으로 검색
	
	//Target({ METHOD, CONSTRUCTOR, FIELD })
	@Inject //Autowired와 같은 방식으로 동작 (spring에 종속되어있지않아서 자유롭게 쓸 수 있음)
	@Named("dao_oracle")
	public SampleServiceImpl(SampleDAO dao) {
		super();
		log.info("{} 객체 생성, dao 파라미터를 받는 생성자", this);
		this.dao = dao;
	}


	public void setDao(SampleDAO dao) {
		log.info("dao 를 setter에서 주입받음.");
		this.dao = dao;
	}
	

	public void start() {         //this -> 현재 container에서 관리하고 있는 bean
		log.info("{}  초기화(주입) 완료. {}", this, dao);
	}
	
	public void stop() {//container closing 이후에 객체가 소멸 됨 
		log.info("{}겍체 소멸.", this);
	}
	
	
	@Override
	public String retrieveTeam(Integer teamNumber) {
		
		String [] rawdata = dao.selectTeam(teamNumber);
		 String information = Arrays.toString(rawdata);//information 
		                      //배열을 문자열로 변환 
		 return information;
	}

}
