package com.project.zpace.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login_details")
public class LoginEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;

    private String customer_id;

    private String mobile;

    private  String name;

    private String email;


    private String address;
    private  String pincode;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoginEntity(long id, String customer_id, String mobile, String name, String email, String address, String pincode) {
        this.id = id;
        this.customer_id = customer_id;
        this.mobile = mobile;
        this.name = name;
        this.email = email;
        this.address = address;
        this.pincode = pincode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
