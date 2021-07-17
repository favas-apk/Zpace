package com.project.zpace.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.zpace.R;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.fragment.Fragment_Order_Details;
import com.project.zpace.interfac.HomeInterface;

import java.util.List;

public class AdapterOrderDetails extends RecyclerView.Adapter<AdapterOrderDetails.ViewHolderClass>  {



    Context context;
    List<String> list;




    public AdapterOrderDetails(Context context, List<String> list) {

        this.context = context;
        this.list = list;




    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_order_details, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view,viewType);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final String cpr = list.get(position);

        holder.txt1.setText(cpr);

        holder.txt2.setText("10.00 AM, 8 Jan 2021");


        if( !holder.txt1.getText().toString().contains("Requested") && !holder.txt1.getText().toString().contains("Packed"))
        {
            Drawable drawable = context.getResources().getDrawable(R.drawable.marker_completed);
            holder.timeline.setMarker(drawable);
           holder.timeline.setEndLineColor(R.color.red,0);




        }

//       holder. timeline.setLabels(title)
//                .setBarColorIndicator(context.getResources().getColor(R.color.material_blue_grey_800))
//                .setProgressColorIndicator(context.getResources().getColor(R.color.orange))
//                .setLabelColorIndicator(context.getResources().getColor(R.color.orange))
//                .setCompletedPosition(0)
//                .drawView();



//        if(holder.txt1.getText().toString().toLowerCase().contains("deliv"))
//        {
//            holder holder.timeline.setEndLineColor(R.color.pale_ash,1);

//        }
//        else
//        {
//            holder.timeline.setEndLineColor(R.color.blue,1);
//        }











//
    //   Glide.with(context).load("https://neptonglobal.in/zpa/offers/"+img+".jpg").fitCenter().into( holder.iv1);





//
//









      //  holder.txtold.setBackgroundResource(R.color.color1);







    }

    @Override
    public int getItemCount() {
        return list.size();
    }


//    @Override
//    public int getItemViewType(int position) {
//        return timeline.getTimeLineViewType(position, getItemCount());
//    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {



        ImageView iv1;
        TextView txt1,txt2;

        com.tcqq.timelineview.TimelineView timeline;


        public ViewHolderClass(View itemView, int viewType) {
            super(itemView);





            iv1 = itemView.findViewById(R.id.iv1);
            txt1=itemView.findViewById(R.id.txt1);
            txt2=itemView.findViewById(R.id.txt2);
            timeline=itemView.findViewById(R.id.timeline);

        }
    }





}
