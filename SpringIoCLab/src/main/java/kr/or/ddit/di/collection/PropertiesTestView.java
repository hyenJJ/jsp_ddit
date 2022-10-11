package kr.or.ddit.di.collection;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class PropertiesTestView {

	public static void main(String[] args) {
		
//		Properties props = System.getProperties();
//		
//		for( Entry<Object, Object> entry: props.entrySet()) {
//			System.out.printf("%s : %s \n", entry.getKey(), entry.getValue());
//		}
//		
//		Map<String, String> env =  System.getenv(); // 환경변수
//		
//		for( Entry<String, String> entry: env.entrySet()) {
//			System.err.printf("%s : %s \n", entry.getKey(), entry.getValue());
//		}
		
		
		ConfigurableApplicationContext context = new GenericXmlApplicationContext("classpath:/kr/or/ddit/di/conf/Properties-Conext.xml");
		
		
		context.registerShutdownHook();
		
		DBInfoVO info1 = context.getBean("info1", DBInfoVO.class);
		log.info("spEL 로 주입된 객체 : {}", info1);

		DBInfoVO info2 = context.getBean("info2", DBInfoVO.class);
		log.info("placeholder 로 주입된 객체 : {}", info2);
		
		
		
	}
}
