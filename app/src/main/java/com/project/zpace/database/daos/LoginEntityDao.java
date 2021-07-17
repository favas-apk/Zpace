package com.project.zpace.database.daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.zpace.database.entities.LoginEntity;
import com.project.zpace.database.entities.UserEntity;

import java.util.List;


@Dao
public interface LoginEntityDao {

    @Query(" Select count(*)  from login_details")
    public int get_count();



    @Query(" Select * from login_details")
    public List<LoginEntity> get_all_datas();



    @Query(" Delete from login_details")
    public int del_all();


    @Insert
    public Long insert_user_details(LoginEntity tbl);



    @Query(" Select customer_id from login_details ")
    public List<String> get_customer_id();


    @Query(" update login_details set name=:name,email=:email,address=:ads,pincode=:pincode  where customer_id=:cust_id" )
    public int update(String name,String email,String ads,String pincode,String cust_id);




}
