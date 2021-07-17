package com.project.zpace.pojos.read_item_by_group;

import com.google.gson.annotations.SerializedName;

public class DetailsColItem{

	@SerializedName("col")
	private String col;

	public void setCol(String col){
		this.col = col;
	}

	public String getCol(){
		return col;
	}
}