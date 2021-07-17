package com.project.zpace.pojos.base;

import com.google.gson.annotations.Expose;

public class PojomodelRazorId extends Pojomodelbase{


    @Expose
    private String razor_order_id;


    public String getRazor_order_id() {
        return razor_order_id;
    }

    public void setRazor_order_id(String razor_order_id) {
        this.razor_order_id = razor_order_id;
    }
}
