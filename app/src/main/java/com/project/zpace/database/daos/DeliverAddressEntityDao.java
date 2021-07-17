package com.project.zpace.database.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.zpace.database.entities.DashEntity;
import com.project.zpace.database.entities.DeliveryAddressEntity;

import java.util.List;


@Dao
public interface DeliverAddressEntityDao {

    @Query(" Select count(*)  from deliver_address")
    public int get_count();


    @Query(" Delete from deliver_address")
    public int del_all();


    @Insert
    public Long insert_single_details(DeliveryAddressEntity tbl);

    @Query(" Select * from deliver_address")
    public List<DeliveryAddressEntity> get_all_datas();




}
