package kr.or.ddit.props.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.props.vo.PropertyVO;

public class PropertyServiceImplTest {
	
	PropertyService service = new PropertyServiceImpl(); //test하기 위한
    // 마크 어노테이션
	
	@Test
	public void testReadPropertyExist() {
		PropertyVO vo = service.readProperty("prop1");
		assertNotNull(vo);
	}
	
	@Test(expected = PKNotFoundException.class) //어떤 에러가 발생할지 
	public void testReadPropertyNotExist() {
		PropertyVO vo = service.readProperty("prop1adfadsfae");
		
	}

	@Test
	public void testReadProperties() {
		List<PropertyVO> dataList = service.readProperties();
		assertNotNull(dataList);
		assertNotEquals(0, dataList.size());
		assertNotNull(dataList.get(0).getDescription()); 
	}

	@Test
	public void testCreateProperty() {
//		Builder Pattern
		
		PropertyVO vo = new PropertyVO();
		vo.setPropertyName("prop4");
		vo.setPropertyValue("value4");
		
		service.createProperty(vo);
	}

}
