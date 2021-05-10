package WEEK5.Threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuterExample implements Runnable {
    private final String taskName;
    // Constructor
    public ExecuterExample(String taskName){
        this.taskName=taskName;
    }
    // Method run by the threads
    public void run(){
        try{
            System.out.println(taskName+ " going to sleep for 3 seconds");
            Thread.sleep(3000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(taskName+" is done sleeping");
    }

    public static void main(String[] args){
        // Create and name each runnable
        ExecuterExample t1 = new ExecuterExample("t1");
        ExecuterExample t2 = new ExecuterExample("t2");
        ExecuterExample t3 = new ExecuterExample("t3");

        System.out.println("Starting Executor");
        // Create Executor to manage threads
        ExecutorService executorService = Executors.newCachedThreadPool();
        // Start the three Tasks
        executorService.execute(t1);
        executorService.execute(t2);
        executorService.execute(t3);
        // The tasks execute randomly
        executorService.shutdown();

    }
    }
