package com.project.milan.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;


import com.project.milan.apiservice.ApiClient;
import com.project.milan.apiservice.Endpoint;
import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.Utils;
import com.project.milan.activity.HomeActivity;
import com.project.milan.database.appdb.Appdb;
import com.project.milan.database.entities.DeliveryAddressEntity;
import com.project.milan.database.entities.LoginEntity;
import com.project.milan.pojos.base.Pojomodelbase;
import com.project.milan.pojos.login.PojomodelLogin;
import com.project.milan.pojos.login.PojomodelSignup;
import com.project.milan.pojos.save_del_ads.DetailsItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_Profile extends Fragment {

    private Appdb db;
    private HomeActivity activity;
    private View view;
    private de.hdodenhof.circleimageview.CircleImageView iv_profile;
    private com.camerash.toggleedittextview.ToggleEditTextView toggleEditTextView_pincode, toggleEditTextView_name, toggleEditTextView_gender, toggleEditTextView_location, toggleEditTextView_email, toggleEditTextView_mob;
    private com.camerash.toggleedittextview.ToggleEditButton toggleEditbtn;
    private EditText edtusername, edtpswd, edtname, edtpin, edtads, edtpassword, edtconfirmpassword, edtemail, edtmob, edt_otp;
    private br.com.simplepass.loadingbutton.customViews.CircularProgressButton   btn_verify_otp;
    private TextView txtlogin, txtforgot, txtcreate, txtlogin2, txt_timer, txtresendotp;
    private androidx.cardview.widget.CardView card1;
    private ConstraintLayout clmain, clsub, clmain3;
    private LinearLayout llmain1;
    private Button btnedit, btnsave,btn1,btnsignup;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+", generated_otp = "";
    private boolean email_flag = false;
    private boolean otp_flg = false;
private ProgressBar pbar_login,pbar_signup;
    public enum gate {login, signup}

    private CountDownTimer countDownTimer;
    private String custid = "", mob = "", name = "", email = "", pincode = "", address = "";
    public gate open_gate;
    private List<DetailsItem> list_del_ads;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_profileb, container, false);
        setupWindowAnimations();
        init();

        activity = (HomeActivity) getActivity();
        db = Appdb.getDb_instance(getActivity());
        // gate open_gate;
        show_app_screen();


        txtresendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!otp_flg) {
                    otp_flg = true;
                    generate_and_send_otp();
                } else {
                    activity.showSnack_W("Plz wait few seconds");
                }

            }
        });


        btn_verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.hideSoftKeyboard(getActivity(), v);


                if (otp_flg) {


                    if (edt_otp.getText().toString().trim().equals(generated_otp)) {

                        //cancel timer
                        countDownTimer.cancel();
                        //call api


                        if (open_gate == gate.login) {


                            db.getLoginEntityDao().insert_user_details(new LoginEntity(0, custid, mob, name, email, address, pincode));

                            show_app_screen();

                            Constants.getHomeInterface().Show_hide_logout();
                        } else {

                            btn_verify_otp.setEnabled(false);
                            btn_verify_otp.startAnimation();


                            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                            Call<Pojomodelbase> call = apiService.update_status(Constants.api_key, Utils.GVCOT(mob), Utils.GVCOT(custid));
                            call.enqueue(new Callback<Pojomodelbase>() {
                                @Override
                                public void onResponse(Call<Pojomodelbase> call, Response<Pojomodelbase> response) {

                                    if (response.body().getResult().equals("1")) {

                                        btn_verify_otp.stopAnimation();
                                        btn_verify_otp.revertAnimation();
                                        btn_verify_otp.setBackgroundResource(R.drawable.button_style_nep);
                                        btn_verify_otp.setEnabled(true);

                                        db.getUserEntityDao().del_all();


                                        db.getLoginEntityDao().insert_user_details(new LoginEntity(0, custid, mob, name, email, address, pincode));

                                        show_app_screen();

                                        Constants.getHomeInterface().Show_hide_logout();
                                    } else {
                                        btn_verify_otp.stopAnimation();
                                        btn_verify_otp.revertAnimation();
                                        btn_verify_otp.setBackgroundResource(R.drawable.button_style_nep);
                                        btn_verify_otp.setEnabled(true);


                                        show_app_screen();
                                    }


                                }

                                @Override
                                public void onFailure(Call<Pojomodelbase> call, Throwable t) {
                                    show_app_screen();

                                    btn_verify_otp.stopAnimation();
                                    btn_verify_otp.revertAnimation();
                                    btn_verify_otp.setBackgroundResource(R.drawable.button_style_nep);
                                    btn_verify_otp.setEnabled(true);

                                }
                            });

                        }


                    }
                } else {
                    activity.showSnack_W(getString(R.string.otp_exp));
                }


            }
        });


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

                activity.hideSoftKeyboard(getActivity(), view);

                if (!edtusername.getText().toString().equals("") && !edtpswd.getText().toString().equals("")) {


                    login();


                } else {
                    activity.showSnack_W(getString(R.string.all_fields_mandatory));
                }


            }
        });


        txtlogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clsub.setVisibility(View.GONE);
                llmain1.setVisibility(View.GONE);
                clmain.setVisibility(View.VISIBLE);

            }
        });


        txtcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llmain1.setVisibility(View.GONE);
                clmain.setVisibility(View.GONE);
                clsub.setVisibility(View.VISIBLE);
            }
        });


        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!edtname.getText().toString().equals("") && !edtmob.getText().toString().equals("") && !edtemail.getText().toString().equals("") &&
                        !edtpassword.getText().toString().equals("") &&
                        !edtconfirmpassword.getText().toString().equals("") && !edtads.getText().toString().trim().equals("") && !edtpin.getText().toString().trim().equals("")
                ) {
                    if (edtpassword.getText().toString().length() > 3) {

                        if (edtpassword.getText().toString().equals(edtconfirmpassword.getText().toString())) {

                            if (email_flag) {
                                signup();
                            } else {
                                activity.showSnack_W(getString(R.string.not_valid_email));
                            }

                        } else {
                            activity.showSnack_W(getString(R.string.password_missmatch));
                        }

                    } else {
                        activity.showSnack_W(getString(R.string.all_fields_pswd_minimum));
                    }

                } else {
                    activity.showSnack_W(getString(R.string.all_fields_mandatory));
                }


            }
        });


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleEditbtn.setEditing(true, false);


            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEditbtn.setEditing(false, false);


                if (!db.getLoginEntityDao().get_all_datas().get(0).getCustomer_id().equals("") && !toggleEditTextView_name.getText().toString().trim().equals("") && !toggleEditTextView_email.getText().toString().trim().equals("") && !toggleEditTextView_location.getText().toString().trim().equals("") && !toggleEditTextView_pincode.getText().toString().trim().equals("")) {
                    save();
                }


            }
        });


        toggleEditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (toggleEditbtn.getEditing()) {
                    toggleEditbtn.setEditing(true, false);

                    // Toast.makeText(getActivity(),"1",Toast.LENGTH_LONG).show();
                } else {
                    toggleEditbtn.setEditing(false, false);

                    // Toast.makeText(getActivity(),"save",Toast.LENGTH_LONG).show();
                    //call api to save
                    //  save();


                }


            }
        });


        return view;


    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("resume", "resume");

    }

    private void setupWindowAnimations() {
        Fade fade = (Fade) TransitionInflater.from(getActivity()).inflateTransition(R.transition.fade);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setEnterTransition(fade);
        }
    }

    private void init() {

        edtname = view.findViewById(R.id.edtname);
        edtmob = view.findViewById(R.id.edtmob);
        edtemail = view.findViewById(R.id.edtemail);
        edtpassword = view.findViewById(R.id.edtpassword);
        edtconfirmpassword = view.findViewById(R.id.edtconfirmpassword);
        edtads = view.findViewById(R.id.edtads);
        edtpin = view.findViewById(R.id.edtpin);


        btnsignup = view.findViewById(R.id.btnsignup);
        txtcreate = view.findViewById(R.id.txtcreate);
        txtlogin = view.findViewById(R.id.txtlogin);
        txtlogin2 = view.findViewById(R.id.txtlogin2);
        btn1 = view.findViewById(R.id.btn1);
        edtpswd = view.findViewById(R.id.edtpswd);
        edtusername = view.findViewById(R.id.edtusername);
        card1 = view.findViewById(R.id.card1);
        txtforgot = view.findViewById(R.id.txtforgot);
        clmain = view.findViewById(R.id.clmain);

        clmain3 = view.findViewById(R.id.clmain3);
        clsub = view.findViewById(R.id.clsub);
        llmain1 = view.findViewById(R.id.llmain1);
        btnsave = view.findViewById(R.id.btnsave);
        txt_timer = view.findViewById(R.id.txt_timer);
        btn_verify_otp = view.findViewById(R.id.btn_verify_otp);
        edt_otp = view.findViewById(R.id.edt_otp);
        txtresendotp = view.findViewById(R.id.txtresendotp);

        toggleEditbtn = view.findViewById(R.id.toggleEditbtn);

        toggleEditTextView_name = view.findViewById(R.id.toggleEditTextView_name);
        toggleEditTextView_gender = view.findViewById(R.id.toggleEditTextView_gender);
        toggleEditTextView_mob = view.findViewById(R.id.toggleEditTextView_mob);
        toggleEditTextView_email = view.findViewById(R.id.toggleEditTextView_email);
        toggleEditTextView_location = view.findViewById(R.id.toggleEditTextView_location);
        toggleEditTextView_pincode = view.findViewById(R.id.toggleEditTextView_pincode);


        toggleEditbtn.bind(toggleEditTextView_name, toggleEditTextView_pincode, toggleEditTextView_gender, toggleEditTextView_email, toggleEditTextView_location);

        btnedit = view.findViewById(R.id.btnedit);
        pbar_login=view.findViewById(R.id.pbar_login);
        pbar_signup=view.findViewById(R.id.pbar_signup);
    }

    private void login() {
        list_del_ads = new ArrayList<>();

        open_gate = gate.login;
        btn1.setEnabled(false);
      //  btn1.startAnimation();
        btn1.setText("");
        pbar_login.setVisibility(View.VISIBLE);

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<PojomodelLogin> call = apiService.do_login(Constants.api_key, Utils.GVCOT(edtusername.getText().toString()), Utils.GVCOT(edtpswd.getText().toString()));

        call.enqueue(new Callback<PojomodelLogin>() {
            @Override
            public void onResponse(Call<PojomodelLogin> call, Response<PojomodelLogin> response) {

                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {

                        //otp
                        pbar_login.setVisibility(View.GONE);
                        btn1.setText(getString(R.string.login));
                     //   btn1.stopAnimation();
                  //      btn1.revertAnimation();
                        btn1.setEnabled(true);


                  //      btn1.setBackgroundResource(R.drawable.button_style_nep);


                        clmain.setVisibility(View.GONE);
                        llmain1.setVisibility(View.GONE);
                        clsub.setVisibility(View.GONE);
                        clmain3.setVisibility(View.VISIBLE);

                        edt_otp.setText("");

                        // 120000

                        db.getDelAdressEntityDao().del_all();
                        list_del_ads.addAll(response.body().getDetails());
                        for (DetailsItem row : list_del_ads) {
                            db.getDelAdressEntityDao().insert_single_details(new DeliveryAddressEntity(0, row.getSlno(), response.body().getCustomerId(), row.getName(), row.getMob(), row.getPin(), row.getHouse(), row.getArea(), row.getLandmark(), row.getCity(), row.getState()));
                        }
                        custid = response.body().getCustomerId();
                        mob = response.body().getMob();
                        name = response.body().getName();
                        email = response.body().getEmail();
                        pincode = response.body().getPincode();
                        address = response.body().getAddress();


                        if (mob.trim().length() == 10) {

                            otp_flg = true;

                            generate_and_send_otp();


                        } else {
                            activity.showSnack_W(getString(R.string.plz_enter_valid_mob));
                        }


                    } else {
                        activity.showSnack_W(response.body().getMessage());
                        pbar_login.setVisibility(View.GONE);
                        btn1.setText(getString(R.string.login));
                      //  btn1.stopAnimation();
                     //   btn1.revertAnimation();
                        btn1.clearAnimation();
                   //     btn1.setWidth(100);
                     //   btn1.setBackgroundResource(R.drawable.button_style_nep);
                        btn1.setEnabled(true);
                    }
                }

            }

            @Override
            public void onFailure(Call<PojomodelLogin> call, Throwable t) {
                activity.showSnack_W(getString(R.string.sme_wrg));
                pbar_login.setVisibility(View.GONE);
                btn1.setText(getString(R.string.login));
           //     btn1.stopAnimation();
           //     btn1.revertAnimation();


             //   btn1.setBackgroundResource(R.drawable.button_style_nep);
                btn1.setEnabled(true);
            }
        });

    }

    private void save() {

        btnsave.setEnabled(false);
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Pojomodelbase> call = apiService.do_update(Constants.api_key, Utils.GVCOT(toggleEditTextView_name.getText().toString().trim()), Utils.GVCOT(toggleEditTextView_email.getText().toString().trim()), Utils.GVCOT(toggleEditTextView_location.getText().toString().trim()), Utils.GVCOT(toggleEditTextView_pincode.getText().toString().trim()), Utils.GVCOT(db.getLoginEntityDao().get_all_datas().get(0).getCustomer_id()));


        call.enqueue(new Callback<Pojomodelbase>() {
            @Override
            public void onResponse(Call<Pojomodelbase> call, Response<Pojomodelbase> response) {

                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {

                        activity.showSnack_W(response.body().getMessage());


                        db.getLoginEntityDao().update(toggleEditTextView_name.getText().toString().trim(), toggleEditTextView_email.getText().toString().trim(), toggleEditTextView_location.getText().toString().trim(), toggleEditTextView_pincode.getText().toString().trim(), db.getLoginEntityDao().get_all_datas().get(0).getCustomer_id());
                        btnsave.setEnabled(true);

                    } else {
                        activity.showSnack_W(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<Pojomodelbase> call, Throwable t) {
                activity.showSnack_W(getString(R.string.sme_wrg));
                btnsave.setEnabled(true);
            }
        });


    }


    private void signup() {

        open_gate = gate.signup;
        btnsignup.setEnabled(false);
  //      btnsignup.startAnimation();
        btnsignup.setText("");
        pbar_signup.setVisibility(View.VISIBLE);

//
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<PojomodelSignup> call = apiService.do_sign_up(Constants.api_key, Utils.GVCOT(edtname.getText().toString().trim()), Utils.GVCOT(edtmob.getText().toString().trim()), Utils.GVCOT(edtemail.getText().toString().trim()), Utils.GVCOT(edtpassword.getText().toString().trim()), Utils.GVCOT(edtads.getText().toString().trim()), Utils.GVCOT(edtpin.getText().toString().trim()));

        call.enqueue(new Callback<PojomodelSignup>() {
            @Override
            public void onResponse(Call<PojomodelSignup> call, Response<PojomodelSignup> response) {


                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {


                        pbar_signup.setVisibility(View.GONE);
                        btnsignup.setText(getString(R.string.register));
                       // btnsignup.stopAnimation();
                     //   btnsignup.revertAnimation();
                        btnsignup.setEnabled(true);
                 //       btnsignup.setBackgroundResource(R.drawable.button_style_nep);


                        clmain.setVisibility(View.GONE);
                        llmain1.setVisibility(View.GONE);
                        clsub.setVisibility(View.GONE);
                        clmain3.setVisibility(View.VISIBLE);

                        edt_otp.setText("");

                        // 120000


                        custid = response.body().getCustomerId();
                        mob = response.body().getMob();
                        name = response.body().getName();
                        email = response.body().getEmail();
                        pincode = response.body().getPincode();
                        address = response.body().getAddress();


                        if (mob.trim().length() == 10) {

                            otp_flg = true;

                            generate_and_send_otp();


                        } else {
                            activity.showSnack_W(getString(R.string.plz_enter_valid_mob));
                        }


                    } else {
                        activity.showSnack_W(response.body().getMessage());
                     //   btnsignup.stopAnimation();
                    //    btnsignup.revertAnimation();
                        pbar_signup.setVisibility(View.GONE);
                        btnsignup.setText(getString(R.string.register));
                        btnsignup.setEnabled(true);
                 //       btnsignup.setBackgroundResource(R.drawable.button_style_nep);
                    }
                }
            }

            @Override
            public void onFailure(Call<PojomodelSignup> call, Throwable t) {
                activity.showSnack_E(t.getMessage());
       //         btnsignup.stopAnimation();
      //          btnsignup.revertAnimation();
                pbar_signup.setVisibility(View.GONE);
                btnsignup.setText(getString(R.string.register));
                btnsignup.setEnabled(true);
             //   btnsignup.setBackgroundResource(R.drawable.button_style_nep);
            }
        });


    }


    public void show_app_screen() {

        db = Appdb.getDb_instance(getActivity());

        if (db.getLoginEntityDao().get_count() > 0) {
            clmain.setVisibility(View.GONE);
            llmain1.setVisibility(View.VISIBLE);


            toggleEditTextView_name.setText(db.getLoginEntityDao().get_all_datas().get(0).getName());
            toggleEditTextView_mob.setText(db.getLoginEntityDao().get_all_datas().get(0).getMobile());
            toggleEditTextView_email.setText(db.getLoginEntityDao().get_all_datas().get(0).getEmail());
            toggleEditTextView_location.setText(db.getLoginEntityDao().get_all_datas().get(0).getAddress());
            toggleEditTextView_pincode.setText(db.getLoginEntityDao().get_all_datas().get(0).getPincode());


        } else {
            llmain1.setVisibility(View.GONE);
            clmain.setVisibility(View.VISIBLE);

            try {
//                btn1.setEnabled(true);
//                btn1.stopAnimation();
//                btn1.revertAnimation();
//                btn1.setBackgroundResource(R.drawable.button_style_nep);
            }
            catch (Exception e)
            {

            }


        }

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            show_app_screen();
        }


    }


    private void generate_and_send_otp() {

        try {
            countDownTimer.cancel();
        }
        catch (Exception e)
        {

        }
        countDownTimer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                txt_timer.setText("Time remaining: " + minutes + ":" + seconds);
            }

            public void onFinish() {
                txt_timer.setText(getString(R.string.otp_exp));
                otp_flg = false;
            }
        }.start();

        //generate 6 digit otp
        Random randomGenerator = new Random();

        int number = randomGenerator.nextInt(999999);

        generated_otp = String.format("%06d", number);

        activity.showSnack_S("An OTP has been sent your mobile");

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Pojomodelbase> call_sms = apiService.send_sms(Constants.api_key, mob, generated_otp);

        call_sms.enqueue(new Callback<Pojomodelbase>() {
            @Override
            public void onResponse(Call<Pojomodelbase> call, Response<Pojomodelbase> response) {

            }

            @Override
            public void onFailure(Call<Pojomodelbase> call, Throwable t) {

            }
        });


    }

}
