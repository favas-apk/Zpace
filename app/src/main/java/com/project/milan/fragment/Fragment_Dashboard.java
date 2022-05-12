package com.project.milan.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.milan.apiservice.ApiClient;
import com.project.milan.apiservice.Endpoint;
import com.project.milan.interfac.Fr_Dash_Interface;
import com.project.milan.pojos.read_dashboard_categories_structure.DetailsItem;
import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.Utils;
import com.project.milan.activity.HomeActivity;
import com.project.milan.adapter.AdapterDashboardGridviewStyle2;
import com.project.milan.adapter.AdapterDashboardGridviewStyle3;
import com.project.milan.adapter.AdapterDashboardGridviewStyle_Square;
import com.project.milan.adapter.AdapterDashboardGridviewStyle_linear;
import com.project.milan.adapter.AdapterDashboardGridview_style1;
import com.project.milan.adapter.AdapterScroll;
import com.project.milan.adapter.SearchAdapter;
import com.project.milan.adapter.SliderAdapter;
import com.project.milan.custom.MyGridView;
import com.project.milan.custom.My_TextView;
import com.project.milan.custom.My_TextView_M;
import com.project.milan.database.appdb.Appdb;
import com.project.milan.database.entities.DashEntity;
import com.project.milan.database.entities.DashGroupEntity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;

import net.gotev.speech.SpeechDelegate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Dashboard extends Fragment implements Fr_Dash_Interface {

    private Appdb db;
    private GridView gridview;
    private View view;



    private LinearLayout ll_speech_progress;


    private SpeechDelegate speechDelegate;


    private HomeActivity activity;

    private List<DetailsItem> list_dash_category;

    private int index = -1;

    private LinearLayout ll1;


    private SearchAdapter adp;
    TextView txt_test,txtmen,txtwomen,txtkids,txtlifestyle;

    LayoutInflater inflater;
    ViewGroup container;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_scrolling, container, false);
        init();
        Constants.setFr_dash_interface(this);
        db = Appdb.getDb_instance(getActivity());
        db.getDashEntityDao().del_all();
        db.getDashGroupEntityDao().del_all();

        activity = (HomeActivity) getActivity();
        //    Speech.init(getActivity(), getActivity().getPackageName());


        Constants.getHomeInterface().show_heading("");
        Constants.getHomeInterface().show_sub_heading("");
        Constants.getHomeInterface().unselect_all_navigation_items();
        //  Constants.getHomeInterface().unlock_nav_drawer();
        Constants.getHomeInterface().select_navigation_item(0);
        Constants.getHomeInterface().select_bottom_navigation_item(0);

        show_live_data();
        save_dashboard();


        txtmen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.sceen = Constants.section;

                Fragment_Section fragment = new Fragment_Section ();
                Bundle args = new Bundle();
                args.putString(Constants.Parcel1, "Men");
                args.putString(Constants.fragment_heading, "Men");
                fragment.setArguments(args);


                activity.hide_fragment_except("Fragment_Section");

                FragmentManager fm=activity.getSupportFragmentManager();

                fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Section").commit();



            }
        });


        txtwomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.sceen = Constants.section;

                Fragment_Section fragment = new Fragment_Section ();
                Bundle args = new Bundle();
                args.putString(Constants.Parcel1, "Women");
                args.putString(Constants.fragment_heading, "Women");
                fragment.setArguments(args);


                activity.hide_fragment_except("Fragment_Section");

                FragmentManager fm=activity.getSupportFragmentManager();

                fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Section").commit();



            }
        });


        txtkids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.sceen = Constants.section;

                Fragment_Section fragment = new Fragment_Section ();
                Bundle args = new Bundle();
                args.putString(Constants.Parcel1, "Kids");
                args.putString(Constants.fragment_heading, "Kids");
                fragment.setArguments(args);


                activity.hide_fragment_except("Fragment_Section");

                FragmentManager fm=activity.getSupportFragmentManager();

                fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Section").commit();



            }
        });

        txtlifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.sceen = Constants.section;

                Fragment_Section fragment = new Fragment_Section ();
                Bundle args = new Bundle();
                args.putString(Constants.Parcel1, "LifeStyle");
                args.putString(Constants.fragment_heading, "LifeStyle");
                fragment.setArguments(args);


                activity.hide_fragment_except("Fragment_Section");

                FragmentManager fm=activity.getSupportFragmentManager();

                fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Section").commit();



            }
        });

//        txt_test.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                show_the_screen();
//            }
//        });


//        ac_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                read_items_for_search_box();
//            }
//        });


//        ac_search.setOnKeyListener(new View.OnKeyListener() {
//
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//
//
//                    if (!ac_search.getText().toString().trim().equals("")) {
//                        activity.hideSoftKeyboard(getActivity(), view);
//                        Constants.sceen = Constants.search_result;
//                        db.getSearchEntityDao().inser_search_details(new SearchEntity(0, ac_search.getText().toString().trim()));
//
//
//                        Fragment_Searchlist fragment = new Fragment_Searchlist();
//                        Bundle args = new Bundle();
//                        args.putString(Constants.Parcel2, ac_search.getText().toString().trim().toLowerCase());
//                        args.putString(Constants.fragment_heading, ac_search.getText().toString().trim().toLowerCase());
//                        fragment.setArguments(args);
//
//
//                        //           getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, "Fragment_Searchlist").commit();
//                        activity.hide_fragment_except("Fragment_Searchlist");
//
//                        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Searchlist").commit();
//
//
//                    }
//
//
//                }
//                return false;
//
//
//            }
//
//        });


//        iv_mic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                voice_flag = true;
////
////
//                ac_search.setVisibility(View.GONE);
//                ll_speech_progress.setVisibility(View.VISIBLE);
//                listen();
//
////
////
//            }
//        });


        return view;
    }


//    public void read_items_for_search_box() {
//
//
//        if (list_search.size() == 0) {
//
//
//            List<String> list_history = new ArrayList<>();
//
//
//            list_history.addAll(db.getSearchEntityDao().get_all_datas());
//
//            for (String item : list_history) {
//
//                list_search.add(new com.project.zpace.pojos.search_box.DetailsItem(item, true));
//
//            }
//
//            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
//
//            Call<com.project.zpace.pojos.search_box.Response> call = apiService.read_item_for_search_box(Constants.api_key);
//
//            call.enqueue(new Callback<com.project.zpace.pojos.search_box.Response>() {
//                @Override
//                public void onResponse(Call<com.project.zpace.pojos.search_box.Response> call, Response<com.project.zpace.pojos.search_box.Response> response) {
//
//
//                    if (Constants.sceen.equals(Constants.dash)) {
//                        if (response.body() != null) {
//
//
//                            if (response.body().getResult().equals("1")) {
//                                list_search.addAll(response.body().getDetails());
//
//
//                                adp = new SearchAdapter(getActivity(), list_search);
//                                ac_search.setAdapter(adp);
//
//
//                            }
//
//                        }
//
//                    }
//
//
//                }
//
//                @Override
//                public void onFailure(Call<com.project.zpace.pojos.search_box.Response> call, Throwable t) {
//
//                }
//            });
//
//        }
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            show_the_screen();


        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //   ((HomeInterface) getActivity()).showHamburgerIcon();
    }

    private void init() {

        txt_test = view.findViewById(R.id.txt_test);
        ll1 = view.findViewById(R.id.ll1);


        gridview = view.findViewById(R.id.gridview);
        txtmen=view.findViewById(R.id.txtmen);
        txtwomen=view.findViewById(R.id.txtwomen);
        txtkids=view.findViewById(R.id.txtkids);
        txtlifestyle=view.findViewById(R.id.txtlifestyle);




        // gridview_clothes = view.findViewById(R.id.gridview_clothes);


        //   btn_view_more_offer=view.findViewById(R.id. btn_view_more_offer);


        //view_pager=view.findViewById(R.id.view_pager);

        //    iv_mic = view.findViewById(R.id.iv_mic);

        //sp_progress = view.findViewById(R.id.sp_progress);
        //    edt_search = view.findViewById(R.id.edt_search);
        ll_speech_progress = view.findViewById(R.id.ll_speech_progress);

        //     ac_search = view.findViewById(R.id.ac_search);


    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        try {
//            Speech.getInstance().shutdown();
//        } catch (Exception e) {
//
//        }
//
//
//    }


//    private void listen() {
//
//        try {
//            // you must have android.permission.RECORD_AUDIO granted at this point
//            Speech.getInstance().startListening(new SpeechDelegate() {
//                @Override
//                public void onStartOfSpeech() {
//                    Log.i("speech", "speech recognition is now active");
//                }
//
//                @Override
//                public void onSpeechRmsChanged(float value) {
//                    Log.d("speech", "rms is now: " + value);
//                }
//
//                @Override
//                public void onSpeechPartialResults(List<String> results) {
//                    StringBuilder str = new StringBuilder();
//                    for (String res : results) {
//                        str.append(res).append(" ");
//                    }
//
//                    Log.i("speech", "partial result: " + str.toString().trim());
//                }
//
//                @Override
//                public void onSpeechResult(String result) {
//
//                    ll_speech_progress.setVisibility(View.GONE);
//                    ac_search.setVisibility(View.VISIBLE);
//
//
//                    if (!result.trim().equals("")) {
//
//                        ac_search.setText("" + result);
//
//                        final Handler handler = new Handler(Looper.getMainLooper());
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                //Do something after 100ms
//                                //    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Searchlist(), "Fragment_Searchlist").commit();
//
//
//                                if (!ac_search.getText().toString().trim().equals("")) {
//                                    activity.hideSoftKeyboard(getActivity(), view);
//                                    Constants.sceen = Constants.search_result;
//                                    //    db.getSearchEntityDao().inser_search_details(new SearchEntity(0, ac_search.getText().toString().trim()));
//
//
//                                    Fragment_Searchlist fragment = new Fragment_Searchlist();
//                                    Bundle args = new Bundle();
//                                    args.putString(Constants.Parcel2, ac_search.getText().toString().trim().toLowerCase());
//                                    args.putString(Constants.fragment_heading, ac_search.getText().toString().trim().toLowerCase());
//                                    fragment.setArguments(args);
//
//
//                                    //       getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, "Fragment_Searchlist").commit();
//
//                                    activity.hide_fragment_except("Fragment_Searchlist");
//
//                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Searchlist").commit();
//
//
//                                }
//
//
//                            }
//                        }, 1000);
//
//
//                        //
//
//                    }
//
//
////
////
////                    Log.i("speech", "result: " + result);
//                }
//            });
//        } catch (SpeechRecognitionNotAvailable exc) {
//            ll_speech_progress.setVisibility(View.GONE);
//
//            Log.e("speech", "Speech recognition is not available on this device!");
//
//            //   Toast.makeText()
//            ((HomeActivity) getActivity()).showSnack_W(getString(R.string.speech_recog_not));
//
//            // You can prompt the user if he wants to install Google App to have
//            // speech recognition, and then you can simply call:
//            //
//            // SpeechUtil.redirectUserToGoogleAppOnPlayStore(this);
//            //
//            // to redirect the user to the Google App page on Play Store
//        } catch (GoogleVoiceTypingDisabledException exc) {
//            ll_speech_progress.setVisibility(View.GONE);
//
//            Log.e("speech", "Google voice typing must be enabled!");
//            ((HomeActivity) getActivity()).showSnack_W(getString(R.string.google_voice_type_must_be_enabled));
//        }
//
//    }


    private void show_the_screen() {


        List<DashGroupEntity> list_undisplayed = db.getDashGroupEntityDao().get_all_downloaded_but_undisplayed_datas();

        for (DashGroupEntity row : list_undisplayed) {
            String display_name = row.getDisplay_name();

            String style = row.getStyle();
            int slno = row.getSlno();
            String type=row.getType();





            if (!style.equals("") && slno > 0) {
                List<DashEntity> list_items = db.getDashEntityDao().get_all_datas_specified_group(slno);


                if (style.equals("style1")) {


                    MyGridView grid = new MyGridView(getActivity());
                    grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    grid.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
                    grid.setPadding(20, 20, 20, 20);
                    grid.setNumColumns(3);
                    grid.setColumnWidth(GridView.AUTO_FIT);
                    grid.setVerticalSpacing(0);
                    grid.setHorizontalSpacing(0);
                    grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );


                    params.setMargins(0, 50, 0, 0);



                    ConstraintLayout layout_head = (ConstraintLayout) View.inflate(
                            getActivity(),
                            R.layout.constraint_layout, null);

                //    mainLayout.addView(layout_cat);

                    TextView txtname = layout_head.findViewById(R.id.txtname);
                    TextView txttype = layout_head.findViewById(R.id.txttype);
                    TextView txtviewall = layout_head.findViewById(R.id.txtviewall);

                    txtname.setText(display_name);






                    if(!type.equals(""))
                    {

                        txttype.setText(""+type);
                        txtviewall.setText("View All");



                        txtviewall.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                              //  activity.showSnack_W(""+txttype.getText().toString());


                                Constants.sceen = Constants.section;

                                Fragment_Section fragment = new Fragment_Section();
                                Bundle args = new Bundle();
                                Constants.Parcel3_Category=null;
                                args.putString(Constants.Parcel4, type);
                                args.putString(Constants.slno, ""+slno);
                                args.putString(Constants.fragment_heading, display_name);
                                fragment.setArguments(args);


                                FragmentManager fm = getActivity().getSupportFragmentManager();



                                activity.hide_fragment_except("Fragment_Section");


                                fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Section").commit();


                            }
                        });



                    }





//                    DottedEdgesCutCornerView dottedEdgesCutCornerView = new DottedEdgesCutCornerView(getActivity());
//                    dottedEdgesCutCornerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                    dottedEdgesCutCornerView.setDotSpacing(1);
//                    dottedEdgesCutCornerView.setDotRadius(10);
//                    dottedEdgesCutCornerView.addDotEdgePosition(DottedEdgesCutCornerView.POSITION_TOP);
//                    dottedEdgesCutCornerView.addDotEdgePosition(DottedEdgesCutCornerView.POSITION_BOTTOM);
//                    dottedEdgesCutCornerView.addDotEdgePosition(DottedEdgesCutCornerView.POSITION_LEFT);
//                    dottedEdgesCutCornerView.addDotEdgePosition(DottedEdgesCutCornerView.POSITION_RIGHT);
//                    //   dottedEdgesCutCornerView.setBackground(getActivity().getResources().getDrawable(R.drawable.gradient_background));
//
//
//                    dottedEdgesCutCornerView.addView(grid);
                 //   params.setMargins(10, 5, 10, 10);
                 //   grid.setLayoutParams(params);


                    AdapterDashboardGridview_style1 adp_style1 = new AdapterDashboardGridview_style1(getActivity(), list_items);

                    if (Constants.sceen.equals(Constants.dash)) {
                        ll1.setVisibility(View.VISIBLE);



                        ll1.addView(layout_head);
                      //  ll1.addView(dottedEdgesCutCornerView);
                        ll1.addView(grid);
                        grid.setAdapter(adp_style1);
                        db.getDashGroupEntityDao().update_display_status(1, slno);

                    }


                } else if (style.equals("style2")) {

                    MyGridView grid = new MyGridView(getActivity());

                    grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    grid.setBackgroundColor(getActivity().getResources().getColor(R.color.light_white));
                    grid.setNumColumns(2);
                    grid.setColumnWidth(GridView.AUTO_FIT);
                    grid.setVerticalSpacing(10);
                    grid.setHorizontalSpacing(10);
                    grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 50, 0, 0);


                    My_TextView_M textView = new My_TextView_M(getActivity());
                    textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));

                    textView.setText(display_name);
                    textView.setLayoutParams(params);

                    AdapterDashboardGridviewStyle2 adp_style2 = new AdapterDashboardGridviewStyle2(getActivity(), list_items);

                    if (Constants.sceen.equals(Constants.dash)) {
                        ll1.addView(textView);
                        ll1.addView(grid);


                        grid.setAdapter(adp_style2);

                        db.getDashGroupEntityDao().update_display_status(1, slno);
                    }


                } else if (style.equals("style3")) {

                    MyGridView grid = new MyGridView(getActivity());

                    grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    grid.setBackgroundColor(getActivity().getResources().getColor(R.color.ash));
                    grid.setNumColumns(2);
                    grid.setColumnWidth(GridView.AUTO_FIT);
                    grid.setVerticalSpacing(20);

                    grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 50, 0, 0);


                    My_TextView_M textView = new My_TextView_M(getActivity());
                    textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));

                    textView.setText(display_name);
                    textView.setLayoutParams(params);

                    grid.setPadding(0, 20, 0, 20);


                    AdapterDashboardGridviewStyle3 adp_style3 = new AdapterDashboardGridviewStyle3(getActivity(), list_items);


                    if (Constants.sceen.equals(Constants.dash)) {
                        ll1.addView(textView);
                        ll1.addView(grid);
                        grid.setAdapter(adp_style3);
                        db.getDashGroupEntityDao().update_display_status(1, slno);

                    }


                } else if (style.equals("scroll1")) {


                    //  CardView cardView = new CardView(getActivity());

                    RecyclerView recyclerView = new RecyclerView(getActivity());

                    recyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    recyclerView.setBackgroundColor(getActivity().getResources().getColor(R.color.light_white));


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 50, 0, 0);


                    My_TextView textView = new My_TextView(getActivity());
                    textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250));
                    //    textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
                    textView.setTextSize(20f);


                    textView.setText(display_name);
                    textView.setLayoutParams(params);

                    recyclerView.setPadding(0, 20, 0, 20);


                    AdapterScroll adp_style4 = new AdapterScroll(getActivity(), list_items);


                    if (Constants.sceen.equals(Constants.dash)) {
                        ll1.addView(textView);
                        ll1.addView(recyclerView);

                        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(lm);
                        recyclerView.setAdapter(adp_style4);
                        db.getDashGroupEntityDao().update_display_status(1, slno);

                    }


                } else if (style.equals("style_square")) {


                    MyGridView grid = new MyGridView(getActivity());

                    grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    grid.setBackgroundColor(getActivity().getResources().getColor(R.color.light_white));
                    grid.setNumColumns(2);
                    grid.setColumnWidth(GridView.AUTO_FIT);
                    grid.setVerticalSpacing(10);
                    grid.setHorizontalSpacing(10);
                    grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 50, 0, 0);


                    My_TextView_M textView = new My_TextView_M(getActivity());
                    textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));

                    textView.setText(display_name);
                    textView.setLayoutParams(params);


                    AdapterDashboardGridviewStyle_Square adp_style2 = new AdapterDashboardGridviewStyle_Square(getActivity(), list_items);


                    if (Constants.sceen.equals(Constants.dash) && !Constants.isFlag_square()) {
                        ll1.addView(textView);
                        ll1.addView(grid);
                        grid.setAdapter(adp_style2);

                        db.getDashGroupEntityDao().update_display_status(1, slno);


                    }


                } else if (style.equals("style_linear")) {


                    MyGridView grid = new MyGridView(getActivity());

                    grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    grid.setBackgroundColor(getActivity().getResources().getColor(R.color.light_white));
                    grid.setNumColumns(2);
                    grid.setColumnWidth(GridView.AUTO_FIT);
                    grid.setVerticalSpacing(0);
                    grid.setHorizontalSpacing(0);
                    grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);


                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(0, 50, 0, 0);


                    My_TextView_M textView = new My_TextView_M(getActivity());
                    textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));

                    textView.setText(display_name);
                    textView.setLayoutParams(params);


                    AdapterDashboardGridviewStyle_linear adp_style2 = new AdapterDashboardGridviewStyle_linear(getActivity(), list_items);


                    if (Constants.sceen.equals(Constants.dash)) {
                        ll1.addView(textView);
                        ll1.addView(grid);
                        grid.setAdapter(adp_style2);

                        db.getDashGroupEntityDao().update_display_status(1, slno);
                    }


                } else if (style.equals("Banner1")) {


                    View inflated = LayoutInflater.from(getActivity()).inflate(R.layout.layout_banner1, ll1, false);
                    inflated.setId(View.generateViewId());


                    if (Constants.sceen.equals(Constants.dash)) {

                        ll1.addView(inflated);
                        com.smarteist.autoimageslider.SliderView image_slide = ll1.findViewById(R.id.image_slide1);
                        image_slide.setIndicatorAnimation(IndicatorAnimationType.COLOR);
                        image_slide.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                        SliderAdapter adp = new SliderAdapter(getActivity(), list_items);
                        image_slide.setSliderAdapter(adp);
                        image_slide.startAutoCycle();

                        db.getDashGroupEntityDao().update_display_status(1, slno);
                    }


                } else if (style.equals("Banner2")) {


                    View inflated = LayoutInflater.from(getActivity()).inflate(R.layout.layout_banner2, ll1, false);
                    inflated.setId(View.generateViewId());


                    if (Constants.sceen.equals(Constants.dash)) {

                        ll1.addView(inflated);
                        com.smarteist.autoimageslider.SliderView image_slide = ll1.findViewById(R.id.image_slide2);
                        image_slide.setIndicatorAnimation(IndicatorAnimationType.SWAP);
                        image_slide.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

                        SliderAdapter adp = new SliderAdapter(getActivity(), list_items);
                        image_slide.setSliderAdapter(adp);


                        db.getDashGroupEntityDao().update_display_status(1, slno);
                    }


                    Constants.getHomeInterface().show_bottom_nav();
                    Constants.getHomeInterface().show_search_cart();


                }


            }


        }


    }


    //    private void Flipimage(int image)
//    {
//        ImageView imageView=new ImageView(getActivity());
//        imageView.setBackgroundResource(image);
//
//        vf1.addView(imageView);
//        vf1.setFlipInterval(4000);
//        vf1.setAutoStart(true);
//
//        vf1.setInAnimation(getActivity(), android.R.anim.slide_in_left);
//        vf1.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
//    }


    //  DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rcv_suggest.getContext(), lm.getOrientation());
    //  rcv_suggest.addItemDecoration(dividerItemDecoration);

//        LayoutAnimationController anim_cont= AnimationUtils.loadLayoutAnimation(HomeActivity.this,R.anim.layout_animation_from_bottom );
//
//        rv1.setLayoutAnimation(anim_cont);
//        adp.notifyDataSetChanged();
//        rv1.scheduleLayoutAnimation();


    //  }


    private void save_dashboard_category() {


        index = index + 1;

        if (index < list_dash_category.size()) {


            List<com.project.milan.pojos.show_dash_gridview.DetailsItem> list_style1 = new ArrayList<>();

            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

            if(list_dash_category.get(index).getType().equals(Constants.Buy_Qty_Free_Percent )   || list_dash_category.get(index).getType().equals(Constants.Buy_Qty_Free_Qty )  ||  list_dash_category.get(index).getType().equals(Constants.Offer )  || list_dash_category.get(index).getType().equals(Constants.Today_Offer ))
            {
                Call<com.project.milan.pojos.show_dash_gridview.Response> call = apiService.show_dash_offer(Constants.api_key, list_dash_category.get(index).getSlno(), list_dash_category.get(index).getType(),"4");

                call.enqueue(new Callback<com.project.milan.pojos.show_dash_gridview.Response>() {
                    @Override
                    public void onResponse(Call<com.project.milan.pojos.show_dash_gridview.Response> call, Response<com.project.milan.pojos.show_dash_gridview.Response> response) {
                        if (response.body() != null) {
                            if (response.body().getResult().equals("1")) {
                                list_style1.addAll(response.body().getDetails());

                                for (com.project.milan.pojos.show_dash_gridview.DetailsItem row : list_style1) {
                                    db.getDashEntityDao().insert_single_details(new DashEntity(0, row.getImage(), row.getDashSlno(), row.getDetails(), row.getType(), row.getSlno(), row.getCompany(), row.getRate(), row.getOffer(), row.getDisplay_name(), row.getOffer_end_date(), row.getBuy_quantity(), row.getFree_quantity(), row.getFree_percentage(), row.getColor()));
                                }

                                if (list_style1.size() == db.getDashEntityDao().get_count_of_items_inside_dash_group(list_dash_category.get(index).getSlno())) {
                                    db.getDashGroupEntityDao().update_downlod_status(1, Integer.parseInt(list_dash_category.get(index).getSlno()));

                                    //   txt_test.setText(list_dash_category.get(index).getSlno());
                                }

                                save_dashboard_category();
                            }
                            else
                            {
                                save_dashboard_category();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<com.project.milan.pojos.show_dash_gridview.Response> call, Throwable t) {
                        String a=t.getLocalizedMessage();

                    }
                });
            }else
            {
                Call<com.project.milan.pojos.show_dash_gridview.Response> call = apiService.show_dash_gridview(Constants.api_key, Utils.GVCOT(list_dash_category.get(index).getSlno()), "customer");

                call.enqueue(new Callback<com.project.milan.pojos.show_dash_gridview.Response>() {
                    @Override
                    public void onResponse(Call<com.project.milan.pojos.show_dash_gridview.Response> call, Response<com.project.milan.pojos.show_dash_gridview.Response> response) {
                        if (response.body() != null) {
                            if (response.body().getResult().equals("1")) {
                                list_style1.addAll(response.body().getDetails());

                                for (com.project.milan.pojos.show_dash_gridview.DetailsItem row : list_style1) {
                                    db.getDashEntityDao().insert_single_details(new DashEntity(0, row.getImage(), row.getDashSlno(), row.getDetails(), row.getType(), row.getSlno(), row.getCompany(), row.getRate(), row.getOffer(), row.getDisplay_name(), row.getOffer_end_date(), row.getBuy_quantity(), row.getFree_quantity(), row.getFree_percentage(), row.getColor()));
                                }

                                if (list_style1.size() == db.getDashEntityDao().get_count_of_items_inside_dash_group(list_dash_category.get(index).getSlno())) {
                                    db.getDashGroupEntityDao().update_downlod_status(1, Integer.parseInt(list_dash_category.get(index).getSlno()));

                                    //   txt_test.setText(list_dash_category.get(index).getSlno());
                                }

                                save_dashboard_category();
                            }
                            else
                            {
                                save_dashboard_category();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<com.project.milan.pojos.show_dash_gridview.Response> call, Throwable t) {

                    }
                });
            }



//                call.enqueue(new Callback<com.project.zpace.pojos.show_dash_gridview.Response>() {
//                    @Override
//                    public void onResponse(Call<com.project.zpace.pojos.show_dash_gridview.Response> call, Response<com.project.zpace.pojos.show_dash_gridview.Response> response) {
//
//
//                 //       if (Constants.sceen.equals(Constants.dash)) {
//                            if (response.body().getResult().equals("1")) {
//
//                                ll1.setVisibility(View.VISIBLE);
//
//
//
//
//                                if (index != -1) {
//
//
//                                    try {
//
//
//                                        if (response.body().getStyle().equals("style1") ) {
//
//
//
//                                            MyGridView grid = new MyGridView(getActivity());
//
//                                            grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            grid.setBackgroundColor(getActivity().getResources().getColor(R.color.white));
//                                            grid.setPadding(20, 20, 20, 20);
//                                            grid.setNumColumns(3);
//                                            grid.setColumnWidth(GridView.AUTO_FIT);
//                                            grid.setVerticalSpacing(0);
//                                            grid.setHorizontalSpacing(0);
//                                            grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
//
//
//                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                                    LinearLayout.LayoutParams.MATCH_PARENT,
//                                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                            );
//                                            params.setMargins(0, 50, 0, 0);
//
//
//                                            My_TextView_M textView = new My_TextView_M(getActivity());
//                                            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            textView.setBackground(getActivity().getResources().getDrawable(R.drawable.gradient_background));
//                                            textView.setTextColor(getActivity().getResources().getColor(R.color.white));
//
//                                            textView.setText("  " + list_dash_category.get(index).getDisplayName());
//
//                                            textView.setLayoutParams(params);
//
//
//
//
//
//                                            DottedEdgesCutCornerView dottedEdgesCutCornerView = new DottedEdgesCutCornerView(getActivity());
//                                            dottedEdgesCutCornerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            dottedEdgesCutCornerView.setDotSpacing(1);
//                                            dottedEdgesCutCornerView.setDotRadius(10);
//                                            dottedEdgesCutCornerView.addDotEdgePosition(DottedEdgesCutCornerView.POSITION_TOP);
//                                            dottedEdgesCutCornerView.addDotEdgePosition(DottedEdgesCutCornerView.POSITION_BOTTOM);
//                                            dottedEdgesCutCornerView.addDotEdgePosition(DottedEdgesCutCornerView.POSITION_LEFT);
//                                            dottedEdgesCutCornerView.addDotEdgePosition(DottedEdgesCutCornerView.POSITION_RIGHT);
//                                            //   dottedEdgesCutCornerView.setBackground(getActivity().getResources().getDrawable(R.drawable.gradient_background));
//
//
//                                            dottedEdgesCutCornerView.addView(grid);
//                                            params.setMargins(10, 5, 10, 10);
//                                            dottedEdgesCutCornerView.setLayoutParams(params);
//
//
//
//
//                                            list_style1.addAll(response.body().getDetails());
//
//                                            for(DetailsItem row:list_style1)
//                                            {
//                                                db.getDashEntityDao().insert_single_details(new DashEntity(0,row.getImage(),row.getDashSlno(),row.getDetails(),row.getType(),row.getSlno(),row.getCompany(),row.getRate(),row.getOffer(),row.getDisplay_name()));
//                                            }
//
//                                         //   AdapterDashboardGridview_style1 adp_style1 = new AdapterDashboardGridview_style1(getActivity(), list_style1);
//
////                                            if(Constants.sceen.equals(Constants.dash)  && !Constants.isFlag_style1())
////                                            {
//
//
////                                                ll1.addView(textView);
////                                                ll1.addView(dottedEdgesCutCornerView);
////                                                grid.setAdapter(adp_style1);
////                                                Constants.setFlag_style1(true);
//                               //             }
//
//
//
//                                        } else if (response.body().getStyle().equals("style2") ) {
//
//
//                                            MyGridView grid = new MyGridView(getActivity());
//
//                                            grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            grid.setBackgroundColor(getActivity().getResources().getColor(R.color.light_white));
//                                            grid.setNumColumns(2);
//                                            grid.setColumnWidth(GridView.AUTO_FIT);
//                                            grid.setVerticalSpacing(10);
//                                            grid.setHorizontalSpacing(10);
//                                            grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
//
//
//                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                                    LinearLayout.LayoutParams.MATCH_PARENT,
//                                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                            );
//                                            params.setMargins(0, 50, 0, 0);
//
//
//                                            My_TextView_M textView = new My_TextView_M(getActivity());
//                                            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
//
//                                            textView.setText("" + list_dash_category.get(index).getDisplayName());
//                                            textView.setLayoutParams(params);
//
//
//
//
//
//                                            list_style1.addAll(response.body().getDetails());
//                                            AdapterDashboardGridviewStyle2 adp_style2 = new AdapterDashboardGridviewStyle2(getActivity(), list_style1);
//
////                                            if(Constants.sceen.equals(Constants.dash)  && !Constants.isFlag_style2())
////                                            {
//                                                ll1.addView(textView);
//                                                ll1.addView(grid);
//
//
//                                                grid.setAdapter(adp_style2);
//                                                Constants.setFlag_style2(true);
//                                        //    }
//
//
//
//
//                                        }
//                                        else if (response.body().getStyle().equals("style3") ) {
//
//                                            MyGridView grid = new MyGridView(getActivity());
//
//                                            grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            grid.setBackgroundColor(getActivity().getResources().getColor(R.color.ash));
//                                            grid.setNumColumns(2);
//                                            grid.setColumnWidth(GridView.AUTO_FIT);
//                                            grid.setVerticalSpacing(20);
//
//                                            grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
//
//
//                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                                    LinearLayout.LayoutParams.MATCH_PARENT,
//                                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                            );
//                                            params.setMargins(0, 50, 0, 0);
//
//
//                                            My_TextView_M textView = new My_TextView_M(getActivity());
//                                            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
//
//                                            textView.setText("" + list_dash_category.get(index).getDisplayName());
//                                            textView.setLayoutParams(params);
//
//                                            grid.setPadding(0, 20, 0, 20);
//
//
//
//
//
//                                            list_style1.addAll(response.body().getDetails());
//                                            AdapterDashboardGridviewStyle3 adp_style3 = new AdapterDashboardGridviewStyle3(getActivity(), list_style1);
//
//
////                                            if(Constants.sceen.equals(Constants.dash)  && !Constants.isFlag_style3())
////                                            {
//                                                ll1.addView(textView);
//                                                ll1.addView(grid);
//                                                grid.setAdapter(adp_style3);
//                                                Constants.setFlag_style3(true);
//                                          //  }
//
//
//
//                                        } else if (response.body().getStyle().equals("scroll1")   && !Constants.isFlag_scroll1()) {
//
//
//                                            //  CardView cardView = new CardView(getActivity());
//
//                                            RecyclerView recyclerView = new RecyclerView(getActivity());
//
//                                            recyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            recyclerView.setBackgroundColor(getActivity().getResources().getColor(R.color.light_white));
//
//
//                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                                    LinearLayout.LayoutParams.MATCH_PARENT,
//                                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                            );
//                                            params.setMargins(0, 50, 0, 0);
//
//
//                                            My_TextView textView = new My_TextView(getActivity());
//                                            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250));
//                                            //    textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
//                                            textView.setTextSize(20f);
//
//
//                                            textView.setText("" + list_dash_category.get(index).getDisplayName());
//                                            textView.setLayoutParams(params);
//
//                                            recyclerView.setPadding(0, 20, 0, 20);
//
//
//
//
//
//                                            //    cardView.addView(recyclerView);
//
//
//
//                                            list_style1.addAll(response.body().getDetails());
//                                            AdapterScroll adp_style4 = new AdapterScroll(getActivity(), list_style1);
//
//
//                                            if(Constants.sceen.equals(Constants.dash)  && !Constants.isFlag_scroll1())
//                                            {
//                                                ll1.addView(textView);
//                                                ll1.addView(recyclerView);
//
//                                                LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//                                                recyclerView.setLayoutManager(lm);
//                                                recyclerView.setAdapter(adp_style4);
//                                                Constants.setFlag_scroll1(true);
//                                            }
//
//
//
//
//                                        } else if (response.body().getStyle().equals("Banner2")) {
//
//
//                                            list_style1.addAll(response.body().getDetails());
//
//
//                                            View inflated = LayoutInflater.from(getActivity()).inflate(R.layout.layout_banner2, ll1, false);
//                                            inflated.setId(View.generateViewId());
//
//
//                                            ll1.addView(inflated);
//
//                                            com.smarteist.autoimageslider.SliderView image_slide = ll1.findViewById(R.id.image_slide2);
//
//
//                                            SliderAdapter adp = new SliderAdapter(getActivity(), list_style1);
//                                            image_slide.setSliderAdapter(adp);
//                                            image_slide.setIndicatorAnimation(IndicatorAnimationType.SWAP);
//                                            image_slide.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//
////                                        list_style1.addAll(response.body().getDetails());
////
////
////                                        View inflated = LayoutInflater.from(getActivity()).inflate(R.layout.layout_banner1, ll1, false);
////                                        inflated.setId(View.generateViewId());
////
////
////                                        ll1.addView(inflated);
////
////                                        com.smarteist.autoimageslider.SliderView image_slide = ll1.findViewById(R.id.image_slide1);
////
////
////                                        SliderAdapter adp = new SliderAdapter(getActivity(), list_style1);
////                                        image_slide.setSliderAdapter(adp);
////                                        image_slide.setIndicatorAnimation(IndicatorAnimationType.WORM);
////                                        image_slide.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
////                                        image_slide.startAutoCycle();
//
//
////                                    AdapterFlipper adapter = new AdapterFlipper(getActivity(), list_banner, list_caption);
////
////                                    //adding it to adapterview flipper
////                                    adapterViewFlipper.setAdapter(adapter);
////                                    adapterViewFlipper.setFlipInterval(2000);
////                                    adapterViewFlipper.startFlipping();
////                                    adapterViewFlipper.setInAnimation(getActivity(), R.animator.left_in);
////                                    adapterViewFlipper.setOutAnimation(getActivity(), R.animator.right_out);
////                                    adapterViewFlipper.showPrevious();
//
//
//                                        } else if (response.body().getStyle().equals("Banner1")) {
//
//
//                                            list_style1.addAll(response.body().getDetails());
//
//
//                                            View inflated = LayoutInflater.from(getActivity()).inflate(R.layout.layout_banner2, ll1, false);
//                                            inflated.setId(View.generateViewId());
//
//
//                                            ll1.addView(inflated);
//
//                                            com.smarteist.autoimageslider.SliderView image_slide = ll1.findViewById(R.id.image_slide2);
//
//
//                                            SliderAdapter adp = new SliderAdapter(getActivity(), list_style1);
//                                            image_slide.setSliderAdapter(adp);
//                                            image_slide.setIndicatorAnimation(IndicatorAnimationType.SWAP);
//                                            image_slide.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//
//
//                                        } else if (response.body().getStyle().equals("style_square")   && !Constants.isFlag_square()) {
//
//
//                                            MyGridView grid = new MyGridView(getActivity());
//
//                                            grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            grid.setBackgroundColor(getActivity().getResources().getColor(R.color.light_white));
//                                            grid.setNumColumns(2);
//                                            grid.setColumnWidth(GridView.AUTO_FIT);
//                                            grid.setVerticalSpacing(10);
//                                            grid.setHorizontalSpacing(10);
//                                            grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
//
//
//                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                                    LinearLayout.LayoutParams.MATCH_PARENT,
//                                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                            );
//                                            params.setMargins(0, 50, 0, 0);
//
//
//                                            My_TextView_M textView = new My_TextView_M(getActivity());
//                                            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
//
//                                            textView.setText("" + list_dash_category.get(index).getDisplayName());
//                                            textView.setLayoutParams(params);
//
//
//
//
//
//                                            list_style1.addAll(response.body().getDetails());
//                                            AdapterDashboardGridviewStyle_Square adp_style2 = new AdapterDashboardGridviewStyle_Square(getActivity(), list_style1);
//
//
//                                            if(Constants.sceen.equals(Constants.dash)  && !Constants.isFlag_square())
//                                            {
//                                                ll1.addView(textView);
//                                                ll1.addView(grid);
//                                                grid.setAdapter(adp_style2);
//                                                Constants.setFlag_square(true);
//
//
//                                            }
//
//
//
//                                        } else if (response.body().getStyle().equals("style_linear")   && !Constants.isFlag_linear()) {
//
//
//                                            MyGridView grid = new MyGridView(getActivity());
//
//                                            grid.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            grid.setBackgroundColor(getActivity().getResources().getColor(R.color.light_white));
//                                            grid.setNumColumns(2);
//                                            grid.setColumnWidth(GridView.AUTO_FIT);
//                                            grid.setVerticalSpacing(0);
//                                            grid.setHorizontalSpacing(0);
//                                            grid.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
//
//
//                                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                                                    LinearLayout.LayoutParams.MATCH_PARENT,
//                                                    LinearLayout.LayoutParams.WRAP_CONTENT
//                                            );
//                                            params.setMargins(0, 50, 0, 0);
//
//
//                                            My_TextView_M textView = new My_TextView_M(getActivity());
//                                            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//                                            textView.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
//
//                                            textView.setText("" + list_dash_category.get(index).getDisplayName());
//                                            textView.setLayoutParams(params);
//
//
//
//
//
//                                            list_style1.addAll(response.body().getDetails());
//                                            AdapterDashboardGridviewStyle_linear adp_style2 = new AdapterDashboardGridviewStyle_linear(getActivity(), list_style1);
//
//
//                                            if(Constants.sceen.equals(Constants.dash)  && !Constants.isFlag_linear())
//                                            {
//                                                ll1.addView(textView);
//                                                ll1.addView(grid);
//                                                grid.setAdapter(adp_style2);
//                                                Constants.setFlag_linear(true);
//                                            }
//
//
//
//                                        }
//
//
//                                        show_dashboard_category();
//                                    } catch (Exception e) {
//
//                                    }
//
//                                }
//
//
//                            }
//                    //    }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<com.project.zpace.pojos.show_dash_gridview.Response> call, Throwable t) {
//                        activity.showSnack_W(getString(R.string.sme_wrg));
//
//
//                    }
//                });


            //      }


        }


    }


    public String getURLForResource(int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
    }


//    private void setImageInFlipr(String imgUrl) {
//
//        ImageView image = new ImageView(getActivity());
//     //   Picasso.with(getActivity()).load(imgUrl).into(image);
//        //Picasso.get(getActivity()).load(imgUrl).into(image);
//        Picasso.with(getActivity()).load(imgUrl).memoryPolicy(MemoryPolicy.NO_CACHE).into(image);
//        vf1.addView(image);
//
//
//        vf1.setFlipInterval(1000);
//        vf1.setAutoStart(true);
//
//        vf1.setInAnimation(getActivity(), android.R.anim.slide_in_left);
//        vf1.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
//
//    }

//    private  void display_default_banner()
//    {
//        int images[]={R.drawable.ban1,R.drawable.ban2,R.drawable.ban3,R.drawable.ban4};
//        for(int i=0;i <images.length;i++)
//        {
//            Flipimage(images[i]);
//        }
//    }


   /* private void read()
    {
        list=new ArrayList<>();
        gridview.setAdapter(null);

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<PojomodelReadLeaders> call = apiService.read_leaders("");

        call.enqueue(new Callback<PojomodelReadLeaders>() {
            @Override
            public void onResponse(Call<PojomodelReadLeaders> call, Response<PojomodelReadLeaders> response) {
                if(response.body().getResult().equals("1"))
                {
                   // ((HomeActivity)getActivity()).showSnack_E(response.body().getMessage());
                    list.addAll(response.body().getDetails());

                    gridview.setAdapter(new AdapterLeaders(getActivity(), list));
                }
                else{
                    ((HomeActivity)getActivity()).showSnack_E(response.body().getMessage());
                }


            }

            @Override
            public void onFailure(Call<PojomodelReadLeaders> call, Throwable t) {
                ((HomeActivity)getActivity()).showSnack_E(t.getLocalizedMessage());
            }
        });



    }

    */


    private void save_dashboard() {


        list_dash_category = new ArrayList<>();
        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<com.project.milan.pojos.read_dashboard_categories_structure.Response> call = apiService.read_dashboard_category(Constants.api_key);

        call.enqueue(new Callback<com.project.milan.pojos.read_dashboard_categories_structure.Response>() {
            @Override
            public void onResponse(Call<com.project.milan.pojos.read_dashboard_categories_structure.Response> call, Response<com.project.milan.pojos.read_dashboard_categories_structure.Response> response) {


                if (response.body() != null) {
                    if (response.body().getResult().equals("1")) {


                        list_dash_category = new ArrayList<>();
                        list_dash_category.addAll(response.body().getDetails());
                       Constants.setToday_date(response.body().getDat());

                        for (DetailsItem row : list_dash_category) {
                            db.getDashGroupEntityDao().insert_single_details(new DashGroupEntity(0, Integer.parseInt(row.getSlno()), Integer.parseInt(row.getOrderNo()), row.getStyle(), row.getDisplayName(), 0, 0,row.getType(),row.getCount()));
                        }


                        save_dashboard_category();


                    }
                }


            }

            @Override
            public void onFailure(Call<com.project.milan.pojos.read_dashboard_categories_structure.Response> call, Throwable t) {

            }
        });


    }


//    @Override
//    public void delete(String item) {
//
//        //   Toast.makeText(getActivity()," "+item,Toast.LENGTH_LONG).show();
//
//        db.getSearchEntityDao().del(item);
//        int pos = -1;
//
//        for (com.project.zpace.pojos.search_box.DetailsItem row : list_search) {
//            pos = pos + 1;
//            if (row.getItemname().equals(item)) {
//                list_search.remove(pos);
//
//                adp = new SearchAdapter(getActivity(), list_search);
//                ac_search.setAdapter(adp);
//
//                break;
//            }
//        }
//
//        //   Toast.makeText(getActivity()," "+db.getSearchEntityDao().get_count(),Toast.LENGTH_LONG).show();
//
////        list_search.remove(new com.project.zpace.pojos.search_box.DetailsItem(item,true));
////        adp.notifyDataSetChanged();
//
//    }

//    @Override
//    public void search(String item) {
//
//
//        activity.hideSoftKeyboard(getActivity(), view);
//        Constants.sceen = Constants.search_result;
//
//
//        Fragment_Searchlist fragment = new Fragment_Searchlist();
//        Bundle args = new Bundle();
//        args.putString(Constants.Parcel2, item);
//        args.putString(Constants.fragment_heading, item);
//        fragment.setArguments(args);
//
//        // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, "Fragment_Searchlist").commit();
//        activity.hide_fragment_except("Fragment_Searchlist");
//
//        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Searchlist").commit();
//
//
//    }

    @Override
    public String test() {
        return txt_test.getText().toString();
    }

    public void show_live_data() {
        LiveData<Integer> liveData = db.getDashGroupEntityDao().get_count_of_downloaded_group();

        liveData.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer != null && integer > 0) {
                    show_the_screen();
                }
            }
        });
    }


}
