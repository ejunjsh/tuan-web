package com.sky.tuan.mongodb.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sky.tuan.annotation.NeedCache;
import com.sky.tuan.mongodb.entity.City;
import com.sky.tuan.mongodb.entity.Tag;

@Repository
public class TagRepository extends BaseRepository<Tag, ObjectId> implements
		ITagRepository {

	public Tag findByName(String name) {
		  return getMongoTemplate().findOne(new Query(Criteria.where("name").is(name)), Tag.class);     
	}
	
	@NeedCache
	public List<Tag> findAllWithSpell()
	{
		return getMongoTemplate().find(new Query().with(new Sort(Sort.Direction.ASC, "cnSpell")), Tag.class);
	}
    
	@NeedCache
	public List<Tag> findHotTag(int top)
	{
		return getMongoTemplate().find(new Query().with(new Sort(Sort.Direction.DESC, "refCount")).skip(0).limit(top), Tag.class);
	}
}