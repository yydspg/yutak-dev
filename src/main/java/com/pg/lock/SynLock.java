package com.pg.lock;

public class SynLock {
    public long a = -100000L;

    public synchronized void add(long args) {
        a += args;
    }
}
