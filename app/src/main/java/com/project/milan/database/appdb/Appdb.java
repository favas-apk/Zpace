package com.project.milan.database.appdb;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.project.milan.database.entities.CartEntity;
import com.project.milan.database.entities.DashEntity;
import com.project.milan.database.entities.DashGroupEntity;
import com.project.milan.database.entities.InventoryEntity;
import com.project.milan.database.entities.LoginEntity;
import com.project.milan.database.entities.OrderEntity;
import com.project.milan.database.entities.SearchEntity;
import com.project.milan.database.entities.UserEntity;
import com.project.milan.database.entities.WishEntity;
import com.project.milan.Constants;
import com.project.milan.database.daos.CartEntityDao;
import com.project.milan.database.daos.DashEntityDao;
import com.project.milan.database.daos.DashGroupEntityDao;
import com.project.milan.database.daos.DeliverAddressEntityDao;
import com.project.milan.database.daos.InvEntityDao;
import com.project.milan.database.daos.LoginEntityDao;
import com.project.milan.database.daos.OrderEntityDao;
import com.project.milan.database.daos.SearchEntityDao;
import com.project.milan.database.daos.UserEntityDao;
import com.project.milan.database.daos.WishEntityDao;
import com.project.milan.database.entities.DeliveryAddressEntity;

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
