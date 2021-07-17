package com.project.zpace.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.adapter.AdapterCategories;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.interfac.CatInterface;
import com.project.zpace.pojos.read_category_with_random_images.DetailsItem;
import com.project.zpace.pojos.read_category_with_random_images.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class Fragment_Categories extends Fragment implements CatInterface {
    // private GridView gridview;
    private View view;
    private RecyclerView recv;
    private HomeActivity activity;
 //   List<DetailsItem> list;
  //  private AdapterCategories adp;
    private TextView txt_last;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_categories, container, false);

        Constants.setCatInterface(this);

      //  Constants.getHomeInterface().lock_nav_drawer();


;
      //  Constants.getHomeInterface().show_ham();
    //    Constants.getHomeInterface().show_back();




        init();
        activity = (HomeActivity) getActivity();
        Constants.from="";
        read_categories();

        return view;


    }

    @Override
    public void onResume() {
        super.onResume();

     Log.d("category","category");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

      Log.d("category",""+hidden);

      if(!hidden) {
          if (!Constants.getCatInterface().test().equals("ok")) {
              init();
              activity = (HomeActivity) getActivity();
              Constants.from = "";
              read_categories();

           //   onCreateView(getActivity().getLayoutInflater(),getActivity().getWindow().getDecorView().findViewById(android.R.id.content),new Bundle());


          }
      }




    }

    private void init() {
        txt_last=view.findViewById(R.id.txt_last);


        recv = view.findViewById(R.id.recv);

    }


    private void read_categories() {



        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<com.project.zpace.pojos.read_category_with_random_images.Response> call = apiService.read_category1_with_random_images(Constants.api_key);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<com.project.zpace.pojos.read_category_with_random_images.Response> call, retrofit2.Response<com.project.zpace.pojos.read_category_with_random_images.Response> response) {


                if (Constants.sceen.equals(Constants.category)) {
                    if (response.body() != null) {

                        if (response.body().getResult().equals("1")) {
                            List<DetailsItem> list =new ArrayList<>();
                         //   list = new ArrayList<>();
                            // activity.showSnack_W("ok");
                            list.addAll(response.body().getDetails());
                            AdapterCategories           adp = new AdapterCategories(getActivity(), list);

                            LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                            recv.setLayoutManager(lm);

                            try {

                                recv.setAdapter(null);
                                recv.setAdapter(adp);


                                txt_last.setText("ok");
                            }
                            catch (Exception e)
                            {

                            }








                        } else {
                            activity.showSnack_W(response.body().getMessage());
                        }

                    }
                }


            }

            @Override
            public void onFailure(Call<com.project.zpace.pojos.read_category_with_random_images.Response> call, Throwable t) {

                activity.showSnack_W(getString(R.string.sme_wrg));

            }
        });


    }


    @Override
    public String test() {
        return txt_last.getText().toString();
    }
}
