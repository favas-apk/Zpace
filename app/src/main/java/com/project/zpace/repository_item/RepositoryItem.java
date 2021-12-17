package com.project.zpace.repository_item;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.project.zpace.Constants;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.pojos.read_item_by_stkid_cust_app.Response;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;

public class RepositoryItem {


    public MutableLiveData<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> getitem_bystockid(String stockid) {

        MutableLiveData<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> liveData = new MutableLiveData<>();


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Response> call = apiService.read_item_by_stkid(Constants.api_key, stockid);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {


                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {

                        liveData.postValue(response.body());


                    }
                }


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                liveData.setValue(null);
            }
        });




        return liveData;


    }


}
