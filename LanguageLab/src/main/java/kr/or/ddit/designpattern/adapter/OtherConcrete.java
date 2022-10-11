package kr.or.ddit.designpattern.adapter;

public class OtherConcrete implements Target {

	@Override
	public void request() {
		System.out.println("target의 전체에 OtherConcrete 동작");

	}

}
