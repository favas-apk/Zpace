package com.project.milan.pojos.read_web_rate_and_offers;

import com.google.gson.annotations.SerializedName;

public class DetailsItem_no_stock {

	@SerializedName("avb_stockid")
	private String avbStockId;

	@SerializedName("avb_stk")
	private String avbStk;

	public String getAvbStockId() {
		return avbStockId;
	}

	public void setAvbStockId(String avbStockId) {
		this.avbStockId = avbStockId;
	}

	public String getAvbStk() {
		return avbStk;
	}

	public void setAvbStk(String avbStk) {
		this.avbStk = avbStk;
	}
}