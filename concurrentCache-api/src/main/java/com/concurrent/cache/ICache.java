package com.concurrent.cache;

public interface ICache {

	/**
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value);

	/**
	 * @param key
	 */
	public void remove(String key);

	/**
	 * @param key
	 * @return Object
	 */
	public Object get(String key);

	public void clear();

	public long size();
}