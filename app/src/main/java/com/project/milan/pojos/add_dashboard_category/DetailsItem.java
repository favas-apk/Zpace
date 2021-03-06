package com.project.milan.pojos.add_dashboard_category;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("order_no")
	private String orderNo;

	@SerializedName("category")
	private String category;

	@SerializedName("display_name")
	private String displayName;

	@SerializedName("slno")
	private String slno;


	@SerializedName("status")
	private String status;


	@SerializedName("style")
	private String style;

	private boolean is_ticked=false;

	public boolean isIs_ticked() {
		return is_ticked;
	}

	public void setIs_ticked(boolean is_ticked) {
		this.is_ticked = is_ticked;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getOrderNo(){
		return orderNo;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	public String getDisplayName(){
		return displayName;
	}

	public void setSlno(String slno){
		this.slno = slno;
	}

	public String getSlno(){
		return slno;
	}
}