package com.concurrent;
import java.util.concurrent.TimeUnit;

import com.concurrent.cache.ConcurrentExpiryCache;
import com.concurrent.notifications.ConsoleNotification;
import com.concurrent.utils.ConstantValues;

// Driver class
public class EntryPoint {

    

    public static void main(String[] args) throws InterruptedException {
        ConcurrentExpiryCache cache = ConcurrentExpiryCache.builder()
                .withCapacity(5)
                .expireAfter(5)
                .timeUnit(TimeUnit.SECONDS)
                .withNotifier(new ConsoleNotification())
                .build();

        cache.add("1", "one second");
        Thread.sleep(ConstantValues.SLEEP_MILLIS);
        cache.add("2", "two second");
        Thread.sleep(ConstantValues.SLEEP_MILLIS);
        cache.add("3", "three second");
        Thread.sleep(ConstantValues.SLEEP_MILLIS);
        cache.add("4", "four second");
        Thread.sleep(ConstantValues.SLEEP_MILLIS);
        cache.add("5", "five second");
        Thread.sleep(ConstantValues.SLEEP_MILLIS);

        System.out.println(cache.showCache());

        new Thread(() -> {
            while (true) {
                System.out.println(cache.showCache());
                try {
                    Thread.sleep(ConstantValues.SLEEP_MILLIS);
                } catch (InterruptedException ignore) { }
                cache.get("3");
            }
        }).start();
    }

}
