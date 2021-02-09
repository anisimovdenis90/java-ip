package com.anisimovdenis.hw3;

import java.util.ArrayList;
import java.util.List;

public class PingPong {

    private static volatile String lastWrote;
    private static final String PING = "Ping ";
    private static final String PONG = "Pong ";

    public static void main(String[] args) throws InterruptedException {
        lastWrote = PONG;
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(new MyRunnable(PING)));
        threads.add(new Thread(new MyRunnable(PONG)));
        for (Thread thread : threads) {
            thread.start();
        }
        Thread.sleep(10);
        for (Thread thread : threads) {
            thread.interrupt();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    static class MyRunnable implements Runnable {

        String stringToWrite;

        public MyRunnable(String stringToWrite) {
            this.stringToWrite = stringToWrite;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (!lastWrote.equals(stringToWrite)) {
                    System.out.println(stringToWrite);
                    lastWrote = stringToWrite;
                }
            }
        }
    }
}
