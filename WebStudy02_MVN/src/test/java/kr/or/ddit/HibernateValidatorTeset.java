package kr.or.ddit;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class HibernateValidatorTeset {

	private static Validator validator;
	
	@BeforeClass // 아무리 testcase가 많아도 이 메소드는 한번 실행
	public static void setUpClass() {
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
	
	@Test
	public void test() {
		MemberVO target = new MemberVO();
		/* target.setMemId("a001");
		target.setMemPass("abc");
		target.setMemMail("ab@naver.com");
		target.setMemMileage(-1); */
		Set<ConstraintViolation<MemberVO>> violations = 
				validator.validate(target, UpdateGroup.class, InsertGroup.class );//Default.class
		//insertGroup으로 묶은 것만 검증, 다른것도 다 검증하고싶음  Default.class 추가  
		// 항상 추가 하면 번거로우니 Insert interface를 만들어서 Default를 상속하면 insertGroup만 하면 다 검증 가능
		for( ConstraintViolation violation : violations) {					
			String propertyName = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			log.info("{} : {}" , propertyName,message);// {} = %s
		}
	}

}
