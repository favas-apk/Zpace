package com.project.zpace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.zpace.R;

import java.util.List;

public class AdapterPhotosbycustomer extends RecyclerView.Adapter<AdapterPhotosbycustomer.ViewHolderClass>  {



    Context context;
    List<String> list;




    public AdapterPhotosbycustomer(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_photo_by_customer, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final String cpr = list.get(position);











//
        Glide.with(context).load("https://neptonglobal.in/zpa/photo_by_cust/"+cpr).fitCenter().into( holder.iv1);




//
//









      //  holder.txtold.setBackgroundResource(R.color.color1);







    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolderClass extends RecyclerView.ViewHolder {



        ImageView iv1;



        public ViewHolderClass(View itemView) {
            super(itemView);





            iv1 = itemView.findViewById(R.id.iv1);



        }
    }





}
