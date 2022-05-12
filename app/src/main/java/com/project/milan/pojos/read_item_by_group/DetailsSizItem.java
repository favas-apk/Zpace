package com.project.milan.pojos.read_item_by_group;

import com.google.gson.annotations.SerializedName;

public class DetailsSizItem{

	@SerializedName("siz")
	private String siz;

	public void setSiz(String siz){
		this.siz = siz;
	}

	public String getSiz(){
		return siz;
	}
}