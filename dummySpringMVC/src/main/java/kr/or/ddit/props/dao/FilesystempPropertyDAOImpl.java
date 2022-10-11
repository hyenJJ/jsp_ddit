package kr.or.ddit.props.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.management.RuntimeErrorException;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.springframework.stereotype.Repository;

import kr.or.ddit.props.vo.PropertyVO;

@Repository
public class FilesystempPropertyDAOImpl implements PropertyDAO {
	
	private String path = "D:\\A_TeachingMaterial\\6.JspSpring\\workspace\\WebStudy01\\src\\main\\resources\\kr\\or\\ddit\\props\\DataStore.properties";
	private Properties properties;
	
	
	public FilesystempPropertyDAOImpl() {
		properties = new Properties();
//		properties file load : InputStream 사용됨. --> IOException 처리
		try (             //try 뒤에 괄호가 있는것은 Statement Class의 인스턴스나 Stream 타입의 클래스들이 동작후 필요로하는 close() 메소드를 자동실행 해주는 공간 
				          // fis.close(); 를 안 하고도 try 리소스 문 끝날 때,자동으로 close() 된다는 것을 보장 받는다.
	        FileInputStream fis = new FileInputStream(path); //경로에 있는파일을 inputstream 으로 읽어온다 
	    ){
			
			properties.load(fis);   //
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	
	}
	

//	TDD (Test-Driven Development)
	@Override
	public PropertyVO selectProperty(String propertyName) {
		PropertyVO finded = null;
		String propertyValue = properties.getProperty(propertyName);
		
		if(propertyValue != null) {
			finded = new PropertyVO();
			finded.setPropertyName(propertyName);
			finded.setPropertyValue(propertyValue);
		}
		return finded;
	}
	@Override
	public List<PropertyVO> selectProperties() {
		List<PropertyVO> dataList = new ArrayList<>();
		properties.forEach((k,v)->{ //forEach 람다식 
			             
			PropertyVO vo = new PropertyVO();
			vo.setPropertyName(k.toString());  
			vo.setPropertyValue(v.toString());
			dataList.add(vo);
			
		});
		                                
		return dataList;
	
		
	}
	@Override
	public void insertProperty(PropertyVO propertyVO) {
		
		properties.setProperty(propertyVO.getPropertyName(), propertyVO.getPropertyValue());
	    try (
		    FileOutputStream fos = new FileOutputStream(path);
		){
			properties.store(fos, String.format("'%tc' 에 저장함.",Calendar.getInstance()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		//properties.st`
	
	
	}
	
	
	

}
