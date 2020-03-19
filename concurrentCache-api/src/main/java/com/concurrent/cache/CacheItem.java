package com.concurrent.cache;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class CacheItem implements Delayed {

    private String key;
    private Object value;
    private final long expiryTime;
    

    /**
	 * @param key
	 * @param value
	 * @param expiryTime
	 */
	public CacheItem(String key, Object value, long expiryTime) {
		super();
		this.key = key;
		this.value = value;
		this.expiryTime = expiryTime;
	}
	

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}


	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}


	/**
	 * @return the expiryTime
	 */
	public long getExpiryTime() {
		return expiryTime;
	}


	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}


	
	
	
	
	@Override
	public String toString() {
		return "CacheItem [key=" + key + ", value=" + value + ", expiryTime=" + expiryTime + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (expiryTime ^ (expiryTime >>> 32));
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheItem other = (CacheItem) obj;
		if (expiryTime != other.expiryTime)
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}


	public long getDelay(final TimeUnit unit) {
        return unit.convert(expiryTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public int compareTo(final Delayed item) {
        return Long.valueOf(expiryTime - ((CacheItem) item).expiryTime).intValue();
    }
}
