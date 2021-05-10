package WEEK5.Synchronize;

public class Counter_Sync {

	private int c = 0;

	public synchronized void increment() {
		c = c + 1;
	}

	public synchronized void decrement() {
		c = c - 1;
	}

	public int get() {
		return c;
	}
}
