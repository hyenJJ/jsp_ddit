package kr.or.ddit.di.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DBInfoVO {
	
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	private int initialSize;
	private int maxTotal;
	private int maxIdle;
	private int maxWait;
	
}
