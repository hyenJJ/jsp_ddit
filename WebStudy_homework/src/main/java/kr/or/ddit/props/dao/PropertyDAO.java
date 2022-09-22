package kr.or.ddit.props.dao;

import java.util.List;

import kr.or.ddit.props.vo.PropertyVO;

/**
 * 
 * Property 관리를 위한 Persistence Layer
 *
 */
public interface PropertyDAO { 
    public PropertyVO selectProperty(String propertyName); 
  //500원을 받아서 라면을 사와야함
    
    public List<PropertyVO> selectProperties();
    
    
    public void insertProperty(PropertyVO propertyVO); 

}
