package com.sky.tuan.cache;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisCacheManager implements ICacheManager{

	@Autowired  
	private RedisTemplate<String, Object> redisTemplate; 
	static Logger logger = Logger.getLogger(RedisCacheManager.class.getName());
	
	public void insert(String key,Object o) {
		logger.debug("begin cache:key-"+key);
		
		redisTemplate.setKeySerializer(new StringRedisSerializer());  
		
		
		ValueOperations<String, Object> ops = redisTemplate.opsForValue(); 
		ops.set(key, o);
	}

	public Object get(String key) {
		
		logger.debug("get cache:key-"+key);
		
		redisTemplate.setKeySerializer(new StringRedisSerializer());  
		
		//redisTemplate.setValueSerializer(new JacksonJsonRedisSerializer(c)); 
	    
		ValueOperations<String, Object> ops = redisTemplate.opsForValue(); 
		return ops.get(key);
	}


	public void delete(String key) {
		redisTemplate.delete(key);
	}

	public void clear() {
		redisTemplate.discard();
	}

	public void update(String key, Object o) {
        logger.debug("begin cache:key-"+key);
		
		redisTemplate.setKeySerializer(new StringRedisSerializer());  
		
		
		ValueOperations<String, Object> ops = redisTemplate.opsForValue(); 
		ops.set(key, o);
	}

}
