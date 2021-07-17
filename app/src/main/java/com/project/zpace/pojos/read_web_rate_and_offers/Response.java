package com.project.zpace.pojos.read_web_rate_and_offers;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("result")
	private String result;

	@SerializedName("details")
	private List<DetailsItem> details;

	@SerializedName("details_no_stock")
	private List<DetailsItem_no_stock> details_no_stock;

	@SerializedName("order_no")
	private String order_no;

	@SerializedName("total")
	private String total;


	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<DetailsItem_no_stock> getDetails_no_stock() {
		return details_no_stock;
	}

	public void setDetails_no_stock(List<DetailsItem_no_stock> details_no_stock) {
		this.details_no_stock = details_no_stock;
	}

	@SerializedName("message")
	private String message;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setDetails(List<DetailsItem> details){
		this.details = details;
	}

	public List<DetailsItem> getDetails(){
		return details;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}