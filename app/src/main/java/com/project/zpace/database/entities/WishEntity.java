package com.project.zpace.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "wish_details",indices = {@Index(value = {"itemcode"},
        unique = true)})

public class WishEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private   long id;



    @ColumnInfo(name = "itemcode")
    private String itemcode;


    @ColumnInfo(name = "fname")
    private String fname;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "rate")
    private String rate;

    @ColumnInfo(name = "brand")
    private String brand;

    @ColumnInfo(name = "color")
    private String color;






    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public WishEntity(long id, String itemcode, String fname, String name, String rate, String brand, String color) {
        this.id = id;
        this.itemcode = itemcode;
        this.fname = fname;
        this.name = name;
        this.rate = rate;
        this.brand=brand;
        this.color=color;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
