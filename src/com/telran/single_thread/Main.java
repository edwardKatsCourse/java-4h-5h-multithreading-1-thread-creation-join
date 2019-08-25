package com.telran.single_thread;

public class Main {

    public static void main(String[] args) {


        //instance - new MyClass()
        //static   - MyClass.variable

        //new Thread(() -> {}); //new Runnable() { public void run() { } }
        Thread thread = new Thread(new MyFirstThread(), "Northern Thread"); //new Runnable() { public void run() { } }
        thread.start(); //запустить В НОВОМ ПОТОКЕ!!!!!

        MySecondThread thread2 = new MySecondThread("Southern Thread");
        thread2.start();

        System.out.println("abcabcabcb -> " + Thread.currentThread().getName());
        //new Thread(() -> {while (true) System.out.println("abc");}).start();
    }
}

class MySecondThread extends Thread {

    public MySecondThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(super.getName() + ": I am extending Thread");
        while (true) {
            String threadName = super.getName();
            System.out.println(threadName + ": I'm double slower");
            try {
                Thread.sleep(2000);
                System.out.println(threadName + ": I'm awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyFirstThread implements Runnable {

    @Override
    public void run() { //выполнить!!
        while (true) {
            try {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + ": I am going to sleep");
                Thread.sleep(1000);
                System.out.println(threadName + ": I'm awake");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
