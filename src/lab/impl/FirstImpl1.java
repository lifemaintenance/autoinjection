package lab.impl;

import lab.anot.Bean;
import lab.interf.First;

@Bean(level = 1)
public class FirstImpl1 implements First {

	@Override
	public void first() {
		System.out.println("FirstImpl1");
	}
}
