package com.project.zpace.pojos.read_size_and_details;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("result")
	private String result;

	@SerializedName("stock_flag")
	private String stock_flag;


	@SerializedName("details")
	private List<DetailsItem> details;

	@SerializedName("message")
	private String message;


	public String getStock_flag() {
		return stock_flag;
	}

	public void setStock_flag(String stock_flag) {
		this.stock_flag = stock_flag;
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