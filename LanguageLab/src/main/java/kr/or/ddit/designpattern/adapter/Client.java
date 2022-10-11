package kr.or.ddit.designpattern.adapter;

public class Client {

	public static void main(String[] args) {
		// wrapper pattern, new Integer(3);
		Target target = new Adapter(new Adaptee());
		target.request();
	}
}
