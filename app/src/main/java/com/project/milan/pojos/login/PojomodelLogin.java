package com.project.milan.pojos.login;

import com.google.gson.annotations.SerializedName;
import com.project.milan.pojos.save_del_ads.DetailsItem;

import java.util.List;

public class PojomodelLogin{

	@SerializedName("result")
	private String result;

	@SerializedName("mob")
	private String mob;

	@SerializedName("pincode")
	private String pincode;

	@SerializedName("address")
	private String address;

	@SerializedName("name")
	private String name;

	@SerializedName("message")
	private String message;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("email")
	private String email;


	@SerializedName("details")
	private List<DetailsItem> details;

	public List<DetailsItem> getDetails() {
		return details;
	}

	public void setDetails(List<DetailsItem> details) {
		this.details = details;
	}

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setMob(String mob){
		this.mob = mob;
	}

	public String getMob(){
		return mob;
	}

	public void setPincode(String pincode){
		this.pincode = pincode;
	}

	public String getPincode(){
		return pincode;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}