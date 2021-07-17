package com.project.zpace.pojos.read_filter;

import com.google.gson.annotations.SerializedName;

public class DetailsbItem{

	@SerializedName("brand")
	private String brand;

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}
}