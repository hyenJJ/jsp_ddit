package kr.or.ddit.di.collection;

import org.springframework.beans.factory.FactoryBean;

// implements가 나온 순간부터 POJO 가 될수가 없다
public class StringArrayFactoryBean implements FactoryBean<String[]> {
	
	//<BEAN>을 사용하는 것과 같은 것 
	@Override
	public String[] getObject() throws Exception {
		
		return new String[] {"arrayValue1",null,"arrayValue2"} ;
	}

	@Override
	public Class<?> getObjectType() {
		
		return String[].class;
	}

	@Override      
	public boolean isSingleton() {
		
		//return false;
		return true; //새로운 객체를 계속 만들겠다다 
	}

}
