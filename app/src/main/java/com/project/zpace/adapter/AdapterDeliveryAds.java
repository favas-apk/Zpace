package com.project.zpace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.database.entities.DeliveryAddressEntity;
import com.project.zpace.pojos.read_similar.DetailsItem;

import java.util.List;

public class AdapterDeliveryAds extends RecyclerView.Adapter<AdapterDeliveryAds.ViewHolderClass> {


    Context context;
    List<DeliveryAddressEntity> list;
    boolean choose;


    public AdapterDeliveryAds(Context context, List<DeliveryAddressEntity> list, boolean choose) {
        this.context = context;
        this.list = list;
        this.choose = choose;


    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_del_ads, parent, false);

        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final DeliveryAddressEntity cpr = list.get(position);

        if (cpr.getCity().equals(Constants.Add_an_del_ads)) {
            holder.txt_city_pin.setText(Constants.Add_an_del_ads);


        } else {
            holder.txt_name.setText(cpr.getFull_name());
            holder.txt_ads.setText(cpr.getHouse());
            holder.txt_area.setText(cpr.getArea());
            holder.txt_landmark.setText("" + cpr.getLandmark());
            holder.txt_city_pin.setText(cpr.getCity() + " " + cpr.getPincode());
            holder.txt_mob.setText(cpr.getMob());
        }


        holder.ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (choose) {
                    Constants.getFr_cartInterface().save_order(cpr.getSlno());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txt_name, txt_ads, txt_area, txt_landmark, txt_city_pin, txt_mob;

        LinearLayout ll1;


        public ViewHolderClass(View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);

            txt_ads = itemView.findViewById(R.id.txt_ads);

            txt_area = itemView.findViewById(R.id.txt_area);
//
            txt_landmark = itemView.findViewById(R.id.txt_landmark);
            txt_city_pin = itemView.findViewById(R.id.txt_city_pin);
//
            txt_mob = itemView.findViewById(R.id.txt_mob);
            ll1 = itemView.findViewById(R.id.ll1);

//


        }
    }


}
