package com.project.milan.database.daos;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.project.milan.database.entities.CartEntity;

import java.util.List;


@Dao
public interface CartEntityDao {

    @Query(" Select count(*)  from cart")
    public int get_count();


    @Query(" Select * from cart order by id")
    public List<CartEntity> get_all_datas();



    @Query(" Delete from cart")
    public int del_all();


    @Query(" Select sum(qty) from cart ")
    public LiveData<Integer> get_total_qty();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Long insert_cart_item(CartEntity tbl);


    @Query(" Select *  from cart where stkid=:stockid")
    public  CartEntity get_qty_of_stockid(String stockid);

    @Update
    void update(CartEntity tbl);


    @Query(" Update cart set qty=:qty where id=:id")
    public  int update_qty(long id,int qty);

    @Query(" Delete from cart where  id=:id")
    public int del_item(long id);

    @Query(" Update cart set rate=:rate ,offer_price=:offer_price,buy_qty=:buy_qty,free_qty=:free_qty,free_percent=:free_percent,offer_end_date=:offer_end_date  where stkid=:stkid")
    public int update_new_rate_and_offers(String stkid,float rate,float offer_price,int buy_qty,int free_qty,float free_percent,String offer_end_date);

    @Query(" Update cart set qty=:qty  where stkid=:stkid")
    public int update_avb_qty(String stkid,int qty);


}
