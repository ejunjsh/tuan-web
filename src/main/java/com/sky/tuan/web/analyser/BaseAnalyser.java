package com.sky.tuan.web.analyser;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sky.tuan.mongodb.entity.Tag;
import com.sky.tuan.mongodb.repository.ITagRepository;
import com.sky.tuan.mongodb.repository.ICityRepository;
import com.sky.tuan.mongodb.repository.IDealRepository;


public class BaseAnalyser implements IAnalyser {
    
	
	private ICityRepository cityRepository;
	
	
	private IDealRepository dealRepository;
	
	
	private ITagRepository tagRepository;
	
	
	public void analyse() throws IOException {
		
	}


	public ICityRepository getCityRepository() {
		return cityRepository;
	}

	@Autowired
	public void setCityRepository(ICityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}


	public IDealRepository getDealRepository() {
		return dealRepository;
	}

	@Autowired
	public void setDealRepository(IDealRepository dealRepository) {
		this.dealRepository = dealRepository;
	}


	public ITagRepository getTagRepository() {
		return tagRepository;
	}

	@Autowired
	public void setTagRepository(ITagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}


    public void splitTags(List<Tag> tags,String tagString)
    {
    	  if(tagString.indexOf("-")>0
    		 ||tagString.indexOf(",")>0
    		 ||tagString.indexOf("/")>0)
          {
       	   String[] strs=tagString.split("\\-|\\,|\\/");
       	   for(String s :strs)
       	   {
       		   if(s!=null&&!s.isEmpty())
       		   {
           		  Tag tag=new Tag();
                      tag.setName(s);
                      tags.add(tag);
       		   }
       	   }
          }
          else
          {
       	     Tag tag=new Tag();
              tag.setName(tagString);
              tags.add(tag);
          }
    }

}
