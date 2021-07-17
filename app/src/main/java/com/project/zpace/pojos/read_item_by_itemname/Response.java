package com.project.zpace.pojos.read_item_by_itemname;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{

	@SerializedName("result")
	private String result;

	@SerializedName("details")
	private List<DetailsItem> details;

	@SerializedName("size")
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@SerializedName("message")
	private String message;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setDetails(List<DetailsItem> details){
		this.details = details;
	}

	public List<DetailsItem> getDetails(){
		return details;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}