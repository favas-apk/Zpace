package com.project.zpace.pojos.read_orders;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("result")
	private String result;

	@SerializedName("details")
	private List<DetailsItem> details;

	@SerializedName("message")
	private String message;

	public String getResult(){
		return result;
	}

	public List<DetailsItem> getDetails(){
		return details;
	}

	public String getMessage(){
		return message;
	}
}