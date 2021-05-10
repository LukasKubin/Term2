package WEEK5.Threads;

public class InteruptExample extends Thread{
    public void run() {
        try{
            while (true){
                System.out.println(Thread.currentThread().getName()+ " is running");
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName()+" is interrupted");
        }
        finally{
            System.out.println("Finally...");
        }

    }

    public static void main(String args[]){
        InteruptExample ex1 = new InteruptExample();
        ex1.start();
        ex1.interrupt();
    }
}
