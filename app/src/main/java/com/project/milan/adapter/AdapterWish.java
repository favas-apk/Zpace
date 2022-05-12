package com.project.milan.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.milan.activity.HomeActivity;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.database.appdb.Appdb;
import com.project.milan.database.entities.WishEntity;
import com.project.milan.fragment.Fragment_Single_View;
import com.project.milan.Constants;
import com.project.milan.R;

import java.io.Serializable;
import java.util.List;

public class AdapterWish extends RecyclerView.Adapter<AdapterWish.ViewHolderClass>  {



    Context context;
    List<WishEntity> list;
    private Appdb db;




    public AdapterWish(Context context, List<WishEntity> list) {
        this.context = context;
        this.list = list;
        db = Appdb.getDb_instance(context);



    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_fragment_wishlist, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final WishEntity cpr = list.get(position);



//
        holder.txtname.setText(cpr.getName());

       holder.txtbrand.setText(cpr.getBrand());

        holder.txtcolor.setText(cpr.getColor());

        holder.txtrate.setAmount(Float.parseFloat(cpr.getRate()));


        String fname = cpr.getFname();

        String[] fnames = new String[100];

        if (!fname.equals("")) {
            if (fname.contains(",")) {
                fnames = fname.split(",");
            } else {

                fnames[0] = fname;
            }

        }





            String pic= ApiClient.BASE_URL+"zpa/images/images/"+fnames[0] +"th.jpeg";

        Glide.with(context).load(pic)
                .sizeMultiplier(1.0f)

                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.iv1);


        holder.iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Constants.getWishlistInterface().remove_item_from_wishlist(cpr.getItemcode());

            }
        });


        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Constants.sceen.equals(Constants.single)) {
                    Constants.sceen = Constants.single;
                    ((HomeActivity) context).hide_fragment_except("Fragment_Single_View");

                    Fragment_Single_View fragment = new Fragment_Single_View();
                    Bundle args = new Bundle();
                    args.putString("from", "wish");

                    args.putSerializable("Serialized_Details", (Serializable) cpr);
                    //   args.putString(Constants.Parcel_single_fname, cpr.getImage());
                    // args.putString(Constants.fragment_heading, cpr.getCategory());
                    fragment.setArguments(args);

                    FragmentManager fm = ((HomeActivity) context).getSupportFragmentManager();

                    fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Single_View").commit();

                }


                Constants.getHomeInterface().show_heading("");

            }
        });
        }





























    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txtname,txtbrand,txtcolor;
        ImageView iv1,iv2;
        org.fabiomsr.moneytextview.MoneyTextView txtrate;





        public ViewHolderClass(View itemView) {
            super(itemView);


            iv1=itemView.findViewById(R.id.iv1);
            iv2=itemView.findViewById(R.id.iv2);
            txtname = itemView.findViewById(R.id.txtname);
            txtbrand = itemView.findViewById(R.id.txtbrand);
            txtcolor = itemView.findViewById(R.id.txtcolor);
            txtrate = itemView.findViewById(R.id.txtrate);





        }
    }





}
