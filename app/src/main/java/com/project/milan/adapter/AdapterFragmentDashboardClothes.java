package com.project.milan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.milan.R;
import com.project.milan.pojos.clothes.Detail;

import java.util.List;


public class AdapterFragmentDashboardClothes extends BaseAdapter {


    Context context;
    List<Detail> list;


    public AdapterFragmentDashboardClothes(Context context, List<Detail> list) {
        this.context = context;
        this.list = list;

    }

    private class ViewHolder {
     //   CardView card1;
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

            view = inflater.inflate(R.layout.model_fragment_dash_offer, null);


            holder = new ViewHolder();

          //  holder.card1 = view.findViewById(R.id.card1);
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



        Glide.with(context).load("https://neptonglobal.in/zpa/cloth/"+cpr.getSlno()+".jpg").fitCenter().into( holder.iv1);

   //     Glide.with(context).load("https://neptonglobal.in/zpa/clothes/"+cpr.getSlno()+".jpg").fitCenter().diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into( holder.iv1);



        return view;
    }




}
