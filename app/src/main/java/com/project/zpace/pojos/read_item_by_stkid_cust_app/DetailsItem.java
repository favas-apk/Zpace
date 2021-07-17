package com.project.zpace.pojos.read_item_by_stkid_cust_app;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailsItem implements Serializable {

	@SerializedName("offer")
	private String offer;

	@SerializedName("fname")
	private String fname;

	@SerializedName("color")
	private String color;

	@SerializedName("size")
	private String size;

	@SerializedName("itemname")
	private String itemname;

	@SerializedName("rate")
	private String rate;

	@SerializedName("company")
	private String company;

	@SerializedName("sub_category1")
	private String subCategory1;

	@SerializedName("sub_category2")
	private String subCategory2;

	@SerializedName("category")
	private String category;

	@SerializedName("brand")
	private String brand;

	@SerializedName("stkid_from_server")
	private String stkidFromServer;


	@SerializedName("offer_end_date")
	private String offer_end_date;

	@SerializedName("buy_quantity")
	private String buy_quantity;

	@SerializedName("free_quantity")
	private String free_quantity;

	@SerializedName("free_percentage")
	private String free_percentage;


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

	public void setOffer(String offer){
		this.offer = offer;
	}

	public String getOffer(){
		return offer;
	}

	public void setFname(String fname){
		this.fname = fname;
	}

	public String getFname(){
		return fname;
	}

	public void setColor(String color){
		this.color = color;
	}

	public String getColor(){
		return color;
	}

	public void setSize(String size){
		this.size = size;
	}

	public String getSize(){
		return size;
	}

	public void setItemname(String itemname){
		this.itemname = itemname;
	}

	public String getItemname(){
		return itemname;
	}

	public void setRate(String rate){
		this.rate = rate;
	}

	public String getRate(){
		return rate;
	}

	public void setCompany(String company){
		this.company = company;
	}

	public String getCompany(){
		return company;
	}

	public void setSubCategory1(String subCategory1){
		this.subCategory1 = subCategory1;
	}

	public String getSubCategory1(){
		return subCategory1;
	}

	public void setSubCategory2(String subCategory2){
		this.subCategory2 = subCategory2;
	}

	public String getSubCategory2(){
		return subCategory2;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}

	public void setStkidFromServer(String stkidFromServer){
		this.stkidFromServer = stkidFromServer;
	}

	public String getStkidFromServer(){
		return stkidFromServer;
	}
}