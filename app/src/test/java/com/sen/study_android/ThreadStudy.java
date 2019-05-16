package com.sen.study_android;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadStudy {
    @Test
    public void testFutureTask() {
        FutureTask<String> futureTask = new FutureTask<String>(
                new Callable<String>() {

                    @Override
                    public String call() throws Exception {
                        Thread.sleep(3000);
                        System.out.println("test--->futureTask");
                        return "test";
                    }
                }

        );

        new Thread(futureTask).start();


        new Thread(
        ) {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    futureTask.cancel(true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();


        try {
            System.out.println("test--->futureTask-->" + futureTask.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testConmunication() {

        String obj = new String();
        String obj1 = new String("1");


        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
                do {
                    synchronized (obj1) {
                        System.out.println(" thread 1 notify thread2  -->");
                        obj1.notify();
                    }

                    synchronized (obj) {
                        try {
                            System.out.println(" thread 1 waite  -->");
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } while (true);
            }
        };


        Thread thread2 = new Thread() {
            @Override
            public void run() {
                super.run();

                do {
                    synchronized (obj) {
                        obj.notify();
                        System.out.println(" thread 2 notify thread1  -->");
                    }

                    synchronized (obj1) {
                        try {
                            System.out.println(" thread 2 waite  -->");
                            obj1.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }


                } while (true);
            }
        };
        thread1.start();
        thread2.start();


    }

//    public static void main(String[] args) {
//        new ThreadStudy().testConmunication();
//    }

}
