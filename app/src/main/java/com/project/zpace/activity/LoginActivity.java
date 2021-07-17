package com.project.zpace.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.Utils;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.base.BaseActivity;
import com.project.zpace.database.appdb.Appdb;
import com.project.zpace.database.entities.LoginEntity;
import com.project.zpace.pojos.login.PojomodelLogin;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends BaseActivity {
    private ConstraintLayout clmain;
    private int display_cnt = 0;
    private CardView card1;
    private LinearLayout ll1;
    private br.com.simplepass.loadingbutton.customViews.CircularProgressButton btn1;
    private EditText edtusername, edtpswd;
    private TextView txt_skp, txt_reg;
    private Appdb db;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private boolean email_flag = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = Appdb.getDb_instance(getApplicationContext());

        init();

        txt_skp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                diable();
                Intent in = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(in);
                finish();

            }
        });

        txt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(in);
                finish();

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideSoftKeyboard(LoginActivity.this, view);

                if (!edtusername.getText().toString().equals("") && !edtpswd.getText().toString().equals("")) {


                    login();


                } else {
                    showSnack_W(getString(R.string.all_fields_mandatory));
                }


            }
        });


        clmain.getViewTreeObserver().addOnGlobalLayoutListener(new
                                                                       ViewTreeObserver.OnGlobalLayoutListener() {
                                                                           @Override
                                                                           public void onGlobalLayout() {
                                                                               display_cnt = display_cnt + 1;


                                                                               if (display_cnt > 1) {

                                                                                   Rect r = new Rect();
                                                                                   clmain.getWindowVisibleDisplayFrame(r);
                                                                                   int screenHeight = clmain.getRootView().getHeight();
                                                                                   int keypadHeight = screenHeight - r.bottom;
                                                                                   if (keypadHeight > screenHeight * 0.15) {

                                                                                       ObjectAnimator animator = ObjectAnimator.ofFloat(ll1, "translationY", 250f);
                                                                                       animator.setDuration(2000);
                                                                                       animator.start();

                                                                                       //   Toast.makeText(LoginActivity.this, "Keyboard is showing", Toast.LENGTH_LONG).show();
                                                                                   } else {


                                                                                       ObjectAnimator animator = ObjectAnimator.ofFloat(ll1, "translationY", 650f);
                                                                                       animator.setDuration(2000);
                                                                                       animator.start();


                                                                                       //Toast.makeText(LoginActivity.this, "keyboard closed"+ display_cnt, Toast.LENGTH_LONG).show();
                                                                                   }

                                                                               }
                                                                           }

                                                                       });


    }

    @Override
    protected void onResume() {
        super.onResume();
        enable();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    private void init() {
        clmain = findViewById(R.id.clmain);
        card1 = findViewById(R.id.card1);
        btn1 = findViewById(R.id.btn1);


        edtpswd = findViewById(R.id.edtpswd);
        edtusername = findViewById(R.id.edtusername);

        ll1 = findViewById(R.id.ll1);

        txt_skp = findViewById(R.id.txt_skp);
        txt_reg = findViewById(R.id.txt_reg);
    }


    private void login() {

        btn1.setEnabled(false);
        btn1.startAnimation();


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<PojomodelLogin> call = apiService.do_login(Constants.api_key, Utils.GVCOT(edtusername.getText().toString()), Utils.GVCOT(edtpswd.getText().toString()));

        call.enqueue(new Callback<PojomodelLogin>() {
            @Override
            public void onResponse(Call<PojomodelLogin> call, Response<PojomodelLogin> response) {

                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {
                        db.getLoginEntityDao().del_all();

                        db.getLoginEntityDao().insert_user_details(new LoginEntity(0, response.body().getCustomerId(),response.body().getMob(),response.body().getName(),response.body().getEmail(),response.body().getAddress(),response.body().getPincode()));
                        Intent in = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(in);
                        finish();

                    }
                    else
                    {
                        showSnack_W(response.body().getMessage());
                        btn1.stopAnimation();
                        btn1.revertAnimation();
                        btn1.setEnabled(true);
                    }
                }

            }

            @Override
            public void onFailure(Call<PojomodelLogin> call, Throwable t) {
                showSnack_W(getString(R.string.sme_wrg));
                btn1.stopAnimation();
                btn1.revertAnimation();
                btn1.setEnabled(true);
            }
        });


    }


    private void diable() {

        txt_skp.setEnabled(false);

    }

    private void enable() {

        txt_skp.setEnabled(true);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}