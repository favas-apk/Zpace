package com.project.zpace.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.base.BaseActivity;
import com.project.zpace.database.appdb.Appdb;
import com.project.zpace.database.entities.CartEntity;
import com.project.zpace.fragment.Fragment_Cart;
import com.project.zpace.fragment.Fragment_Categories;
import com.project.zpace.fragment.Fragment_Orders;
import com.project.zpace.fragment.Fragment_Dashboard;
import com.project.zpace.fragment.Fragment_Profile;
import com.project.zpace.fragment.Fragment_SearchBefore;
import com.project.zpace.fragment.Fragment_Searchlist;
import com.project.zpace.fragment.Fragment_Shop_By_Category;
import com.project.zpace.fragment.Fragment_Single_View;
import com.project.zpace.fragment.Fragment_Wishlist;
import com.project.zpace.interfac.HomeInterface;

import com.project.zpace.pojos.read_item_by_stkid_cust_app.DetailsItem;
import com.theartofdev.edmodo.cropper.CropImage;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

import static maes.tech.intentanim.CustomIntent.customType;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, HomeInterface {

    private androidx.drawerlayout.widget.DrawerLayout drawer;
    private androidx.appcompat.widget.Toolbar toolbar;


    private com.google.android.material.navigation.NavigationView navigationView;


    private ImageView iv_cart, iv_ham_menu;
    private LinearLayout ll_search;
    private TextView txt_toobar_heading, txt_toobar_sub_heading, txtuser_or_shop, txt_cart_qty;
    ActionBarDrawerToggle actionBarDrawerToggle;

    private de.hdodenhof.circleimageview.CircleImageView iv_txt_cart_qty_back;
    private View headerview;
    private boolean flag_profile_pic = false;
    private BottomNavigationView bottomNavigationView;
    private Appdb db;
    private ConstraintLayout header;
    private String today_str = "";

    //final  Fragmenf2=new Fragment_Categories(),f3=new Fragment_Profile();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeb);
        db = Appdb.getDb_instance(getApplicationContext());

        init();

        Constants.setHomeInterface(this);


        //   final  Fragment f1=new Fragment_Dashboard();
        bottomNavigationView.setSelectedItemId(R.id.menu_home);


        iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Switch_to_fragment("Fragment_Cart");

            }
        });


        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show_heading("");
                show_sub_heading("");

                if (!Constants.sceen.equals(Constants.search_before)) {

                    Constants.sceen = Constants.search_before;
                    bottomNavigationView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);
                    bottomNavigationView.findViewById(R.id.uncheckedItem).setVisibility(View.GONE);

                    if (getSupportFragmentManager().findFragmentByTag("Fragment_SearchBefore") != null) {


                        if (getSupportFragmentManager().findFragmentByTag("Fragment_SearchBefore").isAdded()) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                            ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_SearchBefore"));

                            ft.commit();
                        } else {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                            ft.add(R.id.fragment_container, new Fragment_SearchBefore(), "Fragment_SearchBefore");

                            ft.commit();
                        }


                    } else {

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                        ft.add(R.id.fragment_container, new Fragment_SearchBefore(), "Fragment_SearchBefore");

                        ft.commit();


                    }

                    Constants.setCurrent_fragment(Constants.search_before);
                    hide_fragment_except("Fragment_SearchBefore");


                }

            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.menu_wish:


                        Switch_to_fragment("Fragment_Wishlist");


                        return true;


                    case R.id.menu_categories:

                        //    Constants.getFr_dash_interface().test();

                        if (!Constants.sceen.equals(Constants.category)) {

                            Constants.sceen = Constants.category;

                            //if the fragment exists, show it.

                            if (getSupportFragmentManager().findFragmentByTag("Fragment_Categories") != null) {


                                if (getSupportFragmentManager().findFragmentByTag("Fragment_Categories").isAdded()) {
                                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                    ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Categories"));

                                    ft.commit();
                                } else {
                                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                    ft.add(R.id.fragment_container, new Fragment_Categories(), "Fragment_Categories");
                                    //   ft.addToBackStack(null);
                                    ft.commit();
                                }


                            } else {

                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                ft.add(R.id.fragment_container, new Fragment_Categories(), "Fragment_Categories");
                                //   ft.addToBackStack(null);
                                ft.commit();


                                //  getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Fragment_Categories(), "Fragment_Categories").commit();

                            }

                            Constants.setCurrent_fragment(Constants.category);
                            hide_fragment_except("Fragment_Categories");


                            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Categories(), "Fragment_Categories").commit();

                            txt_toobar_heading.setText(getString(R.string.categories));
                            txt_toobar_sub_heading.setText("");


                            navigationView.getMenu().getItem(0).setChecked(false);
                            navigationView.getMenu().getItem(1).setChecked(false);
                            navigationView.getMenu().getItem(2).setChecked(false);
                            navigationView.getMenu().getItem(3).setChecked(false);
                            navigationView.getMenu().getItem(4).setChecked(false);


                        }
                        show_header();
                        return true;

                    case R.id.menu_home:

                        if (!Constants.sceen.equals(Constants.dash)) {

                            Constants.sceen = Constants.dash;

                            if (getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard") != null) {
                                //if the fragment exists, show it.

                                if (getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard").isAdded()) {
                                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                    ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard"));
                                    ft.commit();
                                } else {
                                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                    ft.add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard");
                                    ft.commit();
                                }





                            } else {
                                Constants.sceen = Constants.dash;
                                //    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard").commit();

                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                ft.add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard");
                                ft.commit();

                            }

                            Constants.setCurrent_fragment(Constants.dash);
                            hide_fragment_except("Fragment_Dashboard");


                            //   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard").commit();

                            txt_toobar_heading.setText("");
                            txt_toobar_sub_heading.setText("");


                        }
                        show_header();
                        return true;


                    case R.id.menu_profile:
                        hide_header();


                        if (!Constants.sceen.equals(Constants.profile)) {


                            Constants.sceen = Constants.profile;

                            if (getSupportFragmentManager().findFragmentByTag("Fragment_Profile") != null) {

                                if (getSupportFragmentManager().findFragmentByTag("Fragment_Profile").isAdded()) {
                                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                    ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Profile"));

                                    ft.commit();
                                } else {
                                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                    ft.add(R.id.fragment_container, new Fragment_Profile(), "Fragment_Profile");
                                    //   ft.addToBackStack(null);
                                    ft.commit();
                                }


                            } else {
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                ft.add(R.id.fragment_container, new Fragment_Profile(), "Fragment_Profile");

                                ft.commit();
                            }


                            Constants.setCurrent_fragment(Constants.profile);


                            hide_fragment_except("Fragment_Profile");


                            txt_toobar_heading.setText("My Profile");
                            txt_toobar_sub_heading.setText("");


                            navigationView.getMenu().getItem(0).setChecked(false);
                            navigationView.getMenu().getItem(1).setChecked(false);
                            navigationView.getMenu().getItem(2).setChecked(false);
                            navigationView.getMenu().getItem(3).setChecked(false);
                            navigationView.getMenu().getItem(4).setChecked(false);


                        }

                        return true;


                }

                return false;
            }
        });


        Show_hide_logout();


        setSupportActionBar(toolbar);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(HomeActivity.this);


        if (savedInstanceState == null) {


            String fname = "", stockid = "";

            try {

                com.project.zpace.pojos.read_item_by_stkid_cust_app.DetailsItem obj = (DetailsItem) getIntent().getSerializableExtra("Serialized_Details");


                if (obj == null) {
                    Constants.sceen = Constants.dash;
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard").commit();
                    navigationView.setCheckedItem(R.id.nav_home);
                    Constants.setCurrent_fragment(Constants.dash);
                } else {


                    //     fname = getIntent().getExtras().getString("fname");
                    //       stockid = getIntent().getExtras().getString("Stockid");


                    Constants.sceen = Constants.single;

                    Fragment_Single_View fragment = new Fragment_Single_View();
                    Bundle args = new Bundle();

                    args.putString("from", "outside");
                    //      args.putString(Constants.Parcel_single_fname, fname);

                    args.putSerializable("Serialized_Details", (Serializable) obj);
                    fragment.setArguments(args);

                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Single_View").commit();
                    Constants.setCurrent_fragment(Constants.single);
                    unselect_all_navigation_items();
                    bottomNavigationView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);
                    bottomNavigationView.findViewById(R.id.uncheckedItem).setVisibility(View.GONE);

                }

            } catch (Exception e) {

            }

//
//            if (!fname.equals("")) {
//
//
//            } else {
//
//
//            }


            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard").commit();

        }

//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                // showSnack_W("ok");
//
//
//                if (Constants.from.equals(Constants.category)) {
//
//                    Constants.sceen = Constants.category;
//
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Categories(), "Fragment_Categories").commit();
//
//                    txt_toobar_heading.setText(getString(R.string.categories));
//                    txt_toobar_sub_heading.setText("");
//
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                    actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
//
//
//                    navigationView.getMenu().getItem(0).setChecked(false);
//                    navigationView.getMenu().getItem(1).setChecked(false);
//                    navigationView.getMenu().getItem(2).setChecked(false);
//                    navigationView.getMenu().getItem(3).setChecked(false);
//                    navigationView.getMenu().getItem(4).setChecked(false);
//
//                    bottomNavigationView.setSelectedItemId(R.id.menu_categories);
//
//
//
//
//                }
//                else {
//
//
//                    if (!actionBarDrawerToggle.isDrawerIndicatorEnabled()) {
//
//                        Constants.sceen = Constants.dash;
//                     //   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Dashboard()).commit();
//
//                        if( getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard") != null) {
//                            //if the fragment exists, show it.
//
//
//
//                            getSupportFragmentManager().beginTransaction().show(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard"))).commit();
//                        }
//                        else
//                        {
//                            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard").commit();
//                        }
//
//
//
//                        navigationView.setCheckedItem(R.id.nav_home);
//                        getSupportFragmentManager().popBackStack();
//                    } else {
//                        drawer.openDrawer(GravityCompat.START);
//                    }
//
//                }
//
//
//            }
//        });


        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Constants.sceen = Constants.profile;
                drawer.closeDrawer(GravityCompat.START);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Profile(), "Fragment_Profile").commit();

                txt_toobar_heading.setText(getString(R.string.myprofile));
                txt_toobar_sub_heading.setText("");


                bottomNavigationView.getMenu().findItem(R.id.menu_profile).setChecked(true);


            }
        });


        iv_ham_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   showSnack_W("Bismillah");

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);


                }


            }
        });


        if (db.getLoginEntityDao().get_count() == 0) {
            txtuser_or_shop.setText(getString(R.string.milan));
        } else {
            txtuser_or_shop.setText("Hi " + db.getLoginEntityDao().get_all_datas().get(0).getName());
        }


        //   showSnack_S(""+db.getWishEntityDao().get_total_count());


        //    show_live_data();

    }

//    public void show_live_data() {
//        LiveData<Integer> liveData = db.getWishEntityDao().get_total_count();
//
////        liveData.observe(this, new Observer<Integer>() {
////            @Override
////            public void onChanged(Integer integer) {
////                showSnack_S("" + integer);
////            }
////        });
//
//    }

    public void show_live_cart_count() {
        LiveData<Integer> liveData = db.getCartEntityDao().get_total_qty();

        liveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                //      showSnack_S("" + integer);

                if (integer != null && integer > 0) {


                    txt_cart_qty.setVisibility(View.VISIBLE);
                    iv_txt_cart_qty_back.setVisibility(View.VISIBLE);

                    txt_cart_qty.setText(String.valueOf(integer));

//                    if (Constants.sceen.equals(Constants.cart)) {
//                        for (CartEntity row : db.getCartEntityDao().get_all_datas()) {
//
//                        }
//                    }


                } else {
                    hide_search_cart();
                }


            }
        });

    }


    private void init() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        headerview = navigationView.getHeaderView(0);


        iv_cart = findViewById(R.id.iv_cart);
        txt_toobar_heading = findViewById(R.id.txt_toobar_heading);

        ll_search = findViewById(R.id.ll_search);


        txt_toobar_sub_heading = findViewById(R.id.txt_toobar_sub_heading);
        bottomNavigationView = findViewById(R.id.bot_nav);
        iv_ham_menu = findViewById(R.id.iv_ham_menu);

        txtuser_or_shop = headerview.findViewById(R.id.txtuser_or_shop);
        header = findViewById(R.id.header);
        txt_cart_qty = findViewById(R.id.txt_cart_qty);
        iv_txt_cart_qty_back = findViewById(R.id.iv_txt_cart_qty_back);
    }


    @Override
    public void onBackPressed() {


//        Fragment myFragment;
//        myFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//        String tag = (String) myFragment.getTag();
//
//        if (tag == null) {
//            if (drawer.isDrawerOpen(GravityCompat.START)) {
//                drawer.closeDrawer(GravityCompat.START);
//            } else {
//
//                Constants.sceen="";
//                super.onBackPressed();
//            }
//        } else {

        //    if (tag.equals("Fragment_Orders") || tag.equals("Fragment_Wishlist") || tag.equals("Fragment_Cart") || tag.equals("Fragment_Profile") || tag.equals("Fragment_Categories") || tag.equals("Fragment_Searchlist") )


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (Constants.sceen.equals(Constants.search_result) || Constants.sceen.equals(Constants.section) || Constants.sceen.equals(Constants.order) || Constants.sceen.equals(Constants.cart) || Constants.sceen.equals(Constants.wish) || Constants.sceen.equals(Constants.single) || Constants.getCurrent_fragment().equals(Constants.category) || Constants.getCurrent_fragment().equals(Constants.profile)) {


            if (!Constants.sceen.equals(Constants.dash)) {
                bottomNavigationView.setSelectedItemId(R.id.menu_home);
                Constants.sceen = Constants.dash;

                navigationView.getMenu().getItem(0).setChecked(true);
                navigationView.getMenu().getItem(1).setChecked(false);
                navigationView.getMenu().getItem(2).setChecked(false);
                navigationView.getMenu().getItem(3).setChecked(false);
                navigationView.getMenu().getItem(4).setChecked(false);

            }


        }

//            else if (tag.equals("Fragment_Section")) {
//
//
//                if (Constants.from.equals(Constants.category)) {
//                    Constants.sceen = Constants.category;
//
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Categories(), "Fragment_Categories").commit();
//
//                    txt_toobar_heading.setText(getString(R.string.categories));
//                    txt_toobar_sub_heading.setText("");
//
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                    actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
//
//
//                    navigationView.getMenu().getItem(0).setChecked(false);
//                    navigationView.getMenu().getItem(1).setChecked(false);
//                    navigationView.getMenu().getItem(2).setChecked(false);
//                    navigationView.getMenu().getItem(3).setChecked(false);
//                    navigationView.getMenu().getItem(4).setChecked(false);
//
//                    bottomNavigationView.setSelectedItemId(R.id.menu_categories);
//
//
//                } else {
//                    Constants.sceen = Constants.dash;
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard").commit();
//
//                    txt_toobar_heading.setText("");
//                    txt_toobar_sub_heading.setText("");
//                    unlock_nav_drawer();
//
//                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                    actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
//                    bottomNavigationView.setSelectedItemId(R.id.menu_home);
//
//                    //   navigationView.setCheckedItem(R.id.nav_home);
//                    navigationView.getMenu().getItem(0).setChecked(true);
//                    navigationView.getMenu().getItem(1).setChecked(false);
//                    navigationView.getMenu().getItem(2).setChecked(false);
//                    navigationView.getMenu().getItem(3).setChecked(false);
//                    navigationView.getMenu().getItem(4).setChecked(false);
//
//
//                }
//
//
//            }
        else if (Constants.getCurrent_fragment().equals(Constants.dash)) {

            Constants.sceen = "";
            super.onBackPressed();

//                if (drawer.isDrawerOpen(GravityCompat.START)) {
//                    drawer.closeDrawer(GravityCompat.START);
//                }
//                else {
//                       Constants.sceen="";
//                       super.onBackPressed();
//                }


        }

        //  }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        try {

            if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
                //  Toast.makeText(getApplicationContext(),"Bismillah1",Toast.LENGTH_SHORT).show();

                Uri res = CropImage.getPickImageResultUri(this, data);

                startcrop(res);
                //
            } else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
                Uri res = UCrop.getOutput(data);
//
                flag_profile_pic = true;

                Fragment_Profile fragment = (Fragment_Profile) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                //    fragment.setIv_profile(res);


                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), res);
                    // Log.d(TAG, String.valueOf(bitmap));

                    //    saveOutput(bitmap);
                    saveReceivedImage(bitmap, "prof");


                    //       iv3.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        } catch (Exception e) {

        }


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:


                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_Dashboard(),"Fragment_Dashboard").commit();


                if (!Constants.sceen.equals(Constants.dash)) {

                    Constants.sceen = Constants.dash;


                    if (getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard") != null) {
                        //if the fragment exists, show it.

                        if (getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard").isAdded()) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                            ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard"));
                            ft.commit();
                        } else {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                            ft.add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard");
                            ft.commit();
                        }


                    } else {

                        Constants.sceen = Constants.dash;
                        //    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard").commit();

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                        ft.add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard");
                        ft.commit();
                    }

                    Constants.setCurrent_fragment(Constants.dash);
                    hide_fragment_except("Fragment_Dashboard");


                    txt_toobar_heading.setText("");
                    txt_toobar_sub_heading.setText("");

                    bottomNavigationView.setSelectedItemId(R.id.menu_home);


                }


                break;

            case R.id.nav_order:

                Switch_to_fragment("Fragment_Orders");

                break;


            case R.id.nav_wish_list:

                Switch_to_fragment("Fragment_Wishlist");


                break;


            case R.id.nav_cart:
                Switch_to_fragment("Fragment_Cart");

                break;


            case R.id.nav_shop_by_category:


                if (!Constants.sceen.equals(Constants.shop_by_category)) {


                    Constants.sceen = Constants.shop_by_category;
                    txt_toobar_heading.setText("");
                    txt_toobar_sub_heading.setText("");
                    bottomNavigationView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);
                    bottomNavigationView.findViewById(R.id.uncheckedItem).setVisibility(View.GONE);
                    hide_fragment_except("Fragment_Shop_By_Category");

                    if (getSupportFragmentManager().findFragmentByTag("Fragment_Shop_By_Category") != null) {


                        if (getSupportFragmentManager().findFragmentByTag("Fragment_Shop_By_Category").isAdded()) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                            ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Shop_By_Category"));

                            ft.commit();
                        } else {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                            ft.add(R.id.fragment_container, new Fragment_Categories(), "Fragment_Shop_By_Category");
                            //   ft.addToBackStack(null);
                            ft.commit();
                        }


                    } else {

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                        ft.add(R.id.fragment_container, new Fragment_Shop_By_Category(), "Fragment_Shop_By_Category");
                        ft.commit();


                    }


                }


                break;

            case R.id.nav_logout:


                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(false);

                final View customLayout
                        = getLayoutInflater()
                        .inflate(
                                R.layout.custom_layout_logout,
                                null);

                builder.setView(customLayout);

                TextView txtmsg = customLayout.findViewById(R.id.txtmsg);
                TextView txt_logout = customLayout.findViewById(R.id.txt_logout);
                TextView txt_cancel = customLayout.findViewById(R.id.txt_cancel);

                txtmsg.setText(db.getLoginEntityDao().get_all_datas().get(0).getName() + getString(R.string.logout_warning));


                AlertDialog dialog = builder.create();
                dialog.show();


                txt_logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.getLoginEntityDao().del_all();
                        db.getCartEntityDao().del_all();
                        db.getDelAdressEntityDao().del_all();
                        finish();
                    }
                });

                txt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        unselect_all_navigation_items();
                        dialog.cancel();


                    }
                });


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    @Override
//    public void showHamburgerIcon() {
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
//        iv_bell.setVisibility(View.VISIBLE);
//        iv_exit.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void showBackIcon() {
//        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        iv_bell.setVisibility(View.GONE);
//        iv_exit.setVisibility(View.GONE);
//
//    }

    @Override
    public void Show_hide_logout() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();

        if (db.getLoginEntityDao().get_count() > 0) {
            nav_Menu.findItem(R.id.nav_logout).setVisible(true);
        } else {
            nav_Menu.findItem(R.id.nav_logout).setVisible(false);
        }


    }

    @Override
    public void hide_bottom_nav() {
        bottomNavigationView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void show_bottom_nav() {
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void show_search_cart() {
        ll_search.setVisibility(View.VISIBLE);
        iv_cart.setVisibility(View.VISIBLE);


        show_live_cart_count();

    }

    @Override
    public void hide_search_cart() {


        txt_cart_qty.setVisibility(View.INVISIBLE);
        iv_txt_cart_qty_back.setVisibility(View.INVISIBLE);


    }


    private void get_today_date() {

    }


    @Override
    public void Switch_to_fragment(String fragment_tag) {


        if (fragment_tag.equals("Fragment_Searchlist")) {
            if (!Constants.sceen.equals(Constants.search_result)) {

                Constants.sceen = Constants.search_result;
                bottomNavigationView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);
                bottomNavigationView.findViewById(R.id.uncheckedItem).setVisibility(View.GONE);

                if (getSupportFragmentManager().findFragmentByTag("Fragment_Searchlist") != null) {


                    if (getSupportFragmentManager().findFragmentByTag("Fragment_Searchlist").isAdded()) {


                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

                        ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Searchlist"));

                        ft.commit();
                    } else {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                        ft.add(R.id.fragment_container, new Fragment_Searchlist(), "Fragment_Searchlist");

                        ft.commit();
                    }


                } else {

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                    ft.add(R.id.fragment_container, new Fragment_Searchlist(), "Fragment_Searchlist");

                    ft.commit();


                }

                Constants.setCurrent_fragment(Constants.search_result);
                hide_fragment_except("Fragment_Searchlist");


            }
        } else if (fragment_tag.equals("Fragment_Cart")) {


            if (db.getLoginEntityDao().get_count() > 0) {

                if (!Constants.sceen.equals(Constants.cart)) {


                    if (!Constants.getToday_date().equals("")) {
                        Constants.sceen = Constants.cart;
                        bottomNavigationView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);
                        bottomNavigationView.findViewById(R.id.uncheckedItem).setVisibility(View.GONE);

                        if (getSupportFragmentManager().findFragmentByTag("Fragment_Cart") != null) {


                            if (getSupportFragmentManager().findFragmentByTag("Fragment_Cart").isAdded()) {
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Cart"));

                                ft.commit();
                            } else {
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                                ft.add(R.id.fragment_container, new Fragment_Cart(), "Fragment_Cart");

                                ft.commit();
                            }


                        } else {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                            ft.add(R.id.fragment_container, new Fragment_Cart(), "Fragment_Cart");

                            ft.commit();
                        }
                        Constants.setCurrent_fragment(Constants.cart);
                        hide_fragment_except("Fragment_Cart");

                    }
                    else
                    {
                        showSnack_W(getString(R.string.plz_chk_net));
                    }


                }

            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.Plz_do_login), Toast.LENGTH_LONG).show();
            }
        } else if (fragment_tag.equals("Fragment_Wishlist")) {
            show_header();
            bottomNavigationView.getMenu().findItem(R.id.menu_wish).setChecked(true);
            txt_toobar_heading.setText(getString(R.string.mywishlist));
            txt_toobar_sub_heading.setText("");

            navigationView.getMenu().getItem(0).setChecked(false);
            navigationView.getMenu().getItem(1).setChecked(false);
            navigationView.getMenu().getItem(2).setChecked(true);
            navigationView.getMenu().getItem(3).setChecked(false);
            navigationView.getMenu().getItem(4).setChecked(false);

            if (!Constants.sceen.equals(Constants.wish)) {

                Constants.sceen = Constants.wish;


                if (getSupportFragmentManager().findFragmentByTag("Fragment_Wishlist") != null) {


                    if (getSupportFragmentManager().findFragmentByTag("Fragment_Wishlist").isAdded()) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                        ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Wishlist"));

                        ft.commit();
                    } else {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                        ft.add(R.id.fragment_container, new Fragment_Wishlist(), "Fragment_Wishlist");

                        ft.commit();
                    }


                } else {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                    ft.add(R.id.fragment_container, new Fragment_Wishlist(), "Fragment_Wishlist");

                    ft.commit();
                }


                Constants.setCurrent_fragment(Constants.wish);
                hide_fragment_except("Fragment_Wishlist");

            }


        }
        else if(fragment_tag.equals("Fragment_Orders"))
        {

            if (!Constants.sceen.equals(Constants.order)) {
                Constants.sceen = Constants.order;


                if (getSupportFragmentManager().findFragmentByTag("Fragment_Orders") != null) {
                    //if the fragment exists, show it.
                    if (getSupportFragmentManager().findFragmentByTag("Fragment_Orders").isAdded()) {


                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);

                        ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Orders"));

                        ft.commit();
                    } else {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                        ft.add(R.id.fragment_container, new Fragment_Orders(), "Fragment_Orders");

                        ft.commit();
                    }


                } else {

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                    ft.add(R.id.fragment_container, new Fragment_Orders(), "Fragment_Orders");

                    ft.commit();

                }

                Constants.setCurrent_fragment(Constants.order);
                hide_fragment_except("Fragment_Orders");


                txt_toobar_heading.setText(getString(R.string.orders));
                txt_toobar_sub_heading.setText("");


                bottomNavigationView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);
                bottomNavigationView.findViewById(R.id.uncheckedItem).setVisibility(View.GONE);

            }
        }
//        else if(fragment_tag.equals("Fragment_Dashboard"))
//        {
//            if (!Constants.sceen.equals(Constants.dash)) {
//
//                Constants.sceen = Constants.dash;
//
//                if (getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard") != null) {
//                    //if the fragment exists, show it.
//
//                    if (getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard").isAdded()) {
//                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
//                        ft.show(getSupportFragmentManager().findFragmentByTag("Fragment_Dashboard"));
//                        ft.commit();
//                    } else {
//                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
//                        ft.add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard");
//                        ft.commit();
//                    }
//
//
//
//
//
//                } else {
//                    Constants.sceen = Constants.dash;
//                    //    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard").commit();
//
//                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
//                    ft.add(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard");
//                    ft.commit();
//
//                }
//
//                Constants.setCurrent_fragment(Constants.dash);
//                hide_fragment_except("Fragment_Dashboard");
//
//
//                //   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_Dashboard(), "Fragment_Dashboard").commit();
//
//                txt_toobar_heading.setText("");
//                txt_toobar_sub_heading.setText("");
//
//
//            }
//        }

    }

    @Override
    public void do_payment() {
        Intent in = new Intent(getApplicationContext(), PaymentActivity.class);
        startActivity(in);


    }


    private void startcrop(@NonNull Uri uri) {

        try {
            String destn = "profile2.jpg";

            UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getCacheDir(), destn)));
            uCrop.withAspectRatio(1, 1);
            uCrop.withMaxResultSize(225, 225);
            uCrop.withOptions(getoptions());
            uCrop.start(HomeActivity.this);
        } catch (Exception e) {
            //Timber.e("erroe");
        }

    }


    private UCrop.Options getoptions() {
        UCrop.Options opt = new UCrop.Options();
        opt.setCompressionQuality(70);
        //  opt.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        opt.setHideBottomControls(false);
        opt.setFreeStyleCropEnabled(true);

        return opt;
    }


    private void saveReceivedImage(Bitmap bitmap, String imageName) {
        try {


            File path = new File(getApplicationContext().getFilesDir(), File.separator);
            //     File path = new File(getApplicationContext().getFilesDir(), "Recycler" + File.separator + "Images");
            if (!path.exists()) {
                path.mkdirs();
            }
            File outFile = new File(path, imageName + ".jpeg");
            FileOutputStream outputStream = new FileOutputStream(outFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
        } catch (FileNotFoundException e) {
            // Log.e(TAG, "Saving received message failed with", e);
        } catch (IOException e) {
            //   Log.e(TAG, "Saving received message failed with", e);
        }
    }


    @Override
    public void show_heading(String heading) {

        txt_toobar_heading.setText(heading);
        bottomNavigationView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);
        bottomNavigationView.findViewById(R.id.uncheckedItem).setVisibility(View.GONE);

    }

    @Override
    public void show_sub_heading(String subheading) {
        txt_toobar_sub_heading.setText(subheading);
    }

    @Override
    public void hide_hamburger() {
        //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //   actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
    }

    @Override
    public void unselect_all_navigation_items() {

        navigationView.getMenu().getItem(0).setChecked(false);
        navigationView.getMenu().getItem(1).setChecked(false);
        navigationView.getMenu().getItem(2).setChecked(false);
        navigationView.getMenu().getItem(3).setChecked(false);
        navigationView.getMenu().getItem(4).setChecked(false);
        navigationView.getMenu().getItem(5).setChecked(false);

    }

    @Override
    public void select_navigation_item(int position) {
        navigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void select_bottom_navigation_item(int position) {
        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
    }

//    @Override
//    public void lock_nav_drawer() {
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//    }

    @Override
    public void unlock_nav_drawer() {
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void show_back() {
        //   actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void show_ham() {
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //    actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

    }


    public void hide_header() {
        header.setVisibility(View.GONE);
    }


    public void show_header() {
        header.setVisibility(View.VISIBLE);
    }
}
