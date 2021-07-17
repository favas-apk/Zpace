package com.project.zpace.pojos.read_size_and_details;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{
	public DetailsItem(String freeQty, String buyQty, String freePercent, String size, String rate, String offerEndDate, String stkidFromServer, String offerPrice, boolean selected) {
		this.freeQty = freeQty;
		this.buyQty = buyQty;
		this.freePercent = freePercent;
		this.size = size;
		this.rate = rate;
		this.offerEndDate = offerEndDate;
		this.stkidFromServer = stkidFromServer;
		this.offerPrice = offerPrice;
		this.selected = selected;
	}

	@SerializedName("free_qty")
	private String freeQty;

	@SerializedName("buy_qty")
	private String buyQty;

	@SerializedName("free_percent")
	private String freePercent;

	@SerializedName("size")
	private String size;

	@SerializedName("rate")
	private String rate;

	@SerializedName("offer_end_date")
	private String offerEndDate;

	@SerializedName("stkid_from_server")
	private String stkidFromServer;

	@SerializedName("offer_price")
	private String offerPrice;

	private boolean selected=false;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setFreeQty(String freeQty){
		this.freeQty = freeQty;
	}

	public String getFreeQty(){
		return freeQty;
	}

	public void setBuyQty(String buyQty){
		this.buyQty = buyQty;
	}

	public String getBuyQty(){
		return buyQty;
	}

	public void setFreePercent(String freePercent){
		this.freePercent = freePercent;
	}

	public String getFreePercent(){
		return freePercent;
	}

	public void setSize(String size){
		this.size = size;
	}

	public String getSize(){
		return size;
	}

	public void setRate(String rate){
		this.rate = rate;
	}

	public String getRate(){
		return rate;
	}

	public void setOfferEndDate(String offerEndDate){
		this.offerEndDate = offerEndDate;
	}

	public String getOfferEndDate(){
		return offerEndDate;
	}

	public void setStkidFromServer(String stkidFromServer){
		this.stkidFromServer = stkidFromServer;
	}

	public String getStkidFromServer(){
		return stkidFromServer;
	}

	public void setOfferPrice(String offerPrice){
		this.offerPrice = offerPrice;
	}

	public String getOfferPrice(){
		return offerPrice;
	}
}