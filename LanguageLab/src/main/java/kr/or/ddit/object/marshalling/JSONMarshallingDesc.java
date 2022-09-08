package kr.or.ddit.object.marshalling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.object.TestVO;

/**
 * 
 *  Marshalling / UnMarshalling
 *  
 *  json/xml - 이기종 시스템간 메시지 교환시 사용되는 공통 데이터 표현 방식 
 *  Marshalling : native data -> JSON/XML
 *  UnMarshalling : JSON/XML -> native data
 *  
 *  Serialization : 전송이나 저장을 목적으로 객체의 상태를  byte array(bit stream) 로 변환하는 과정.
 *  DeSerialization : byte array(bit stream) 으로부터 객체의 상태를 복원하는 과정.
 *  
 *  native data    ->    JSON/XML    ->    byte array    ->    JSON/XML    ->    native data
 *            Marshalling      Serialization       DeSerialization    UnMarshalling

 * 
 *  native data    <-    JSON/XML    <-    byte array     <-    JSON/XML     <-    native data
 *            UnMarshalling    DeSerialization      Serialization      Marshalling
 *
 */
public class JSONMarshallingDesc {
	public static void main(String[] args) throws JsonProcessingException  {
		TestVO vo = new TestVO();
		vo.setProp1("Value1");
		vo.setProp2("Value2");

//      serialize(vo);
//      deSelrialize();
		
//		String json = marshalling(vo);
//		TestVO vo2 = unMarshalling(json, TestVO.class);
//		
//		System.out.println(vo2);

		transfer(vo);
		
//		 String json2 = "{\r\n" + "  \"prop1\" : \"Value1\",\r\n" +
//		                 "  \"prop2\" : \"Value2\"\r\n" + "}\r\n" + "";
//		 
//		 TestVO vo2 = mapper.readValue(json, TestVO.class); 
//		 System.out.println(vo2);
		 
	
	}
	
	
	private static void transfer(Object target) {
		String json = marshalling(target);
		serialize(json);
		
		
	}
	                                                   //T 타입변수
	private static <T> T  unMarshalling(String json, Class<T> javaType) {
		         // 제네릭 타입 : 오는타입에 맞게 바뀜
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json, javaType);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private static String marshalling(Object target) {
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper
					.writerWithDefaultPrettyPrinter()
					.writeValueAsString(target);
			System.out.println(json);
			
			return json;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	// 역직렬화 작업
	private static void deSelrialize() {
						
		File objFile = new File("d:/contents", "obj.dat");
		
		try(
		      FileInputStream fis = new FileInputStream(objFile);
			  ObjectInputStream ois = new ObjectInputStream(fis);
		){
			  Object obj = ois.readObject();
			  System.out.println(obj);
			                    
		} catch (IOException | ClassNotFoundException e) {  //IOException : 처리하지않으면 오류 
			throw new RuntimeException(e) ; // RuntimeException : Unchecked 최상의 exception
		}
	}
	
	
	// 직렬화 작업
	private static void serialize(Object target) {
		
		File objFile = new File("d:/contents", "obj.dat");
		//읽을라면 저장할 매체가 필요
			
		try(
	        FileOutputStream fos = new FileOutputStream(objFile);
		    ObjectOutputStream oos = new ObjectOutputStream(fos);
		                          //직렬화 되는 매체를 콘솔에 출력
	    ){
		    oos.writeObject(target);
			
         }catch(IOException e) {
			throw new RuntimeException(e);
		}
		
	}

}
