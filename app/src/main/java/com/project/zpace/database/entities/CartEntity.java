package com.project.zpace.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart",indices = {@Index(value = {"stkid"},
        unique = true)})
public class CartEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;



    @ColumnInfo(name = "stkid")
   private String  stkid;

   private int qty;

   private  float rate;

   private float offer_price;

   private String offer_end_date;

   private int buy_qty;

   private  int free_qty;

   private float free_percent;

   private String itemname;



   private String fname;

   private String brand;

   private String color;

   private String size;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public CartEntity(long id, String stkid, int qty, float rate, float offer_price, String offer_end_date, int buy_qty, int free_qty, float free_percent,String itemname,String fname,String brand,String color,String size) {
        this.id = id;
        this.stkid = stkid;
        this.qty = qty;
        this.rate = rate;
        this.offer_price = offer_price;
        this.offer_end_date = offer_end_date;
        this.buy_qty = buy_qty;
        this.free_qty = free_qty;
        this.free_percent = free_percent;
        this.itemname=itemname;

        this.fname=fname;

        this.brand=brand;
        this.color=color;

        this.size=size;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStkid() {
        return stkid;
    }

    public void setStkid(String stkid) {
        this.stkid = stkid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(float offer_price) {
        this.offer_price = offer_price;
    }

    public String getOffer_end_date() {
        return offer_end_date;
    }

    public void setOffer_end_date(String offer_end_date) {
        this.offer_end_date = offer_end_date;
    }

    public int getBuy_qty() {
        return buy_qty;
    }

    public void setBuy_qty(int buy_qty) {
        this.buy_qty = buy_qty;
    }

    public int getFree_qty() {
        return free_qty;
    }

    public void setFree_qty(int free_qty) {
        this.free_qty = free_qty;
    }

    public float getFree_percent() {
        return free_percent;
    }

    public void setFree_percent(float free_percent) {
        this.free_percent = free_percent;
    }
}
