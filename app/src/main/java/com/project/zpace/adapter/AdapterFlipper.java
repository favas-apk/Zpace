package com.project.zpace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.zpace.R;


import java.util.List;

public class AdapterFlipper extends BaseAdapter {
    private Context context;
    private List<String> list;
    private  List<String> list_caption;

    public AdapterFlipper(Context context, List<String> list, List<String> list_caption){
        this.context = context;
        this.list = list;
        this.list_caption = list_caption;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String cpr = list.get(position);

        String cap = list_caption.get(position);
        LayoutInflater inflater;
        View view = null;
        
        try
        {
            inflater = LayoutInflater.from(context);
             view = inflater.inflate(R.layout.model_flipper_itemsb, null);
            TextView txt1 = (TextView) view.findViewById(R.id.txt1);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            txt1.setText(cap);

            Glide.with(context).load(cpr).fitCenter().centerCrop().into(imageView);







        }
        catch(Exception e)
        {
            
        }
        
      
        
      
      //  Picasso.with(context).load(cpr).fit().centerCrop().into(imageView);


        return view;
    }
}