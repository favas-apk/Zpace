package com.project.milan.database.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.milan.database.entities.SearchEntity;

import java.util.List;


@Dao
public interface SearchEntityDao {

    @Query(" Select count(*)  from search_details")
    public int get_count();


    @Query(" Select search_word from search_details")
    public List<String> get_all_datas();



    @Query(" Delete from search_details")
    public int del_all();

    @Query(" Delete from search_details where search_word=:item")
    public int del(String item);


    @Insert(onConflict = OnConflictStrategy.IGNORE)

    public Long inser_search_details(SearchEntity tbl);

}
