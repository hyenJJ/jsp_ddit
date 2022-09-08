package kr.or.ddit.object;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BeanUtilsTest {
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap();
		map.put("prop1", "VALUE1");		
		map.put("prop2", "VALUE2");
		
		TestVO vo = new TestVO();   //map에서 vo에다가 담기
		                            //      1.reflection	
		Class<? extends TestVO> clz = vo.getClass();  
		                           // =TestVO.class
		Field[] fields = clz.getDeclaredFields();   //getFields -> public만 
		for(Field fld : fields) {                   //getDeclaredFields -> public private.. 모두 다 
			//System.out.println(fld);
			String fldName = fld.getName();
			System.out.println(fldName);
			fld.setAccessible(true);  // 모든 접근 가능하게  (private -> public)
			
			try {
				fld.set(vo, map.get(fldName));    //불확실성..언제든지 예외가 발생할수있음
			} catch (IllegalArgumentException | IllegalAccessException e) {
				
				e.printStackTrace();
			} 
		}
		
		System.out.println(vo);
		
		vo = new TestVO();
		for( Entry<String, Object> entry : map.entrySet()) {
			
			//System.out.println(entry.getKey());
		      String key = entry.getKey();
		      Object value = entry.getValue();
		      
		      try {
				Field fld = clz.getDeclaredField(key);
				//vo.setProp1("VALUE1");
				String setterName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
				Method setter = clz.getDeclaredMethod(setterName, value.getClass());
				setter.invoke(vo, value);
			} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				
				e.printStackTrace();
			}
		}
		System.out.println(vo);
		
		//BeanUtils.populate(vo, map);
	}
	

}
