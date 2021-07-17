package com.project.zpace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.zpace.R;
import com.project.zpace.pojos.suggest.Detail;

import java.util.List;

public class AdapterSuggest extends RecyclerView.Adapter<AdapterSuggest.ViewHolderClass>  {



    Context context;
    List<Detail> list;




    public AdapterSuggest(Context context, List<Detail> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_suggest, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final Detail cpr = list.get(position);











//
        holder.txt1.setText(cpr.getName());


    //    Glide.with(context).load("https://neptonglobal.in/zpa/suggest/"+cpr.getSlno()+".jpg").fitCenter().centerCrop().into( holder.iv1);

       Glide.with(context).load("https://neptonglobal.in/zpa/suggests/"+cpr.getSlno()+".jpg").fitCenter().into( holder.iv1);

//
//









      //  holder.txtold.setBackgroundResource(R.color.color1);







    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txt1;
        ImageView iv1;



        public ViewHolderClass(View itemView) {
            super(itemView);



            txt1 = itemView.findViewById(R.id.txt1);

            iv1 = itemView.findViewById(R.id.iv1);



        }
    }





}
