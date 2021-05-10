package WEEK5.Threads;

// We can only extend one class in java so if we have one class inheriting from another class
// We cant extend Thread
public class Example2 implements Runnable{
    public void run() {
        System.out.println("thread is running..."+Thread.currentThread().getName());
    }

    public static void main(String args[]){
        Example2 ex1 = new Example2();
        // We have to first instance of the interface
        Thread t1 = new Thread(ex1);
        t1.start();
    }
}
