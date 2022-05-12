package com.project.milan.activity;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.project.milan.R;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.apiservice.Endpoint;
import com.project.milan.Constants;

import com.project.milan.Utils;
import com.project.milan.base.BaseActivity;
import com.project.milan.pojos.login.PojomodelSignup;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends BaseActivity {
    private ConstraintLayout clmain;
    private int display_cnt = 0;
    private CardView card1;
    private LinearLayout ll1;
    private br.com.simplepass.loadingbutton.customViews.CircularProgressButton btn1;
    private EditText edtname, edtmob, edtpswd, edtcpswd, edtemail, edtads, edtpin;


    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private boolean email_flag = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupb);



        init();


        edtemail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (edtemail.getText().toString().matches(emailPattern) && s.length() > 0) {
                    email_flag = true;
                    // or
                    //  textView.setText("valid email");
                } else {
                    email_flag = false;

                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideSoftKeyboard(SignupActivity.this, view);

                if (!edtname.getText().toString().equals("") && !edtmob.getText().toString().equals("") && !edtemail.getText().toString().equals("") &&
                        !edtpswd.getText().toString().equals("") &&
                        !edtcpswd.getText().toString().equals("") && !edtads.getText().toString().trim().equals("") && !edtpin.getText().toString().trim().equals("")
                ) {


                    if (edtpswd.getText().toString().length() > 3) {
                        if (edtpswd.getText().toString().equals(edtcpswd.getText().toString())) {

                            if (email_flag) {
                                Save();
                            } else {
                                showSnack_W(getString(R.string.not_valid_email));
                            }


                        } else {
                            showSnack_W(getString(R.string.password_missmatch));
                        }
                    } else {
                        showSnack_W(getString(R.string.all_fields_pswd_minimum));
                    }


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

                                                                                       ObjectAnimator animator = ObjectAnimator.ofFloat(ll1, "translationY", 150f);
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


    private void init() {
        clmain = findViewById(R.id.clmain);
        card1 = findViewById(R.id.card1);
        btn1 = findViewById(R.id.btn1);


        edtname = findViewById(R.id.edtname);
        edtmob = findViewById(R.id.edtmob);
        edtemail = findViewById(R.id.edtemail);


        edtpswd = findViewById(R.id.edtpswd);
        edtcpswd = findViewById(R.id.edtcpswd);

        ll1 = findViewById(R.id.ll1);


        edtads = findViewById(R.id.edtads);
        edtpin = findViewById(R.id.edtpin);


    }

    private void Save() {


//
        btn1.setEnabled(false);
        btn1.startAnimation();
//
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<PojomodelSignup> call = apiService.do_sign_up(Constants.api_key, Utils.GVCOT(edtname.getText().toString().trim()), Utils.GVCOT(edtmob.getText().toString().trim()), Utils.GVCOT(edtemail.getText().toString().trim()), Utils.GVCOT(edtpswd.getText().toString().trim()), Utils.GVCOT(edtads.getText().toString().trim()), Utils.GVCOT(edtpin.getText().toString().trim()));

        call.enqueue(new Callback<PojomodelSignup>() {
            @Override
            public void onResponse(Call<PojomodelSignup> call, Response<PojomodelSignup> response) {


                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {


                        showSnack_S(response.body().getMessage());
                        // db.getUserEntityDao().del_all();
                        //   db.getUserEntityDao().insert_user_details(new UserEntity(0, true, "", "", "", response.body().getUserid()));


                        //  saveImage();




                    } else {
                        showSnack_W(response.body().getMessage());
                        btn1.stopAnimation();
                        btn1.revertAnimation();
                        btn1.setEnabled(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<PojomodelSignup> call, Throwable t) {
                showSnack_E(t.getMessage());
//                btn1.stopAnimation();
//                btn1.revertAnimation();
//                btn1.setEnabled(true);
            }
        });




    }


    private void goto_next() {

//        Intent in = new Intent(getApplicationContext(), HomeActivity.class);
////        startActivity(in);
////        finish();

        showSnack_S("Admin will approve your membership soon ");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}