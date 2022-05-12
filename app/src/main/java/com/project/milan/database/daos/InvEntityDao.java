package com.project.milan.database.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project.milan.database.entities.InventoryEntity;

import java.util.List;


@Dao
public interface InvEntityDao {

    @Query(" Select count(*)  from inventory")
    public int get_count();


    @Query(" Select  itemname from inventory")
    public List<String> get_all_datas();



    @Query(" Delete from inventory")
    public int del_all();



    @Insert(onConflict = OnConflictStrategy.IGNORE)

    public Long insert_inv_item(InventoryEntity tbl);



   
}
