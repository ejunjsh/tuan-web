package com.sky.tuan.cache;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Component;

@Component
public class EhcacheManager implements ICacheManager {

	@Resource(name="methodCache")
    private Cache cache;
	
	public void insert(String key, Object o) {
		
		Element e=new Element(key,o);
		cache.put(e);
	}

	public Object get(String key) {
		Element e=cache.get(key);
		if(e!=null)
		    return e.getObjectValue();
		else
			return null;
	}

	public void update(String key, Object o) {
		Element e=new Element(key,o);
		cache.put(e);

	}

	public void delete(String key) {
		cache.remove(key);
	}

	public void clear() {
         cache.flush();
	}

}
