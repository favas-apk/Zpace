package com.project.zpace.database.appdb;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.zpace.Constants;
import com.project.zpace.database.daos.CartEntityDao;
import com.project.zpace.database.daos.DashEntityDao;
import com.project.zpace.database.daos.DashGroupEntityDao;
import com.project.zpace.database.daos.DeliverAddressEntityDao;
import com.project.zpace.database.daos.InvEntityDao;
import com.project.zpace.database.daos.LoginEntityDao;
import com.project.zpace.database.daos.OrderEntityDao;
import com.project.zpace.database.daos.SearchEntityDao;
import com.project.zpace.database.daos.UserEntityDao;
import com.project.zpace.database.daos.WishEntityDao;
import com.project.zpace.database.entities.CartEntity;
import com.project.zpace.database.entities.DashEntity;
import com.project.zpace.database.entities.DashGroupEntity;
import com.project.zpace.database.entities.DeliveryAddressEntity;
import com.project.zpace.database.entities.InventoryEntity;
import com.project.zpace.database.entities.LoginEntity;
import com.project.zpace.database.entities.OrderEntity;
import com.project.zpace.database.entities.SearchEntity;
import com.project.zpace.database.entities.UserEntity;
import com.project.zpace.database.entities.WishEntity;

@Database(version = 44,entities = {UserEntity.class, SearchEntity.class, WishEntity.class, LoginEntity.class, DashEntity.class, DashGroupEntity.class, CartEntity.class, InventoryEntity.class, DeliveryAddressEntity.class, OrderEntity.class})
public abstract  class Appdb extends RoomDatabase {


  private static Appdb db;

  public abstract UserEntityDao getUserEntityDao();

  public abstract SearchEntityDao getSearchEntityDao();

  public abstract WishEntityDao getWishEntityDao();

  public abstract LoginEntityDao getLoginEntityDao();


  public abstract DashEntityDao getDashEntityDao();
  public abstract DashGroupEntityDao getDashGroupEntityDao();


  public abstract CartEntityDao getCartEntityDao();

  public abstract InvEntityDao getInvEntityDao();


  public abstract DeliverAddressEntityDao getDelAdressEntityDao();


  public abstract OrderEntityDao getOrderEntityDao();

  public static synchronized Appdb getDb_instance(Context context)
  {

    if(db==null)
    {
      db = Room.databaseBuilder(context.getApplicationContext(),
              Appdb.class, Constants.Database_Name)
              .fallbackToDestructiveMigration()
              .allowMainThreadQueries()
              .build();



    }


    return db;

  }



}
