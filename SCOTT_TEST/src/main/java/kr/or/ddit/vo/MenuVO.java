package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  //기본 생성자
public class MenuVO {
	
	
	public MenuVO(String menuURL, String menuText) {
		super();   //무조건 있어야 하는 데이터 
		this.menuURL = menuURL;
		this.menuText = menuText;
	}
	
	private String menuURL;
	private String menuText;
	private String menuId;
	private String menuColor;

}
