package kr.or.ddit.designpattern.templatemethod.example01;

public abstract class TemplateClass {
	
	public final void template() {  // final이라 고정되어서 순서를 바꿀수 없음
		stepOne();
		stepTwo();
		stepThree();
	}
	
	private void stepOne() {
		System.out.println("1단계");
		
	}
	
	protected abstract  void stepTwo();
	
	private void stepThree() {
		System.out.println("3단계");
	}

}
