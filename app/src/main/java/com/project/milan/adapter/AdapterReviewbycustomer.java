package com.project.milan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.project.milan.database.appdb.Appdb;
import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.Utils;
import com.project.milan.model.model_like_dislike;

import java.util.List;

public class AdapterReviewbycustomer extends RecyclerView.Adapter<AdapterReviewbycustomer.ViewHolderClass> {


    Context context;
    List<model_like_dislike> list;
    private Appdb db;
    String stockid;


    public AdapterReviewbycustomer(Context context, List<model_like_dislike> list,String  stockid) {
        this.context = context;
        this.list = list;
        db = Appdb.getDb_instance(context);

        this.stockid=stockid;

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_review_by_customer, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final  model_like_dislike cpr = list.get(position);


        holder.txtcomm.setText(cpr.getComments());
        holder.txtname.setText(cpr.getName());


        holder.ratebar.setRating(Float.parseFloat(cpr.getStar()));

        holder.txtdate.setText(cpr.getDat());

        holder.txtlike.setText(cpr.getLikes());

        holder.txtdislike.setText(cpr.getDislikes());



        if(cpr.isFlag_up_hand())
        {

            holder.iv_up.setImageResource(R.drawable.ic_like_full);
        }
        else
        {
            holder.iv_up.setImageResource(R.drawable.ic_like);
        }

        if(cpr.isFlag_down_hand())
        {
            holder.iv_down.setImageResource(R.drawable.ic_dislike_full);
        }
        else
        {
            holder.iv_down.setImageResource(R.drawable.ic_dislike);
        }


        holder.ratebar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });


        holder.iv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(db.getLoginEntityDao().get_count()>0)
                {

                    if(!cpr.isFlag_up_hand() && !cpr.isFlag_down_hand())
                    {
                      //  holder.iv_up.setImageResource(R.drawable.ic_like_full);
                        Constants.getFr_single_interface().new_insert_like_dislike(Utils.GVCOT(stockid),Utils.GVCOT(cpr.getRating_id()),Utils.GVCOT(db.getLoginEntityDao().get_customer_id().get(0)),Utils.GVCOT("1"),Utils.GVCOT("0"));
                    }
                    else
                    {

                        if(cpr.isFlag_up_hand())
                        {

                         //   holder.iv_up.setImageResource(R.drawable.ic_like);
                            Constants.getFr_single_interface().del_like_dislike(Utils.GVCOT(cpr.getRating_id()),Utils.GVCOT(db.getLoginEntityDao().get_customer_id().get(0)),Utils.GVCOT(stockid));
                        }
                        else
                        {
                           // holder.iv_down.setImageResource(R.drawable.ic_dislike);
                            //holder.iv_up.setImageResource(R.drawable.ic_like_full);

                            Constants.getFr_single_interface().update_like_dislike(Utils.GVCOT(stockid),Utils.GVCOT(cpr.getRating_id()),Utils.GVCOT(db.getLoginEntityDao().get_customer_id().get(0)),Utils.GVCOT("1"),Utils.GVCOT("0"));

                        }


                    }

                }
                else
                {
                    Toast.makeText(context,context.getResources().getString(R.string.Plz_do_login),Toast.LENGTH_LONG).show();


                }
            }
        });

        holder.iv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(db.getLoginEntityDao().get_count()>0)
                {
                    if(!cpr.isFlag_up_hand() && !cpr.isFlag_down_hand())
                    {

                        Constants.getFr_single_interface().new_insert_like_dislike(Utils.GVCOT(stockid),Utils.GVCOT(cpr.getRating_id()),Utils.GVCOT(db.getLoginEntityDao().get_customer_id().get(0)),Utils.GVCOT("0"),Utils.GVCOT("1"));
                     //   Toast.makeText(context,"new insert needed",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(cpr.isFlag_down_hand())
                        {
                           // Toast.makeText(context,"remove only",Toast.LENGTH_LONG).show();

                            Constants.getFr_single_interface().del_like_dislike(Utils.GVCOT(cpr.getRating_id()),Utils.GVCOT(db.getLoginEntityDao().get_customer_id().get(0)),Utils.GVCOT(stockid));
                        }
                        else
                        {
                           // holder.iv_up.setImageResource(R.drawable.ic_like);
                          //  holder.iv_down.setImageResource(R.drawable.ic_dislike_full);


                            Constants.getFr_single_interface().update_like_dislike(Utils.GVCOT(stockid),Utils.GVCOT(cpr.getRating_id()),Utils.GVCOT(db.getLoginEntityDao().get_customer_id().get(0)),Utils.GVCOT("0"),Utils.GVCOT("1"));

                        }


                    }
                }
                else
                {
                    Toast.makeText(context,"Please do login",Toast.LENGTH_LONG).show();
                }


            }
        });



//


//
//


        //  holder.txtold.setBackgroundResource(R.color.color1);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txtcomm, txtlike, txtdislike, txtname,txtdate;
        com.iarcuschin.simpleratingbar.SimpleRatingBar ratebar;
        ImageView iv_up,iv_down;


        public ViewHolderClass(View itemView) {
            super(itemView);


            txtcomm = itemView.findViewById(R.id.txtcomm);
            txtlike = itemView.findViewById(R.id.txtlike);
            txtdislike = itemView.findViewById(R.id.txtdislike);
            txtname = itemView.findViewById(R.id.txtname);
            ratebar= itemView.findViewById(R.id.ratebar);
            txtdate= itemView.findViewById(R.id.txtdate);
            iv_up=itemView.findViewById(R.id.iv_up);
            iv_down=itemView.findViewById(R.id.iv_down);


        }
    }


}
