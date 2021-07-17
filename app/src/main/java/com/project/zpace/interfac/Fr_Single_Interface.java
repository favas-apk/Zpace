package com.project.zpace.interfac;

import com.project.zpace.pojos.read_similar.DetailsItem;

public interface Fr_Single_Interface {

    void new_insert_like_dislike(String stockid, String rating_id,String cust_id ,String likes,String dislike);


    void del_like_dislike( String rating_id,String posted_id ,String stockid);

    void update_like_dislike(String stockid, String rating_id,String cust_id ,String likes,String dislike);


    void load_view_again(DetailsItem row);

    void set_prices_and_offers(String rate,String offer_price,String stockid,String buy_qty,String free_qty,String free_percent,String offer_end_date,String from);

 void set_size_flag( String size);

}
