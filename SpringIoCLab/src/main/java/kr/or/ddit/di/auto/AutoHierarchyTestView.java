package kr.or.ddit.di.auto;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.view.SampleView;

public class AutoHierarchyTestView {
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext root = 
				new ClassPathXmlApplicationContext("/kr/or/ddit/auto/conf/Parent-Context.xml");
		
		ConfigurableApplicationContext child = 
				new ClassPathXmlApplicationContext(
						new String[] {"/kr/or/ddit/auto/conf/Child-Context.xml"}
						, root
				);
		
		SampleView view =  child.getBean(SampleView.class);
		
		view.rendermessage(3);
		
		
	}
	
}
