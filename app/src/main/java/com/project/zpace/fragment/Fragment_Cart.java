package com.project.zpace.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.Utils;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.activity.PaymentActivity;
import com.project.zpace.adapter.AdapterCart;
import com.project.zpace.adapter.AdapterDeliveryAds;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.custom.PlayEditTextView;
import com.project.zpace.database.appdb.Appdb;
import com.project.zpace.database.entities.CartEntity;
import com.project.zpace.database.entities.DeliveryAddressEntity;
import com.project.zpace.database.entities.OrderEntity;
import com.project.zpace.interfac.Fr_CartInterface;
import com.project.zpace.model.model_offer_calculation;
import com.project.zpace.pojos.base.PojomodelRazorId;
import com.project.zpace.pojos.base.Pojomodelbase;
import com.project.zpace.pojos.read_web_rate_and_offers.DetailsItem_no_stock;
import com.project.zpace.pojos.states.DetailsItem;
import com.project.zpace.pojos.states.Response;


import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;


public class Fragment_Cart extends Fragment implements Fr_CartInterface {

    private View view;
    private RecyclerView rcv_cartlist;

    private Button btn_do_payment;
    private TextView txt1, txt2, txt_tot_no, txt_tot_free_no, txt_amt;
    private ImageView iv_add, iv_show;
    private HomeActivity activity;
    private Appdb db;
    private com.flipboard.bottomsheet.BottomSheetLayout bottomSheet;
    private List<DetailsItem> list_states = new ArrayList<>();
    private CardView card1;
    private List<com.project.zpace.pojos.save_del_ads.DetailsItem> list_del_ads;
    private List<DeliveryAddressEntity> list_saved_ads;
    List<DetailsItem_no_stock> list_stock_prob_items = new ArrayList<>();
    private AdapterCart adp;
    model_offer_calculation offer_class;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_cartlist, container, false);


        init();

        Constants.setFr_cartInterface(this);
        db = Appdb.getDb_instance(getActivity());

        activity = (HomeActivity) getActivity();

        show_cartlist();


        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.getDelAdressEntityDao().get_count() > 0) {
                    show_saved_del_ads(false);

                }


            }
        });

        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_del_address();
            }
        });

        btn_do_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.getLoginEntityDao().get_count() > 0) {

                    if (db.getDelAdressEntityDao().get_count() == 0) {
                        add_del_address();
                    } else {

                        show_saved_del_ads(true);
                        // do remaining works
                        //Toast.makeText(getActivity(), "do remaining works", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getActivity(), getString(R.string.Plz_do_login), Toast.LENGTH_LONG).show();
                }


            }
        });


        return view;


    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private void init() {
        rcv_cartlist = view.findViewById(R.id.rcv_cartlist);
        btn_do_payment = view.findViewById(R.id.btn_do_payment);
        txt1 = view.findViewById(R.id.txt1);
        bottomSheet = view.findViewById(R.id.bottomSheet);
        card1 = view.findViewById(R.id.card1);
        txt2 = view.findViewById(R.id.txt2);
        iv_add = view.findViewById(R.id.iv_add);
        iv_show = view.findViewById(R.id.iv_show);
        txt_tot_no = view.findViewById(R.id.txt_tot_no);
        txt_tot_free_no = view.findViewById(R.id.txt_tot_free_no);
        txt_amt = view.findViewById(R.id.txt_amt);


    }


    private void show_saved_del_ads(boolean choose) {


        bottomSheet.showWithSheetView(LayoutInflater.from(getActivity()).inflate(R.layout.sheet_show_del_ads, bottomSheet, false));

        RecyclerView recv1 = bottomSheet.findViewById(R.id.recv1);

        list_saved_ads = new ArrayList<>();

        list_saved_ads.addAll(db.getDelAdressEntityDao().get_all_datas());

        //  list_saved_ads.add(new DeliveryAddressEntity(0, "", "", "", "", "", "", "", "", Constants.Add_an_del_ads, ""));
        AdapterDeliveryAds adp = new AdapterDeliveryAds(getActivity(), list_saved_ads, choose);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recv1.setLayoutManager(lm);
        recv1.setAdapter(adp);


    }


    public void add_del_address() {


        list_del_ads = new ArrayList<>();

        List<String> list = new ArrayList<>();


        bottomSheet.showWithSheetView(LayoutInflater.from(getActivity()).inflate(R.layout.sheet_add_del_ads, bottomSheet, false));


        Spinner spn1 = bottomSheet.findViewById(R.id.spn1);

        PlayEditTextView edtname = bottomSheet.findViewById(R.id.edtname);
        PlayEditTextView edtmob = bottomSheet.findViewById(R.id.edtmob);
        PlayEditTextView edtpin = bottomSheet.findViewById(R.id.edtpin);

        PlayEditTextView edtads = bottomSheet.findViewById(R.id.edtads);
        PlayEditTextView edtarea = bottomSheet.findViewById(R.id.edtarea);
        PlayEditTextView edtlandmark = bottomSheet.findViewById(R.id.edtlandmark);

        PlayEditTextView edtcity = bottomSheet.findViewById(R.id.edtcity);

        // edtcity.scrollTo(0, 10);
        br.com.simplepass.loadingbutton.customViews.CircularProgressButton btnNext = view.findViewById(R.id.btnNext);


        String jsonString = activity.getJsonFromAssets(getActivity(), "states.json");

        Response pojo = new Response();
        Gson gson = new Gson();
        pojo = gson.fromJson(jsonString, Response.class);


        list_states.addAll(pojo.getDetails());

        for (DetailsItem row : list_states) {
            list.add(row.getName());
        }

        if (list.size() > 0) {
            ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spn1.setAdapter(aa);
        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (edtname.getText().toString().trim().equals("")) {
                    edtname.setError("Name");

                }


                if (edtmob.getText().toString().trim().equals("")) {
                    edtmob.setError("Mobile");
                }
                if (edtpin.getText().toString().trim().equals("")) {
                    edtpin.setError("Pincode");
                }


                if (edtads.getText().toString().trim().equals("")) {
                    edtads.setError("Address");
                }

                if (edtarea.getText().toString().trim().equals("")) {
                    edtarea.setError("Area");
                }

                if (edtcity.getText().toString().trim().equals("")) {
                    edtcity.setError("City");
                }


                if (edtname.getText().toString().trim().equals("") || edtmob.getText().toString().trim().equals("") || edtpin.getText().toString().trim().equals("") || edtads.getText().toString().trim().equals("") || edtarea.getText().toString().trim().equals("") || edtcity.getText().toString().trim().equals("")
                        || spn1.getSelectedItem().toString().equals("Select State")
                ) {

                    activity.showSnack_W("Please fill all the details");
                } else {
                    // save to web db

                    btnNext.setEnabled(false);
                    btnNext.startAnimation();


                    Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
                    Call<com.project.zpace.pojos.save_del_ads.Response> call = apiService.save_del_addres(Constants.api_key, Utils.GVCOT(db.getLoginEntityDao().get_customer_id().get(0)), Utils.GVCOT(edtname.getText().toString().trim()), Utils.GVCOT(edtmob.getText().toString().trim()), Utils.GVCOT(edtpin.getText().toString().trim()), Utils.GVCOT(edtads.getText().toString().trim()), Utils.GVCOT(edtarea.getText().toString().trim()), Utils.GVCOT(edtlandmark.getText().toString().trim()), Utils.GVCOT(edtcity.getText().toString().trim()), Utils.GVCOT(spn1.getSelectedItem().toString().trim()));

                    call.enqueue(new Callback<com.project.zpace.pojos.save_del_ads.Response>() {
                        @Override
                        public void onResponse(Call<com.project.zpace.pojos.save_del_ads.Response> call, retrofit2.Response<com.project.zpace.pojos.save_del_ads.Response> response) {


                            if (response.body() != null) {
                                if (response.body().getResult().equals("1")) {
                                    db.getDelAdressEntityDao().del_all();
                                    list_del_ads.addAll(response.body().getDetails());


                                    for (com.project.zpace.pojos.save_del_ads.DetailsItem row : list_del_ads) {
                                        db.getDelAdressEntityDao().insert_single_details(new DeliveryAddressEntity(0, row.getSlno(), db.getLoginEntityDao().get_customer_id().get(0), row.getName(), row.getMob(), row.getPin(), row.getHouse(), row.getArea(), row.getLandmark(), row.getCity(), row.getState()));
                                    }
                                    activity.showSnack_W(response.body().getMessage());
                                    bottomSheet.dismissSheet();


                                } else {
                                    activity.showSnack_W(response.body().getMessage());
                                }

                                btnNext.stopAnimation();
                                btnNext.revertAnimation();
                                btnNext.setEnabled(true);
                                btnNext.setBackgroundResource(R.drawable.button_style_nep);

                            }
                        }

                        @Override
                        public void onFailure(Call<com.project.zpace.pojos.save_del_ads.Response> call, Throwable t) {

                            activity.showSnack_W(getString(R.string.sme_wrg));
                            btnNext.stopAnimation();
                            btnNext.revertAnimation();
                            btnNext.setEnabled(true);
                            btnNext.setBackgroundResource(R.drawable.button_style_nep);

                        }
                    });

                }


            }
        });


    }

    private void save_order_two(String del_slno)
    {
        int send_size = 0;
        int k = -1;
        String whole_stockid = "";
        JSONObject jsonObject, wholejsonObject;
        wholejsonObject = new JSONObject();
        List<CartEntity> list_cart = db.getCartEntityDao().get_all_datas();



        if (!db.getLoginEntityDao().get_customer_id().get(0).toString().trim().equals("")) {
            for (CartEntity row : list_cart) {

                String stockid = row.getStkid();
                int qty = row.getQty();

                int alloted_free_qty = 0;

                BigDecimal amt = new BigDecimal("0.0");

                float sending_rate = 0;
                BigDecimal discount = new BigDecimal("0.0");

                offer_class = new model_offer_calculation(row.getRate(), row.getOffer_price(), row.getBuy_qty(), row.getFree_qty(), row.getFree_percent(), row.getOffer_end_date());

                if(offer_class.get_offer_type().equals("A"))
                {
                    BigDecimal offer_rate_big = new BigDecimal(row.getOffer_price());
                    BigDecimal real_total = offer_rate_big.multiply(new BigDecimal(qty));

                    amt = real_total;
                    alloted_free_qty=0;
                    sending_rate=row.getOffer_price();
                    discount = new BigDecimal("0.0");


                }
                else if(offer_class.get_offer_type().equals("B"))
                {

                    BigDecimal rate_big = new BigDecimal(row.getRate());
                    BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                    amt = real_total;
                    sending_rate=row.getRate();
                    discount = new BigDecimal("0.0");

                    if (qty > row.getBuy_qty() || qty == row.getBuy_qty()) {
                        int no = qty / row.getBuy_qty();
                        alloted_free_qty = no * row.getFree_qty();


                    }
                    else
                    {
                        alloted_free_qty=0;
                    }


                }
                else if(offer_class.get_offer_type().equals("C"))
                {

                    sending_rate=row.getRate();
                    alloted_free_qty=0;
                    if (qty > row.getBuy_qty() || qty == row.getBuy_qty()) {


                        BigDecimal rate_big = new BigDecimal(row.getRate());

                        BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                        BigDecimal temp = real_total.multiply(new BigDecimal(row.getFree_percent()));


                         discount = temp.divide(new BigDecimal("100.0"), 3, RoundingMode.HALF_UP);

                        amt = real_total.subtract(discount);


                    }
                    else
                    {
                        BigDecimal rate_big = new BigDecimal(row.getRate());
                        BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                        amt = real_total;
                        discount = new BigDecimal("0.0");
                    }



                }
                else {

                    BigDecimal rate_big = new BigDecimal(row.getRate());
                    BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                    amt = real_total;



                    sending_rate=row.getRate();
                    alloted_free_qty=0;
                    discount = new BigDecimal("0.0");


                }




//
//                int buy_qty = row.getBuy_qty();
//                int free_qty = row.getFree_qty();
//                float free_percent = row.getFree_percent();
//
//
//
//
//                float offer_price = row.getOffer_price();
//                float rate = row.getRate();
//                String offer_end_date = row.getOffer_end_date();
//
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//                Date offer_end_date_date, today_date_date;
//
//              //  float sending_rate = 0;
//             //   BigDecimal discount = new BigDecimal("0.0");
//
//                try {
//                    offer_end_date_date = sdf.parse(offer_end_date);
//
//                } catch (Exception e) {
//                    offer_end_date_date = null;
//                }
//
//                try {
//
//                    today_date_date = sdf.parse(Constants.getToday_date());
//                } catch (Exception e) {
//                    today_date_date = null;
//                }




                //offer1
//                if (today_date_date != null && offer_end_date_date != null && offer_price > 0 && !offer_end_date.equals("0000-00-00") && !offer_end_date.trim().equals("") && (offer_end_date_date.after(today_date_date) || offer_end_date_date.equals(today_date_date))) {
//                    BigDecimal offer_rate_big = new BigDecimal(offer_price);
//                    BigDecimal real_total = offer_rate_big.multiply(new BigDecimal(qty));
//
//                    amt = real_total;
//
//
//                    sending_rate = offer_price;
//                }
                //offer2
//                else if (buy_qty > 0 && free_qty > 0) {
//
//                    BigDecimal rate_big = new BigDecimal(rate);
//                    BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));
//
//                    amt = real_total;
//                    sending_rate = rate;
//
//                    if (qty > buy_qty || qty == buy_qty) {
//                        int no = qty / buy_qty;
//                        alloted_free_qty = no * free_qty;
//
//
//                    }
//
//
//                }
                //offer3
//                else if (buy_qty > 0 && free_percent > 0) {
//
//
//                    if (qty > buy_qty || qty == buy_qty) {
//
//
//                        BigDecimal rate_big = new BigDecimal(rate);
//
//                        BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));
//
//                        BigDecimal temp = real_total.multiply(new BigDecimal(free_percent));
//                        discount = temp.divide(new BigDecimal("100.0"), 3, RoundingMode.HALF_UP);
//
//                        amt = real_total.subtract(discount);
//                        sending_rate = rate;
//
//
//                    } else {
//                        BigDecimal rate_big = new BigDecimal(rate);
//                        BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));
//
//                        amt = real_total;
//                        sending_rate = rate;
//                    }
//
//                }
                //no offer
//                else {
//                    BigDecimal rate_big = new BigDecimal(rate);
//                    BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));
//
//                    amt = real_total;
//                }


                try {
                    jsonObject = new JSONObject();


                    jsonObject.put("sold_custid", db.getLoginEntityDao().get_customer_id().get(0));
                    jsonObject.put("sold_stockid", stockid);


                    jsonObject.put("sold_rate", "" + sending_rate);
                    jsonObject.put("sold_qty", "" + row.getQty());
                    jsonObject.put("sold_free_qty", "" + alloted_free_qty);


                    jsonObject.put("sold_amt", amt.toPlainString());


                    jsonObject.put("sold_discount", discount.toPlainString());

                    jsonObject.put("sold_delivery_id", del_slno);


                    jsonObject.put("rate", "" + row.getRate());
                    jsonObject.put("offer_price", "" + row.getOffer_price());
                    jsonObject.put("buy_qty", "" + row.getBuy_qty());
                    jsonObject.put("free_qty", "" + row.getFree_qty());
                    jsonObject.put("free_percent", "" + row.getFree_percent());
                    jsonObject.put("offer_end_date", "" + row.getOffer_end_date());


                    send_size = send_size + 1;

                    k = k + 1;

                    wholejsonObject.put("rawdata" + k, jsonObject);


                    if (!whole_stockid.trim().equals("")) {
                        whole_stockid = whole_stockid + ",";
                    }
                    whole_stockid = whole_stockid + stockid;

                } catch (Exception e) {

                }

            }

            if (send_size > 0) {
                List<com.project.zpace.pojos.read_web_rate_and_offers.DetailsItem> list_web_rate_and_offers = new ArrayList<>();
                list_stock_prob_items = new ArrayList<>();
                whole_stockid = "(" + whole_stockid + ")";
                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<com.project.zpace.pojos.read_web_rate_and_offers.Response> call = apiService.save_order(Constants.api_key, wholejsonObject.toString(), send_size, whole_stockid);
                call.enqueue(new Callback<com.project.zpace.pojos.read_web_rate_and_offers.Response>() {
                    @Override
                    public void onResponse(Call<com.project.zpace.pojos.read_web_rate_and_offers.Response> call, retrofit2.Response<com.project.zpace.pojos.read_web_rate_and_offers.Response> response) {

                        if (response.body() != null) {
                            if (response.body().getResult().equals("1")) {

                                // first store order no,



                                //STEP2
                                // OPEN PAYMENT ACTIVITY WITH PASSING NO AND ID AND TOTAL

                                db.getOrderEntityDao().del_all();

                             String order_no=   response.body().getOrder_no();
                             String total=response.body().getTotal();

                             db.getOrderEntityDao().insert_Order_info(new OrderEntity(0,order_no,""));


                             if(db.getOrderEntityDao().get_count()==1  && !db.getOrderEntityDao().get_order_no().get(0).trim().equals(""))
                             {



                                 Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                                 Call<PojomodelRazorId> call1 = apiService.generate_razor_order_id(Constants.api_key, order_no,total, db.getLoginEntityDao().get_customer_id().get(0).toString().trim());

                                 call1.enqueue(new Callback<PojomodelRazorId>() {
                                     @Override
                                     public void onResponse(Call<PojomodelRazorId> call, retrofit2.Response<PojomodelRazorId> response) {
                                         if(response.body() !=null)
                                         {
                                             if(response.body().getResult().equals("1"))
                                             {
                                                 db.getOrderEntityDao().save_razor_order_id(order_no,response.body().getRazor_order_id());

                                                 if(!db.getOrderEntityDao().get_razor_order_id().get(0).equals(""))
                                                 {
                                                     // now jump to payment activity

                                                     Intent in =new Intent(getActivity(), PaymentActivity.class);
                                                     in.putExtra("o",order_no);
                                                     in.putExtra("i",db.getOrderEntityDao().get_razor_order_id().get(0));
                                                     in.putExtra("t",total);

                                                     startActivity(in);

                                                 }


                                             }
                                         }
                                     }

                                     @Override
                                     public void onFailure(Call<PojomodelRazorId> call, Throwable t) {

                                     }
                                 });




                             }

                                activity.showSnack_W(response.body().getMessage());
                            } else if (response.body().getResult().equals("-1")) {
                                //missing datas
                                activity.showSnack_W(response.body().getMessage());
                            } else if (response.body().getResult().equals("100")) {
                                //   activity.showSnack_W(response.body().getMessage());

                                list_web_rate_and_offers.addAll(response.body().getDetails());


                                rcv_cartlist.setAdapter(null);
                                for (com.project.zpace.pojos.read_web_rate_and_offers.DetailsItem row : list_web_rate_and_offers) {
                                    //update cartlist and call the method show cartlist with show total

                                    int count = db.getCartEntityDao().update_new_rate_and_offers(row.getWebStockid(), Utils.GetFloat(row.getWebRate()), Utils.GetFloat(row.getWebOfferPrice()), Utils.GetInt(row.getWebBuyQty()), Utils.GetInt(row.getWebFreeQty()), Utils.GetFloat(row.getWebFreePerc()), row.getWebOfferEnd());

                                }
                                show_cartlist();

                                activity.showSnack_W(response.body().getMessage());


                            } else if (response.body().getResult().equals("200")) {

                                list_stock_prob_items.addAll(response.body().getDetails_no_stock());

                                rcv_cartlist.setAdapter(null);
//                                for (DetailsItem_no_stock row : list_stock_prob_items) {
//                                    //update cartlist and call the method show cartlist with show total
//
//                                    int count = db.getCartEntityDao().update_avb_qty(row.getAvbStockId(), Utils.GetInt(row.getAvbStk()));
//
//                                }
                                show_cartlist();


                                activity.showSnack_W(response.body().getMessage());


                            } else {
                                activity.showSnack_W(response.body().getMessage());
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<com.project.zpace.pojos.read_web_rate_and_offers.Response> call, Throwable t) {

                    }
                });

            }
        } else {
            activity.showSnack_W(getString(R.string.Plz_do_login));
        }








    }


    @Override
    public void dismiss_bottomsheet() {
        bottomSheet.dismissSheet();
    }

    @Override
    public void save_order(String del_slno) {


        bottomSheet.dismissSheet();



        if (!db.getLoginEntityDao().get_customer_id().get(0).toString().trim().equals("")) {

        int count=db.getOrderEntityDao().get_count();



        if(count==0)
        {
            //case1 ie order_no="" and order_id=""

            save_order_two(del_slno);
        }
        else {

            String order_no = "", razor_order_id = "";
            try {
                order_no = db.getOrderEntityDao().get_order_no().get(0).toString().trim();
            } catch (Exception e) {
                order_no = "";
            }


            try {
                razor_order_id = db.getOrderEntityDao().get_razor_order_id().get(0).toString().trim();
            } catch (Exception e) {
                razor_order_id = "";
            }

            //case1
            if (order_no.equals("") && razor_order_id.equals("")) {
                save_order_two(del_slno);
            }
            //case2
            else if (!order_no.equals("") && razor_order_id.equals("")) {
                // need to delete all items in web corresponding to order no
                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<Pojomodelbase> call = apiService.delete_order(Constants.api_key, order_no, db.getLoginEntityDao().get_customer_id().get(0).toString().trim());

                call.enqueue(new Callback<Pojomodelbase>() {
                    @Override
                    public void onResponse(Call<Pojomodelbase> call, retrofit2.Response<Pojomodelbase> response) {

                        if (response.body().getResult().equals("1")) {

                            db.getOrderEntityDao().del_all();
                            save_order_two(del_slno);


                        }

                    }

                    @Override
                    public void onFailure(Call<Pojomodelbase> call, Throwable t) {

                    }
                });


            }
            //case3
            else if (order_no.equals("") && !razor_order_id.equals("")) {
                db.getOrderEntityDao().del_all();
                save_order_two(del_slno);
            }
            else if(!order_no.equals("") && !razor_order_id.equals("") )
            {
                // use php

                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                Call<Pojomodelbase> call = apiService.read_payment(Constants.api_key,order_no, razor_order_id,db.getLoginEntityDao().get_customer_id().get(0).toString().trim());

                call.enqueue(new Callback<Pojomodelbase>() {
                    @Override
                    public void onResponse(Call<Pojomodelbase> call, retrofit2.Response<Pojomodelbase> response) {

                        if (response.body().getResult().equals("1")) {

                            db.getOrderEntityDao().del_all();
                            save_order_two(del_slno);


                        }

                    }

                    @Override
                    public void onFailure(Call<Pojomodelbase> call, Throwable t) {

                    }
                });


            }


        }


        }
        else
        {
            activity.showSnack_W(getString(R.string.Plz_do_login));
        }



        //  Constants.getHomeInterface().do_payment();


    }


    private void show_cartlist() {


        if (Constants.sceen.equals(Constants.cart)) {


            txt1.setText(getString(R.string.del_ads));
            rcv_cartlist.setAdapter(null);


            List<CartEntity> list_cart = db.getCartEntityDao().get_all_datas();


            adp = new AdapterCart(getActivity(), list_cart, list_stock_prob_items);

            LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rcv_cartlist.setLayoutManager(lm);
            rcv_cartlist.setAdapter(adp);

            Constants.setFragment_cart_done_flag(1);

            show_the_total();




        }


//
    }

    @Override
    public void show_the_total() {


        int total_qty = 0;
        int total_alloted_free_qty = 0;
        BigDecimal tot_amt = new BigDecimal("0.0");


        List<CartEntity> list_cart = db.getCartEntityDao().get_all_datas();

        for (CartEntity row : list_cart) {


            int qty = row.getQty();
            int alloted_free_qty = 0;

            BigDecimal amt = new BigDecimal("0.0");


            model_offer_calculation offer_class = new model_offer_calculation(row.getRate(), row.getOffer_price(), row.getBuy_qty(), row.getFree_qty(), row.getFree_percent(), row.getOffer_end_date());
            if (offer_class.get_offer_type().equals("A")) {

                BigDecimal offer_rate_big = new BigDecimal(row.getOffer_price());
                BigDecimal real_total = offer_rate_big.multiply(new BigDecimal(qty));

                amt = real_total;
                alloted_free_qty = 0;


            } else if (offer_class.get_offer_type().equals("B")) {

                BigDecimal rate_big = new BigDecimal(row.getRate());
                BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                amt = real_total;

                if (qty > row.getBuy_qty() || qty == row.getBuy_qty()) {
                    int no = qty / row.getBuy_qty();
                    alloted_free_qty = no * row.getFree_qty();
                } else {
                    alloted_free_qty = 0;
                }


            } else if (offer_class.get_offer_type().equals("C")) {

                if (qty > row.getBuy_qty() || qty == row.getBuy_qty()) {
                    BigDecimal rate_big = new BigDecimal(row.getRate());
                    BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                    BigDecimal temp = real_total.multiply(new BigDecimal(row.getFree_percent()));
                    BigDecimal discount = temp.divide(new BigDecimal("100.0"), 3, RoundingMode.HALF_UP);

                    amt = real_total.subtract(discount);
                    alloted_free_qty = 0;
                } else {
                    BigDecimal rate_big = new BigDecimal(row.getRate());
                    BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                    amt = real_total;
                    alloted_free_qty = 0;
                }


            } else {

                BigDecimal rate_big = new BigDecimal(row.getRate());
                BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                amt = real_total;
                alloted_free_qty = 0;

            }


            total_qty = total_qty + qty;

            tot_amt = tot_amt.add(amt);
            total_alloted_free_qty = total_alloted_free_qty + alloted_free_qty;

         

        }

        txt_tot_no.setText(String.valueOf(total_qty));
        txt_tot_free_no.setText(String.valueOf(total_alloted_free_qty));
        txt_amt.setText(String.valueOf(tot_amt));

        if(total_qty==0)
        {
            card1.setVisibility(View.INVISIBLE);
        }
        else
        {
            card1.setVisibility(View.VISIBLE);
        }



    }

    @Override
    public void refresh_carlist() {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {


            show_cartlist();

        }

    }


}
