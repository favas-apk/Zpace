package com.project.milan.pojos.read_item_by_group;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("result")
	private String result;

	@SerializedName("size")
	private int size;

	@SerializedName("details_cat")
	private List<DetailsCatItem> detailsCat;

	@SerializedName("details_siz")
	private List<DetailsSizItem> detailsSiz;

	@SerializedName("details")
	private List<DetailsItem> details;

	@SerializedName("details_bra")
	private List<DetailsBraItem> detailsBra;

	@SerializedName("message")
	private String message;

	@SerializedName("details_col")
	private List<DetailsColItem> detailsCol;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setDetailsCat(List<DetailsCatItem> detailsCat){
		this.detailsCat = detailsCat;
	}

	public List<DetailsCatItem> getDetailsCat(){
		return detailsCat;
	}

	public void setDetailsSiz(List<DetailsSizItem> detailsSiz){
		this.detailsSiz = detailsSiz;
	}

	public List<DetailsSizItem> getDetailsSiz(){
		return detailsSiz;
	}

	public void setDetails(List<DetailsItem> details){
		this.details = details;
	}

	public List<DetailsItem> getDetails(){
		return details;
	}

	public void setDetailsBra(List<DetailsBraItem> detailsBra){
		this.detailsBra = detailsBra;
	}

	public List<DetailsBraItem> getDetailsBra(){
		return detailsBra;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setDetailsCol(List<DetailsColItem> detailsCol){
		this.detailsCol = detailsCol;
	}

	public List<DetailsColItem> getDetailsCol(){
		return detailsCol;
	}
}