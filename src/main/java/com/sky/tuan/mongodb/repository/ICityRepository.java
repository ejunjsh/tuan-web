package com.sky.tuan.mongodb.repository;

import java.util.List;

import org.bson.types.ObjectId;

import com.sky.tuan.mongodb.entity.City;

public interface ICityRepository extends IBaseRepository<City, ObjectId> {
	City findByName(String name);
	
	List<City> findAllWithSpell();
	
	List<City> findHotCity(int top);
}
