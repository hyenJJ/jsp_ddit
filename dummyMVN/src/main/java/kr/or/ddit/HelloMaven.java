package kr.or.ddit;

import java.io.InputStream;

public class HelloMaven {
	public static void main(String[] args) {
		
		System.out.println("Hello maven");
		
		try {
			InputStream is = HelloMaven.class.getResourceAsStream("");
		} catch (Exception e) {
			
		}
	}

}
