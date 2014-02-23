package com.sky.tuan.mongodb.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.index.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

@CompoundIndexes({
	 @CompoundIndex(name = "city_index", def = "{city.name : 1}")
})
public class Deal extends BaseEntity {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 3932100020907351286L;
    
	@XStreamOmitField  
	private int dealType;
	
	@Indexed(unique=true)
	private String key;
	
	private String siteUrl;
	
	@XStreamOmitField 
	private List<City> city;
	
	private String title;
	
	private String shortTitle;
	
	private String imageUrl;
	
	@XStreamOmitField
	private List<Tag> tags;
	
	private Date startTime;
	
	@Indexed(direction=IndexDirection.DESCENDING)
	private Date endTime;
	
	private float originalPrice;
	
	private float currentPrice;
	
	private float rebate;
	
	private int boughtCount;


	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public float getRebate() {
		return rebate;
	}

	public void setRebate(float rebate) {
		this.rebate = rebate;
	}

	public int getBoughtCount() {
		return boughtCount;
	}

	public void setBoughtCount(int boughtCount) {
		this.boughtCount = boughtCount;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}
	
	
	
}
