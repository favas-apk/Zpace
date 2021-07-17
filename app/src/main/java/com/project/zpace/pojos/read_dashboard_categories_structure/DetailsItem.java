package com.project.zpace.pojos.read_dashboard_categories_structure;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{


	@SerializedName("slno")
	private String slno;

	@SerializedName("order_no")
	private String orderNo;

	@SerializedName("style")
	private String style;

	@SerializedName("display_name")
	private String displayName;



	@SerializedName("status")
	private String status;

	@SerializedName("type")
	private String type;

	@SerializedName("count")
	private String count;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}

	public String getOrderNo(){
		return orderNo;
	}

	public void setStyle(String style){
		this.style = style;
	}

	public String getStyle(){
		return style;
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}