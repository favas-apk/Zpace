package com.project.zpace.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.Utils;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.adapter.AdapterSearchlist;
import com.project.zpace.adapter.SearchAdapter;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.database.appdb.Appdb;
import com.project.zpace.database.entities.InventoryEntity;
import com.project.zpace.database.entities.SearchEntity;
import com.project.zpace.interfac.Fr_SearchBefore_Interface;
import com.project.zpace.pojos.search_box.DetailsItem;
import com.project.zpace.pojos.search_box.Response;

import net.gotev.speech.GoogleVoiceTypingDisabledException;
import net.gotev.speech.Speech;
import net.gotev.speech.SpeechDelegate;
import net.gotev.speech.SpeechRecognitionNotAvailable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class Fragment_SearchBefore extends Fragment implements Fr_SearchBefore_Interface {

    private View view;
    private Appdb db;
    private SearchAdapter adp;
    private ImageView iv_mic;
    private HomeActivity activity;
    private AutoCompleteTextView ac_search;
    private List<com.project.zpace.pojos.search_box.DetailsItem> list_search = new ArrayList<>();
    private boolean voice_flag = false;
    private LinearLayout ll_speech_progress;
    private SpeechDelegate speechDelegate;
    private  int api_cnt=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_searchbefore, container, false);


        init();
        Constants.setFr_searchBefore_interface(this);
        activity = (HomeActivity) getActivity();
        Speech.init(getActivity(), getActivity().getPackageName());
        db = Appdb.getDb_instance(getActivity());


        db.getInvEntityDao().del_all();
        read_items_for_search_box();

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
//
//            }
//        });


        ac_search.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {


                    if (!ac_search.getText().toString().trim().equals("")) {
                        activity.hideSoftKeyboard(getActivity(), view);
                    //    Constants.sceen = Constants.search_result;
                        db.getSearchEntityDao().inser_search_details(new SearchEntity(0, ac_search.getText().toString().trim()));


//                        Fragment_Searchlist fragment = new Fragment_Searchlist();
//                        Bundle args = new Bundle();
//                        args.putString(Constants.Parcel2, ac_search.getText().toString().trim().toLowerCase());
//                        args.putString(Constants.fragment_heading, ac_search.getText().toString().trim().toLowerCase());
//                        fragment.setArguments(args);


                        Constants.setSearch_word(ac_search.getText().toString().trim().toLowerCase());
                        Constants.getHomeInterface().Switch_to_fragment("Fragment_Searchlist");





                        //           getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, "Fragment_Searchlist").commit();
                     //   activity.hide_fragment_except("Fragment_Searchlist");

                     //   getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Searchlist").commit();


                    }


                }
                return false;


            }

        });


        iv_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                voice_flag = true;

                ac_search.setVisibility(View.GONE);
                ll_speech_progress.setVisibility(View.VISIBLE);
                listen();

//
//
            }
        });

        return view;


    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private void init() {
        iv_mic = view.findViewById(R.id.iv_mic);
        ac_search = view.findViewById(R.id.ac_search1);
        ll_speech_progress = view.findViewById(R.id.ll_speech_progress);
    }


    private void read_items_for_search_box() {


    //    if (list_search.size() == 0) {

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

            Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

            Call<com.project.zpace.pojos.search_box.Response> call = apiService.read_item_for_search_box(Constants.api_key);

            call.enqueue(new Callback<com.project.zpace.pojos.search_box.Response>() {
                @Override
                public void onResponse(Call<com.project.zpace.pojos.search_box.Response> call, retrofit2.Response<Response> response) {



                        if (response.body() != null) {


                            if (response.body().getResult().equals("1")) {

                                for(DetailsItem row: response.body().getDetails())
                                {
                                    db.getInvEntityDao().insert_inv_item(new InventoryEntity(0,row.getItemname()));
                                }

                                set_the_adapter();



                            }

                        }




                }

                @Override
                public void onFailure(Call<com.project.zpace.pojos.search_box.Response> call, Throwable t) {

                }
            });

    //    }
    }





        private void listen() {

        try {
            // you must have android.permission.RECORD_AUDIO granted at this point
            Speech.getInstance().startListening(new SpeechDelegate() {
                @Override
                public void onStartOfSpeech() {
                    Log.i("speech", "speech recognition is now active");
                }

                @Override
                public void onSpeechRmsChanged(float value) {
                    Log.d("speech", "rms is now: " + value);
                }

                @Override
                public void onSpeechPartialResults(List<String> results) {
                    StringBuilder str = new StringBuilder();
                    for (String res : results) {
                        str.append(res).append(" ");
                    }

                    Log.i("speech", "partial result: " + str.toString().trim());
                }

                @Override
                public void onSpeechResult(String result) {

                    ll_speech_progress.setVisibility(View.GONE);
                    ac_search.setVisibility(View.VISIBLE);


                    if (!result.trim().equals("")) {

                        ac_search.setText("" + result);

                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                //    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Searchlist(), "Fragment_Searchlist").commit();


                                if (!ac_search.getText().toString().trim().equals("")) {
                                    activity.hideSoftKeyboard(getActivity(), view);
                                 //   Constants.sceen = Constants.search_result;
                                    //    db.getSearchEntityDao().inser_search_details(new SearchEntity(0, ac_search.getText().toString().trim()));


//                                    Fragment_Searchlist fragment = new Fragment_Searchlist();
//                                    Bundle args = new Bundle();
//                                    args.putString(Constants.Parcel2, ac_search.getText().toString().trim().toLowerCase());
//                                    args.putString(Constants.fragment_heading, ac_search.getText().toString().trim().toLowerCase());
//                                    fragment.setArguments(args);
//
//
//
//
//                                    activity.hide_fragment_except("Fragment_Searchlist");
//
//                                    getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Searchlist").commit();



                                    Constants.setSearch_word(ac_search.getText().toString().trim().toLowerCase());
                                    Constants.getHomeInterface().Switch_to_fragment("Fragment_Searchlist");



                                }


                            }
                        }, 1000);


                        //

                    }


//
//
//                    Log.i("speech", "result: " + result);
                }
            });
        } catch (SpeechRecognitionNotAvailable exc) {
            ll_speech_progress.setVisibility(View.GONE);

            Log.e("speech", "Speech recognition is not available on this device!");

            //   Toast.makeText()
            ((HomeActivity) getActivity()).showSnack_W(getString(R.string.speech_recog_not));

            // You can prompt the user if he wants to install Google App to have
            // speech recognition, and then you can simply call:
            //
            // SpeechUtil.redirectUserToGoogleAppOnPlayStore(this);
            //
            // to redirect the user to the Google App page on Play Store
        } catch (GoogleVoiceTypingDisabledException exc) {
            ll_speech_progress.setVisibility(View.GONE);

            Log.e("speech", "Google voice typing must be enabled!");
            ((HomeActivity) getActivity()).showSnack_W(getString(R.string.google_voice_type_must_be_enabled));
        }

    }

    @Override
    public void delete(String item) {

        //   Toast.makeText(getActivity()," "+item,Toast.LENGTH_LONG).show();

        db.getSearchEntityDao().del(item);
        int pos = -1;

        for (com.project.zpace.pojos.search_box.DetailsItem row : list_search) {
            pos = pos + 1;
            if (row.getItemname().equals(item)) {
                list_search.remove(pos);

                adp = new SearchAdapter(getActivity(), list_search);
                ac_search.setAdapter(adp);

                break;
            }
        }

        //   Toast.makeText(getActivity()," "+db.getSearchEntityDao().get_count(),Toast.LENGTH_LONG).show();

//        list_search.remove(new com.project.zpace.pojos.search_box.DetailsItem(item,true));
//        adp.notifyDataSetChanged();

    }

    @Override
    public void search(String item) {


        activity.hideSoftKeyboard(getActivity(), view);
      //  Constants.sceen = Constants.search_result;


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


        Constants.setSearch_word(item);
        Constants.getHomeInterface().Switch_to_fragment("Fragment_Searchlist");


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            Speech.getInstance().shutdown();
        } catch (Exception e) {

        }


        }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            api_cnt=0;
            set_the_adapter();
        }


    }



    private void set_the_adapter()
    {

        if (db.getInvEntityDao().get_count()>10) {

            ac_search.setAdapter(null);
            list_search=new ArrayList<>();


            for (String item : db.getSearchEntityDao().get_all_datas()) {

                list_search.add(new com.project.zpace.pojos.search_box.DetailsItem(item, true));

            }

            for(String item:db.getInvEntityDao().get_all_datas())
            {
                list_search.add(new com.project.zpace.pojos.search_box.DetailsItem(item, false));
            }




              adp = new SearchAdapter(getActivity(),  list_search);
            if (Constants.sceen.equals(Constants.search_before)){
                ac_search.setAdapter(adp);
            }



        }
        else
        {
            if(api_cnt<2)
            {
                api_cnt=api_cnt+1;
                read_items_for_search_box();
            }

        }


    }


}
