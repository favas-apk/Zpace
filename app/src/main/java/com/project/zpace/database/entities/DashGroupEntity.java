package com.project.zpace.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dashgroup")
public class DashGroupEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;

    private int slno;

    private int order_no;

    private  String style;

    private String display_name;


    private int download_st;
    private  int  disp_st;


    private String type;
    private String count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public DashGroupEntity(long id, int slno, int order_no, String style, String display_name, int download_st, int disp_st,String type,String count) {
        this.id = id;
        this.slno = slno;
        this.order_no = order_no;
        this.style = style;
        this.display_name = display_name;
        this.download_st = download_st;
        this.disp_st = disp_st;

        this.type=type;
        this.count=count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSlno() {
        return slno;
    }

    public void setSlno(int slno) {
        this.slno = slno;
    }

    public int getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public int getDownload_st() {
        return download_st;
    }

    public void setDownload_st(int download_st) {
        this.download_st = download_st;
    }

    public int getDisp_st() {
        return disp_st;
    }

    public void setDisp_st(int disp_st) {
        this.disp_st = disp_st;
    }
}
