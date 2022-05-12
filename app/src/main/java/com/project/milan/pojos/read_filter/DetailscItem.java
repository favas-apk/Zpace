package com.project.milan.pojos.read_filter;

import com.google.gson.annotations.SerializedName;

public class DetailscItem{

	@SerializedName("color")
	private String color;

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}
}