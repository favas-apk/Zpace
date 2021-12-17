package com.project.zpace.apiservice;




import com.project.zpace.pojos.base.PojomodelRazorId;
import com.project.zpace.pojos.base.Pojomodelbase;

import com.project.zpace.pojos.login.PojomodelLogin;
import com.project.zpace.pojos.login.PojomodelSignup;
import com.project.zpace.pojos.offer.PojomodelOffer;

import com.project.zpace.pojos.show_dash_gridview.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Endpoint {


 @FormUrlEncoded
 @POST("api/GetCustomer")
 Call<com.project.zpace.pojos.pojo_Ptest.Response> read_customer(@Field("search_key") String apikey);


 @FormUrlEncoded
 @POST("zpa/save_payment_id.php")
 Call<Pojomodelbase> save_payment_id(@Field("apikey") String apikey, @Field("order_no") String orderno,  @Field("custid") String custid,@Field("razor_order_id") String razor_order_id,@Field("payment_id") String payment_id);



 @FormUrlEncoded
 @POST("zpa/razorpay-php/create_order.php")
 Call<PojomodelRazorId> generate_razor_order_id(@Field("apikey") String apikey, @Field("orderno") String orderno, @Field("total") String total, @Field("custid") String custid);



 @FormUrlEncoded
 @POST("zpa/read_payment_id.php")
 Call<Pojomodelbase> read_payment(@Field("apikey") String apikey, @Field("order_no") String order_no, @Field("razor_order_id") String razor_order_id,@Field("custid") String custid);



 @FormUrlEncoded
 @POST("zpa/delete_order.php")
 Call<Pojomodelbase> delete_order(@Field("apikey") String apikey, @Field("order_no") String order_no, @Field("custid") String custid);


 @FormUrlEncoded
 @POST("zpa/save_order.php")
 Call<com.project.zpace.pojos.read_web_rate_and_offers.Response> save_order(@Field("apikey") String apikey, @Field("cartMap") String data, @Field("size") int size, @Field("whole_stockid") String whole_stockid);


 @GET("zpa/read_today_date.php")
 Call<com.project.zpace.pojos.read_today_date.Response> read_today_date();


 @FormUrlEncoded
 @POST("zpa/save_del_ads.php")
 Call<com.project.zpace.pojos.save_del_ads.Response> save_del_addres(@Field("apikey") String apikey, @Field("custid") String custid, @Field("name") String name, @Field("mob") String mob, @Field("pin") String pin, @Field("ads") String ads, @Field("area") String area, @Field("landmark") String landmark, @Field("city") String city, @Field("state") String state);


 @FormUrlEncoded
 @POST("zpa/read_size_details.php")
 Call<com.project.zpace.pojos.read_size_and_details.Response> read_size_and_details(@Field("apikey") String apikey, @Field("itemname") String itemname);




 @FormUrlEncoded
 @POST("zpa/update_status.php")
 Call<Pojomodelbase> update_status(@Field("apikey") String apikey,@Field("mob") String mob,@Field("customer_id") String customer_id);




 @FormUrlEncoded
 @POST("zpa/send_sms.php")
 Call<Pojomodelbase> send_sms(@Field("apikey") String apikey,@Field("mob") String mob,@Field("otp") String otp);


 @FormUrlEncoded
 @POST("zpa/update.php")
 Call<Pojomodelbase> do_update(@Field("apikey") String apikey, @Field("name") String name,@Field("email") String email,@Field("address") String address,@Field("pincode") String pincode,@Field("customer_id") String customer_id);





 @FormUrlEncoded
 @POST("zpa/read_item_by_similar.php")
 Call<com.project.zpace.pojos.read_similar.Response> read_similar(@Field("apikey") String apikey, @Field("stockid") String stockid);


 @FormUrlEncoded
 @POST("zpa/update_like_dislike.php")
 Call<com.project.zpace.pojos.rates.Response> update_like_dislike(@Field("apikey") String apikey, @Field("stockid") String stockid,@Field("rating_id") String rating_id,@Field("posted_id") String posted_id,@Field("likes") String likes,@Field("dislikes") String dislikes);


 @FormUrlEncoded
 @POST("zpa/del_like_unlike.php")
 Call<com.project.zpace.pojos.rates.Response> del_like_dislike(@Field("apikey") String apikey, @Field("rating_id") String rating_id,@Field("posted_id") String posted_id,@Field("stockid") String stockid);



 @FormUrlEncoded
 @POST("zpa/insert_like_dislike.php")
 Call<com.project.zpace.pojos.rates.Response> insert_like_dislike(@Field("apikey") String apikey, @Field("stockid") String stockid,@Field("rating_id") String rating_id,@Field("posted_id") String posted_id,@Field("likes") String likes,@Field("dislikes") String dislikes);


 @FormUrlEncoded
 @POST("zpa/get_ratings.php")
 Call<com.project.zpace.pojos.rates.Response> get_rating(@Field("apikey") String apikey, @Field("stockid") String stockid);





 @FormUrlEncoded
 @POST("zpa/login.php")
 Call<PojomodelLogin> do_login(@Field("apikey") String apikey, @Field("mob") String mob, @Field("pswd") String pswd);





 @FormUrlEncoded
 @POST("zpa/signup.php")
 Call<PojomodelSignup> do_sign_up(@Field("apikey") String apikey, @Field("name") String name, @Field("mob") String mob, @Field("email") String email, @Field("pswd") String pswd, @Field("address") String address, @Field("pincode") String pincode);



 @FormUrlEncoded
 @POST("zpa/read_item_by_stockid_customer_app.php")
 Call<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> read_item_by_stkid(@Field("apikey") String apikey, @Field("stkid") String stkid);



// @FormUrlEncoded
// @POST("zpa/read_filters.php")
// Call<com.project.zpace.pojos.read_filter.Response> read_filters(@Field("apikey") String apikey);
//



 @FormUrlEncoded
 @POST("zpa/read_item_by_itemname_wth_lmt.php")
 Call<com.project.zpace.pojos.read_item_by_group.Response> read_item_by_itemname(@Field("apikey") String apikey, @Field ("itemname") String itemname,@Field ("from") int from,@Field ("count") int count);



 @FormUrlEncoded
 @POST("zpa/read_item_by_group.php")
 Call<com.project.zpace.pojos.read_item_by_group.Response> read_item_by_group(@Field("apikey") String apikey, @Field ("group") String group, @Field ("from") int from, @Field ("count") int count,@Field ("order_by_cash") String order_by_cash,@Field ("cat") String cat,@Field ("bra") String bra,@Field ("col") String col,@Field ("siz") String siz);



 @FormUrlEncoded
 @POST("zpa/read_item_by_particular_group.php")
 Call<com.project.zpace.pojos.read_item_by_group.Response> read_item_by_particular_group(@Field("apikey") String apikey,@Field ("cat") String cat, @Field ("sub1") String sub1,@Field ("sub2") String sub2, @Field ("from") int from, @Field ("count") int count,@Field ("order_by_cash") String order_by_cash,@Field ("bra") String bra,@Field ("col") String col,@Field ("siz") String siz);



 @FormUrlEncoded
 @POST("zpa/read_item_by_category_with_limit.php")
 Call<com.project.zpace.pojos.read_item_by_group.Response> read_item_by_category(@Field("apikey") String apikey, @Field ("category") String category, @Field ("from") int from, @Field ("count") int count,@Field ("order_by_cash")String order_by_cash,@Field ("cat") String cat,@Field ("bra") String bra,@Field ("col") String col,@Field ("siz") String siz);


 @FormUrlEncoded
 @POST("zpa/read_item_for_search_box.php")
 Call<com.project.zpace.pojos.search_box.Response> read_item_for_search_box(@Field("apikey") String apikey);





 @FormUrlEncoded
 @POST("zpa/read_category3.php")
 Call<com.project.zpace.pojos.read_category.Response> read_sub2(@Field("apikey") String apikey,@Field("category") String category,@Field("sub_category1") String sub_category1);




 @FormUrlEncoded
 @POST("zpa/read_category2.php")
 Call<com.project.zpace.pojos.read_category.Response> read_sub1(@Field("apikey") String apikey, @Field("category") String category);


 @FormUrlEncoded
 @POST("zpa/read_category1.php")
 Call<com.project.zpace.pojos.read_category.Response> read_category1(@Field("apikey") String apikey);


 @FormUrlEncoded
 @POST("zpa/read_category1_with_images.php")
 Call<com.project.zpace.pojos.read_category_with_random_images.Response> read_category1_with_random_images(@Field("apikey") String apikey);


 @FormUrlEncoded
 @POST("zpa/show_dash_offer.php")
 Call<Response> show_dash_offer(@Field("apikey") String apikey, @Field("dash_slno") String dash_slno,@Field("type") String type,@Field("count") String count);



 @FormUrlEncoded
 @POST("zpa/show_dashborad_gridview.php")
 Call<Response> show_dash_gridview(@Field("apikey") String apikey, @Field("dash_slno") String dash_slno,@Field("call_from") String call_from);

 @FormUrlEncoded
 @POST("zpa/read_dasboard_category_customer.php")
 Call<com.project.zpace.pojos.read_dashboard_categories_structure.Response> read_dashboard_category(@Field("apikey") String apikey);





 @GET("zpa/read_offer.php")
 Call<PojomodelOffer> read_offer_live();






}
