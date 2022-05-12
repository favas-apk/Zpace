package com.project.milan.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.database.entities.DashEntity;
import com.project.milan.R;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<DashEntity> list = new ArrayList<>();

    public SliderAdapter(Context context,  List<DashEntity> list) {
        this.context = context;
        this.list = list;
    }




    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_view_pager, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH holder, final int position) {

        final DashEntity cpr = list.get(position);

       Glide.with(context).load(ApiClient.BASE_URL + "zpa/images/images/"+cpr.getImage()+".jpeg").fitCenter().centerCrop().into(holder.imageView);


//        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + cpr.getImage() +"th" + ".jpeg";
//        Glide.with(context).load(profilepic)
//                .sizeMultiplier(1.0f)
//
//                .placeholder(R.drawable.blanc_pic)
//                .error(R.drawable.blanc_pic)
//                .fallback(R.drawable.blanc_pic)
//                .dontAnimate()
//                .into(holder.imageView);



        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,""+cpr.getCompany(),Toast.LENGTH_LONG).show();

            }
        });
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return list.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageView;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);

            this.itemView = itemView;
        }
    }

}