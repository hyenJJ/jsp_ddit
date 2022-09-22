package kr.or.ddit.commons.exception;

// 이 예외를 발생하면 수동으로 설정하지않더라도 톰캣에게 전달이 되어야함
public class UserNotFoundException extends PKNotFoundException {
                               	          //회원이 존재하지 않을 때 상황

	private String memId;
	
	public UserNotFoundException(String memId) {
		this.memId = memId;
		
	}
	
	@Override
	public String getMessage() {
	
	    return String.format("%s 아이디 회원이 존재하지 않음.", this.memId);
	}

	
}
