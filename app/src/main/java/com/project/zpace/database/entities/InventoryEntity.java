package com.project.zpace.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "inventory")

public class InventoryEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;



    @ColumnInfo(name = "itemname")
    private String itemname;


    public InventoryEntity(long id, String itemname) {
        this.id = id;
        this.itemname = itemname;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }



}
