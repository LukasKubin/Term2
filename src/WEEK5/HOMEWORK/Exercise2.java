package WEEK5.HOMEWORK;

import java.util.*; // for Random

public class Exercise2 extends Thread {
	private int[] a;
	private int min, max, sum;

	// Constructor
	public Exercise2(int[] a, int min, int max) {
		this.a = a;
		this.min = min;
		this.max = max;
	}

	// Computes the total sum of all elements of the given array.
	public static int sum(int[] a) {
		int result = 0;
		for (int i = 0; i < a.length; i++)
			result += a[i];
		return result;
	}

	// Computes the total sum of elements of the given array in given range
	// (min,max)
	public static int sumRange(int[] a, int min, int max) {
		int total = 0;
		for (int i = min; i < max; i++)
			total += a[i];
		return total;
	}

	// thread run method
	public void run() {
		sum = sumRange(a, min, max);
	}

	// Parallel version (two threads)
	public static int sum_parallel(int[] a) {
		Exercise2 firstHalf = new Exercise2(a, 0, a.length / 2);
		Exercise2 secondHalf = new Exercise2(a, a.length / 2, a.length);
		Thread thread1 = new Thread(firstHalf);
		Thread thread2 = new Thread(secondHalf);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException ie) {
		}
		return firstHalf.sum + secondHalf.sum;
	}

	// Create an array of given length containing random values
	public static int[] createRandomArray(int length) {
		int[] a = new int[length];
		Random rand = new Random();
		for (int i = 0; i < a.length; i++)  
			a[i] = rand.nextInt(50);	 
		return a;
	}

	public static void main(String[] args) {
		int size = 100; // length of array
		int[] a = createRandomArray(size);
		
 		int total = sum_parallel(a); // With Threads
 		
 		int correct = sum(a); // Without Threads
  
		if (total != correct) {
			throw new RuntimeException("wrong sum: " + total + " vs. " + correct);
		}
		else
			System.out.println("Sum = " + total);
	}
}
