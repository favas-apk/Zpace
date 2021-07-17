package com.project.zpace.database.daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.zpace.database.entities.DashEntity;
import com.project.zpace.database.entities.DashGroupEntity;

import java.util.List;


@Dao
public interface DashGroupEntityDao {

    @Query(" Select count(*)  from dashgroup")
    public int get_count();




    @Query(" Select * from dashgroup order by order_no")
    public List<DashGroupEntity> get_all_datas();

    @Query(" Select * from dashgroup where disp_st=0  and download_st=1 order by order_no")
    public List<DashGroupEntity> get_all_downloaded_but_undisplayed_datas();

    @Query(" Delete from dashgroup")
    public int del_all();


    @Insert
    public Long insert_single_details(DashGroupEntity tbl);


    @Query(" update dashgroup set download_st=:download_st where slno=:slno" )
    public int update_downlod_status(int download_st,int slno);

    @Query(" Select sum(download_st) from dashgroup ")
    public LiveData<Integer> get_count_of_downloaded_group();



    @Query(" update dashgroup set disp_st=:disp_st where slno=:slno" )
    public int update_display_status(int disp_st,int slno);




}
