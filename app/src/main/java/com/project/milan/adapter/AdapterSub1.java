package com.project.milan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.project.milan.R;
import com.project.milan.pojos.read_category.DetailsItem;

import java.util.List;

public class AdapterSub1 extends RecyclerView.Adapter<AdapterSub1.ViewHolderClass> {

    View view;
    Context context;
    List<DetailsItem> list;


    public AdapterSub1(Context context, List<DetailsItem> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.model_sub1, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final DetailsItem cpr = list.get(position);


           holder.txt_sub1.setText(cpr.getCategory());













    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txt_sub1;

        // com.project.zpace.custom.ProportionalImageView iv1;
       // ImageView iv1;


        public ViewHolderClass(View itemView) {
            super(itemView);


            txt_sub1= itemView.findViewById(R.id.txt_sub1);
//



        }
    }


}
