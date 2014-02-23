package com.sky.tuan.mongodb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.lang.reflect.ParameterizedType;  

public class BaseRepository<T,ID> implements IBaseRepository<T, ID> {

	
	private MongoTemplate mongoTemplate;
	
	@Autowired
	public void setMongoTemplate(MongoTemplate value)
	{
		mongoTemplate=value;
	}
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseRepository()
	{
		 entityClass =(Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public List<T> findAll() {
		return mongoTemplate.findAll(entityClass);
	}


	public T findByID(ID id) {
		return mongoTemplate.findById(id, entityClass);
	}

	public T insert(T t) {
		mongoTemplate.insert(t);
		return t;
	}


	public void update(T t) {
		mongoTemplate.save(t);
	}


	public void delete(T t) {
		mongoTemplate.remove(t);
	}

}
