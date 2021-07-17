package com.project.zpace.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

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


public class AdapterDashboardGridview_style1 extends BaseAdapter {


    Context context;
    List<DashEntity> list;


    public AdapterDashboardGridview_style1(Context context, List<DashEntity> list) {
        this.context = context;
        this.list = list;

    }

    private class ViewHolder {
        //   CardView card1;
        de.hdodenhof.circleimageview.CircleImageView iv1;
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

        final ViewHolder holder;
        if (view == null) {



      /*      imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);


       */

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.model_dash_gridview_style1, null);


            holder = new ViewHolder();

            //  holder.card1 = view.findViewById(R.id.card1);
            holder.iv1 = view.findViewById(R.id.iv1);
            holder.txt1 = view.findViewById(R.id.txt1);


            view.setTag(holder);
        } else {
            //  imageView = (ImageView) view;

            holder = (ViewHolder) view.getTag();
        }
        //    imageView.setImageResource(mThumbIds[position]);


        final DashEntity cpr = list.get(position);


        holder.txt1.setText("" + cpr.getDisplay_name());


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
                .sizeMultiplier(.5f)

                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.iv1);


        // Glide.with(context).load( ApiClient.BASE_URL +"zpa/images/images/"+cpr.getImage()+".jpeg").fitCenter().into( holder.iv1);


        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //  Toast.makeText(context,""+cpr.getType()+"-"+cpr.getDetails(),Toast.LENGTH_LONG).show();


                if (cpr.getType().equals("group")) {
                    Constants.sceen = Constants.section;

                    Fragment_Section fragment = new Fragment_Section();
                    Bundle args = new Bundle();
                    args.putString(Constants.Parcel2, cpr.getDetails());
                    args.putString(Constants.fragment_heading, cpr.getDisplay_name());
                    fragment.setArguments(args);


                    FragmentManager fm = ((HomeActivity) context).getSupportFragmentManager();


//                    ((HomeActivity)context).getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragment_container, fragment,"Fragment_Section" )
//                            .commit();


                    ((HomeActivity) context).hide_fragment_except("Fragment_Section");


                    fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Section").commit();

                } else {
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


        return view;
    }


}
