package com.project.milan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.pojos.read_similar.DetailsItem;

import java.util.List;

public class AdapterSimilarProduct extends RecyclerView.Adapter<AdapterSimilarProduct.ViewHolderClass>  {



    Context context;
    List<DetailsItem> list;





    public AdapterSimilarProduct(Context context, List<DetailsItem> list) {
        this.context = context;
        this.list = list;


    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_similar_products, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final DetailsItem cpr = list.get(position);





        String fname = cpr.getFname();


        String[] fnames = new String[100];

        if (!fname.equals("")) {
            if (fname.contains(",")) {
                fnames = fname.split(",");
            } else {

                fnames[0] = fname;
            }

        }

        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + fnames[0] +"th"+ ".jpeg";
        Glide.with(context).load(profilepic)
                .sizeMultiplier(1.0f)
                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.iv1);

        holder.txt_name.setText(cpr.getItemname());



        holder.txt_rate.setAmount(Float.parseFloat(cpr.getRate()));


//        Glide.with(context).load("https://neptonglobal.in/zpa/offers/1.jpg").fitCenter().into( holder.iv1);




//
//





holder.ll1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {



        Constants.getFr_single_interface().load_view_again(cpr);

    }
});











    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolderClass extends RecyclerView.ViewHolder {



        ImageView iv1;
        TextView txt_name;
        org.fabiomsr.moneytextview.MoneyTextView txt_rate;
        LinearLayout ll1;



        public ViewHolderClass(View itemView) {
            super(itemView);





            iv1 = itemView.findViewById(R.id.iv1);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_rate=itemView.findViewById(R.id.txt_rate);
            ll1=itemView.findViewById(R.id.ll1);



        }
    }





}
