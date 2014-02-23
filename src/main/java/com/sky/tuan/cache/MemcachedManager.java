package com.sky.tuan.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.spy.memcached.MemcachedClient;  

@Component
public class MemcachedManager implements ICacheManager {
    
	@Autowired
	private MemcachedClient memcachedClient;
	
	public void insert(String key, Object o) {
		memcachedClient.add(key, 3600, o);
	}
	
	public void update(String key,Object o)
	{
		memcachedClient.set(key, 3600, o);
	}

	public Object get(String key) {
		return memcachedClient.get(key);
	}

	public void delete(String key) {
		memcachedClient.delete(key);
	}

	public void clear() {
		memcachedClient.flush();
	}

}
