package kr.or.ddit.designpattern.templatemethod.example02;

public abstract class SqlMapClient {
	public final void queryForObject(String id, Object param, Class<?> resultClass) {
		
		connect();
		String query = statement(id);
		String result = executeQuery(query, param);
		handleResult(result, resultClass);
		
	}
	private void connect() {
		System.out.println("연결수립");
	}
	private String statement(String id) {
		return id + " 아이디의 쿼리 객체 생성 및 변환 ";
	}
	private String executeQuery(String stmt, Object param){
		return stmt + " 를 실행할 때, 파라미터 " + param+"설정했음.";
	}
	
	protected abstract void handleResult(String result, Class<?> resultClass); // 추상메소드
	

}
