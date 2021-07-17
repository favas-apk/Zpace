package com.project.zpace.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_info")

public class OrderEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;



    @ColumnInfo(name = "order_no")
    private String order_no;


    @ColumnInfo(name = "razor_order_id")
    private String razor_order_id;

    public OrderEntity(long id, String order_no, String razor_order_id) {
        this.id = id;
        this.order_no = order_no;
        this.razor_order_id = razor_order_id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getRazor_order_id() {
        return razor_order_id;
    }

    public void setRazor_order_id(String razor_order_id) {
        this.razor_order_id = razor_order_id;
    }
}
