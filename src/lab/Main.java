package lab;

import lab.anot.Application;
import lab.myclass.MyClass;

public class Main {
	public static void main(String[] args) {
		MyClass myClass = (MyClass) Application.inject(new MyClass());
		assert myClass != null;
		myClass.foo();
	}
}
