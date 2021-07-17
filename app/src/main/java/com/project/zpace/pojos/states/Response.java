package com.project.zpace.pojos.states;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("details")
	private List<DetailsItem> details;

	public void setDetails(List<DetailsItem> details){
		this.details = details;
	}

	public List<DetailsItem> getDetails(){
		return details;
	}
}