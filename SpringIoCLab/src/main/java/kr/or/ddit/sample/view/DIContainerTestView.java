package kr.or.ddit.sample.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

//컨테이너가 관리하는 bean(객체)도 생명주기가 존재한다 
// 생성주기 내에서 호출할수있는 callback이 존재 
@Slf4j
public class DIContainerTestView {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/sample/conf/DI-Context.xml");
					 				//classpathxml이 아닌 Generic이기 때문에 classpath:를 써줘야함 (file, http 등이 올수도 있음)
		
		//context.close();  container의 생명주기를 끝냄 
		//그러나 항상 언제 끝나는지 알수 가 없기 때문에 
		
		context.registerShutdownHook();//  예약 쓰레드 -> 알아서 종료해줌
		
//		SampleView view1 = (SampleView)context.getBean("sampleView1");//프레임워크의 최소 데이타 타입은 object 타입	
//		SampleView view2 = (SampleView)context.getBean("sampleView2");	
//		log.info("주입된 객체 : {}",view1);
//		log.info("주입된 객체 : {}",view2);
//		log.info("동일 객체 여부 : {} " , view1 == view2); 
	

		
	}
}
