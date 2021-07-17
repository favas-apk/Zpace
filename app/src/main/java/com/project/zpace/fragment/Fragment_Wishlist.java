package com.project.zpace.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.adapter.AdapterWish;
import com.project.zpace.database.appdb.Appdb;
import com.project.zpace.database.entities.WishEntity;
import com.project.zpace.interfac.WishlistInterface;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Wishlist extends Fragment implements WishlistInterface {

    private Appdb db;
    private View view;
    private RecyclerView recv;
    private List<WishEntity> list_wishlist;

    private AdapterWish adp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        db = Appdb.getDb_instance(getActivity());
        view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        init();
        show_wishlist();



        Constants.setWishlistInterface(this);

        return view;


    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private void init() {
        recv = view.findViewById(R.id.recv);


    }


    private void show_wishlist() {


        if (Constants.sceen.equals(Constants.wish)) {


            list_wishlist = new ArrayList<>();

            list_wishlist.addAll(db.getWishEntityDao().get_all_datas());


            adp = new AdapterWish(getActivity(), list_wishlist);

            LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            recv.setLayoutManager(lm);

            Constants.setFragment_wish_done_flag(1);


            recv.setAdapter(adp);


         //   txt_last.setText("ok");


        }
//
    }


    @Override
    public void remove_item_from_wishlist(String itemcode) {

       int j=-1;
       for(WishEntity row  :list_wishlist)
       {
           j=j+1;
           if(row.getItemcode().equals(itemcode))
           {
               db.getWishEntityDao().del(itemcode);
               list_wishlist.remove(j);

               adp.notifyDataSetChanged();
               break;

           }
       }






    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {


            show_wishlist();

        }



    }
}
