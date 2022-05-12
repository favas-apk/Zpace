package com.project.milan.database.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "search_details",indices = {@Index(value = {"search_word"},
        unique = true)})

public class SearchEntity {

    @PrimaryKey(autoGenerate = true)
    private   long id;



    @ColumnInfo(name = "search_word")
    private String search_word;




    public SearchEntity(long id, String search_word) {
        this.id = id;
        this.search_word = search_word;

    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSearch_word() {
        return search_word;
    }

    public void setSearch_word(String search_word) {
        this.search_word = search_word;
    }
}
