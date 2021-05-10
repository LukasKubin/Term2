package WEEK5.Synchronize;

/*
 * Example of Java method Synchronization
 * Date: 26 Feb 2021
 * 
 */
public class methodSynchronized extends Thread {

	private String name;
	private Counter_Sync mcounter;

	public methodSynchronized(String name, Counter_Sync counter) {
		this.name = name;
		this.mcounter = counter;
	}

	public void run() {
		System.out.println("Thread " + name + " got started");
 			for (int i = 0; i < 1000; i++) {
				mcounter.increment();
				mcounter.decrement();
			}
 
		System.out.println("Thread " + name + " finished");
	}

	public static void main(String args[]) {
		String[] name = { "A", "B" };
		System.out.println("Master got started");

		Counter_Sync mcounter = new Counter_Sync();

		Thread[] slave = new Thread[name.length];

		for (int i = 0; i < name.length; i++) {
			System.out.println("Master is starting Thread " + name[i]);
			slave[i] = new methodSynchronized(name[i], mcounter);
			slave[i].start();
		}

		System.out.println("Master will now wait for each Thread to finish");

		try {
			for (Thread s : slave)
				s.join();
		} catch (InterruptedException e) {
			System.out.println("Interruption before completion of the joins" + e);
			System.out.println("Master has given up waiting for slaves");
		}

		System.out.println("counter should be 0 and actually is " + mcounter.get());

		System.out.println("Master is exiting");
	}
}
