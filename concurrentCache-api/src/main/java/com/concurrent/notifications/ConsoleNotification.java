package com.concurrent.notifications;


public class ConsoleNotification implements INotification{

    /**
     * for console output
     */
    @Override
    public void notify(final Object evictedValue) {
        
        System.out.println("Evicted " + evictedValue + " from the cache.");
    }
}
