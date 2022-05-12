package com.project.milan.model;




public class model_like_dislike  {


    public model_like_dislike(String comments, String star, String dat, String name, String dislikes, String likes,  boolean flag_up_hand, boolean flag_down_hand,String rating_id,String cust_id) {
        this.comments = comments;
        this.star = star;
        this.dat = dat;
        this.name = name;
        this.dislikes = dislikes;
        this.likes = likes;

        this.flag_up_hand = flag_up_hand;
        this.flag_down_hand = flag_down_hand;

        this.rating_id=rating_id;

        this.cust_id=cust_id;
    }

    private String comments;


    private String star;


    private String dat;


    private String name;


    private String dislikes;


    private String likes;

    private String rating_id;

    private String cust_id;


    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getRating_id() {
        return rating_id;
    }

    public void setRating_id(String rating_id) {
        this.rating_id = rating_id;
    }

    private boolean flag_up_hand=false;

    private boolean flag_down_hand=false;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }



    public boolean isFlag_up_hand() {
        return flag_up_hand;
    }

    public void setFlag_up_hand(boolean flag_up_hand) {
        this.flag_up_hand = flag_up_hand;
    }

    public boolean isFlag_down_hand() {
        return flag_down_hand;
    }

    public void setFlag_down_hand(boolean flag_down_hand) {
        this.flag_down_hand = flag_down_hand;
    }
}
