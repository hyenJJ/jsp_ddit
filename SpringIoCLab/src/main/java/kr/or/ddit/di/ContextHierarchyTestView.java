package kr.or.ddit.di;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextHierarchyTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent =
				new ClassPathXmlApplicationContext("/kr/or/ddit/di/conf/Properties-Conext.xml");
		
		
		ConfigurableApplicationContext context1 = 
				new ClassPathXmlApplicationContext(
						new String[] {"/kr/or/ddit/di/conf/CollectionDI-Context.xml"}
						,parent
				);
		
		ConfigurableApplicationContext context2 = 
				new ClassPathXmlApplicationContext(
						new String[] {"/kr/or/ddit/di/conf/VariousDI-Context.xml"}
						, parent
				);
	
		
//		context1.getBean("dbInfo");
	}
}
