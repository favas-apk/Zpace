package com.project.milan.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.project.milan.activity.HomeActivity;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.database.appdb.Appdb;
import com.project.milan.database.entities.WishEntity;
import com.project.milan.fragment.Fragment_Single_View;
import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.Utils;
import com.project.milan.model.model_offer_calculation;
import com.project.milan.pojos.read_item_by_group.DetailsItem;

import java.io.Serializable;
import java.util.List;


public class AdapterSection extends BaseAdapter {


    Context context;
    List<DetailsItem> list;
    private Appdb db;
    model_offer_calculation offer_class;

    public AdapterSection(Context context, List<DetailsItem> list) {
        this.context = context;
        this.list = list;
        db = Appdb.getDb_instance(context);

    }

    private class ViewHolder {
        //      CardView card1;
        ImageView iv1, iv_heart;
        TextView txt1, txt2, txt3;
        org.fabiomsr.moneytextview.MoneyTextView txtcash;
        LinearLayout ll1;

        de.hdodenhof.circleimageview.CircleImageView iv_heart_back;


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

            view = inflater.inflate(R.layout.model_section, null);


            holder = new ViewHolder();

            //       holder.card1 = view.findViewById(R.id.card1);
            holder.iv1 = view.findViewById(R.id.iv1);
            holder.iv_heart = view.findViewById(R.id.iv_heart);
            holder.iv_heart_back = view.findViewById(R.id.iv_heart_back);
            holder.txt1 = view.findViewById(R.id.txt1);
            holder.txt2 = view.findViewById(R.id.txt2);
            holder.txt3 = view.findViewById(R.id.txt3);
            holder.txtcash = view.findViewById(R.id.txtcash);
            holder.ll1 = view.findViewById(R.id.ll1);


            view.setTag(holder);
        } else {
            //  imageView = (ImageView) view;

            holder = (ViewHolder) view.getTag();
        }
        //    imageView.setImageResource(mThumbIds[position]);


        final DetailsItem cpr = list.get(position);


        offer_class = new model_offer_calculation(Utils.GetFloat(cpr.getRate()), Utils.GetFloat(cpr.getOffer()), Utils.GetInt(cpr.getBuy_quantity()), Utils.GetInt(cpr.getFree_quantity()), Utils.GetFloat(cpr.getFree_percentage()), cpr.getOffer_end_date());


        holder.txt1.setText(cpr.getItemname());


        if(offer_class.get_offer_type().equals("A"))
        {
            holder.txt2.setVisibility(View.VISIBLE);
            holder.txt3.setVisibility(View.VISIBLE);
            holder.txtcash.setAmount(Float.parseFloat(offer_class.get_result_offer_price()));

            holder.txt2.setText(" "+offer_class.get_result_rate()+" ");
            holder.txt3.setText(offer_class.get_result_perc_off());


            holder.txt2.setPaintFlags(holder.txt2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else if(offer_class.get_offer_type().equals("B"))
        {
            holder.txtcash.setAmount(Float.parseFloat(offer_class.get_result_rate()));
            holder.txt2.setVisibility(View.VISIBLE);
            holder.txt3.setVisibility(View.INVISIBLE);

            holder.txt2.setText(" "+offer_class.get_result_buy_get()+" ");
        }
        else if(offer_class.get_offer_type().equals("C"))
        {
            holder.txtcash.setAmount(Float.parseFloat(offer_class.get_result_rate()));
            holder.txt2.setVisibility(View.VISIBLE);
            holder.txt3.setVisibility(View.INVISIBLE);

            holder.txt2.setText(" "+offer_class.get_result_buy_get()+" ");
        }

        else
        {
            holder.txt2.setVisibility(View.INVISIBLE);
            holder.txt3.setVisibility(View.INVISIBLE);

            holder.txtcash.setAmount(Float.parseFloat(offer_class.get_result_rate()));
        }










        String fname = cpr.getFname();


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


        String itemcode = cpr.getStkidFromServer();

        holder.iv_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.getWishEntityDao().get_count(itemcode) > 0) {
                    db.getWishEntityDao().del(itemcode);
                    notifyDataSetChanged();
                    //      holder.iv_heart.setImageResource(R.drawable.ic_heart_empty);


                } else {
                    db.getWishEntityDao().insert_wish_details(new WishEntity(0, itemcode, cpr.getFname(), cpr.getItemname(), cpr.getRate(), cpr.getBrand(), cpr.getColor()));
                    notifyDataSetChanged();
                    //      holder.iv_heart.setImageResource(R.drawable.ic_heart);

                }

            }
        });


        if (db.getWishEntityDao().get_count(itemcode) > 0) {
            holder.iv_heart.setImageResource(R.drawable.ic_heart);
        } else {
            holder.iv_heart.setImageResource(R.drawable.ic_heart_empty);
        }


        holder.ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!Constants.sceen.equals(Constants.single)) {
                    Constants.sceen = Constants.single;
                    ((HomeActivity) context).hide_fragment_except("Fragment_Single_View");

                    Fragment_Single_View fragment = new Fragment_Single_View();
                    Bundle args = new Bundle();


                    args.putString("from", "");
                    //              args.putString("Stockid", cpr.getStkidFromServer());
                    args.putSerializable("Serialized_Details", (Serializable) cpr);

                    fragment.setArguments(args);

                    FragmentManager fm = ((HomeActivity) context).getSupportFragmentManager();

                    fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Single_View").commit();

                }

            }
        });


        return view;
    }


}
