package com.project.milan.fragment;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.adapter.AdapterZoom;
import com.project.milan.adapter.VPagerAdapter;
import com.project.milan.apiservice.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Zoom_Page extends Fragment {
    private View view;
    private List<String> list_pics = new ArrayList<>();
    private JsonObject json_pics = null;
    private RecyclerView recv;
    private AdapterZoom adp;
    private VPagerAdapter adp2;
    private List<View> list_views=new ArrayList<>();
    private ViewPager viewPager1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zoom_page, container, false);
        Constants.sceen="zoom";

        init();


        try {

            List<String> list_pics = (List<String>) getArguments().getSerializable("pics");
            Log.d("list_img", "" + new Gson().toJson(list_pics));





          //  adp=new AdapterZoom(getActivity(),list_pics,list_views);

         //   LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        //    recv.setLayoutManager(lm);


       //     recv.setAdapter(adp);
            adp2=new VPagerAdapter(getActivity(),list_pics);
            viewPager1.setAdapter(adp2);



        } catch (Exception e) {
            list_pics = new ArrayList<>();
        }


        return view;

    }

    private void  init()
    {
        recv=view.findViewById(R.id.recv);
        viewPager1=view.findViewById(R.id.viewPager1);


    }



}
