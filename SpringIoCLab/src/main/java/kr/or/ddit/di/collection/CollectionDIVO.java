package kr.or.ddit.di.collection;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CollectionDIVO {
	
	private List<String> strList;
	private Set<Date> dateSet;
	private Map<String, Object> map;
	private Properties props;
	
	private String[] array;
	
	
}
