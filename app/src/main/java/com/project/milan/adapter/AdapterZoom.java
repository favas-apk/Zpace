package com.project.milan.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.database.appdb.Appdb;
import com.project.milan.database.entities.CartEntity;
import com.project.milan.database.entities.WishEntity;
import com.project.milan.model.model_offer_calculation;
import com.project.milan.pojos.read_web_rate_and_offers.DetailsItem_no_stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AdapterZoom extends RecyclerView.Adapter<AdapterZoom.ViewHolderClass> {


    Context context;
    List<String> list;


    List<DetailsItem_no_stock> list_stock_prob_items;

    public AdapterZoom(Context context, List<String> list) {
        this.context = context;
        this.list = list;


    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_cart_item, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final String cpr = list.get(position);












        String fname = cpr;

//
//        String[] fnames = new String[100];
//
//        if (!fname.equals("")) {
//            if (fname.contains(",")) {
//                fnames = fname.split(",");
//            } else {
//
//                fnames[0] = fname;
//            }
//
//        }


        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + fname + "th" + ".jpeg";
        Glide.with(context).load(profilepic)
                .sizeMultiplier(1.0f)

                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.iv1);









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
