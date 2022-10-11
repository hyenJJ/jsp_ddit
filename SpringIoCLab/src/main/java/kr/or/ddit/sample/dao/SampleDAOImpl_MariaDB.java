package kr.or.ddit.sample.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("dao_MariaDB")
public class SampleDAOImpl_MariaDB implements SampleDAO {

	private static final Map<Integer, String[]> TEAMTABLE = new LinkedHashMap<>();
	
	
	
	public SampleDAOImpl_MariaDB() {
		super();
		TEAMTABLE.put(1, new String[] {"이유화_mariadb", "오용택_mariadb","정경환_mariadb","윤정식_mariadb" ,"이원걸_mariadb","이찬솔_mariadb"});
		TEAMTABLE.put(2, new String[] {"김호겸_mariadb", "임찬우_mariadb","장혜연_mariadb","임지수_mariadb" ,"이주원_mariadb","장윤식_mariadb"});
		TEAMTABLE.put(3, new String[] {"이유영_mariadb", "방형준_mariadb","강은비_mariadb","김건호_mariadb" ,"최현우_mariadb","구지현_mariadb"});
		TEAMTABLE.put(4, new String[] {"최지훈_mariadb", "홍무곤_mariadb","정요한_mariadb","강명범_mariadb" ,"김유리_mariadb","조수빈_mariadb"});
	}

	

	public void start() {         //this -> 현재 container에서 관리하고 있는 bean
		log.info("{} 객체 생성 후 초기화 완료.", this);
	}
	
	public void stop() {//container closing 이후에 객체가 소멸 됨 
		log.info("{}겍체 소멸.", this);
	}


	@Override
	public String[] selectTeam(Integer teamNumber) {
	
		
		return TEAMTABLE.get(teamNumber); //없는 team 번호 들어오면 null 값 반환
	}

}
