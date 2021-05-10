package WEEK5.Threads;

public class Example1 extends Thread{
    // Two ways of creating a thread:
    // By extending the Thread class
    // By implementing the Runnable interface
    // Have to overwrite the run() method
    public void run() {
        System.out.println("thread is running..."+ Thread.currentThread().getName());
    }
    public static void main(String args[]){
        Example1 ex1 = new Example1();
        ex1.start();

        Example1 ex2 = new Example1();
        ex2.start();

    }
}
