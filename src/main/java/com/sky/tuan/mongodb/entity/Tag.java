package com.sky.tuan.mongodb.entity;

import org.springframework.data.mongodb.core.index.Indexed;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
 
public class Tag extends BaseEntity {
	
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2840919306050490699L;


	private String cnSpell;
    
    
    @Indexed(unique=true)
    private String name;
    
    @XStreamOmitField  
    private int refCount;


	public String getCnSpell() {
		return cnSpell;
	}

	public void setCnSpell(String cnSpell) {
		this.cnSpell = cnSpell;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRefCount() {
		return refCount;
	}

	public void setRefCount(int refCount) {
		this.refCount = refCount;
	}

}
