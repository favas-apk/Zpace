package com.project.zpace.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.activity.HomeActivity;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.database.entities.DashEntity;
import com.project.zpace.fragment.Fragment_Section;
import com.project.zpace.fragment.Fragment_Single_View;
import com.project.zpace.pojos.show_dash_gridview.DetailsItem;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AdapterScroll extends RecyclerView.Adapter<AdapterScroll.ViewHolderClass> {


    Context context;
    List<DashEntity> list;


    public AdapterScroll(Context context, List<DashEntity> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_style4, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final DashEntity cpr = list.get(position);

        String fname = cpr.getImage();


        String[] fnames = new String[100];

        if (!fname.equals("")) {
            if (fname.contains(",")) {
                fnames = fname.split(",");
            } else {

                fnames[0] = fname;
            }

        }



        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + fnames[0] + "th" + ".jpeg";
        Glide.with(context).load(profilepic)
                .sizeMultiplier(1.0f)

                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.iv1);


//
           holder.txt1.setText(cpr.getDisplay_name());

        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


           //     Toast.makeText(context,""+cpr.getType()+"-"+cpr.getDetails(),Toast.LENGTH_LONG).show();





                if(cpr.getType().equals("group"))
                {
                    Constants.sceen = Constants.section;

                    Fragment_Section fragment = new Fragment_Section ();
                    Bundle args = new Bundle();
                    args.putString(Constants.Parcel2, cpr.getDetails());
                    args.putString(Constants.fragment_heading, cpr.getDisplay_name());
                    fragment.setArguments(args);


//                    ((HomeActivity)context).getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragment_container, fragment,"Fragment_Section" )
//                            .commit();

                    ((HomeActivity) context).hide_fragment_except("Fragment_Section");

                    FragmentManager fm=((HomeActivity)context).getSupportFragmentManager();
                    fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Section").commit();

                }
                else
                {

                    if (!Constants.sceen.equals(Constants.single)) {
                        Constants.sceen = Constants.single;
                        ((HomeActivity) context).hide_fragment_except("Fragment_Single_View");

                        Fragment_Single_View fragment = new Fragment_Single_View();
                        Bundle args = new Bundle();
                        args.putString("from", "dash");

                        args.putSerializable("Serialized_Details", (Serializable) cpr);
                        //   args.putString(Constants.Parcel_single_fname, cpr.getImage());
                        // args.putString(Constants.fragment_heading, cpr.getCategory());
                        fragment.setArguments(args);

                        FragmentManager fm = ((HomeActivity) context).getSupportFragmentManager();

                        fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Single_View").commit();

                    }

                }

                Constants.getHomeInterface().show_heading("");

            }
        });


        //    Glide.with(context).load("https://neptonglobal.in/zpa/suggest/"+cpr.getSlno()+".jpg").fitCenter().centerCrop().into( holder.iv1);

        //     Glide.with(context).load("https://neptonglobal.in/zpa/suggests/"+cpr.getSlno()+".jpg").fitCenter().into( holder.iv1);

//
//


        //  holder.txtold.setBackgroundResource(R.color.color1);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txt1;
        // com.project.zpace.custom.ProportionalImageView iv1;
        ImageView iv1;


        public ViewHolderClass(View itemView) {
            super(itemView);


            txt1 = itemView.findViewById(R.id.txt1);
//
            iv1 = itemView.findViewById(R.id.iv1);


        }
    }


}
