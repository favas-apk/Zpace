package com.project.zpace.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.Utils;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.adapter.AdapterFrSingleViewGridSize;
import com.project.zpace.adapter.AdapterPhotosbycustomer;
import com.project.zpace.adapter.AdapterReviewbycustomer;
import com.project.zpace.adapter.AdapterSimilarProduct;
import com.project.zpace.adapter.AdapterSingleView;

import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.database.appdb.Appdb;
import com.project.zpace.database.entities.CartEntity;
import com.project.zpace.database.entities.DashEntity;
import com.project.zpace.database.entities.WishEntity;
import com.project.zpace.interfac.Fr_Single_Interface;
import com.project.zpace.model.model_like_dislike;
import com.project.zpace.model.model_offer_calculation;
import com.project.zpace.pojos.rates.DetailsItem;
import com.project.zpace.pojos.rates.Response;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class Fragment_Single_View extends Fragment implements Fr_Single_Interface {

    private View view;
    private List<String> list_pics = new ArrayList<>();
    List<model_like_dislike> list_pack = new ArrayList<>();

    List<com.project.zpace.pojos.read_size_and_details.DetailsItem> list_size_and_details = new ArrayList<>();
    List<com.project.zpace.pojos.read_size_and_details.DetailsItem> list_size_and_details_b = new ArrayList<>();
    private com.smarteist.autoimageslider.SliderView image_slide1;

    private com.project.zpace.custom.MyGridView gridview_size;
    private List<String> list_size;
    private List<String> list_photos_by_customer;
    private AdapterSingleView adp_single;
    private List<com.project.zpace.pojos.read_similar.DetailsItem> list_similar_product;

    private List<DetailsItem> list_rates = new ArrayList<>();

    private RecyclerView rcv_photos_by_customer, rcv_review_by_customer, rcv_similar_products;
    private TextView txt_similar_caption, txt_size_caption,txt_see_more, txt_details, txtbrand, txt_view_all_reviews, txt_qty, txt_caption, txt_saved_cash, txt_offer_2_or_3;
    private at.blogc.android.views.ExpandableTextView expandableTextView2;
    private String fname = "", stockid = "", customer_id = "", expand_contract_recv = "", itemname = "", color = "", brand = "", rate = "", size = "", offer_price = "", buy_qty = "", free_qty = "", free_percent = "", offer_end_date = "";
    private com.iarcuschin.simpleratingbar.SimpleRatingBar star_total;
    private Appdb db;
    AdapterReviewbycustomer adp;
    private LinearLayout ll_review, ll_dec, ll_inc, ll_offer;
    private Button btn_add_to_cart;
    private HomeActivity activity;
    int height = 50;
    private androidx.core.widget.NestedScrollView scroll;

    private org.fabiomsr.moneytextview.MoneyTextView txt_rate;

    private int qty = 0;
    private com.hosle.libcurrencyview.CurrencyTextView txt_real_price;
    private String offer_percent = "";
    private boolean size_flag = false;
    private AdapterFrSingleViewGridSize adp_size;
    private ConstraintLayout cl_offer1;
    private boolean stock_flag;
    private boolean read_flag = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_single_viewb, container, false);

        Constants.setFr_single_interface(Fragment_Single_View.this);
        db = Appdb.getDb_instance(getActivity());

        try {
            customer_id = db.getLoginEntityDao().get_customer_id().get(0);
        } catch (Exception e) {
            customer_id = null;
        }

        Constants.setCurrent_fragment(Constants.single);


        init();
        activity = (HomeActivity) getActivity();
        try {

            Bundle bundle = getArguments();

            String from = getArguments().getString("from");

            if (from.equals("outside")) {
                com.project.zpace.pojos.read_item_by_stkid_cust_app.DetailsItem obj = (com.project.zpace.pojos.read_item_by_stkid_cust_app.DetailsItem) bundle.getSerializable("Serialized_Details");


                fname = obj.getFname();
                stockid = obj.getStkidFromServer();
                txt_details.setText(obj.getItemname());

                brand = obj.getBrand();
                txtbrand.setText("Brand:" + brand);

                color = obj.getColor();

                itemname = obj.getItemname();
                rate = obj.getRate();
                offer_price = obj.getOffer();
                offer_end_date = obj.getOffer_end_date();
                buy_qty = obj.getBuy_quantity();
                free_qty = obj.getFree_quantity();
                free_percent = obj.getFree_percentage();

                if (!fname.equals("")) {
                    show_big_picture();
                }
                set_prices_and_offers(rate, offer_price, stockid, buy_qty, free_qty, free_percent, offer_end_date, "initial_call");

            } else if (from.equals("dash")) {
                DashEntity obj = (DashEntity) bundle.getSerializable("Serialized_Details");

                fname = obj.getImage();
                stockid = obj.getDetails();
                txt_details.setText(obj.getDisplay_name());
                brand = obj.getCompany();
                txtbrand.setText("Brand:" + brand);
                color = obj.getColor();

                itemname = obj.getDisplay_name();
                rate = obj.getRate();

                offer_price = obj.getOffer();
                offer_end_date = obj.getOffer_end_date();
                buy_qty = obj.getBuy_quantity();
                free_qty = obj.getFree_quantity();
                free_percent = obj.getFree_percentage();

                if (!fname.equals("")) {
                    show_big_picture();
                }
                set_prices_and_offers(rate, offer_price, stockid, buy_qty, free_qty, free_percent, offer_end_date, "initial_call");


            } else if (from.equals("wish")) {
                WishEntity obj = (WishEntity) bundle.getSerializable("Serialized_Details");
                fname = obj.getFname();
                stockid = obj.getItemcode();
                txt_details.setText(obj.getName());
                color = obj.getColor();

                brand = obj.getBrand();
                txtbrand.setText("Brand:" + brand);


                itemname = obj.getName();
                //   rate = obj.getRate();
                if (!fname.equals("")) {
                    show_big_picture();
                }

                read_current_rate_and_offers(stockid);


            } else if (from.equals("search")) {
                com.project.zpace.pojos.read_item_by_group.DetailsItem obj = (com.project.zpace.pojos.read_item_by_group.DetailsItem) bundle.getSerializable("Serialized_Details");
                fname = obj.getFname();
                stockid = obj.getStkidFromServer();
                txt_details.setText(obj.getItemname());
                brand = obj.getBrand();
                txtbrand.setText("Brand:" + brand);
                color = obj.getColor();
                itemname = obj.getItemname();
                rate = obj.getRate();
                offer_price = obj.getOffer();
                offer_end_date = obj.getOffer_end_date();
                buy_qty = obj.getBuy_quantity();
                free_qty = obj.getFree_quantity();
                free_percent = obj.getFree_percentage();

                if (!fname.equals("")) {
                    show_big_picture();
                }
                set_prices_and_offers(rate, offer_price, stockid, buy_qty, free_qty, free_percent, offer_end_date, "initial_call");


            } else if (from.equals("")) {
                com.project.zpace.pojos.read_item_by_group.DetailsItem obj = (com.project.zpace.pojos.read_item_by_group.DetailsItem) bundle.getSerializable("Serialized_Details");
                fname = obj.getFname();
                stockid = obj.getStkidFromServer();
                txt_details.setText(obj.getItemname());
                brand = obj.getBrand();
                txtbrand.setText("Brand:" + brand);
                color = obj.getColor();

                itemname = obj.getItemname();
                rate = obj.getRate();
                offer_price = obj.getOffer();
                offer_end_date = obj.getOffer_end_date();
                buy_qty = obj.getBuy_quantity();
                free_qty = obj.getFree_quantity();
                free_percent = obj.getFree_percentage();

                if (!fname.equals("")) {
                    show_big_picture();
                }
                set_prices_and_offers(rate, offer_price, stockid, buy_qty, free_qty, free_percent, offer_end_date, "initial_call");


            } else if (from.equals("dash_offer")) {
                com.project.zpace.pojos.show_dash_gridview.DetailsItem obj = (com.project.zpace.pojos.show_dash_gridview.DetailsItem) bundle.getSerializable("Serialized_Details");
                fname = obj.getImage();
                stockid = obj.getDetails();
                txt_details.setText(obj.getDisplay_name());
                brand = obj.getCompany();
                txtbrand.setText("Brand:" + brand);
                color = obj.getColor();
                itemname = obj.getDisplay_name();
                rate = obj.getRate();

                offer_price = obj.getOffer();
                offer_end_date = obj.getOffer_end_date();
                buy_qty = obj.getBuy_quantity();
                free_qty = obj.getFree_quantity();
                free_percent = obj.getFree_percentage();


                if (!fname.equals("")) {
                    show_big_picture();
                }
                set_prices_and_offers(rate, offer_price, stockid, buy_qty, free_qty, free_percent, offer_end_date, "initial_call");


            }


        } catch (Exception e) {

            Log.d("fgfgf", "gfgfg");

        }


//        star_total.setRating(3f);
//
//        star_total.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                return true;
//            }
//        });


        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.getLoginEntityDao().get_count() > 0) {

                    if (read_flag) {

                        if(stock_flag)
                        {

                        if (qty > 0) {

                            if (size_flag) {


                                //now add to cart


                                CartEntity raw = db.getCartEntityDao().get_qty_of_stockid(stockid);
                                int old_qty = 0;
                                long id = 0;

                                if (raw != null) {
                                    old_qty = raw.getQty();
                                    id = raw.getId();
                                }


                                if (old_qty > 0) {
                                    old_qty = old_qty + qty;
                                    db.getCartEntityDao().update(new CartEntity(id, stockid, old_qty, Float.parseFloat(rate), Float.parseFloat(offer_price), ""+offer_end_date, Integer.parseInt(buy_qty), Integer.parseInt(free_qty), Integer.parseInt(free_percent), itemname, fname, brand, color, size));
                                } else {
                                    db.getCartEntityDao().insert_cart_item(new CartEntity(0, stockid, qty, Float.parseFloat(rate), Float.parseFloat(offer_price), ""+offer_end_date, Integer.parseInt(buy_qty), Integer.parseInt(free_qty), Integer.parseInt(free_percent), itemname, fname, brand, color, size));
                                }


                                activity.showSnack_W("Added");

                            } else {
                                activity.showSnack_W(getString(R.string.plz_select_size));
                            }


                        } else {
                            activity.showSnack_W(getString(R.string.plz_select_qty));
                        }

                        //   activity.showSnack_W("stockid" + stockid + "  qty:"+qty);

                    }
                        else
                        {
                            Toast.makeText(getActivity(), getString(R.string.stock_not_available), Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getActivity(), getString(R.string.Plz_wait), Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getActivity(), getString(R.string.Plz_do_login), Toast.LENGTH_LONG).show();
                }

            }
        });

        ll_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    qty = Integer.parseInt(txt_qty.getText().toString().trim());

                } catch (Exception e) {

                    qty = 0;
                }

                if (qty < 10) {
                    qty = qty + 1;
                }
                txt_qty.setText(String.valueOf(qty));

            }
        });

        ll_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    qty = Integer.parseInt(txt_qty.getText().toString().trim());

                } catch (Exception e) {

                    qty = 0;
                }

                if (qty > 0) {
                    qty = qty - 1;
                }

                txt_qty.setText(String.valueOf(qty));


            }
        });

        txt_see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (expandableTextView2.isExpanded()) {
                    expandableTextView2.collapse();
                    txt_see_more.setText(R.string.See_more);
                } else {
                    expandableTextView2.expand();
                    txt_see_more.setText(R.string.See_less);
                }


            }
        });


        txt_view_all_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!expand_contract_recv.equals("expand")) {
                    expand_contract_recv = "expand";

                    txt_view_all_reviews.setText(getString(R.string.View_Less));

                } else {
                    expand_contract_recv = "contract";
                    txt_view_all_reviews.setText(getString(R.string.View_More));
                }

                expand_contract();


            }
        });


        show_reviews_by_customer();
        show_similar_product();


        show_photos_posted_by_customer();


        return view;


    }

    private void expand_contract() {
        int max_height = 0;
        if (list_pack.size() > 7) {
            max_height = 1000;
        } else if (list_pack.size() < 8 && list_pack.size() > 4) {

            max_height = 750;
        } else {
            max_height = 500;
        }


        if (expand_contract_recv.equals("expand") && height < max_height) {

            height = height + 10;

            rcv_review_by_customer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));


            set_delay();
        } else if (expand_contract_recv.equals("contract") && height > 150) {
            height = height - 10;

            rcv_review_by_customer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
            set_delay();
        }


    }


    private void set_delay() {


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                expand_contract();

            }
        }, 20);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private void init() {

        txt_similar_caption=view.findViewById(R.id.txt_similar_caption);
        txt_size_caption=view.findViewById(R.id.txt_size_caption);
        txt_details = view.findViewById(R.id.txt_details);
        txtbrand = view.findViewById(R.id.txtbrand);

        image_slide1 = view.findViewById(R.id.image_slide1);
        gridview_size = view.findViewById(R.id.gridview_size);

        txt_see_more = view.findViewById(R.id.txt_see_more);
        expandableTextView2 = view.findViewById(R.id.expandableTextView2);
        rcv_photos_by_customer = view.findViewById(R.id.rcv_photos_by_customer);
        rcv_review_by_customer = view.findViewById(R.id.rcv_review_by_customer);
        rcv_similar_products = view.findViewById(R.id.rcv_similar_products);
        star_total = view.findViewById(R.id.star_total);

        txt_view_all_reviews = view.findViewById(R.id.txt_view_all_reviews);
        ll_review = view.findViewById(R.id.ll_review);

        scroll = view.findViewById(R.id.scroll);
        txt_rate = view.findViewById(R.id.txt_rate);
        btn_add_to_cart = view.findViewById(R.id.btn_add_to_cart);
        ll_inc = view.findViewById(R.id.ll_inc);

        txt_qty = view.findViewById(R.id.txt_qty);
        ll_dec = view.findViewById(R.id.ll_dec);
        txt_real_price = view.findViewById(R.id.txt_real_price);
        txt_caption = view.findViewById(R.id.txt_caption);
        ll_offer = view.findViewById(R.id.ll_offer);
        txt_saved_cash = view.findViewById(R.id.txt_saved_cash);

        cl_offer1 = view.findViewById(R.id.cl_offer1);
        txt_offer_2_or_3 = view.findViewById(R.id.txt_offer_2_or_3);

    }


    private void show_similar_product() {

        if (!stockid.equals("")) {
            list_similar_product = new ArrayList<>();
            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

            Call<com.project.zpace.pojos.read_similar.Response> call = apiService.read_similar(Constants.api_key, stockid);


            call.enqueue(new Callback<com.project.zpace.pojos.read_similar.Response>() {
                @Override
                public void onResponse(Call<com.project.zpace.pojos.read_similar.Response> call, retrofit2.Response<com.project.zpace.pojos.read_similar.Response> response) {

                    if (response.body() != null) {
                        if (response.body().getResult().equals("1")) {

                            list_similar_product.addAll(response.body().getDetails());


                            AdapterSimilarProduct adp = new AdapterSimilarProduct(getActivity(), list_similar_product);
                            LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);


                            if (Constants.sceen.equals(Constants.single)) {
                                rcv_similar_products.setLayoutManager(lm);
                                rcv_similar_products.setAdapter(adp);
                            }

                            if( list_similar_product.size()==0)
                            {
                                txt_similar_caption.setVisibility(View.INVISIBLE);
                            }


                        }
                        else
                        {
                            txt_similar_caption.setVisibility(View.INVISIBLE);
                        }


                    }


                }

                @Override
                public void onFailure(Call<com.project.zpace.pojos.read_similar.Response> call, Throwable t) {

                }
            });
        }


    }


    private void show_reviews_by_customer() {

        if (list_rates.size() > 0) {
            list_rates.clear();
        }


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.get_rating(Constants.api_key, stockid);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.body() != null) {

                    if (response.body().getResult().equals("1")) {
                        ll_review.setVisibility(View.VISIBLE);
                        if (Constants.sceen.equals(Constants.single)) {


                            list_rates.addAll(response.body().getDetails());

                            set_listview();


                            adp = new AdapterReviewbycustomer(getActivity(), list_pack, stockid);

                            LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);


                            rcv_review_by_customer.setLayoutManager(lm);
                            rcv_review_by_customer.setAdapter(adp);
                        }


                    } else {

                        ll_review.setVisibility(View.GONE);
                        list_rates.clear();
                        list_pack.clear();
                        rcv_review_by_customer.setAdapter(null);
                    }


                }


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });


    }


    private void set_listview() {

        if (list_pack.size() > 0) {
            list_pack.clear();
        }


        String prev_comm = "", prev_star = "", prev_name = "", prev_user_id = "", prev_posted_id = "", prev_rating_id = "";

        int prev_likes = 0, prev_dis_likes = 0;
        boolean prev_flag_up_hand = false, prev_flag_down_hand = false;

        for (DetailsItem row : list_rates) {


            if (prev_user_id.equals("")) {
                prev_comm = row.getComments();
                prev_star = row.getStar();
                prev_name = row.getName();

                prev_user_id = row.getUser_id();

                prev_likes = Integer.parseInt(row.getLikes());

                prev_dis_likes = Integer.parseInt(row.getDislikes());

                prev_rating_id = row.getRating_id();

                prev_posted_id = row.getPosted_id();


                if (customer_id != null) {
                    if (row.getPosted_id().equals(customer_id)) {

                        prev_flag_up_hand = false;
                        prev_flag_down_hand = false;

                        if (prev_likes == 1) {
                            prev_flag_up_hand = true;
                        } else {
                            prev_flag_up_hand = false;
                        }

                        if (prev_dis_likes == 1) {
                            prev_flag_down_hand = true;
                        } else {
                            prev_flag_down_hand = false;
                        }
                    }
                }


            } else if (!prev_user_id.equals("") && prev_user_id.equals(row.getUser_id())) {

                prev_likes = prev_likes + Integer.parseInt(row.getLikes());
                prev_dis_likes = prev_dis_likes + Integer.parseInt(row.getDislikes());

                if (customer_id != null) {
                    if (row.getPosted_id().equals(customer_id)) {

                        prev_flag_up_hand = false;
                        prev_flag_down_hand = false;


                        if (row.getLikes().equals("1")) {
                            prev_flag_up_hand = true;
                        } else {
                            prev_flag_up_hand = false;
                        }
                        if (row.getDislikes().equals("1")) {
                            prev_flag_down_hand = true;
                        } else {
                            prev_flag_down_hand = false;
                        }

                    }
                }


            } else if (!prev_user_id.equals("") && !prev_user_id.equals(row.getUser_id())) {

                list_pack.add(new model_like_dislike(prev_comm, prev_star, "10/10/2020", prev_name, "" + prev_dis_likes, "" + prev_likes, prev_flag_up_hand, prev_flag_down_hand, prev_rating_id, prev_user_id));

                prev_flag_up_hand = false;
                prev_flag_down_hand = false;


                prev_comm = row.getComments();
                prev_star = row.getStar();
                prev_name = row.getName();

                prev_user_id = row.getUser_id();

                prev_likes = Integer.parseInt(row.getLikes());

                prev_dis_likes = Integer.parseInt(row.getDislikes());

                prev_rating_id = row.getRating_id();


                if (customer_id != null) {
                    if (row.getPosted_id().equals(customer_id)) {


                        if (prev_likes == 1) {
                            prev_flag_up_hand = true;
                        } else {
                            prev_flag_up_hand = false;
                        }

                        if (prev_dis_likes == 1) {
                            prev_flag_down_hand = true;
                        } else {
                            prev_flag_down_hand = false;
                        }
                    }
                }


            }

        }


    }


    private void show_photos_posted_by_customer() {

//        list_photos_by_customer = new ArrayList<>();
//        list_photos_by_customer.add("back1.jpg");
//        list_photos_by_customer.add("2.jpg");
//        list_photos_by_customer.add("3.jpg");
//
//        list_photos_by_customer.add("4.jpg");
//        list_photos_by_customer.add("5.jpg");
//
//        AdapterPhotosbycustomer adp = new AdapterPhotosbycustomer(getActivity(), list_photos_by_customer);
//
//        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        rcv_photos_by_customer.setLayoutManager(lm);
//        rcv_photos_by_customer.setAdapter(adp);

    }


    private void show_size_and_details() {
        // call api by passing itemname response will contains rate, offerprice , offer buy qty,free qty, free %,
        list_size_and_details = new ArrayList<>();
        list_size_and_details_b = new ArrayList<>();

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.zpace.pojos.read_size_and_details.Response> call = apiService.read_size_and_details(Constants.api_key, Utils.GVCOT(itemname));

        call.enqueue(new Callback<com.project.zpace.pojos.read_size_and_details.Response>() {
            @Override
            public void onResponse(Call<com.project.zpace.pojos.read_size_and_details.Response> call, retrofit2.Response<com.project.zpace.pojos.read_size_and_details.Response> response) {


                if (response.body() != null) {

                    if (response.body().getResult().equals("1")) {
                        read_flag = true;
                        if (response.body().getStock_flag().equals("1")) {

                            stock_flag = true;
                            list_size_and_details.addAll(response.body().getDetails());


                            String entered = "";
                            for (com.project.zpace.pojos.read_size_and_details.DetailsItem row : list_size_and_details) {

                                if (!row.getSize().equals("") && !row.getSize().equals("0")) {
                                    entered = "100";

                                    list_size_and_details_b.add(new com.project.zpace.pojos.read_size_and_details.DetailsItem(row.getFreeQty(),row.getBuyQty(),row.getFreePercent(),row.getSize(),row.getRate(),row.getOfferEndDate(),row.getStkidFromServer(),row.getOfferPrice(),false) );

                                   // break;
                                }

                            }
                            if (entered.equals("100")) {
                                size_flag = false;
                            } else {
                                size_flag = true;
                                size = "0";
                            }

                            if (Constants.sceen.equals(Constants.single)) {

                                if (list_size_and_details_b.size() > 0)
                                {

                                    adp_size = new AdapterFrSingleViewGridSize(getActivity(), list_size_and_details_b);
                                gridview_size.setAdapter(adp_size);
                                adp_size.notifyDataSetChanged();

                            }
                                else
                                {
                                    txt_size_caption.setVisibility(View.INVISIBLE);
                                }
                            }
                        } else {

                            stock_flag = false;
                            Toast.makeText(getActivity(), "No stock", Toast.LENGTH_LONG).show();
                        }


                    }


                }

            }

            @Override
            public void onFailure(Call<com.project.zpace.pojos.read_size_and_details.Response> call, Throwable t) {

            }
        });


    }


    private void show_big_picture() {

        list_pics = new ArrayList<>();


        if (fname.contains(",")) {
            String[] temp = fname.split(",");

            for (String fname : temp) {
                list_pics.add(fname);
            }


        } else {
            list_pics.add(fname);
        }


    }

    @Override
    public void new_insert_like_dislike(String stockid, String rating_id, String posted_id, String likes, String dislike) {

        // call api and then refresh

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.insert_like_dislike(Constants.api_key, stockid, rating_id, posted_id, likes, dislike);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                if (response.body() != null) {

                    if (response.body().getResult().equals("1")) {
                        list_rates.clear();

                        list_rates.addAll(response.body().getDetails());

                        set_listview();
                        adp.notifyDataSetChanged();


                    }

                }


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });


    }

    @Override
    public void del_like_dislike(String rating_id, String posted_id, String stockid) {

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.del_like_dislike(Constants.api_key, rating_id, posted_id, stockid);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                if (response.body() != null) {

                    if (response.body().getResult().equals("1")) {
                        list_rates.clear();

                        list_rates.addAll(response.body().getDetails());

                        set_listview();
                        adp.notifyDataSetChanged();

                    }

                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });


    }

    @Override
    public void update_like_dislike(String stockid, String rating_id, String posted_id, String likes, String dislike) {


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.update_like_dislike(Constants.api_key, stockid, rating_id, posted_id, likes, dislike);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                if (response.body() != null) {

                    if (response.body().getResult().equals("1")) {
                        list_rates.clear();

                        list_rates.addAll(response.body().getDetails());

                        set_listview();
                        adp.notifyDataSetChanged();


                    }

                }


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });


    }

    @Override
    public void load_view_again(com.project.zpace.pojos.read_similar.DetailsItem row) {

        //    com.project.zpace.pojos.read_similar.DetailsItem obj = ( com.project.zpace.pojos.read_similar.DetailsItem) row.getSerializable("Serialized_Details");

        rate = row.getRate();
        offer_price = row.getOffer();
        stockid = row.getStkidFromServer();
        buy_qty = row.getBuy_quantity();
        free_qty = row.getFree_quantity();
        free_percent = row.getFree_percentage();
        offer_end_date = row.getOffer_end_date();

        itemname = row.getItemname();
        fname = row.getFname();

        txt_details.setText(itemname);

        brand = row.getBrand();
        txtbrand.setText("Brand:" + brand);

        color = row.getColor();


        txt_rate.setAmount(Float.parseFloat(rate));

        if (!fname.equals("")) {
            show_big_picture();
        }
        gridview_size.setAdapter(null);
        adp_size.notifyDataSetChanged();

        set_prices_and_offers(rate, offer_price, stockid, buy_qty, free_qty, free_percent, offer_end_date, "initial_call");


        show_reviews_by_customer();
        show_similar_product();
        scroll.scrollTo(0, scroll.getTop());

    }

    @Override
    public void set_prices_and_offers(String rate, String offer_price, String stockid, String buy_qty, String free_qty, String free_percent, String offer_end_date, String from) {

        ll_offer.setVisibility(View.GONE);
        cl_offer1.setVisibility(View.GONE);
        txt_offer_2_or_3.setVisibility(View.INVISIBLE);


        this.stockid = stockid;
        this.rate = rate;
        this.offer_price = offer_price;
        this.buy_qty = buy_qty;
        this.free_qty = free_qty;
        this.free_percent = free_percent;
        this.offer_end_date = offer_end_date;


        float flt_offer_price = 0;
        try {
            flt_offer_price = Float.parseFloat(offer_price);
        } catch (Exception e) {
            flt_offer_price = 0;
        }

        float flt_free_qty = 0;
        try {
            flt_free_qty = Float.parseFloat(free_qty);
        } catch (Exception e) {
            flt_free_qty = 0;
        }

        float flt_free_percent = 0;
        try {
            flt_free_percent = Float.parseFloat(free_percent);
        } catch (Exception e) {
            flt_free_percent = 0;
        }


      model_offer_calculation  offer_class = new model_offer_calculation(Utils.GetFloat(this.rate), flt_offer_price, Utils.GetInt(this.buy_qty ), Utils.GetInt(this.free_qty), flt_free_percent, this.offer_end_date);
        if(offer_class.get_offer_type().equals("A"))
        {
            ll_offer.setVisibility(View.VISIBLE);
            cl_offer1.setVisibility(View.VISIBLE);
            txt_rate.setAmount(flt_offer_price);
            txt_real_price.setText(rate);

            String txt_offer = "Save " + getString(R.string.Rs) + " " + (Float.parseFloat(rate) - flt_offer_price);
            txt_saved_cash.setText(txt_offer);

            BigDecimal rate_big = new BigDecimal(rate);
            BigDecimal offer_big = new BigDecimal(offer_price);
            BigDecimal diff_big = rate_big.subtract(offer_big);
            BigDecimal offer_percent_big = diff_big.divide(rate_big, 3, RoundingMode.HALF_UP);
            offer_percent_big = offer_percent_big.multiply(new BigDecimal("100.0"));


            offer_percent_big = offer_percent_big.setScale(1, RoundingMode.HALF_UP);


            //  Constants.getAd_single_interface().set_offer_percent();
            offer_percent = offer_percent_big.toPlainString() + "%\noff";

            if (list_pics.size() > 0) {
                adp_single = new AdapterSingleView(getActivity(), list_pics, stockid, itemname, rate, offer_percent);
                image_slide1.setSliderAdapter(adp_single);
            }
            adp_single.notifyDataSetChanged();

        }
        else if(offer_class.get_offer_type().equals("B"))
        {
            ll_offer.setVisibility(View.VISIBLE);
            txt_offer_2_or_3.setVisibility(View.VISIBLE);
            txt_rate.setAmount(Float.parseFloat(rate));

            txt_offer_2_or_3.setText("Buy " + buy_qty + " Free " + free_qty);

            offer_percent = "";
            if (list_pics.size() > 0) {
                adp_single = new AdapterSingleView(getActivity(), list_pics, stockid, itemname, rate, offer_percent);
                image_slide1.setSliderAdapter(adp_single);
            }
            adp_single.notifyDataSetChanged();
        }
        else if(offer_class.get_offer_type().equals("C"))
        {
            ll_offer.setVisibility(View.VISIBLE);
            txt_offer_2_or_3.setVisibility(View.VISIBLE);
            txt_offer_2_or_3.setText("Buy " + buy_qty + " " + free_percent + "% off");
            txt_rate.setAmount(Float.parseFloat(rate));

            offer_percent = "";
            if (list_pics.size() > 0) {
                adp_single = new AdapterSingleView(getActivity(), list_pics, stockid, itemname, rate, offer_percent);
                image_slide1.setSliderAdapter(adp_single);
            }
            adp_single.notifyDataSetChanged();
        }
        else
        {
            ll_offer.setVisibility(View.INVISIBLE);

            txt_rate.setAmount(Float.parseFloat(rate));
            txt_real_price.setText("");


            offer_percent = "";
            if (list_pics.size() > 0) {
                adp_single = new AdapterSingleView(getActivity(), list_pics, stockid, itemname, rate, offer_percent);
                image_slide1.setSliderAdapter(adp_single);
            }
            adp_single.notifyDataSetChanged();
        }



        if (from.equals("initial_call")) {
            show_size_and_details();
        }


    }

    @Override
    public void set_size_flag(String size) {

        size_flag = true;
        this.size = size;

    }


    private void read_current_rate_and_offers(String stokid) {
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> call = apiService.read_item_by_stkid(Constants.api_key, stockid);


        call.enqueue(new Callback<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response>() {
            @Override
            public void onResponse(Call<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> call, retrofit2.Response<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> response) {

                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {


                        rate = response.body().getDetails().get(0).getRate();

                        offer_price = response.body().getDetails().get(0).getOffer();
                        offer_end_date = response.body().getDetails().get(0).getOffer_end_date();
                        buy_qty = response.body().getDetails().get(0).getBuy_quantity();
                        free_qty = response.body().getDetails().get(0).getFree_quantity();
                        free_percent = response.body().getDetails().get(0).getFree_percentage();

                        set_prices_and_offers(rate, offer_price, stockid, buy_qty, free_qty, free_percent, offer_end_date, "initial_call");

                    }
                }

            }

            @Override
            public void onFailure(Call<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> call, Throwable t) {

            }
        });


    }

}
