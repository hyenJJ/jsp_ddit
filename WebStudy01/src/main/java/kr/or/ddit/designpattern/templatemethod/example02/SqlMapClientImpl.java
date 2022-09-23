package kr.or.ddit.designpattern.templatemethod.example02;

public class SqlMapClientImpl extends SqlMapClient {

	@Override
	protected void handleResult(String result, Class<?> resultClass) {
		System.out.printf("[%s] 를 받아서 [%s] 로 객체화 시킴." ,result, resultClass);
		
	}

}
