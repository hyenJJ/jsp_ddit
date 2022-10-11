package kr.or.ddit.validate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidateUtils<T> {

private static Validator validator;
	
	//클래스가 로딩할 때 한번 실행 
	 static  {
		 validator = Validation.byDefaultProvider()
		        .configure()
		        .messageInterpolator(
		                new ResourceBundleMessageInterpolator(		
		                        new PlatformResourceBundleLocator( "kr.or.ddit.msgs.message" )
		                )
		        )
		        .buildValidatorFactory()
		        .getValidator();
		
	}
	 						// 가변형 파라미터 
	 public Map<String, String> validate(T target, Class...groups) {
		 					//여러가지 VO 가 오기때문에
		 Set<ConstraintViolation<T>> violations = 
					validator.validate(target, groups);
			Map<String, String> errors = new HashMap<>();
			for( ConstraintViolation violation : violations) {					
				String propertyName = violation.getPropertyPath().toString();
				String message = violation.getMessage();
				log.info("{} : {}" , propertyName,message);// {} = %s
				errors.put(propertyName, message); //map message 넣어줌 
			}
			return errors;
	 }
}
