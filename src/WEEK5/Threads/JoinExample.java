package WEEK5.Threads;

public class JoinExample extends Thread{
    public JoinExample(String name){
        super(name);
    }
    public void run() {
        System.out.println(Thread.currentThread().getName()+" sub thread started");
        try{
            Thread.sleep(10000);
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
        System.out.println(Thread.currentThread().getName()+" sub thread end");
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main thread start");
        JoinExample t1= new JoinExample("t1");
        t1.start();
        System.out.println("main wait t1 finish");
        // once we reach this point in the program
        // the main method wont proccede until this join method has completed
        // t1 join can interupt the thread from the sleep
        t1.join();
        System.out.println("main thread t1.join()");
        JoinExample t2 = new JoinExample("t2");
        t2.start();
        System.out.println("main thread wait t2 1 second, if t2 not finished continue");
        t2.join(3000);
        System.out.println("main thread after t2.join(1000)");


    }
}
