package com.project.milan.fragment;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.milan.Constants;
import com.project.milan.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Zoom_Page extends Fragment {
    private View view;
    private List<String> list_pics = new ArrayList<>();
private JsonObject json_pics=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zoom_page, container, false);


        try {

          List<String> list_pics =(List<String>) getArguments().getSerializable("pics");
          Log.d("list",""+new Gson().toJson(list_pics));


        } catch (Exception e) {
            list_pics=new ArrayList<>();
        }


        return  view;

    }
}
