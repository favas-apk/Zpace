package com.project.zpace.repository_general;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.pojos.offer.Detail;
import com.project.zpace.pojos.offer.PojomodelOffer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MvvmTestActivityRepository  {

    private static final MvvmTestActivityRepository ourInstance = new MvvmTestActivityRepository();

    private MutableLiveData<List<Detail>> mOfferList = new MutableLiveData();


    public static MvvmTestActivityRepository getInstance() {
        return ourInstance;
    }

    public LiveData<List<Detail>> getOfferList() {



        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<PojomodelOffer> call = apiService.read_offer_live();

        call.enqueue(new Callback<PojomodelOffer>() {
            @Override
            public void onResponse(Call<PojomodelOffer> call, Response<PojomodelOffer> response) {



                if (response.body().getResult().equals("1")) {

                    mOfferList=new MutableLiveData<>();
                    mOfferList.setValue(  response.body().getDetails());
                    String a="vfff";



                }

            }

            @Override
            public void onFailure(Call<PojomodelOffer> call, Throwable t) {
                String a="vfff";
            }
        });


        return mOfferList;
    }





}
