package com.project.milan.fragment_pickup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.milan.R;

public class Fragment_Pickup_Pay_Vehicle_confirm extends Fragment {


    private  View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_pickup_pay_vehicle_confirm, container, false);

        init();




        return  view;





    }



    private  void init()
    {


    }
}
