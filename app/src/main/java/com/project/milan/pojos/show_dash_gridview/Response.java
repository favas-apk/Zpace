package com.project.milan.pojos.show_dash_gridview;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response{

	@SerializedName("result")
	private String result;

	@SerializedName("details")
	private List<DetailsItem> details;

	@SerializedName("message")
	private String message;


	@SerializedName("style")
	private String style;

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

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