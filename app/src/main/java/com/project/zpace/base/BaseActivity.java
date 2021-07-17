package com.project.zpace.base;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.interfac.WishlistInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class BaseActivity extends AppCompatActivity  {

    private static WishlistInterface WishInter=null;

    public static WishlistInterface getWishInter() {
        return WishInter;
    }

    public static void setWishInter(WishlistInterface wishInter) {
        WishInter = wishInter;
    }








    public  void hide_fragment_except(String tag)
    {

        FragmentManager fm=getSupportFragmentManager();
        if(fm.findFragmentByTag("Fragment_Dashboard") != null  && !tag.equals("Fragment_Dashboard"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Dashboard"))) .commit();
        }
        if(fm.findFragmentByTag("Fragment_Categories") != null   && !tag.equals("Fragment_Categories"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Categories"))) .commit();
        }
        if(fm.findFragmentByTag("Fragment_Profile") != null && !tag.equals("Fragment_Profile"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Profile"))) .commit();
        }



        if(fm.findFragmentByTag("Fragment_Orders") != null && !tag.equals("Fragment_Orders"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Orders"))) .commit();
        }

        if(fm.findFragmentByTag("Fragment_Cart") != null && !tag.equals("Fragment_Cart"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Cart"))) .commit();
        }

        if(fm.findFragmentByTag("Fragment_Wishlist") != null && !tag.equals("Fragment_Wishlist") )
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Wishlist"))) .commit();
        }


        if(fm.findFragmentByTag("Fragment_Section") != null && !tag.equals("Fragment_Section"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Section"))) .commit();
        }

        if(fm.findFragmentByTag("Fragment_Searchlist") != null && !tag.equals("Fragment_Searchlist"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Searchlist"))) .commit();
        }


        if(fm.findFragmentByTag("Fragment_Single_View") != null && !tag.equals("Fragment_Single_View"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Single_View"))) .commit();
        }

        if(fm.findFragmentByTag("Fragment_Shop_By_Category") != null && !tag.equals("Fragment_Shop_By_Category"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_Shop_By_Category"))) .commit();
        }


        if(fm.findFragmentByTag("Fragment_SearchBefore") != null && !tag.equals("Fragment_SearchBefore"))
        {
            fm.beginTransaction().hide(Objects.requireNonNull(fm.findFragmentByTag("Fragment_SearchBefore"))) .commit();
        }




    }




    public void showSnack_E(String message)
    {
        Snackbar.with(BaseActivity.this,null)
                .type(Type.ERROR)
                .message(message)
                .duration(Duration.LONG)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();


    }



    public void showSnack_S(String message)
    {
        Snackbar.with(BaseActivity.this,null)
                .type(Type.SUCCESS)
                .message(message)
                .duration(Duration.LONG)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();


    }

    public void showSnack_W(String message)
    {
        Snackbar.with(BaseActivity.this,null)
                .type(Type.WARNING)
                .message(message)
                .duration(Duration.LONG)
                .fillParent(true)
                .textAlign(Align.LEFT)
                .show();


    }





    public static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }



    public   static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }


}
