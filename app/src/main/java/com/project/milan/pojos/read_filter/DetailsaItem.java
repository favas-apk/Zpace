package com.project.milan.pojos.read_filter;

import com.google.gson.annotations.SerializedName;

public class DetailsaItem{

	@SerializedName("category")
	private String category;

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}
}