package com.project.zpace.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "dash")
public class DashEntity  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private   long id;

    private String image;

    private String dash_slno;

    private  String details;

    private String type;


    private String slno;
    private  String company;


    private String rate;
    private  String offer;

    private  String display_name;



    private String offer_end_date;


    private String buy_quantity;


    private String free_quantity;


    private String free_percentage;

    private  String color;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDash_slno() {
        return dash_slno;
    }

    public void setDash_slno(String dash_slno) {
        this.dash_slno = dash_slno;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public DashEntity(long id, String image, String dash_slno, String details, String type, String slno, String company, String rate, String offer, String display_name,String offer_end_date,String buy_quantity,String free_quantity,String free_percentage,String color) {
        this.id = id;
        this.image = image;
        this.dash_slno = dash_slno;
        this.details = details;
        this.type = type;
        this.slno = slno;
        this.company = company;
        this.rate = rate;
        this.offer = offer;
        this.display_name = display_name;

        this.offer_end_date=offer_end_date;
        this.buy_quantity=buy_quantity;
        this.free_quantity=free_quantity;
        this.free_percentage=free_percentage;
        this.color=color;


    }





}
