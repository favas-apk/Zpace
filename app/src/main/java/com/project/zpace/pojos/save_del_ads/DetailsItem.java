package com.project.zpace.pojos.save_del_ads;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("area")
	private String area;

	@SerializedName("mob")
	private String mob;

	@SerializedName("pin")
	private String pin;

	@SerializedName("city")
	private String city;

	@SerializedName("name")
	private String name;

	@SerializedName("state")
	private String state;

	@SerializedName("landmark")
	private String landmark;

	@SerializedName("house")
	private String house;

	@SerializedName("slno")
	private String slno;

	public void setArea(String area){
		this.area = area;
	}

	public String getArea(){
		return area;
	}

	public void setMob(String mob){
		this.mob = mob;
	}

	public String getMob(){
		return mob;
	}

	public void setPin(String pin){
		this.pin = pin;
	}

	public String getPin(){
		return pin;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setLandmark(String landmark){
		this.landmark = landmark;
	}

	public String getLandmark(){
		return landmark;
	}

	public void setHouse(String house){
		this.house = house;
	}

	public String getHouse(){
		return house;
	}

	public void setSlno(String slno){
		this.slno = slno;
	}

	public String getSlno(){
		return slno;
	}
}