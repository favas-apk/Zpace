package com.project.zpace.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.Utils;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.adapter.AdapterSearchlist;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.pojos.read_item_by_group.DetailsItem;
import com.project.zpace.pojos.read_item_by_group.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class Fragment_Searchlist extends Fragment {

    private View view;
    private com.project.zpace.custom.MyGridView grid_searchlist;


    private AdapterSearchlist adp;

    private List<DetailsItem> list=new ArrayList<>();
//    private String Parcel2 = "", fragment_head = "";
    private int from = 0, count = 20;
   // private int size = 0;
    private LinearLayout ll_prog;
    private NestedScrollView scroll_view;

    private HomeActivity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_searchlist, container, false);

//        try {
//            Parcel2 = getArguments().getString(Constants.Parcel2);
//
//            fragment_head = getArguments().getString(Constants.fragment_heading);
//            Constants.getHomeInterface().show_heading(fragment_head);
//        } catch (Exception e) {
//            Parcel2 = "";
//        }




        Constants.getHomeInterface().show_heading(Constants.getSearch_word());

        init();
        activity = (HomeActivity) getActivity();


        show_searchlist();


        scroll_view.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY== (  v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight() )  )
                {
                    ll_prog.setVisibility(View.VISIBLE);


                    show_searchlist();


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
        grid_searchlist = view.findViewById(R.id.grid_searchlist);
        ll_prog = view.findViewById(R.id.ll_prog);
        scroll_view = view.findViewById(R.id.scroll_view);

    }


    private void show_searchlist() {




        if (!Constants.getSearch_word().equals("")) {
           String search_word = "%" + Constants.getSearch_word() + "%";

            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

            Call<com.project.zpace.pojos.read_item_by_group.Response> call = apiService.read_item_by_itemname(Constants.api_key, Utils.GVCOT(search_word), from, count);




            call.enqueue(new Callback<com.project.zpace.pojos.read_item_by_group.Response>() {
                @Override
                public void onResponse(Call<com.project.zpace.pojos.read_item_by_group.Response> call, retrofit2.Response<com.project.zpace.pojos.read_item_by_group.Response> response) {

                    if (Constants.sceen.equals(Constants.search_result)) {
                        if (response.body() != null) {
                            if (response.body().getResult().equals("1")) {

                                from = from + count;
//                                if(response.body().getSize()!=0)
//                                {
//                                    size = response.body().getSize();
//                                }

                                ll_prog.setVisibility(View.GONE);
                                list.addAll(response.body().getDetails());

                                adp = new AdapterSearchlist(getActivity(), list);

                                grid_searchlist.setAdapter(adp);
                              //  Constants.getHomeInterface().show_sub_heading(list.size()+" of "+size);

//                                if(size ==list.size())
//                                {
//                                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                            LinearLayout.LayoutParams.MATCH_PARENT,
//                                            LinearLayout.LayoutParams.WRAP_CONTENT
//                                    );
//                                    params.setMargins(0, 0, 0, 130);
//
//                                    grid_searchlist.setLayoutParams(params);
//                                }

                            }
                            else
                            {
                                ll_prog.setVisibility(View.GONE);
                            }
                        }
                    }


                }

                @Override
                public void onFailure(Call<com.project.zpace.pojos.read_item_by_group.Response> call, Throwable t) {
                    ll_prog.setVisibility(View.GONE);
                }
            });

        }





//
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(!hidden) {

            Constants.getHomeInterface().show_heading(Constants.getSearch_word());

            init();
            activity = (HomeActivity) getActivity();

            list=new ArrayList<>();
            grid_searchlist.setAdapter(null);
            from=0;
            show_searchlist();

        }

    }
}
