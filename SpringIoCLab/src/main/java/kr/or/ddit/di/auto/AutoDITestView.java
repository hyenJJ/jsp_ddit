package kr.or.ddit.di.auto;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.view.SampleView;

public class AutoDITestView {
	public static void main(String[] args) {
		//컨테이너
		//컨테이너의 경로 설정
		//
		
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/auto/conf/AutoDI-Context.xml");
		
		context.registerShutdownHook();
		
		SampleView view =  context.getBean(SampleView.class);
		view.rendermessage(4);
		
	}
}
