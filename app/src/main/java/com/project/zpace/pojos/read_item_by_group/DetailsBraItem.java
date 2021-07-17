package com.project.zpace.pojos.read_item_by_group;

import com.google.gson.annotations.SerializedName;

public class DetailsBraItem{

	@SerializedName("bra")
	private String bra;

	public void setBra(String bra){
		this.bra = bra;
	}

	public String getBra(){
		return bra;
	}
}