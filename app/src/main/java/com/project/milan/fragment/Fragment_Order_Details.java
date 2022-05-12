package com.project.milan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.milan.R;
import com.project.milan.adapter.AdapterOrderDetails;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Order_Details extends Fragment {

    private View view;
    private  androidx.recyclerview.widget.RecyclerView rcv_track;
    private List<String> list_track;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {





        view= inflater.inflate(R.layout.fragment_order_details,container,false);

       init();
        show_track();




        rcv_track.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //   Toast.makeText(getApplicationContext(),"not allowed",Toast.LENGTH_LONG).show();
                return true;
            }
        });





      return view;




    }





    @Override
    public void onResume() {
        super.onResume();

    }

    private void init()
    {

        rcv_track=view.findViewById(R.id.rcv_track);

    }






    private void show_track() {

        list_track = new ArrayList<>();

        list_track.add("Pickup Requested");
        list_track.add("Order Packed");
        list_track.add("Order Dispatched");

        list_track.add("Parcel Delivered");

        AdapterOrderDetails adp = new AdapterOrderDetails(getActivity(),  list_track);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv_track.setLayoutManager(lm);
        rcv_track.setAdapter(adp);



//
    }





}
