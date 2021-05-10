package WEEK5.Synchronize;

public class Counter {

	private int c = 0;

	public void increment() {
		c = c + 1;
	}

	public void decrement() {
		c = c - 1;
	}

	public int get() {
		return c;
	}
}
