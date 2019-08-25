package com.telran.many_threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //should sleep 3 to 10 seconds


        List<MyThread> threads = new ArrayList<>();

        //created 10 threads
        for (int i = 0; i < 10; i++) {
            int sleepSeconds = ThreadLocalRandom.current().nextInt(3, 10 + 1);
            threads.add(new MyThread("Thread #" + (i+1), sleepSeconds * 1000));
        }

        //started all threads
        for (MyThread myThread : threads) {
            myThread.start();
        }

        for (MyThread myThread : threads) {
            myThread.join(); //main thread (current thread) HANGS here until the thread is done working
            //              is thread has finished - nothing to wait

            //t1 - 10s
            //t2 - 8s
            //t3 - 7s <--- stops main here
        }


        System.out.println("Done!");
        System.out.println("Congratulations!");
    }
}

class MyThread extends Thread {

    private long sleepTime;

    public MyThread(String name, long sleepTime) {
        super(name);
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {
            try {
                counter++;
                System.out.println(super.getName() + " is starting to work for " + sleepTime/1000 + " seconds");
                Thread.sleep(sleepTime);

                if (counter == 3) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
