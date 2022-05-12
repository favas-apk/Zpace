package com.project.milan.pojos.read_filter;

import com.google.gson.annotations.SerializedName;

public class DetailsdItem{

	@SerializedName("size")
	private String size;

	public void setSize(String size){
		this.size = size;
	}

	public String getSize(){
		return size;
	}
}