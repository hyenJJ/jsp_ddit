package kr.or.ddit.designpattern.templatemethod;

import java.util.HashMap;
import java.util.LinkedHashMap;

import kr.or.ddit.designpattern.templatemethod.example02.SqlMapClient;
import kr.or.ddit.designpattern.templatemethod.example02.SqlMapClientImpl;

public class Example2Ground {
   
	public static void main(String[] args) {
		SqlMapClient client = new SqlMapClientImpl();
		client.queryForObject("selectMember", "a001", HashMap.class);
		client.queryForObject("selectPropd", "P101000001", LinkedHashMap.class);
	}		
		
}
