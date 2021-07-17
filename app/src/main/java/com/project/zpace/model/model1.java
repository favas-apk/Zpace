package com.project.zpace.model;

public class model1 {

    private  String item;

    private  boolean wish;


    private String cash;

    private  String details;

    public model1(String item, boolean wish, String cash, String details) {
        this.item = item;
        this.wish = wish;
        this.cash = cash;
        this.details = details;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isWish() {
        return wish;
    }

    public void setWish(boolean wish) {
        this.wish = wish;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
