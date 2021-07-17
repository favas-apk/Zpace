package com.project.zpace.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.zpace.R;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.fragment.Fragment_Order_Details;
import com.project.zpace.fragment.Fragment_Orders;
import com.project.zpace.fragment.Fragment_Single_View;
import com.project.zpace.interfac.HomeInterface;

import java.util.List;

public class AdapterOrders extends RecyclerView.Adapter<AdapterOrders.ViewHolderClass>  {



    Context context;
    List<String> list;




    public AdapterOrders(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_orders, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final String cpr = list.get(position);

        holder.txtname.setText(cpr);

        holder.txtdate.setText("Delivered 30-Nov-2020");






        String img="";

        switch (position)
        {

            case 0:img="1";
            break;

            case 1:img="2";
                break;
            case 2:img="3";
                break;
            case 3:img="4";
                break;

            default:img="1";
            break;
        }





//
        Glide.with(context).load("https://neptonglobal.in/zpa/offers/"+img+".jpg").fitCenter().into( holder.iv1);


//        holder.card1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Fragment_Order_Details fragment = new Fragment_Order_Details();
//                ((HomeActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment,"OptionsFragment").commit();
//
//
//
//            }
//        });



//
//









      //  holder.txtold.setBackgroundResource(R.color.color1);







    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolderClass extends RecyclerView.ViewHolder {



        ImageView iv1,iv_right_arrow;
        TextView txtname,txtdate;
        CardView card1;



        public ViewHolderClass(View itemView) {
            super(itemView);





            iv1 = itemView.findViewById(R.id.iv1);
            txtname=itemView.findViewById(R.id.txtname);
            txtdate=itemView.findViewById(R.id.txtdate);
            iv_right_arrow=itemView.findViewById(R.id.iv_right_arrow);
            card1=itemView.findViewById(R.id.card1);

        }
    }





}
