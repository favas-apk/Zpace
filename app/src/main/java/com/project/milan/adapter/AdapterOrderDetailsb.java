package com.project.milan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.vipulasri.timelineview.TimelineView;
import com.project.milan.R;

import java.util.List;

public class AdapterOrderDetailsb extends RecyclerView.Adapter<AdapterOrderDetailsb.ViewHolderClass>  {

    public  TimelineView mTimelineView;

    private String[] title = {
            "Abundance",
            "Anxiety",
            "Bruxism",
            "Discipline",
            "Drug Addiction"
    };
    Context context;
    List<String> list;




//    public AdapterOrderDetailsb(Context context, List<String> list) {
//
//        this.context = context;
//        this.list = list;
//
//
//
//
//    }

    public AdapterOrderDetailsb(Context context, List<String> list,View itemView, int viewType) {
        super();
        mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline);
        mTimelineView.initLine(viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

//    @Override
//    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.model_order_detailsb, parent, false);
//        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
//        // view.setMinimumHeight(160);
//        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
//        return new ViewHolderClass(view,viewType);
//
//    }


    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.model_order_details, null);
        return new ViewHolderClass(view, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final String cpr = list.get(position);

        holder.txt1.setText(cpr);

        holder.txt2.setText("10.00 AM, 8 Jan 2021");

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

      //  com.tcqq.timelineview.TimelineView timeline;


        public ViewHolderClass(View itemView, int viewType) {
            super(itemView);





            iv1 = itemView.findViewById(R.id.iv1);
            txt1=itemView.findViewById(R.id.txt1);
            txt2=itemView.findViewById(R.id.txt2);
        //    timeline=itemView.findViewById(R.id.timeline);

        }
    }





}
