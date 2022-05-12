package com.project.milan.pojos.read_category_with_random_images;

import com.google.gson.annotations.SerializedName;

public class FnamesItem{

	@SerializedName("fname")
	private String fname;

	public void setFname(String fname){
		this.fname = fname;
	}

	public String getFname(){
		return fname;
	}
}