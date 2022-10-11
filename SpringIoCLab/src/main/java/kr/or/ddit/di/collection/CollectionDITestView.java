package kr.or.ddit.di.collection;

import java.util.Date;

import javax.xml.crypto.Data;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollectionDITestView {
	public static void main(String[] args) {
		// 컨테이너 생성
		// 해당 vo injection
		// 컨테이너의 구현체는 
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/kr/or/ddit/di/conf/CollectionDI-Context.xml");
		
		context.registerShutdownHook();
		
		CollectionDIVO vo1 = context.getBean("colVO1" , CollectionDIVO.class);
		CollectionDIVO vo2 = context.getBean("colVO2" , CollectionDIVO.class);
		log.info("{}", vo1);
		log.info("{}", vo2);
		Date now = context.getBean(Date.class);
		log.info("{} : {}", now, new Date());
	}
}
