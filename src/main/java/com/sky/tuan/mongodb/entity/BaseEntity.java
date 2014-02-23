package com.sky.tuan.mongodb.entity;

import org.bson.types.ObjectId;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class BaseEntity implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7400338323179185684L;
	
	@XStreamOmitField  
	private ObjectId id;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
}
