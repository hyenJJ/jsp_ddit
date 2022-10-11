package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.vo.PropertyVO;

/**
 * 
 * Property 관리를 위한 Business Logic Layer(Service Logic Layer, Logic Layer)
 *
 */
public interface PropertyService {
	
	/**
	 * 
	 * 프로퍼티 한쌍 조회, 해당 프로퍼티가 존재하지 않는 경우, PKNotFoundException(커스텀) 발생시킴.
	 * @param propertyNameString
	 * @return
	 */
	public PropertyVO readProperty(String propertyName);
	
	
	/**
	 * 프로퍼티 조회후 description 이 없는 경우,
	 * "라볶이 만들었음" 이라는 description 을 설정함.
	 * @return
	 */
	public List<PropertyVO> readProperties();
	
	
	/**
	 * 새로운 프로퍼티 추가
	 * @param vo
	 */
	public void createProperty(PropertyVO vo);

}
