package kr.or.ddit.sample.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
//우리가 만든 DAO -> 순수한 POJO (상위에 아무것도 없기 때문에 어딜넣어도 잘 돌아감)
@Slf4j
@Repository("dao_oracle")
public class SampleDAOImpl_Oracle implements SampleDAO {

	private static final Map<Integer, String[]> TEAMTABLE = new LinkedHashMap<>();
	
	
	
	public SampleDAOImpl_Oracle() {
		super();
		log.info("{} 객체 생성", this);
		TEAMTABLE.put(1, new String[] {"이유화_oracle", "오용택_oracle","정경환_oracle","윤정식_oracle" ,"이원걸_oracle","이찬솔_oracle"});
		TEAMTABLE.put(2, new String[] {"김호겸_oracle", "임찬우_oracle","장혜연_oracle","임지수_oracle" ,"이주원_oracle","장윤식_oracle"});
		TEAMTABLE.put(3, new String[] {"이유영_oracle", "방형준_oracle","강은비_oracle","김건호_oracle" ,"최현우_oracle","구지현_oracle"});
		TEAMTABLE.put(4, new String[] {"최지훈_oracle", "홍무곤_oracle","정요한_oracle","강명범_oracle" ,"김유리_oracle","조수빈_oracle"});
	}


	public void start() {         //this -> 현재 container에서 관리하고 있는 bean
		log.info("{}  초기화 완료.", this);
	}
	
	public void stop() {//container closing 이후에 객체가 소멸 됨 
		log.info("{}겍체 소멸.", this);
	}

	@Override
	public String[] selectTeam(Integer teamNumber) {
	
		
		return TEAMTABLE.get(teamNumber); //없는 team 번호 들어오면 null 값 반환
	}

}
