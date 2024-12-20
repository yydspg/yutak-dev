package com.pg.lock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class LockMain {
    public static void main(String[] args) throws Exception {
        ReeLock reeLock = new ReeLock();
        SynLock synLock = new SynLock();

        List<Long> synList = new ArrayList<>();
        List<Long> lockList = new ArrayList<>();
        for(int j = 0; j < 100; j++) {
            new Thread(()->{
                long start = System.currentTimeMillis();
                List<CompletableFuture> futures = new ArrayList<>();
                for (int i = 0; i < 10000000; i++) {
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        reeLock.add(1L);
                    });
                    futures.add(future);
                }
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
                System.out.println(reeLock.a);
                long end = System.currentTimeMillis();
                System.out.println("test reentrantLock");
                System.out.println(end - start);
                lockList.add(end-start);
            }).run();
            new Thread(()->{
                List<CompletableFuture> futures = new ArrayList<>();
                long start = System.currentTimeMillis();
                for (int i = 0; i < 10000000; i++) {
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        synLock.add(1L);
                    });
                    futures.add(future);
                }
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
                System.out.println(synLock.a);
                long end = System.currentTimeMillis();
                System.out.println("test synchronizedLock");
                System.out.println(end - start);
                synList.add(end- start);
            }).run();
        }
        Double syn = synList.stream().collect(Collectors.averagingDouble(Long::longValue));
        Double lock = lockList.stream().collect(Collectors.averagingLong(Long::longValue));
        System.out.println("syn module cost average time"+syn);
        System.out.println("lock module cost average time"+lock);
        TimeUnit.SECONDS.sleep(1);
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1,1);

    }
}
