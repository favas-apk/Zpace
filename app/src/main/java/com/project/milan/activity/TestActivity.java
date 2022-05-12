package com.project.milan.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.milan.R;


public class TestActivity  extends AppCompatActivity {


    private Button btn1;
    private WebView webview1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        init();

        webview1.loadUrl("https://aims.tvs.in/qr_asset?id=2286158");

//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
//                Call<Response> call = apiService.read_customer("auto");
//
//                call.enqueue(new Callback<Response>() {
//                    @Override
//                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                        Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Response> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
//                    }
//                });



//
//            }
//        });



    }



    private void init()
    {
       webview1=findViewById(R.id.webview1);

    }



}
