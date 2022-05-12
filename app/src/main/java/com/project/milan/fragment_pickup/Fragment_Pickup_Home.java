package com.project.milan.fragment_pickup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.milan.R;

public class Fragment_Pickup_Home extends Fragment {


    private  View view;
    private Button btn_new_request;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_pickup_home, container, false);

        init();


        btn_new_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Fragment fragment = new tasks();
               // FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_Pickup_New_Request(),"Fragment_Pickup_New_Request").commit();

            }
        });

        return  view;





    }

    private  void init()
    {
        btn_new_request=  view.findViewById(R.id.btn_new_request);
    }
}
