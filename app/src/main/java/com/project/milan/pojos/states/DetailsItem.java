package com.project.milan.pojos.states;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("name")
	private String name;

	@SerializedName("key")
	private String key;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setKey(String key){
		this.key = key;
	}

	public String getKey(){
		return key;
	}
}