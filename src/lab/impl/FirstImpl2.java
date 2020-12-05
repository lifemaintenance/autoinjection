package lab.impl;

import lab.anot.Bean;
import lab.interf.First;

@Bean(level = 0)
public class FirstImpl2 implements First {

	@Override
	public void first() {
		System.out.println("FirstImpl2");
	}
}
