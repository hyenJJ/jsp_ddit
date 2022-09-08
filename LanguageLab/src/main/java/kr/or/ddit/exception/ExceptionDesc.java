package kr.or.ddit.exception;

import java.io.IOException;

/**
 * 
 *  예외(exception, throwable object)
 *  Throwable 최상위 트리구조 형성.
 *     - Error
 *     - Exception : 비정상적인 상황이되, 직접 처리가 간으한 예외적 상황을 표현하는 객체.
 *          checked exception : 예외 가능 상황에서 반드시 처리해야 하는 예외, 처리하지 않으면, copile error
 *               ex) IOException, SQLException..
 *          unChecked exception(RuntimeException(상위)) : 직접 처리하지 않더라도, 예외 제어권이 메소드 호출 구조로 전달되는 예외.
 *               ex) NullPointerException, IllegalArgumentException
 *
 *   처리 방법
 *   직접 처리 : try~catch~finally, try~multi catch, try with resource
 *   위임 처리 : throws 
 *   
 *   Custom Exception 정의 
 *
 */
public class ExceptionDesc {
     public static void main(String[] args)  {  // throws IOException : VM이 예외를 가지고 감
//		try {
//			method1();
//		} catch (IOException e) {
//			System.err.println(e.getMessage());
//			throw new RuntimeException(e);
//			
//		}
    	 
    	 try {
			
    		 String text="a23";
    		 int number = method2(text);
    		 System.out.println(number);
    		 
  		 
		} catch (NumberFormatException e) {     
			
			System.err.println(e.getMessage());

			throw e;
		                           
			
		}
    	 
	}
     
     private static int method2(String text) {
    	 
    	 try {
    		 return Integer.parseInt(text);
			
		} catch (RuntimeException e) {
//			e.printStackTrace();
//			return -1;
			throw new CustomException(e);  //numberformatexception 
		}
    
     
     }
     
     private static void method1() throws IOException {
    	 if(1==1)
    		 throw new IOException("강제 발생 예외");    
    	 System.out.println("method1 호출");
     }
}
