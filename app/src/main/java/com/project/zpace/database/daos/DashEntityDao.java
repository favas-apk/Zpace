package com.project.zpace.database.daos;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.zpace.database.entities.DashEntity;
import com.project.zpace.database.entities.LoginEntity;

import java.util.List;


@Dao
public interface DashEntityDao {

    @Query(" Select count(*)  from dash")
    public int get_count();



    @Query(" Select count(*)  from dash where dash_slno=:dash_slno")
    public int get_count_of_items_inside_dash_group(String dash_slno);


    @Query(" Select * from dash")
    public List<DashEntity> get_all_datas();



    @Query(" Select * from dash where  dash_slno=:dash_slno")
    public List<DashEntity> get_all_datas_specified_group(int dash_slno);



    @Query(" Delete from dash")
    public int del_all();


    @Insert
    public Long insert_single_details(DashEntity tbl);

//    @Insert
//    public int insert_all_details(List<DashEntity> list);









}
