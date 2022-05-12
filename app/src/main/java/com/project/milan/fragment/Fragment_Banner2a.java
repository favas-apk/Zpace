package com.project.milan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.milan.R;


public class Fragment_Banner2a extends Fragment {
   // private GridView gridview;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {





        view= inflater.inflate(R.layout.model_view_pager,container,false);

       init();

    //   read();

      return view;




    }

    @Override
    public void onResume() {
        super.onResume();
      //  read();
    }

    private void init()
    {
        //gridview=view.findViewById(R.id.gridview);
    }




}
