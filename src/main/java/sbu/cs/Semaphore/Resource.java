package sbu.cs.Semaphore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Resource {

    public static void accessResource() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        try {
            System.out.println(Thread.currentThread().getName() + " accessed to the resource at " + formatter.format(date));
             Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
