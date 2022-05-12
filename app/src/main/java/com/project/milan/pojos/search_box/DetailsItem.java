package com.project.milan.pojos.search_box;

import com.google.gson.annotations.SerializedName;

public class DetailsItem  {


	public DetailsItem(String itemname, boolean his_flag) {

		this.itemname = itemname;
		this.his_flag = his_flag;

	}

	@SerializedName("itemname")
	private String itemname;



	private boolean his_flag=false;

	public boolean isHis_flag() {
		return his_flag;
	}

	public void setHis_flag(boolean his_flag) {
		this.his_flag = his_flag;
	}

	public void setItemname(String itemname){
		this.itemname = itemname;
	}

	public String getItemname(){
		return itemname;
	}
}