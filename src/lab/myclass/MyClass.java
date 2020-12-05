package lab.myclass;

import lab.anot.AutoInjectable;
import lab.interf.First;
import lab.interf.Second;

public class MyClass {
	private First first;
	private Second second;

	public First getFirst() {
		return first;
	}
	@AutoInjectable
	public void setFirst(First first) {
		this.first = first;
	}

	public Second getSecond() {
		return second;
	}
	@AutoInjectable
	public void setSecond(Second second) {
		this.second = second;
	}

	public void foo() {
		first.first();
		second.second();
	}
}
