package com.concurrent.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;

import com.concurrent.notifications.INotification;

public class CacheCleanerTask implements Runnable {

    private final DelayQueue<CacheItem> delayQueue;
    private final Map<String, Object> cache;
    private INotification notification;


    /**
     * @param cache
     * @param delayQueue
     * @param notifier
     */
    public CacheCleanerTask(final ConcurrentHashMap<String, Object> cache,
            final DelayQueue<CacheItem> delayQueue, final INotification notifier) {
        this.delayQueue = delayQueue;
        this.cache = cache;
        this.notification = notifier;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                CacheItem delayedCacheItem = delayQueue.take();
                cache.remove(delayedCacheItem.getKey(), delayedCacheItem.getValue());
                if (isNotificationEnabled()) {
                    notification.notify(delayedCacheItem.getValue());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private boolean isNotificationEnabled() {
        return notification != null;
    }
}
