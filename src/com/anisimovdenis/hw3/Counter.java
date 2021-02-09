package com.anisimovdenis.hw3;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Counter {

    private Lock lock;
    private volatile int count;

    public Counter() {
        this.lock = new ReentrantLock();
    }

    public void increment() {
        try {
            lock.lock();
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        try {
            lock.lock();
            count--;
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        List<Thread> list = Stream.generate(() -> new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        }))
                .limit(5)
                .peek(Thread::start)
                .collect(Collectors.toList());
        Thread.sleep(500);
        System.out.println(counter.get());
    }
}
