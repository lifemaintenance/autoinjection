package lab.impl;

import lab.anot.Bean;
import lab.interf.Second;

@Bean(level = 2)
public class SecondImpl1 implements Second {
	@Override
	public void second() {
		System.out.println("SecondImpl1");
	}
}
