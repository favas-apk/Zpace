package com.project.zpace.pojos.read_dashboard_categories_structure;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("dat")
	private String dat;

	@SerializedName("result")
	private String result;

	@SerializedName("details")
	private List<DetailsItem> details;

	@SerializedName("message")
	private String message;

	public String getDat() {
		return dat;
	}

	public void setDat(String dat) {
		this.dat = dat;
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