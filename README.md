# ConcurrentCacheAPI
This is concurrent cache api with time based eviction strategy.
it uses builder design pattern for building the api using deined builder objects
this uses DelayQueue for taking the expired object and uses marker interface Delayed which has method getDelay to calculate the
delay for expiration object.The take() method is used for fetching the expired value.
removing the expired elements needs continous checking of the expiration timee so Daemon thread is used.
