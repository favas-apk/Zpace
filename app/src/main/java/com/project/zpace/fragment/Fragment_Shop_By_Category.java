package com.project.zpace.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.Utils;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.adapter.AdapterCart;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.custom.My_TextView_M;
import com.project.zpace.pojos.kids.Detail;
import com.project.zpace.pojos.read_category.DetailsItem;
import com.project.zpace.pojos.read_category.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class Fragment_Shop_By_Category extends Fragment {

    private View view;

    private List<DetailsItem> list;
    private HomeActivity activity;

    private LinearLayout mainLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_shop_by_category, container, false);

        init();
        activity = (HomeActivity) getActivity();

        show2();


        return view;


    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private void init() {

        mainLayout = view.findViewById(R.id.mainLayout);
    }


    private void show2() {
        list = new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.zpace.pojos.read_category.Response> call = apiService.read_category1(Constants.api_key);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.body() != null) {

                    if (response.body().getResult().equals("1")) {

                        list.addAll(response.body().getDetails());

                        for (DetailsItem row : list) {
                            LinearLayout layout_cat = (LinearLayout) View.inflate(
                                    getActivity(),
                                    R.layout.first_row_layout, null);

                            mainLayout.addView(layout_cat);

                            TextView txtcat = layout_cat.findViewById(R.id.txtcat);
                            ProgressBar pbarcat = layout_cat.findViewById(R.id.pbarcat);
                            ImageView ivcat = layout_cat.findViewById(R.id.ivcat);

                            txtcat.setText(row.getCategory());

                            txtcat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    goto_section(txtcat.getText().toString(), "", "");


                                }
                            });

                            ivcat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                    if (layout_cat.getChildCount() > 1) {

                                        int count = layout_cat.getChildCount();
                                        for (int i = count - 1; i > 0; i--) {


                                            View view1 = layout_cat.getChildAt(i);


                                            ((ViewGroup) layout_cat).removeView(view1);


                                        }
                                        ivcat.setImageResource(R.drawable.ic_add_square);


                                    } else {


                                        pbarcat.setVisibility(View.VISIBLE);

                                        ivcat.setEnabled(false);
                                        List<DetailsItem> list_sub1 = new ArrayList<>();

                                        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                                        Call<com.project.zpace.pojos.read_category.Response> call = apiService.read_sub1(Constants.api_key, Utils.GVCOT(txtcat.getText().toString()));
                                        call.enqueue(new Callback<Response>() {
                                            @Override
                                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                                                if (response.body() != null) {
                                                    if (response.body().getResult().equals("1")) {
                                                        list_sub1.addAll(response.body().getDetails());

                                                        for (DetailsItem row_sub1 : list_sub1) {

                                                            LinearLayout layout_sub1 = (LinearLayout) View.inflate(
                                                                    getActivity(),
                                                                    R.layout.second_row_layout, null);

                                                            layout_cat.addView(layout_sub1);

                                                            TextView txtsub1 = layout_sub1.findViewById(R.id.txtsub1);
                                                            ProgressBar pbarsub1 = layout_sub1.findViewById(R.id.pbarsub1);
                                                            ImageView ivsub1 = layout_sub1.findViewById(R.id.ivsub1);

                                                            txtsub1.setText(row_sub1.getCategory());

                                                            txtsub1.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {
                                                                    goto_section(txtcat.getText().toString(), txtsub1.getText().toString(), "");
                                                                }
                                                            });

                                                            ivsub1.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {


                                                                    if (layout_sub1.getChildCount() > 1) {

                                                                        int count = layout_sub1.getChildCount();
                                                                        for (int i = count - 1; i > 0; i--) {


                                                                            View view1 = layout_sub1.getChildAt(i);


                                                                            ((ViewGroup) layout_sub1).removeView(view1);


                                                                        }
                                                                        ivsub1.setImageResource(R.drawable.ic_add_square);


                                                                    } else {


                                                                        ivsub1.setEnabled(false);
                                                                        pbarsub1.setVisibility(View.VISIBLE);
                                                                        List<DetailsItem> list_sub2 = new ArrayList<>();
                                                                        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

                                                                        Call<com.project.zpace.pojos.read_category.Response> call = apiService.read_sub2(Constants.api_key, Utils.GVCOT(txtcat.getText().toString()), Utils.GVCOT(txtsub1.getText().toString()));

                                                                        call.enqueue(new Callback<Response>() {
                                                                            @Override
                                                                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                                                                                if (response.body() != null) {
                                                                                    if (response.body().getResult().equals("1")) {

                                                                                        list_sub2.addAll(response.body().getDetails());

                                                                                        for (DetailsItem row_sub2 : list_sub2) {

                                                                                            LinearLayout layout_sub2 = (LinearLayout) View.inflate(
                                                                                                    getActivity(),
                                                                                                    R.layout.third_row_layout, null);

                                                                                            layout_sub1.addView(layout_sub2);

                                                                                            TextView txtsub2 = layout_sub1.findViewById(R.id.txtsub2);

                                                                                            txtsub2.setText(row_sub2.getCategory());

                                                                                            txtsub2.setOnClickListener(new View.OnClickListener() {
                                                                                                @Override
                                                                                                public void onClick(View v) {
                                                                                                    goto_section(txtcat.getText().toString(), txtsub1.getText().toString(), txtsub2.getText().toString());
                                                                                                }
                                                                                            });


                                                                                        }
                                                                                        ivsub1.setImageResource(R.drawable.ic_minus_square);

                                                                                    }
                                                                                }

                                                                                pbarsub1.setVisibility(View.INVISIBLE);
                                                                                ivsub1.setEnabled(true);
                                                                            }

                                                                            @Override
                                                                            public void onFailure(Call<Response> call, Throwable t) {
                                                                                pbarsub1.setVisibility(View.INVISIBLE);
                                                                                ivsub1.setEnabled(true);

                                                                            }
                                                                        });
                                                                    }

                                                                }
                                                            });


                                                        }
                                                        ivcat.setImageResource(R.drawable.ic_minus_square);


                                                    }
                                                }

                                                pbarcat.setVisibility(View.INVISIBLE);
                                                ivcat.setEnabled(true);
                                            }

                                            @Override
                                            public void onFailure(Call<Response> call, Throwable t) {
                                                pbarcat.setVisibility(View.INVISIBLE);
                                                ivcat.setEnabled(true);

                                            }
                                        });

                                    }

                                }
                            });


                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });


    }


//    private void show() {
//
//        list = new ArrayList<>();
//        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
//
//        Call<com.project.zpace.pojos.read_category.Response> call = apiService.read_category1(Constants.api_key);
//
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//
//                if (response.body() != null) {
//                    if (response.body().getResult().equals("1")) {
//                        list.addAll(response.body().getDetails());
//
//                        for (DetailsItem row : list) {
//
//                            LinearLayout layout_m1 = new LinearLayout(getActivity());
//                            layout_m1.setOrientation(LinearLayout.VERTICAL);
//
//                            LinearLayout layout1 = new LinearLayout(getActivity());
//
//                            layout1.setLayoutParams(new LinearLayout.LayoutParams(800,
//                                    100));
//
//                            layout1.setOrientation(LinearLayout.HORIZONTAL);
//
//                            layout1.setClickable(true);
//
//
//                            ImageView iv1 = new ImageView(getActivity());
//
//
//                            //  iv1.setLayoutParams(new GridView.LayoutParams(100,100 ));
//
//                            //     iv1.setBackgroundColor(getActivity().getResources().getColor(R.color.color3));
//                            iv1.setBackground(getActivity().getResources().getDrawable(R.drawable.plus_round));
//
////iv1.setTextColor(Color.BLACK);
//
//                            // iv1.setBackgroundResource(R.drawable.ic_plus);
////
//
////                            LinearLayout.LayoutParams params_iv1 = new LinearLayout.LayoutParams(  ViewGroup.LayoutParams.WRAP_CONTENT,
////                                    ViewGroup.LayoutParams.MATCH_PARENT);
////
////                            params_iv1.gravity = Gravity.CENTER_VERTICAL;
////
////                            iv1.setLayoutParams(Gravity.CENTER);
//
//
////                            LinearLayout.LayoutParams layoutParamszz=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
////                            layoutParamszz.gravity=Gravity.CENTER;
////                            iv1.setLayoutParams(layoutParamszz);
//                            //                   iv1.setTextSize(5);
//
//                            //                    iv1.setText("aaaa");
//
//                            //   iv1.setPadding(1,10,0,0);
////                            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
////                                    50, 50);
////                            param.weight = 1.0f;
////                            param.gravity = Gravity.CENTER;
////
////
////
////                            iv1.setLayoutParams(param);
//
//
//                            layout1.addView(iv1);
//
//
//                            My_TextView_M tv = new My_TextView_M(getActivity());
//                            //   tv.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100));
//
//
//                            tv.setText(row.getCategory());
//                            tv.setTextSize(18);
//                            tv.setTextColor(Color.BLACK);
//                            //      tv.setBackgroundColor(getActivity().getResources().getColor(R.color.ash));
//
//                            //      tv.setPadding(0, 0, 20, 0);
//
//                            //     tv.setGravity(Gravity.START);
//                            //   tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow, 0);
//
//
//                            layout1.addView(tv);
//
//
//                            ProgressBar progressBar = new ProgressBar(getActivity());
//
//
//                            progressBar.setLayoutParams(new GridView.LayoutParams(50, 50));
////                            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(  50,
////                                    50);
////
////                            params2.gravity = Gravity.CENTER;
////
////                            progressBar.setLayoutParams(params2);
//
//                            progressBar.setVisibility(View.INVISIBLE);
//
//
//                            layout1.addView(progressBar);
//
//
//                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                            );
//
//
//                            params.setMargins(50, 0, 0, 1);
//                            layout1.setLayoutParams(params);
//
//                            layout_m1.addView(layout1);
//
//
//                            mainLayout.addView(layout_m1);
//                            layout1.setOnClickListener(new View.OnClickListener() {
//
//                                @Override
//                                public void onClick(View v) {
//
//                                    if (layout_m1.getChildCount() > 1) {
//
//                                        int count = layout_m1.getChildCount();
//                                        for (int i = count - 1; i > 0; i--) {
//
//
//                                            View view1 = layout_m1.getChildAt(i);
//
//
//                                            ((ViewGroup) layout_m1).removeView(view1);
//
//
//                                        }
//
//
//                                    } else {
//
//                                        progressBar.setVisibility(View.VISIBLE);
//
//                                        layout1.setEnabled(false);
//                                        List<DetailsItem> list_sub1 = new ArrayList<>();
//
//                                        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
//
//                                        Call<com.project.zpace.pojos.read_category.Response> call = apiService.read_sub1(Constants.api_key, Utils.GVCOT(tv.getText().toString()));
//
//                                        call.enqueue(new Callback<Response>() {
//                                            @Override
//                                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//
//                                                if (response.body() != null) {
//                                                    if (response.body().getResult().equals("1")) {
//                                                        list_sub1.addAll(response.body().getDetails());
//
//                                                        for (DetailsItem row_sub : list_sub1) {
//
//                                                            LinearLayout layout_m2 = new LinearLayout(getActivity());
//                                                            layout_m2.setOrientation(LinearLayout.VERTICAL);
//
//                                                            LinearLayout layout2 = new LinearLayout(getActivity());
//
//                                                            layout2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                                                                    LinearLayout.LayoutParams.WRAP_CONTENT));
//
//                                                            layout2.setOrientation(LinearLayout.HORIZONTAL);
//                                                            layout2.setClickable(true);
//
//                                                            ImageView iv2 = new ImageView(getActivity());
//                                                            iv2.setLayoutParams(new LinearLayout.LayoutParams(15, 15));
//                                                            iv2.setBackgroundResource(R.drawable.ic_up_down);
//
//                                                            LinearLayout.LayoutParams params_iv2 = new LinearLayout.LayoutParams(50,
//                                                                    50);
//
//                                                            params_iv2.gravity = Gravity.CENTER;
//
//                                                            iv2.setLayoutParams(params_iv2);
//
//
//                                                            layout2.addView(iv2);
//
//
//                                                            My_TextView_M tv2 = new My_TextView_M(getActivity());
//                                                            tv2.setText(row_sub.getCategory());
//                                                            tv2.setTextSize(15);
//                                                            tv2.setTextColor(Color.BLACK);
//                                                            //                 tv2.setBackgroundColor(getActivity().getResources().getColor(R.color.ash));
//
//                                                            tv2.setPadding(0, 10, 0, 0);
//                                                            tv2.setGravity(Gravity.START);
//
//
//                                                            //       tv2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow, 0);
//
//
//                                                            layout2.addView(tv2);
//
//
//                                                            ProgressBar progressBar2 = new ProgressBar(getActivity());
//                                                            progressBar2.setLayoutParams(new GridView.LayoutParams(50, 50));
//                                                            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(50,
//                                                                    50);
//
//                                                            params3.gravity = Gravity.CENTER;
//
//                                                            progressBar2.setLayoutParams(params3);
//
//                                                            progressBar2.setVisibility(View.INVISIBLE);
//                                                            layout2.addView(progressBar2);
//
//                                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                                                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                                                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                                            );
//
//
//                                                            params.setMargins(200, 0, 0, 1);
//                                                            layout2.setLayoutParams(params);
//
//                                                            layout_m2.addView(layout2);
//
//
//                                                            layout2.setOnClickListener(new View.OnClickListener() {
//                                                                @Override
//                                                                public void onClick(View v) {
//
//
//                                                                    if (layout_m2.getChildCount() > 1) {
//
//
//                                                                        int count = layout_m2.getChildCount();
//                                                                        for (int i = count - 1; i > 0; i--) {
//
//
//                                                                            View view1 = layout_m2.getChildAt(i);
//
//
//                                                                            ((ViewGroup) layout_m2).removeView(view1);
//
//
//                                                                        }
//
//
//                                                                    } else {
//
//                                                                        progressBar2.setVisibility(View.VISIBLE);
//
//
//                                                                        layout2.setEnabled(false);
//                                                                        List<DetailsItem> list_sub2 = new ArrayList<>();
//                                                                        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
//
//                                                                        Call<com.project.zpace.pojos.read_category.Response> call = apiService.read_sub2(Constants.api_key, Utils.GVCOT(tv.getText().toString()), Utils.GVCOT(tv2.getText().toString()));
//                                                                        call.enqueue(new Callback<Response>() {
//                                                                            @Override
//                                                                            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//
//                                                                                if (response.body() != null) {
//                                                                                    if (response.body().getResult().equals("1")) {
//                                                                                        list_sub2.addAll(response.body().getDetails());
//
//
//                                                                                        for (DetailsItem row_sub2 : list_sub2) {
//                                                                                            LinearLayout layout3 = new LinearLayout(getActivity());
//
//                                                                                            layout3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                                                                                                    LinearLayout.LayoutParams.WRAP_CONTENT));
//
//                                                                                            layout3.setOrientation(LinearLayout.VERTICAL);
//                                                                                            layout3.setClickable(true);
//
//
//                                                                                            My_TextView_M tv3 = new My_TextView_M(getActivity());
//                                                                                            tv3.setText(row_sub2.getCategory());
//                                                                                            tv3.setTextSize(13);
//                                                                                            tv3.setTextColor(Color.BLACK);
//                                                                                            //            tv3.setBackgroundColor(getActivity().getResources().getColor(R.color.ash));
//
//                                                                                            tv3.setPadding(0, 10, 0, 0);
//                                                                                            tv3.setGravity(Gravity.START);
//
//                                                                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                                                                                    LinearLayout.LayoutParams.MATCH_PARENT,
//                                                                                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                                                                            );
//
//
//                                                                                            params.setMargins(300, 0, 0, 1);
//                                                                                            tv3.setLayoutParams(params);
//
//                                                                                            layout3.addView(tv3);
//
//
//                                                                                            layout3.setOnClickListener(new View.OnClickListener() {
//                                                                                                @Override
//                                                                                                public void onClick(View v) {
//
//
//                                                                                                    goto_section(tv.getText().toString(), tv2.getText().toString(), tv3.getText().toString());
//
//
//                                                                                                    //    Toast.makeText(getActivity(), tv.getText().toString() + "-" + tv2.getText().toString() + "-" + tv3.getText().toString(), Toast.LENGTH_LONG).show();
//
//                                                                                                    // got to section by passing parameters
//                                                                                                }
//                                                                                            });
//
//                                                                                            layout_m2.addView(layout3);
//
//
//                                                                                        }
//
//
//                                                                                    } else {
//                                                                                        goto_section(tv.getText().toString(), tv2.getText().toString(), "");
//                                                                                    }
//                                                                                }
//
//
//                                                                                layout2.setEnabled(true);
//                                                                                progressBar2.setVisibility(View.INVISIBLE);
//
//                                                                            }
//
//                                                                            @Override
//                                                                            public void onFailure(Call<Response> call, Throwable t) {
//
//                                                                                layout2.setEnabled(true);
//                                                                                progressBar2.setVisibility(View.INVISIBLE);
//
//                                                                            }
//                                                                        });
//
//
//                                                                        //           Toast.makeText(getActivity(),tv.getText().toString()+"-"+ tv2.getText().toString(), Toast.LENGTH_LONG).show();
//
//                                                                    }
//
//
//                                                                }
//                                                            });
//
//                                                            layout_m1.addView(layout_m2);
//
//
//                                                        }
//
//
//                                                    } else {
//                                                        goto_section(tv.getText().toString(), "", "");
//                                                    }
//                                                }
//                                                layout1.setEnabled(true);
//                                                progressBar.setVisibility(View.INVISIBLE);
//
//
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<Response> call, Throwable t) {
//                                                layout1.setEnabled(true);
//                                                progressBar.setVisibility(View.INVISIBLE);
//                                            }
//                                        });
//
//
//                                        // do you work here
////                                    TextView tv_sub = new TextView(getActivity());
////                                    tv_sub.setText("sub");
////                                    tv_sub.setTextSize(10);
////                                    tv_sub.setTextColor(Color.RED);
////                                    tv_sub.setPadding(0, 10, 0, 0);
////                                    tv_sub.setGravity(Gravity.CENTER);
////
////
////
//                                        //  Toast.makeText(getActivity(), tv.getText().toString(), Toast.LENGTH_LONG).show();
//
//                                    }
//
//                                }
//                            });
//
//
//                        }
//
//
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//
//            }
//        });
//
//
//    }


    private void goto_section(String category, String sub1, String sub2) {

        Constants.sceen = Constants.section;

        Fragment_Section fragment = new Fragment_Section();
        Bundle args = new Bundle();

        args.putString(Constants.Parcel3_Category, category);
        args.putString(Constants.Parcel3_Sub1, sub1);
        args.putString(Constants.Parcel3_Sub2, sub2);

        args.putString(Constants.fragment_heading, category + "->" + sub1 + "->" + sub2);
        fragment.setArguments(args);


        activity.hide_fragment_except("Fragment_Section");
        FragmentManager fm = activity.getSupportFragmentManager();

        fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Section").commit();


    }


}
