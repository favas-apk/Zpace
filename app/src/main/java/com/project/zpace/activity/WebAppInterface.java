package com.project.zpace.activity;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.project.zpace.Constants;
import com.project.zpace.database.appdb.Appdb;

public class WebAppInterface {

    Context mContext;
    String data;
    private Appdb db;

    WebAppInterface(Context ctx){
        this.mContext=ctx;

        db = Appdb.getDb_instance(this.mContext);
    }


    @JavascriptInterface
    public void showToast(String toast) {

        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
       // if(toast.equals("1gc02ec014"))
     //   {
            Constants.getPaymentActivityInterface().jump_to_home_activity();

      //  }


    }



}
