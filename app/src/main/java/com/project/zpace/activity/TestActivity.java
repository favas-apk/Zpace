package com.project.zpace.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.pojos.base.Pojomodelbase;
import com.project.zpace.pojos.pojo_Ptest.Response;

import retrofit2.Call;
import retrofit2.Callback;

public class TestActivity  extends AppCompatActivity {


    private Button btn1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
                Call<Response> call = apiService.read_customer("auto");

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });



    }



    void init()
    {
       btn1=findViewById(R.id.btn1);
    }



}
