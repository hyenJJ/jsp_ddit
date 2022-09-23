package kr.or.ddit.designpattern.templatemethod;

import kr.or.ddit.designpattern.templatemethod.example01.DerivedClass1;
import kr.or.ddit.designpattern.templatemethod.example01.DerivedClass2;
import kr.or.ddit.designpattern.templatemethod.example01.TemplateClass;

public class Example1Ground {

	public static void main(String[] args) { 
		TemplateClass[] array = new TemplateClass[] { 
			new DerivedClass1()   //클래스 둘다 templateclass 상속받음
			, new DerivedClass2()
		};
		
		for( TemplateClass obj: array ) {
			obj.template();
		}
	}
}
