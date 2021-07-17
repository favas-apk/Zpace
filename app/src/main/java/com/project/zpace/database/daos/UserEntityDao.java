package com.project.zpace.database.daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.zpace.database.entities.UserEntity;

import java.util.List;


@Dao
public interface UserEntityDao {

    @Query(" Select count(*)  from user_details")
    public int get_count();


    @Query(" Select * from user_details")
    public LiveData<List<UserEntity>> get_all_datas();



    @Query(" Delete from user_details")
    public int del_all();


    @Insert
    public Long insert_user_details(UserEntity tbl);

}
