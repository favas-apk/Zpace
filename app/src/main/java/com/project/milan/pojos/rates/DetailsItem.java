package com.project.milan.pojos.rates;

import com.google.gson.annotations.SerializedName;

public class DetailsItem{

	@SerializedName("comments")
	private String comments;

	@SerializedName("star")
	private String star;

	@SerializedName("dat")
	private String dat;

	@SerializedName("name")
	private String name;

	@SerializedName("dislikes")
	private String dislikes;

	@SerializedName("likes")
	private String likes;




	@SerializedName("user_id")
	private String user_id;

	@SerializedName("rating_id")
	private String rating_id;


	@SerializedName("posted_id")
	private String posted_id;

	public String getPosted_id() {
		return posted_id;
	}

	public void setPosted_id(String posted_id) {
		this.posted_id = posted_id;
	}

	public String getRating_id() {
		return rating_id;
	}

	public void setRating_id(String rating_id) {
		this.rating_id = rating_id;
	}



	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setComments(String comments){
		this.comments = comments;
	}

	public String getComments(){
		return comments;
	}

	public void setStar(String star){
		this.star = star;
	}

	public String getStar(){
		return star;
	}

	public void setDat(String dat){
		this.dat = dat;
	}

	public String getDat(){
		return dat;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDislikes(String dislikes){
		this.dislikes = dislikes;
	}

	public String getDislikes(){
		return dislikes;
	}

	public void setLikes(String likes){
		this.likes = likes;
	}

	public String getLikes(){
		return likes;
	}
}