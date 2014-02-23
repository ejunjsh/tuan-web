package com.sky.tuan.mongodb.repository;

import org.bson.types.ObjectId;

import com.sky.tuan.mongodb.entity.Deal;
import com.sky.tuan.web.utils.Pagination;

public interface IDealRepository extends IBaseRepository<Deal, ObjectId> {
	Deal findByKey(String key);
	
	Pagination<Deal> findBySeveral(int pageNo,int pageSize,boolean isToday,String tag,String city,String searchWord);
}
