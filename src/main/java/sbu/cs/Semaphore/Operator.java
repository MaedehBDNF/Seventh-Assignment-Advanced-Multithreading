package sbu.cs.Semaphore;

import java.util.concurrent.Semaphore;

public class Operator extends Thread {
    Semaphore semaphore;

    public Operator(String name, Semaphore semaphore) {
        super(name);
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
        {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting for a permit.");
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " gets a permit.");
                Resource.accessResource();         // critical section - a Maximum of 2 operators can access the resource concurrently
            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                semaphore.release();
                // printing any phrase here will not help to see releasing semaphore before acquiring it.
                // cause immediately after release, it will be acquired and printing any statement looks like the first
                // event was acquiring before releasing!
            }

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
