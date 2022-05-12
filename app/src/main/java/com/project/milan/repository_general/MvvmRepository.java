package com.project.milan.repository_general;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.project.milan.database.appdb.Appdb;
import com.project.milan.database.daos.UserEntityDao;
import com.project.milan.database.entities.UserEntity;

import java.util.List;

public class MvvmRepository {

    private UserEntityDao userEntityDao;

    private LiveData<List<UserEntity>> list_user_entity;


    public MvvmRepository(Application application)
    {
        Appdb db=Appdb.getDb_instance(application);
        userEntityDao=db.getUserEntityDao();
        list_user_entity= userEntityDao.get_all_datas();

    }



    public  void delete(UserEntity userEntity)
    {


    }


    public  void  insert(UserEntity userEntity)
    {
            new Insert(userEntityDao).execute(userEntity);

    }


    public  void  get_count(UserEntity userEntity)
    {


    }




    public LiveData<List<UserEntity>> getUserEntity()
    {

        return list_user_entity;
    }


    private static class Insert extends AsyncTask <UserEntity,Void,Void>{

        private UserEntityDao userEntityDao;
        public  Insert(UserEntityDao userEntityDao) {
            this.userEntityDao = userEntityDao;
        }






        @Override
        protected Void doInBackground(UserEntity... userEntities) {

            userEntityDao.insert_user_details(new UserEntity(0,1));
            return null;
        }
    }






}
