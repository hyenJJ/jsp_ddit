package kr.or.ddit.enumpkg;

public enum OperatorType {
	PLUS('+',new RealOperator(){  
		     // 인터페이스 익명 객체
		
		@Override
		public int realOperate(int leftOp, int rightOp) {
			return leftOp + rightOp;
		};
    }),
	MINUS('-',new RealOperator() {
		
		public int realOperate(int leftOp, int rightOp) {			
			return leftOp - rightOp;
			
		};	
	}),
	
    //람다함수 ()->{}	: () = 파라미터 파트  /  {} = 메소드 바디  
	MULTIPLY('*', (leftOp, rightOp)->{return leftOp * rightOp;}),
	DIVIDE('/', (leftOp, rightOp)->leftOp /rightOp);
                                       //연산이 한줄만 있다면 바디를 지울 수 있음
	
	
	
	private OperatorType(char sign, RealOperator operator) {
		this.sign = sign;
		this.operator = operator;
	}
	
	
	private char sign;
	private RealOperator operator;
	
	public char getSign() {
		return sign;
	}
	     //연산을 하겠다 ! 두개의 숫자형 피연산자가 파라미터로 와야함
	public int operate(int leftOp, int rightOp) {
		return operator .realOperate(leftOp, rightOp);
	}
	
	private static final String PATTERN = " %d %s %d = %d" ;
	
	public String getExpression(int leftOp, int rightOp) {
		return String.format(PATTERN, leftOp, sign, rightOp, operate(leftOp, rightOp));
		
		
	}

}
