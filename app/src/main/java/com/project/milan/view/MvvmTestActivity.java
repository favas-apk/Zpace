package com.project.milan.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.project.milan.pojos.offer.Detail;
import com.project.milan.viewmodel_general.MvvmTestActivityViewmodel;
import com.project.milan.R;

import java.util.List;

public class MvvmTestActivity  extends AppCompatActivity {

private MvvmTestActivityViewmodel viewmodel;
   // private Object MvvmTestActivityViewmodel;


    private Button btn1;
    private RecyclerView rv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_test_activity);

        viewmodel= new ViewModelProvider(MvvmTestActivity.this).get(MvvmTestActivityViewmodel.class);

        init();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

         //     viewmodel= MvvmTestActivityViewmodel();
               viewmodel.getListLiveData();

            }
        });




        viewmodel.getListLiveData().observe(this, new Observer<List<Detail>>() {
            @Override
            public void onChanged(List<Detail> list) {

                setRecycleView(list);
            }
        });



    }


    private void init()
    {

        btn1=findViewById(R.id.btn1);
        rv1=findViewById(R.id.rv1);



    }


    private void setRecycleView(List<Detail> list)
    {


    }


}
