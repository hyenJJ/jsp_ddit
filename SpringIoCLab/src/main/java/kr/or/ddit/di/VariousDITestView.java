package kr.or.ddit.di;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariousDITestView {

	public static void main(String[] args) {
		//1. 설정파일의 위치가 런타임에 동적으로 결정되는 컨테이너 구현체
		ConfigurableApplicationContext context = new  GenericXmlApplicationContext("classpath:/kr/or/ddit/di/conf/VariousDI-Context.xml"); 
																			//resource loader
		
		
		//2. 컨테이너는 더 이상 사용할 필요 없을 시 자동종료
		
		context.registerShutdownHook();
		
		//3. 해당 컨테이너로 부터 베리어스 주입 받기
		
		VariousDIVO vo1 = context.getBean("vo1",VariousDIVO.class);
		
		
		//4. vo가 가진 property 로그찍기
		log.info("주입된 객체 : {}", vo1);
		log.info("fsFile : {}", vo1.getFsFile());
		log.info("cpFile : {}", vo1.getCpFile());
	}
}
