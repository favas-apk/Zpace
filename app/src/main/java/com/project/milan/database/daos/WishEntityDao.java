package com.project.milan.database.daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.milan.database.entities.WishEntity;

import java.util.List;


@Dao
public interface WishEntityDao {

    @Query(" Select count(*)  from wish_details where itemcode=:itemcode")
    public int get_count(String itemcode);

    @Query(" Select count(*)  from wish_details ")
    public LiveData<Integer> get_total_count();



    @Query(" Select * from wish_details")
    public List<WishEntity> get_all_datas();



    @Query(" Delete from wish_details")
    public int del_all();

    @Query(" Delete from wish_details where itemcode=:itemcode")
    public int del(String itemcode);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Long insert_wish_details(WishEntity tbl);

}
