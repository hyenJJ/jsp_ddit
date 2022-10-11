package kr.or.ddit.props.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.props.dao.FilesystempPropertyDAOImpl;
import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.vo.PropertyVO;
// layer에서 가장 중요한것 -> 의존성
@Service
public class PropertyServiceImpl implements PropertyService {
	// HCLC -> HightCohesionLooseCoupling : 응집도 높게 결합도 낮게 
	// 결합력이 높을수밖에 없는 코드다.
	
	
//	private PropertyDAO dao = new FilesystempPropertyDAOImpl();

	                //dependency injection
//	public void setDao(PropertyDAO dao) {
//		this.dao = dao;
//	}


	private PropertyDAO dao; //내가 원하는대로만 injection해줘라
	
//	@Inject
	public PropertyServiceImpl(PropertyDAO dao) {
		super();
		this.dao = dao; //기본생성자 ㅇ벗어짐
	}

	@Override
	public PropertyVO readProperty(String propertyName) {
		PropertyVO vo = dao.selectProperty(propertyName);
		if(vo==null)
			throw new RuntimeException(String.format("%s 에 해당하는 데이터 없음.", propertyName));
		return vo;
	}

	@Override
	public List<PropertyVO> readProperties() {
		List<PropertyVO> dataList =  dao.selectProperties();
		dataList.forEach((vo)->{ //map이나 properties에서는 key와 value값 두개가 있기때문에 by consumer 
			if(vo.getDescription()==null)
				vo.setDescription("라볶이 만들었음.");
		});
		return dataList;
		
	}

	@Override
	public void createProperty(PropertyVO vo) {
		dao.insertProperty(vo);
		
	}

}
