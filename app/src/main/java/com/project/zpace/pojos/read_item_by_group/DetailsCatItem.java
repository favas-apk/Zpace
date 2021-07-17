package com.project.zpace.pojos.read_item_by_group;

import com.google.gson.annotations.SerializedName;

public class DetailsCatItem{

	@SerializedName("cat")
	private String cat;

	public void setCat(String cat){
		this.cat = cat;
	}

	public String getCat(){
		return cat;
	}
}