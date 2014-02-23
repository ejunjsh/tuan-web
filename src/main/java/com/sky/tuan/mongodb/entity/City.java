package com.sky.tuan.mongodb.entity;

import org.springframework.data.mongodb.core.index.Indexed;

import com.thoughtworks.xstream.annotations.XStreamOmitField;


public class City extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3887561378266932656L;

	@Indexed(unique=true)
    private String name;
    
    private String cnSpell;
    
    @XStreamOmitField  
    private int refCount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnSpell() {
		return cnSpell;
	}

	public void setCnSpell(String cnSpell) {
		this.cnSpell = cnSpell;
	}

	public int getRefCount() {
		return refCount;
	}

	public void setRefCount(int refCount) {
		this.refCount = refCount;
	}
    
}
