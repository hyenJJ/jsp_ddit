package kr.or.ddit.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class MessageBundleRead {

	public static void main(String[] args) {

	 // propertiesRead();
		
	// basename
	// classpath name 이후에 qualified name 형태
	// locale 형태가 아님 -> 언더바 x
	// resourceBundle = message Bundle -> 읽는데 의미가 있다 	
	  String baseName ="egovframework.message.com.message-common";
	  ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.ENGLISH); //locale 언어만 갈아서 끼울 수 있음
	  System.out.println(bundle.getString("fail.common.msg"));  //resource bunle : set X , put X -> read only
		
	}
	private static void propertiesRead() {
		Properties properties = new Properties();
		
		//map 이랑 비슷하지만 map은 inmemory data -> 휘발성이 있다.
		String path = "/egovframework/message/com/message-common_en.properties";  
		
		try(
		  InputStream is = MessageBundleRead.class.getResourceAsStream(path);
				
		){
			
			properties.load(is);
			
			for(Entry<Object, Object> pro : properties.entrySet()) {
				String key = Objects.toString(pro.getKey());
				String value = Objects.toString(pro.getValue());
				System.out.printf("%s : %s \n",key,value);
				
			}
			
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e); 
		}
	
	}

}
