package com.concurrent.notifications;

public class EmailNotification implements INotification{

    /**
     *  for sending email notification 
     *  ToDO
     */
    @Override
    public void notify(final Object evictedValue) {
       System.out.println("Sending Email Notification");
    }
}
