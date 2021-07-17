package com.project.zpace.pojos.show_dash_gridview;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailsItem implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("dash_slno")
	private String dashSlno;

	@SerializedName("details")
	private String details;

	@SerializedName("type")
	private String type;

	@SerializedName("slno")
	private String slno;


	@SerializedName("company")
	private String company;

	@SerializedName("rate")
	private String rate;

	@SerializedName("offer")
	private String offer;


	@SerializedName("offer_end_date")
	private String offer_end_date;

	@SerializedName("buy_quantity")
	private String buy_quantity;

	@SerializedName("free_quantity")
	private String free_quantity;

	@SerializedName("free_percentage")
	private String free_percentage;


	@SerializedName("color")
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}



	public String getOffer_end_date() {
		return offer_end_date;
	}

	public void setOffer_end_date(String offer_end_date) {
		this.offer_end_date = offer_end_date;
	}

	public String getBuy_quantity() {
		return buy_quantity;
	}

	public void setBuy_quantity(String buy_quantity) {
		this.buy_quantity = buy_quantity;
	}

	public String getFree_quantity() {
		return free_quantity;
	}

	public void setFree_quantity(String free_quantity) {
		this.free_quantity = free_quantity;
	}

	public String getFree_percentage() {
		return free_percentage;
	}

	public void setFree_percentage(String free_percentage) {
		this.free_percentage = free_percentage;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@SerializedName("display_name")
	private String display_name;

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setDashSlno(String dashSlno){
		this.dashSlno = dashSlno;
	}

	public String getDashSlno(){
		return dashSlno;
	}

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return details;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setSlno(String slno){
		this.slno = slno;
	}

	public String getSlno(){
		return slno;
	}
}