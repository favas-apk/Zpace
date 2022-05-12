package com.project.milan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.apiservice.Endpoint;
import com.project.milan.custom.MyGridView;
import com.project.milan.pojos.show_dash_gridview.DetailsItem;
import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.Utils;
import com.project.milan.activity.HomeActivity;
import com.project.milan.adapter.AdapterSection;
import com.project.milan.adapter.AdapterSectionb;


import com.project.milan.pojos.read_item_by_group.DetailsBraItem;
import com.project.milan.pojos.read_item_by_group.DetailsCatItem;
import com.project.milan.pojos.read_item_by_group.DetailsColItem;
import com.project.milan.pojos.read_item_by_group.DetailsSizItem;
import com.project.milan.pojos.read_item_by_group.Response;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class Fragment_Section extends Fragment {

    private View view;
    private List<com.project.milan.pojos.read_item_by_group.DetailsItem> list = new ArrayList<>();
    private MyGridView gridview_section;
    private String Parcel1 = "", Parcel2 = "", fragment_head = "", Parcel3_Category = "", Parcel3_Sub1 = "", Parcel3_Sub2 = "", Parcel4 = "", Parcel4_slno = "";
    private HomeActivity activity;
    private NestedScrollView scroll_view;

    private int from = 0;
    private final int count = 20;
    private LinearLayout ll_prog;
    private ConstraintLayout cl_fs;
    //  private int size = 0;
    private BottomSheetLayout bottomSheet;
    private TextView txtfilter, txtsort;

    private String order_by_cash = "0";
    private List<String> list_cat, list_brand, list_color, list_size;
    private String cat = "", bra = "", col = "", siz = "";
    private AdapterSection adp;
    List<DetailsItem> list_offer = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sction, container, false);


        try {
            Parcel1 = getArguments().getString(Constants.Parcel1);
        } catch (Exception e) {
            Parcel1 = "";
        }


        try {
            Parcel2 = getArguments().getString(Constants.Parcel2);

            fragment_head = getArguments().getString(Constants.fragment_heading);
            Constants.getHomeInterface().show_heading(fragment_head);
        } catch (Exception e) {
            Parcel2 = "";
        }


        try {

            Parcel3_Category = getArguments().getString(Constants.Parcel3_Category);
            Parcel3_Sub1 = getArguments().getString(Constants.Parcel3_Sub1);
            Parcel3_Sub2 = getArguments().getString(Constants.Parcel3_Sub2);


            if (!Parcel3_Category.trim().equals("")) {
                Parcel3_Category = Utils.GVCOT(Parcel3_Category);
            }

            if (!Parcel3_Sub1.trim().equals("")) {
                Parcel3_Sub1 = Utils.GVCOT(Parcel3_Sub1);
            }

            if (!Parcel3_Sub2.trim().equals("")) {
                Parcel3_Sub2 = Utils.GVCOT(Parcel3_Sub2);
            }

        } catch (Exception e) {
            Parcel3_Category = "";
            Parcel3_Sub1 = "";
            Parcel3_Sub2 = "";
        }


        try {
            Parcel4 = getArguments().getString(Constants.Parcel4);
            Parcel4_slno = getArguments().getString(Constants.slno);
            fragment_head = getArguments().getString(Constants.fragment_heading);
            Constants.getHomeInterface().show_heading(fragment_head);
        } catch (Exception e) {
            Parcel4 = "";
        }


        //     Constants.getHomeInterface().lock_nav_drawer();
        Constants.getHomeInterface().show_heading(fragment_head);

        init();
        activity = (HomeActivity) getActivity();


        if (Parcel1 != null) {
            if (!Parcel1.equals("")) {
                Constants.from = Constants.category;
                show_items();
                //   Constants.getHomeInterface().hide_hamburger();

                //   Constants.getHomeInterface().unselect_all_navigation_items();
                //         Constants.getHomeInterface().show_ham();
                //      Constants.getHomeInterface().show_back();

            }

        } else if (Parcel4 != null) {
            if (!Parcel4.equals("")) {

                cl_fs.setVisibility(View.INVISIBLE);
                Constants.from = Constants.dash;
                //need to check any problem
                show_offer();


                Constants.getHomeInterface().unselect_all_navigation_items();

            }


        } else if (Parcel2 != null) {
            if (!Parcel2.equals("")) {
                Constants.from = Constants.dash;
                show_group_items();


                Constants.getHomeInterface().unselect_all_navigation_items();


            }

        } else if (Parcel3_Category != null) {
            if (!Parcel3_Category.equals("")) {
                Constants.from = Constants.shop_by_category;

                Constants.getHomeInterface().unselect_all_navigation_items();
                Constants.getHomeInterface().select_navigation_item(4);

                show_particular_section();

            }

        }


        scroll_view.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    ll_prog.setVisibility(View.VISIBLE);


                    if (Parcel1 != null) {
                        if (!Parcel1.equals("")) {

                            show_items();
                        }

                    } else if (Parcel2 != null) {
                        if (!Parcel2.equals("")) {

                            show_group_items();


                        }

                    } else if (Parcel3_Category != null) {
                        if (!Parcel3_Category.equals("")) {
                            show_particular_section();
                        }

                    } else if (Parcel4 != null) {
                        if (!Parcel4.equals("")) {
                            show_offer();
                        }

                    }


                }


            }
        });

        txtsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.showWithSheetView(LayoutInflater.from(getActivity()).inflate(R.layout.sheet_sort, bottomSheet, false));


                TextView txtprice_lth = bottomSheet.findViewById(R.id.txtprice_lth);

                TextView txtprice_htl = bottomSheet.findViewById(R.id.txtprice_htl);

                TextView txtprice_none = bottomSheet.findViewById(R.id.txtprice_none);

                txtprice_lth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        order_by_cash = "12";
                        from = 0;
                        list = new ArrayList<>();

                        bottomSheet.dismissSheet();


                        if (Parcel1 != null) {
                            if (!Parcel1.equals("")) {

                                show_items();
                            }

                        } else if (Parcel2 != null) {
                            if (!Parcel2.equals("")) {

                                show_group_items();

                            }

                        } else if (Parcel3_Category != null) {
                            if (!Parcel3_Category.equals("")) {
                                show_particular_section();
                            }

                        }


                    }
                });


                txtprice_htl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        order_by_cash = "21";
                        from = 0;
                        list = new ArrayList<>();
                        bottomSheet.dismissSheet();


                        if (Parcel1 != null) {
                            if (!Parcel1.equals("")) {

                                show_items();
                            }

                        } else if (Parcel2 != null) {
                            if (!Parcel2.equals("")) {

                                show_group_items();

                            }

                        } else if (Parcel3_Category != null) {
                            if (!Parcel3_Category.equals("")) {
                                show_particular_section();
                            }

                        }


                    }
                });


                txtprice_none.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        order_by_cash = "0";
                        from = 0;
                        list = new ArrayList<>();
                        bottomSheet.dismissSheet();


                        if (Parcel1 != null) {
                            if (!Parcel1.equals("")) {

                                show_items();
                            }

                        } else if (Parcel2 != null) {
                            if (!Parcel2.equals("")) {

                                show_group_items();

                            }

                        } else if (Parcel3_Category != null) {
                            if (!Parcel3_Category.equals("")) {
                                show_particular_section();
                            }

                        }


                    }
                });


            }
        });


        txtfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheet.showWithSheetView(LayoutInflater.from(getActivity()).inflate(R.layout.sheet_filter, bottomSheet, false));


                Spinner sp_cat = bottomSheet.findViewById(R.id.sp_cat);
                Spinner sp_brand = bottomSheet.findViewById(R.id.sp_brand);

                Spinner sp_color = bottomSheet.findViewById(R.id.sp_color);
                Spinner sp_size = bottomSheet.findViewById(R.id.sp_size);

                Button btn_apply = bottomSheet.findViewById(R.id.btn_apply);

                ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_cat);
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_cat.setAdapter(aa);

                if (!cat.equals("")) {
                    sp_cat.setSelection(aa.getPosition(cat));
                }


                ArrayAdapter bb = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_brand);
                bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_brand.setAdapter(bb);

                if (!bra.equals("")) {
                    sp_brand.setSelection(bb.getPosition(bra));
                }

//
                ArrayAdapter cc = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_color);
                cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_color.setAdapter(cc);

                if (!col.equals("")) {
                    sp_color.setSelection(cc.getPosition(col));
                }

//
                ArrayAdapter dd = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, list_size);
                dd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sp_size.setAdapter(dd);

                if (!siz.equals("")) {
                    sp_size.setSelection(dd.getPosition(siz));
                }

                btn_apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (!sp_cat.getSelectedItem().toString().equals("")) {
                            cat = sp_cat.getSelectedItem().toString();
                        } else {
                            cat = "";
                        }


                        if (!sp_brand.getSelectedItem().toString().equals("")) {
                            bra = sp_brand.getSelectedItem().toString();
                        } else {
                            col = "";
                        }


                        if (!sp_color.getSelectedItem().toString().equals("")) {
                            col = sp_color.getSelectedItem().toString();
                        } else {
                            col = "";
                        }


                        if (!sp_size.getSelectedItem().toString().equals("")) {
                            siz = sp_size.getSelectedItem().toString();
                        } else {
                            siz = "";
                        }


                        from = 0;
                        list = new ArrayList<>();

                        bottomSheet.dismissSheet();


                        if (Parcel1 != null) {
                            if (!Parcel1.equals("")) {

                                show_items();
                            }

                        } else if (Parcel2 != null) {
                            if (!Parcel2.equals("")) {

                                show_group_items();

                            }

                        } else if (Parcel3_Category != null) {
                            if (!Parcel3_Category.equals("")) {
                                show_particular_section();
                            }

                        } else if (Parcel4 != null) {
                            if (!Parcel4.equals("")) {
                                show_offer();
                            }

                        }


                    }
                });


            }
        });


        return view;

    }


    private void init() {

        gridview_section = view.findViewById(R.id.gridview_section);
        scroll_view = view.findViewById(R.id.scroll_view);
        //  progress_bar = view.findViewById(R.id.progress_bar);
        ll_prog = view.findViewById(R.id.ll_prog);
        bottomSheet = view.findViewById(R.id.bottomSheet);

        txtsort = view.findViewById(R.id.txtsort);
        txtfilter = view.findViewById(R.id.txtfilter);
        cl_fs = view.findViewById(R.id.cl_fs);


    }


    private void show_offer() {

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<com.project.milan.pojos.show_dash_gridview.Response> call = apiService.show_dash_offer(Constants.api_key, Parcel4_slno, Parcel4, "4");

        call.enqueue(new Callback<com.project.milan.pojos.show_dash_gridview.Response>() {
            @Override
            public void onResponse(Call<com.project.milan.pojos.show_dash_gridview.Response> call, retrofit2.Response<com.project.milan.pojos.show_dash_gridview.Response> response) {
                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {
                        from = from + count;
                        ll_prog.setVisibility(View.GONE);
                        list_offer.addAll(response.body().getDetails());

                        AdapterSectionb adp = new AdapterSectionb(getActivity(), list_offer);
                        gridview_section.setAdapter(adp);
                    } else {
                        ll_prog.setVisibility(View.GONE);
                        activity.showSnack_W(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<com.project.milan.pojos.show_dash_gridview.Response> call, Throwable t) {

                ll_prog.setVisibility(View.GONE);
                activity.showSnack_W(t.getLocalizedMessage());
            }
        });

    }

    private void show_items() {


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.read_item_by_category(Constants.api_key, Utils.GVCOT(Parcel1), from, count, order_by_cash, cat, bra, col, siz);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                if (Constants.sceen.equals(Constants.section)) {
                    if (response.body() != null) {
                        if (response.body().getResult().equals("1")) {

                            from = from + count;

                            ll_prog.setVisibility(View.GONE);

                            list.addAll(response.body().getDetails());

                            if (list_cat == null) {
                                list_cat = new ArrayList<>();

                                list_cat.add("All");

                                for (DetailsCatItem row : response.body().getDetailsCat()) {
                                    list_cat.add(row.getCat());
                                }
                            }

                            if (list_brand == null) {
                                list_brand = new ArrayList<>();

                                list_brand.add("All");


                                for (DetailsBraItem row : response.body().getDetailsBra()) {
                                    list_brand.add(row.getBra());
                                }
                            }


                            if (list_color == null) {
                                list_color = new ArrayList<>();

                                list_color.add("All");


                                for (DetailsColItem row : response.body().getDetailsCol()) {
                                    list_color.add(row.getCol());
                                }
                            }


                            if (list_size == null) {
                                list_size = new ArrayList<>();


                                list_size.add("All");


                                for (DetailsSizItem row : response.body().getDetailsSiz()) {
                                    list_size.add(row.getSiz());
                                }
                            }


                            adp = new AdapterSection(getActivity(), list);
                            gridview_section.setAdapter(adp);

//                            if(size !=0)
//                            {
//                                 //Constants.getHomeInterface().show_sub_heading(list.size() + " of " + size);
//                            }


//                            if (size == list.size()) {
//                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                        LinearLayout.LayoutParams.MATCH_PARENT,
//                                        LinearLayout.LayoutParams.WRAP_CONTENT
//                                );
//                                params.setMargins(0, 0, 0, 130);
//
//                                gridview_section.setLayoutParams(params);
//                            }

                            //  showSnack_W(response.body().getMessage());
                        } else {

                            ll_prog.setVisibility(View.GONE);
                            adp = new AdapterSection(getActivity(), list);
                            gridview_section.setAdapter(adp);


                            //    showSnack_W(response.body().getMessage());
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                //    showSnack_W(getString(R.string.sme_wrg));

            }
        });


    }


    private void show_group_items() {


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.read_item_by_group(Constants.api_key, Parcel2, from, count, order_by_cash, cat, bra, col, siz);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                if (Constants.sceen.equals(Constants.section)) {
                    if (response.body() != null) {
                        if (response.body().getResult().equals("1")) {

                            from = from + count;
                            ll_prog.setVisibility(View.GONE);


                            list.addAll(response.body().getDetails());

                            if (list_cat == null) {
                                list_cat = new ArrayList<>();

                                list_cat.add("All");

                                for (DetailsCatItem row : response.body().getDetailsCat()) {
                                    list_cat.add(row.getCat());
                                }
                            }

                            if (list_brand == null) {
                                list_brand = new ArrayList<>();

                                list_brand.add("All");


                                for (DetailsBraItem row : response.body().getDetailsBra()) {
                                    list_brand.add(row.getBra());
                                }
                            }


                            if (list_color == null) {
                                list_color = new ArrayList<>();

                                list_color.add("All");


                                for (DetailsColItem row : response.body().getDetailsCol()) {
                                    list_color.add(row.getCol());
                                }
                            }


                            if (list_size == null) {
                                list_size = new ArrayList<>();


                                list_size.add("All");


                                for (DetailsSizItem row : response.body().getDetailsSiz()) {
                                    list_size.add(row.getSiz());
                                }
                            }


                            adp = new AdapterSection(getActivity(), list);
                            gridview_section.setAdapter(adp);
                            //    Constants.getHomeInterface().show_sub_heading(list.size() + " of " + size);


//                            if (size == list.size()) {
//                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                        LinearLayout.LayoutParams.MATCH_PARENT,
//                                        LinearLayout.LayoutParams.WRAP_CONTENT
//                                );
//                                params.setMargins(0, 0, 0, 130);
//
//                                gridview_section.setLayoutParams(params);
//                            }


                            //  showSnack_W(response.body().getMessage());
                        } else {
                            ll_prog.setVisibility(View.GONE);
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                activity.showSnack_W(getString(R.string.sme_wrg));

            }
        });


    }


    private void show_particular_section() {


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.read_item_by_particular_group(Constants.api_key, Parcel3_Category, Parcel3_Sub1, Parcel3_Sub2, from, count, order_by_cash, bra, col, siz);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {

                        from = from + count;

                        ll_prog.setVisibility(View.GONE);

                        list.addAll(response.body().getDetails());


                        if (list_cat == null) {
                            list_cat = new ArrayList<>();

                            list_cat.add("All");

                            for (DetailsCatItem row : response.body().getDetailsCat()) {
                                list_cat.add(row.getCat());
                            }
                        }

                        if (list_brand == null) {
                            list_brand = new ArrayList<>();

                            list_brand.add("All");


                            for (DetailsBraItem row : response.body().getDetailsBra()) {
                                list_brand.add(row.getBra());
                            }
                        }


                        if (list_color == null) {
                            list_color = new ArrayList<>();

                            list_color.add("All");


                            for (DetailsColItem row : response.body().getDetailsCol()) {
                                list_color.add(row.getCol());
                            }
                        }


                        if (list_size == null) {
                            list_size = new ArrayList<>();


                            list_size.add("All");


                            for (DetailsSizItem row : response.body().getDetailsSiz()) {
                                list_size.add(row.getSiz());
                            }
                        }

                        adp = new AdapterSection(getActivity(), list);
                        gridview_section.setAdapter(adp);
                        //   Constants.getHomeInterface().show_sub_heading(list.size() + " of " + size);


                    } else {
                        ll_prog.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }


}
