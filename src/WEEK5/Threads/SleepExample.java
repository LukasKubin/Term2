package WEEK5.Threads;

public class SleepExample extends Thread {
    public void run(){
        for (int i =0;i<3;i++){
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.out.println(e);
            }
            System.out.println(Thread.currentThread().getName() +i);
        }
    }
    public static void main(String args[]){
        SleepExample ex1 = new SleepExample();
        ex1.start();
        SleepExample ex2 = new SleepExample();
        ex2.start();
        // the order is non deterministic -> the order the threads execute in are random. need a sleep method
    }
}
