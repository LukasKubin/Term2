package WEEK5.HOMEWORK;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex1b implements Runnable{
    int i;
    public Ex1b(int i){
        this.i=i;
    }
    public void run(){
        for (int x =0;x<10;x++){
            System.out.println(i);
        }
    }
    public static void main(String[] args){
        Ex1b t1 = new Ex1b(0);
        Ex1b t2 = new Ex1b(1);
        Ex1b t3 = new Ex1b(2);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(t1);
        executorService.execute(t2);
        executorService.execute(t3);
        executorService.shutdown();
    }
}
