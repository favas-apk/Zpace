package com.project.zpace.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.pojos.major_category.Detail;

import java.util.List;


public class AdapterFrSingleViewGridSize extends BaseAdapter {


    int row_index = 0;
    Context context;
    List<com.project.zpace.pojos.read_size_and_details.DetailsItem> list;


    public AdapterFrSingleViewGridSize(Context context, List<com.project.zpace.pojos.read_size_and_details.DetailsItem> list) {
        this.context = context;
        this.list = list;

    }

    private class ViewHolder {
        //   CardView card1;

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


            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.model_fragment_dash_grid_size, null);


            holder = new ViewHolder();

            //  holder.card1 = view.findViewById(R.id.card1);

            holder.txt1 = view.findViewById(R.id.txt1);
            //  holder.cl1 = view.findViewById(R.id.cl1);


            view.setTag(holder);
        } else {


            holder = (ViewHolder) view.getTag();
        }


        final com.project.zpace.pojos.read_size_and_details.DetailsItem cpr = list.get(position);


        holder.txt1.setText(cpr.getSize());

        holder.txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                for (int j = 0; j < list.size(); j++) {
                    list.get(j).setSelected(false);
                }

                list.get(position).setSelected(true);
                row_index = position;
                notifyDataSetChanged();

                Constants.getFr_single_interface().set_prices_and_offers(cpr.getRate(), cpr.getOfferPrice(), cpr.getStkidFromServer(), cpr.getBuyQty(), cpr.getFreeQty(), cpr.getFreePercent(), cpr.getOfferEndDate(), "");
                Constants.getFr_single_interface().set_size_flag(cpr.getSize());


            }
        });

        if (cpr.isSelected()) {
            holder.txt1.setBackgroundColor(Color.parseColor("#567845"));
            holder.txt1.setTextColor(context.getResources().getColor(R.color.white));
        } else {


            holder.txt1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.txt1.setTextColor(context.getResources().getColor(R.color.black));
        }


        return view;
    }


}
