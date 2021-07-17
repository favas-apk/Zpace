package com.project.zpace.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.zpace.R;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.fragment.Fragment_Single_View;
import com.project.zpace.interfac.HomeInterface;
import com.project.zpace.pojos.offer.Detail;

import java.util.List;


public class AdapterFragmentDashboardOfferNew extends BaseAdapter {


    Context context;
    List<Detail> list;


    public AdapterFragmentDashboardOfferNew(Context context, List<Detail> list) {
        this.context = context;
        this.list = list;

    }

    private class ViewHolder {
        CardView card1;
        ImageView iv1;
        TextView txt1;
    //    ConstraintLayout cl1;


    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView;
        final ViewHolder holder;
        if (view == null) {



      /*      imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);


       */

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.model_fragment_dash_offer_strike, null);


            holder = new ViewHolder();

            holder.card1 = view.findViewById(R.id.card1);
            holder.iv1 = view.findViewById(R.id.iv1);
            holder.txt1 = view.findViewById(R.id.txt1);
          //  holder.cl1 = view.findViewById(R.id.cl1);

            view.setTag(holder);
        } else {
            //  imageView = (ImageView) view;

            holder = (ViewHolder) view.getTag();
        }
        //    imageView.setImageResource(mThumbIds[position]);


        final Detail cpr = list.get(position);
        holder.txt1.setText(cpr.getName());



        Glide.with(context).load("https://neptonglobal.in/zpa/offers/"+cpr.getSlno()+".jpg").fitCenter().into( holder.iv1);
    //    Glide.with(context).load("https://neptonglobal.in/zpa/offer/"+cpr.getSlno()+".jpg").fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into( holder.iv1);



        holder.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {


                Fragment_Single_View fragment = new Fragment_Single_View ();
                ((HomeActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment,"OptionsFragment").commit();

             //   ((HomeInterface) context).showBackIcon();
//                Dialog settingsDialog = new Dialog(context);
//                settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//
//
//                LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//                view1 = inflater1.inflate(R.layout.image_layout, null);
//
//                settingsDialog.setContentView(view1);
//                settingsDialog.show();
//
//                ImageView ivfull=    view1.findViewById(R.id.ivfull);
//
//
//
//                String profilepic1="https://neptonglobal.in/zpa/offers/"+cpr.getSlno()+".jpg";
//
//
//                Glide.with(context).load("https://neptonglobal.in/zpa/offers/"+cpr.getSlno()+".jpg").fitCenter().into( ivfull);

            }
        });





        return view;
    }




}
