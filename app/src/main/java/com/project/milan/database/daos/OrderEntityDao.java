package com.project.milan.database.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.milan.database.entities.OrderEntity;

import java.util.List;


@Dao
public interface OrderEntityDao {

    @Query(" Select count(*)  from order_info  ")
    public int get_count();




    @Query(" Delete from order_info")
    public int del_all();


    @Query(" Select order_no from order_info ")
    public List<String> get_order_no();


    @Query(" Select razor_order_id from order_info ")
    public List<String> get_razor_order_id();




    @Insert
    public Long insert_Order_info(OrderEntity tbl);


    @Query(" Update  order_info  set razor_order_id=:razor_id  where order_no=:order_no ")
    public void save_razor_order_id(String order_no,String razor_id);


}
