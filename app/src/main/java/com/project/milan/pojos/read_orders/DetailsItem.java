package com.project.milan.pojos.read_orders;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("ono")
	private String ono;

	@SerializedName("total")
	private String total;

	@SerializedName("itemname")
	private String itemname;

	@SerializedName("qty")
	private String qty;

	@SerializedName("stkid")
	private String stkid;

	@SerializedName("fname")
	private String fname;

	@SerializedName("order_tym")
	private String order_tym;


	public String getOrder_tym() {
		return order_tym;
	}

	public String getFname() {
		return fname;
	}

	public String getOno(){
		return ono;
	}

	public String getTotal(){
		return total;
	}

	public String getItemname(){
		return itemname;
	}

	public String getQty(){
		return qty;
	}

	public String getStkid(){
		return stkid;
	}
}