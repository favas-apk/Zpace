package com.project.zpace.viewmodel_general;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project.zpace.database.entities.UserEntity;
import com.project.zpace.repository_general.MvvmRepository;

import java.util.List;

public class MvvmViewModel extends AndroidViewModel {

    private MvvmRepository repository;
    private LiveData<List<UserEntity>> list_user_entity;


    public MvvmViewModel(@NonNull Application application) {
        super(application);
        repository=new MvvmRepository(application);
        list_user_entity=repository.getUserEntity();


    }

    public  void delete(UserEntity userEntity)
    {

        repository.delete(userEntity);

    }


    public  void  insert(UserEntity userEntity)
    {
        repository.insert(userEntity);

    }


    public  void  get_count(UserEntity userEntity)
    {

        repository.get_count(userEntity);

    }

    public LiveData<List<UserEntity>> getUserEntity()
    {

        return list_user_entity;
    }




}
