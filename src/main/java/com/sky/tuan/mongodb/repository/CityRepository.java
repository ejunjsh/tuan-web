package com.sky.tuan.mongodb.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sky.tuan.annotation.NeedCache;
import com.sky.tuan.mongodb.entity.City;

@Repository
public class CityRepository extends BaseRepository<City, ObjectId> implements
		ICityRepository {
	
	public City findByName(String name) {  
         return getMongoTemplate().findOne(new Query(Criteria.where("name").is(name)), City.class);     
  
    }  
	
	@NeedCache
	public List<City> findAllWithSpell()
	{
		return getMongoTemplate().find(new Query().with(new Sort(Sort.Direction.ASC, "cnSpell")), City.class);
	}
	
	@NeedCache
	public List<City> findHotCity(int top)
	{
		return getMongoTemplate().find(new Query().with(new Sort(Sort.Direction.DESC, "refCount")).skip(0).limit(top), City.class);
	}
}
