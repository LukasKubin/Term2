package WEEK5.HOMEWORK;

public class Ex1a extends Thread{
    int number;
    String name;
    public Ex1a(String name, int number){
        this.name = name;
        this.number = number;
    }
    public void run() {
        for (int x =0; x<10;x++){
            System.out.println(number);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Ex1a t1 = new Ex1a("Thread 1",0);
        t1.start();
        Ex1a t2 = new Ex1a("Thread 2",1);
        t2.start();
        Ex1a t3 = new Ex1a("Thread 3",2);
        t3.start();



    }
}
