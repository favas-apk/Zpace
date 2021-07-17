package com.project.zpace.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.zpace.pojos.offer.Detail;
import com.project.zpace.repository.MvvmTestActivityRepository;

import java.util.List;

public class MvvmTestActivityViewmodel extends ViewModel {
    private MvvmTestActivityRepository repository = MvvmTestActivityRepository.getInstance();
    private LiveData<List<Detail>> ListLiveData;


    public MvvmTestActivityViewmodel() {
        super();
    //    ListLiveData=repository.getOfferList();

    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }



    public  LiveData<List<Detail>> getListLiveData()
    {
        ListLiveData=repository.getOfferList();
        return ListLiveData;
    }


}
