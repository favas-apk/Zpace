package com.project.milan.pojos.read_filter;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("result")
	private String result;

	@SerializedName("detailsc")
	private List<DetailscItem> detailsc;

	@SerializedName("detailsb")
	private List<DetailsbItem> detailsb;

	@SerializedName("detailsd")
	private List<DetailsdItem> detailsd;

	@SerializedName("detailsa")
	private List<DetailsaItem> detailsa;

	@SerializedName("message")
	private String message;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setDetailsc(List<DetailscItem> detailsc){
		this.detailsc = detailsc;
	}

	public List<DetailscItem> getDetailsc(){
		return detailsc;
	}

	public void setDetailsb(List<DetailsbItem> detailsb){
		this.detailsb = detailsb;
	}

	public List<DetailsbItem> getDetailsb(){
		return detailsb;
	}

	public void setDetailsd(List<DetailsdItem> detailsd){
		this.detailsd = detailsd;
	}

	public List<DetailsdItem> getDetailsd(){
		return detailsd;
	}

	public void setDetailsa(List<DetailsaItem> detailsa){
		this.detailsa = detailsa;
	}

	public List<DetailsaItem> getDetailsa(){
		return detailsa;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}