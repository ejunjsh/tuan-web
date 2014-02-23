package com.sky.tuan.web.model;

public class PageModel extends BaseModel {
    private int page;
    
    private int size=48;

	public int getPage() {
		if(page==0)
			return 1;
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
