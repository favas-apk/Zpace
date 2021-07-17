package com.project.zpace.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "deliver_address")
public class DeliveryAddressEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private   long id;

    private String slno;

    private String cust_id;


    private String full_name;

    private String mob;

    private String pincode;
    private String house;
    private String area;
    private String landmark;
    private String city;
    private String state;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public DeliveryAddressEntity(long id, String slno, String cust_id, String full_name, String mob, String pincode, String house, String area, String landmark, String city, String state) {
        this.id = id;
        this.slno = slno;
        this.cust_id = cust_id;
        this.full_name = full_name;
        this.mob = mob;
        this.pincode = pincode;
        this.house = house;
        this.area = area;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
    }
}
