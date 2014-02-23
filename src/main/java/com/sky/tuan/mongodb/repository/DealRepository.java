package com.sky.tuan.mongodb.repository;

import java.util.ArrayList;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.sky.tuan.annotation.NeedCache;
import com.sky.tuan.mongodb.entity.Deal;
import com.sky.tuan.web.utils.Pagination;


@Repository
public class DealRepository extends BaseRepository<Deal,ObjectId> implements IDealRepository {

	public Deal findByKey(String key) {
		   return getMongoTemplate().findOne(new Query(Criteria.where("key").is(key)), Deal.class);     
		   
	}

	@NeedCache
    public Pagination<Deal> findBySeveral(int pageNo,int pageSize,boolean isToday,String tag,String city,String searchWord)
    {
    	Query query=new Query();
    	if(isToday)
    	{
    		query.addCriteria(Criteria.where("startDate").lte(new Date()));
    	}
    	
    	if(tag!=null&&!tag.isEmpty())
    	{
    		query.addCriteria(Criteria.where("tags.name").regex(tag));
    	}
    	
    	if(city!=null&&!city.isEmpty())
    	{
    		query.addCriteria(Criteria.where("city.name").is(city));
    	}
    	
    	if(searchWord!=null&&!searchWord.isEmpty())
    	{
    		query.addCriteria(Criteria.where("shortTitle").regex(searchWord).orOperator(Criteria.where("title").regex(searchWord)));
    	}
    	
    	query.with(new Sort(Sort.Direction.DESC, "endTime"));
    	
    	
    	 long totalCount = getMongoTemplate().count(query,  Deal.class);
         Pagination<Deal> page = new Pagination<Deal>(pageNo, pageSize, totalCount);
         
         query.skip(page.getFirstResult());
         query.limit(pageSize);
         page.setData(getMongoTemplate().find(query,Deal.class));
         return page;
    }

}
