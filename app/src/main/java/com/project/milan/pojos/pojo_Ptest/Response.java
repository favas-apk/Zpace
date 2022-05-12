package com.project.milan.pojos.pojo_Ptest;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("success")
	private boolean success;

	@SerializedName("customer")
	private List<CustomerItem> customer;

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setCustomer(List<CustomerItem> customer){
		this.customer = customer;
	}

	public List<CustomerItem> getCustomer(){
		return customer;
	}
}