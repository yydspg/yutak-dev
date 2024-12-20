package com.pg.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReeLock {
    ReentrantLock lock = new ReentrantLock();

    public long a = -100000L;

    public final void add(long args){
        lock.lock();
        a += args;
        lock.unlock();
    }
}
