package com.project.milan.repository_item;

import androidx.lifecycle.MutableLiveData;

import com.project.milan.apiservice.ApiClient;
import com.project.milan.apiservice.Endpoint;
import com.project.milan.pojos.read_item_by_stkid_cust_app.Response;
import com.project.milan.Constants;

import retrofit2.Call;
import retrofit2.Callback;

public class RepositoryItem {


    public MutableLiveData<Response> getitem_bystockid(String stockid) {

        MutableLiveData<Response> liveData = new MutableLiveData<>();


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
