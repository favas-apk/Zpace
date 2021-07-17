package com.project.zpace.pojos.read_category_with_random_images;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("fnames")
	private List<FnamesItem> fnames;

	@SerializedName("category")
	private String category;

	public void setFnames(List<FnamesItem> fnames){
		this.fnames = fnames;
	}

	public List<FnamesItem> getFnames(){
		return fnames;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}
}