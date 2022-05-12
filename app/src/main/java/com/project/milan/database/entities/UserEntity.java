package com.project.milan.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_details")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;




   private int num;


    public UserEntity(long id, int num) {
        this.id = id;
        this.num = num;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
