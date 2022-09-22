package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.commons.exception.PKNotFoundException;
import kr.or.ddit.props.dao.DataBasePropertyDAOImpl;

import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.vo.PropertyVO;
// layer에서 가장 중요한것 -> 의존성
public class PropertyServiceImpl implements PropertyService {
	// HCLC -> HightCohesionLooseCoupling : 응집도 높게 결합도 낮게 
	// 결합력이 높을수밖에 없는 코드다.
//	private PropertyDAO dao = new FilesystempPropertyDAOImpl();
	private PropertyDAO dao = new DataBasePropertyDAOImpl();
	                //dependency injection
//	public void setDao(PropertyDAO dao) {
//		this.dao = dao;
//	}



	@Override
	public PropertyVO readProperty(String propertyName) {
		PropertyVO vo = dao.selectProperty(propertyName);
		if(vo==null)
			throw new PKNotFoundException(String.format("%s 에 해당하는 데이터 없음.", propertyName));
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
