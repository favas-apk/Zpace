package com.project.zpace.fragment_pickup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.zpace.R;

public class Fragment_Pickup_Receiver_details extends Fragment {


    private  View view;
    private  Button  btn_done;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_pickup_receiver_details, container, false);

        init();

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_Pickup_Pay_Vehicle(),"Fragment_Pickup_Pay_Vehicle").commit();

            }
        });




        return  view;





    }



    private  void init()
    {
        btn_done=view.findViewById(R.id.btn_done);

    }
}
