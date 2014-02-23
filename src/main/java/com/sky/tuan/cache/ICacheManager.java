package com.sky.tuan.cache;

public interface ICacheManager {
    void insert(String key,Object o);
    
    Object get(String key);
    
    void update(String key,Object o);
    
    void delete(String key);
    
    void clear();
}
