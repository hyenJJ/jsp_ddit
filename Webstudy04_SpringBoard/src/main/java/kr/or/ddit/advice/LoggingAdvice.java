package kr.or.ddit.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/* proxy server 
 	-> 가짜 서버
 	client 가 요청을 보낼때 proxy server를 거쳐서 진짜 server로 감 
 	
 	
*/
/**
 * Cross Cuttiong Concern 코드화 -> Advice
 * 
 *
 */

@Slf4j
//@Component  // advice 설정  ,  상위 -> dao service weaving 가능
@Aspect
public class LoggingAdvice {
	
	
	//auto proxy
	@Pointcut("execution(* kr.or.ddit.*.service.*.*(..))")
	public void dummy() {
		
	}
	
//	@Before("dummy()") // pointcut
	public void before(JoinPoint joinPoint) {
		log.info("==============================================");
	
	
		Signature signature =  joinPoint.getSignature(); // 타겟 메소드의 선언부 정보
		String targetMethodName = signature.getName();
		Object target = joinPoint.getTarget();
		String targetClassName = target.getClass().getSimpleName();
		
		Object[] args = joinPoint.getArgs(); // 인자를 복수형으로 바꿔줌
		log.info("{}.{}({})" ,targetClassName ,targetMethodName,args);
	}

//	@AfterReturning(pointcut = "dummy()", returning="retValue")
	public void afterReturn(JoinPoint joinPoint, Object retValue) {
		Signature signature =  joinPoint.getSignature(); // 타겟 메소드의 선언부 정보
		String targetMethodName = signature.getName();
		Object target = joinPoint.getTarget();
		String targetClassName = target.getClass().getSimpleName();
		log.info("{}.{} 의 반환값 : {}",targetClassName,targetMethodName, retValue);
		
		
		
	}
	
	// 소요 시간 
	@Around("dummy()") // around가 befor와 after를 포함하고 있기 때문에 around만 설정해줘도 됨 
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable { // 발생한 모든 예외는 spring container로  넘어감 
		
		log.info("==============================================");	
		Signature signature =  joinPoint.getSignature(); // 타겟 메소드의 선언부 정보
		String targetMethodName = signature.getName();
		Object target = joinPoint.getTarget();
		String targetClassName = target.getClass().getSimpleName();
		
		Object[] args = joinPoint.getArgs(); // 인자를 복수형으로 바꿔줌
		log.info("{}.{}({})" ,targetClassName ,targetMethodName,args);
		
		long start = System.currentTimeMillis(); // 시작시간
		
		//-------------타켓 호출
		Object retValue;		
		retValue = joinPoint.proceed(args);   //proceed를 통해서 직접적으로 target을 호출 할 수 있음
		// argument가 있을지 없을지 모르니까 있다는 가정으로 
		
		long end = System.currentTimeMillis(); // 끝난 시간
		
		log.info("{}.{} 의 반환값 : {} \n 소요시간 : {}ms"
				,targetClassName,targetMethodName, retValue
				, (end-start));
			
		return retValue;
		
	}
	
}
