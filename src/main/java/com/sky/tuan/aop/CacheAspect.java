package com.sky.tuan.aop;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.sky.tuan.annotation.NeedCache;
import com.sky.tuan.cache.ICacheManager;
import com.sky.tuan.mongodb.entity.Deal;
import com.sky.tuan.web.analyser.DianPingAnalyser;
import com.sky.tuan.web.utils.Pagination;

@Component
@Aspect
public class CacheAspect {
	
	//@Resource(name="ehcacheManager")
	@Resource(name="memcachedManager")
	//@Resource(name="redisCacheManager")
	private ICacheManager cacheManager;
	
	static Logger logger = Logger.getLogger(CacheAspect.class.getName());
	
	@Around("@annotation(needCache)")  
    public Object cache(ProceedingJoinPoint jp, NeedCache needCache) throws Throwable{  
        logger.debug("begin cache aop....");
        String key = jp.getSignature().getDeclaringTypeName()+":"+jp.getSignature().getName();
        Object[] args = jp.getArgs();  
        for (Object o : args) {  
        	if(o!=null)
                key += o.toString();  
        	else
        		key+="null";
        }  
       
        Object result = cacheManager.get(key); 

        if (null==result) 
        {  
            result = jp.proceed();  
            cacheManager.insert(key,result);
        }  
        return result;  
    }  
	
}
