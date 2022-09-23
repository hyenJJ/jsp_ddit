package kr.or.ddit.enumpkg;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/* enum : 클래스 형태 
          -생성자가 private으로 묶여있음 => 외부에서 instanc 생성 x
          -instance의 갯수 제한이 있음
          -생성잦의 종류를 결정할 때는 상수를 통해서 결정한다
   final : 최종 => 상속 불가능
 */
//유일한 인스턴스
//	public static final BrowserType TRIDENT = new BrowserType();


public enum BrowserType {

	TRIDENT("IE"),EDG("Edg"),CHROME("Chrome"),SAFARI("Safari"),OTHER("기타");
	
		
	
	private BrowserType(String browserName) {
		this.browserName = browserName;
	}


	private String browserName;
	
	
	// get만 있는 이유 값을 변경하지 않겠다 
	public String getBrowserName() {
		return browserName;
	}
	
	public static BrowserType searchBrowser(HttpServletRequest req){
	    String agent = req.getHeader("User-Agent" );
	    agent = Objects.toString(agent,"").toUpperCase();
	                                //agent가 null 경우에 ""
	
		   BrowserType searched = BrowserType.OTHER;
		   for(BrowserType tmp :  BrowserType.values()){
				if(agent.indexOf(tmp.name())>-1){
					searched = tmp;
		    		break;
			   
		 }
				
	  }
      return searched;
   }
	
   public static String searchBrowserName(HttpServletRequest req) {
	    return searchBrowser(req).getBrowserName();
   }
   
}






