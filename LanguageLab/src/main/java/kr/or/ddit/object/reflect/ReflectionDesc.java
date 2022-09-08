package kr.or.ddit.object.reflect;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import kr.or.ddit.reflect.ReflectionTest;

/**
 * 
 * 리플렉션 ?
 *   : 객체로부터 타입, property, method 등 해당 객체의 구조를 추측해 가는 과정.
 *      java.lang.reflect 패키지의 API 들로 지원.
 *         Class, Field, Method, Parameter...
 *         
 *      구체적인 클래스 타입을 알지 못해도 그 클래스의 정보(메서드, 타입, 변수 등등)에 접근할 수 있게 해주는 자바 API 
 *
 */
public class ReflectionDesc {
       public static void main(String[] args) {
    	   //mem_hp 값 변경
    	   Object retValue  = ReflectionTest.getObject();
    	   
    	   System.out.println(retValue);
    	   String setterName = "setMem_hp";
    	   Class clz = retValue.getClass();
    	   
    	   try {
//			Method setter = clz.getDeclaredMethod(setterName, String.class);
//			                                                   //파라미터
//			setter.invoke(retValue,"00-000-0000");
//			
//			String getterName = "getMem_hp";
//			Method getter = clz.getDeclaredMethod(getterName); //getter 파라미터 x
//			Object fldValue = getter.invoke(retValue);
//			System.out.println(fldValue);
			
			PropertyDescriptor pd = new PropertyDescriptor("mem_hp", clz);
			Method setter = pd.getWriteMethod();
			setter.invoke(retValue,"00-000-0000");
			
			Method getter = pd.getReadMethod();
			Object fldValue = getter.invoke(retValue);
			System.out.println(fldValue);
			
		} catch ( SecurityException |  IllegalArgumentException  | IntrospectionException | IllegalAccessException | InvocationTargetException e) {
			
			e.printStackTrace();
		}
       }
       
       
       
}

