package com.sky.tuan.mongodb.repository;

import java.util.List;

import org.bson.types.ObjectId;

import com.sky.tuan.mongodb.entity.Tag;

public interface ITagRepository extends IBaseRepository<Tag, ObjectId> {
	Tag findByName(String name);
	
	List<Tag> findAllWithSpell();
	List<Tag> findHotTag(int top);
}
