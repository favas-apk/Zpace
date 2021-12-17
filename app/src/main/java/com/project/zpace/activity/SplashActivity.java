package com.project.zpace.activity;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.base.BaseActivity;
import com.project.zpace.database.appdb.Appdb;

import com.project.zpace.pojos.read_item_by_group.DetailsItem;
import com.project.zpace.pojos.read_item_by_stkid_cust_app.Response;
import com.project.zpace.viewmodel_item.ViewmodelItem;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static maes.tech.intentanim.CustomIntent.customType;


public class SplashActivity extends BaseActivity {


    String action = "";
    private Appdb db;
    List<DetailsItem> list;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)
        ) {
            finish();
            return;
        }


        db = Appdb.getDb_instance(getApplicationContext());

        Intent intent = getIntent();
        action = intent.getAction();
        //   String type = intent.getType();

        Uri data = intent.getData();

        if (Intent.ACTION_VIEW.equals(action)) {

            read_fname(data.getQueryParameter("data"));


        }


        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                check_perm();

            }

        }, 3000);


    }

    private void read_fname(String stockid) {

        if (!stockid.equals("")) {
            ViewmodelItem viewmodelItem = new ViewmodelItem();
            viewmodelItem.getitem_bystockid(stockid).observe(SplashActivity.this, new Observer<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response>() {
                @Override
                public void onChanged(Response response) {
                    if (!response.getDetails().get(0).getFname().equals("")) {

                        Intent in = new Intent(getApplicationContext(), HomeActivity.class);
                        in.putExtra("Serialized_Details", (Serializable) response.getDetails().get(0));
                        startActivity(in);
                        finish();
                    }
                }
            });


//            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
//
//            Call<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> call = apiService.read_item_by_stkid(Constants.api_key, stockid);
//
//            call.enqueue(new Callback<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response>() {
//                @Override
//                public void onResponse(Call<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> call, retrofit2.Response<com.project.zpace.pojos.read_item_by_stkid_cust_app.Response> response) {
//
//                    if (response.body() != null) {
//                        if (response.body().getResult().equals("1")) {
//                            if (!response.body().getDetails().get(0).getFname().equals("")) {
//
////
//
//                                //list.addAll(response.body().getDetails());
//
//                                Intent in = new Intent(getApplicationContext(), HomeActivity.class);
//                                //     in.putExtra("fname", response.body().getDetails().get(0).getFname());
//                                //     in.putExtra("Stockid", stockid);
//
//                                in.putExtra("Serialized_Details", (Serializable) response.body().getDetails().get(0));
//                                startActivity(in);
//                                finish();
//
//
//                            }
//
//
//                        }
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<Response> call, Throwable t) {
//
//                }
//            });
//
//
        }


    }


    public void check_perm() {


        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) + ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) + ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            //     showSnack_W("app does not have permission now");


            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(
                    SplashActivity.this, Manifest.permission.RECORD_AUDIO)) {


                Request_Perm();
            } else {

                Request_Perm();

            }


        } else {

            goto_next_screen();

//            }

        }


    }


    public void Request_Perm() {


        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setCancelable(false);
        builder.setMessage(" Read and Write External" +
                " Storage permissions are required to do the task.");
        builder.setTitle("Please grant these permissions");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(
                        SplashActivity.this,
                        new String[]{


                                Manifest.permission.WRITE_EXTERNAL_STORAGE,

                                Manifest.permission.READ_EXTERNAL_STORAGE,

                                Manifest.permission.RECORD_AUDIO

                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        });


        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // lmain.setVisibility(View.GONE);
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CODE: {
                // When request is cancelled, the results array are empty
                if (
                        (grantResults.length > 0) &&
                                (grantResults[0] + grantResults[1] + grantResults[2] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permissions are granted

                    showSnack_S(getString(R.string.permission_granted));
                    goto_next_screen();
                } else {

                    //lmain.setVisibility(View.GONE);
                    // Permissions are denied
                    showSnack_E(getString(R.string.permission_denied));
                }
                return;
            }
        }
    }


    private void goto_next_screen() {

        if (Intent.ACTION_VIEW.equals(action)) {

        } else {

//            if(db.getLoginEntityDao().get_count()>0)
//            {


//                Intent in = new Intent(getApplicationContext(), HomeActivity.class);
//            customType(SplashActivity.this,"up-to-bottom");
//                startActivity(in);
//                finish();

            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            customType(SplashActivity.this, "fadein-to-fadeout");
            finish();
            ;
//            }
//            else
//            {
//                Intent in = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(in);
//                finish();
//            }


        }


    }

}


