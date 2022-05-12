package com.project.milan.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.milan.R;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.database.appdb.Appdb;
import com.project.milan.database.entities.CartEntity;
import com.project.milan.database.entities.WishEntity;
import com.project.milan.Constants;

import com.project.milan.model.model_offer_calculation;
import com.project.milan.pojos.read_web_rate_and_offers.DetailsItem_no_stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolderClass> {


    Context context;
    List<CartEntity> list;
    private Appdb db;
    model_offer_calculation offer_class;

    Vibrator vibe;

    List<DetailsItem_no_stock> list_stock_prob_items;

    public AdapterCart(Context context, List<CartEntity> list, List<DetailsItem_no_stock> list_stock_prob_items) {
        this.context = context;
        this.list = list;
        vibe = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        db = Appdb.getDb_instance(context);
        this.list_stock_prob_items = list_stock_prob_items;

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_cart_item, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {


        final CartEntity cpr = list.get(position);


        int alloted_free_qty = 0;

        BigDecimal amt = new BigDecimal("0.0");

        long id1 = cpr.getId();
        String stockid = cpr.getStkid();
        holder.txtwarning.setVisibility(View.GONE);
        for (int jj = 0; jj < list_stock_prob_items.size(); jj++) {
            if (list_stock_prob_items.get(jj).getAvbStockId().equals(stockid)) {
                holder.txtwarning.setVisibility(View.VISIBLE);
                holder.txtwarning.setText(context.getResources().getString(R.string.lsesstk_plz_reduce));
                break;
            }

        }


        int qty = cpr.getQty();


        holder.ll_offer1.setVisibility(View.INVISIBLE);
        holder.txt_offer_2_or_3.setVisibility(View.INVISIBLE);
        offer_class = new model_offer_calculation(cpr.getRate(), cpr.getOffer_price(), cpr.getBuy_qty(), cpr.getFree_qty(), cpr.getFree_percent(), cpr.getOffer_end_date());
        if (offer_class.get_offer_type().equals("A")) {
            BigDecimal offer_rate_big = new BigDecimal(cpr.getOffer_price());
            BigDecimal real_total = offer_rate_big.multiply(new BigDecimal(qty));

            amt = real_total;
            alloted_free_qty = 0;


            holder.ll_offer1.setVisibility(View.VISIBLE);


            holder.txtstrike.setText(context.getResources().getString(R.string.Rs) + cpr.getRate());

            holder.txtstrike.setPaintFlags(holder.txtstrike.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            BigDecimal rate_big = new BigDecimal(Float.toString(cpr.getRate()));
            BigDecimal offer_big = new BigDecimal(Float.toString(cpr.getOffer_price()));


            BigDecimal diff_big = rate_big.subtract(offer_big);
            BigDecimal offer_percent_big = diff_big.divide(rate_big, 3, RoundingMode.HALF_UP);
            offer_percent_big = offer_percent_big.multiply(new BigDecimal("100.0"));


            offer_percent_big = offer_percent_big.setScale(1, RoundingMode.HALF_UP);


            holder.txt_offer1_percent.setText(offer_percent_big.toPlainString() + "%\noff");


            holder.txt_price.setText(context.getResources().getString(R.string.Rs) + cpr.getOffer_price());


            holder.txt_total.setText("Total " + context.getResources().getString(R.string.Rs) + amt.toPlainString());


        } else if (offer_class.get_offer_type().equals("B")) {
            BigDecimal rate_big = new BigDecimal(cpr.getRate());
            BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

            amt = real_total;

            if (qty > cpr.getBuy_qty() || qty == cpr.getBuy_qty()) {
                int no = qty / cpr.getBuy_qty();
                alloted_free_qty = no * cpr.getFree_qty();


            } else {
                alloted_free_qty = 0;
            }

            holder.txt_offer_2_or_3.setVisibility(View.VISIBLE);
            holder.txt_offer_2_or_3.setText("Buy " + cpr.getBuy_qty() + " Free " + cpr.getFree_qty());

            holder.txt_price.setText(context.getResources().getString(R.string.Rs) + cpr.getRate());


            holder.txt_total.setText("Total " + context.getResources().getString(R.string.Rs) + amt.toPlainString());


        } else if (offer_class.get_offer_type().equals("C")) {
            if (qty > cpr.getBuy_qty() || qty == cpr.getBuy_qty()) {


                BigDecimal rate_big = new BigDecimal(cpr.getRate());

                BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                BigDecimal temp = real_total.multiply(new BigDecimal(cpr.getFree_percent()));
                BigDecimal discount = temp.divide(new BigDecimal("100.0"), 3, RoundingMode.HALF_UP);

                amt = real_total.subtract(discount);
                alloted_free_qty = 0;

            } else {
                BigDecimal rate_big = new BigDecimal(cpr.getRate());
                BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

                amt = real_total;
                alloted_free_qty = 0;
            }


            holder.txt_offer_2_or_3.setVisibility(View.VISIBLE);
            holder.txt_offer_2_or_3.setText("Buy " + cpr.getBuy_qty() + " " + cpr.getFree_percent() + "% off");

            holder.txt_price.setText(context.getResources().getString(R.string.Rs) + cpr.getRate());
            holder.txt_total.setText("Total " + context.getResources().getString(R.string.Rs) + amt.toPlainString());

        }


        //no offer
        else {
            BigDecimal rate_big = new BigDecimal(cpr.getRate());
            BigDecimal real_total = rate_big.multiply(new BigDecimal(qty));

            amt = real_total;
            alloted_free_qty = 0;


            holder.txt_price.setText(context.getResources().getString(R.string.Rs) + cpr.getRate());


            holder.txt_total.setText("Total " + context.getResources().getString(R.string.Rs) + amt.toPlainString());


        }


        holder.txtname.setText(cpr.getItemname());
        holder.txtcompany.setText("Brand:" + cpr.getBrand());
        holder.txtcolor.setText("Color:" + cpr.getColor());
        holder.txtsize.setText("Size:" + cpr.getSize());


        holder.txt_qty.setText("" + qty);

        if (qty == 1) {
            holder.iv_dec.setBackgroundResource(R.drawable.ic_recycle);
        } else {
            holder.iv_dec.setBackgroundResource(R.drawable.ic_minus);
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


        holder.txt_mov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage(context.getResources().getString(R.string.do_u_want_to_move_to_wish));
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                if (db.getWishEntityDao().get_count(stockid) == 0) {
                                    db.getWishEntityDao().insert_wish_details(new WishEntity(0, stockid, cpr.getFname(), cpr.getItemname(), "" + cpr.getRate(), cpr.getBrand(), cpr.getColor()));
                                }


                                list.remove(position);
                                db.getCartEntityDao().del_item(id1);


                                dialog.cancel();
                                notifyDataSetChanged();

                                Constants.getFr_cartInterface().show_the_total();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

        holder.txt_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage(context.getResources().getString(R.string.remove_this_item));
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                list.remove(position);
                                db.getCartEntityDao().del_item(id1);


                                dialog.cancel();
                                notifyDataSetChanged();

                                Constants.getFr_cartInterface().show_the_total();

                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();


            }
        });

        holder.ll_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                remove_item_from_list(cpr.getStkid());

                vibe.vibrate(50);

                //   int qty=cpr.getD();
                int qty = 0;


                try {
                    qty = Integer.parseInt(holder.txt_qty.getText().toString());
                } catch (Exception e) {
                    qty = 0;
                }


                if (qty > 0) {
                    qty = qty - 1;


                    //    cpr.setD(qty);
                    //   holder.txt_qty.setText(""+cpr.getD());


                    if (qty == 0) {

                        // warning to remove
                        // remove
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setMessage(context.getResources().getString(R.string.remove_this_item));
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        list.remove(position);
                                        db.getCartEntityDao().del_item(id1);


                                        dialog.cancel();
                                        notifyDataSetChanged();
                                        Constants.getFr_cartInterface().show_the_total();
                                    }
                                });

                        builder1.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    } else {
                        //update

                        db.getCartEntityDao().update_qty(id1, qty);
                        list.get(position).setQty(qty);
                        //  holder.txt_qty.setText("" + qty);
                        notifyDataSetChanged();
                        Constants.getFr_cartInterface().show_the_total();
                    }


                }


            }
        });


        holder.ll_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                remove_item_from_list(cpr.getStkid());
                vibe.vibrate(50);
                //   int qty=cpr.getD();
                int qty = 0;
                try {
                    qty = Integer.parseInt(holder.txt_qty.getText().toString());
                } catch (Exception e) {
                    qty = 0;
                }


                qty = qty + 1;

                if (qty < 11) {
                    db.getCartEntityDao().update_qty(id1, qty);
                    list.get(position).setQty(qty);
                    //  holder.txt_qty.setText("" + qty);
                    notifyDataSetChanged();
                }


                Constants.getFr_cartInterface().show_the_total();

            }
        });


        //  holder.txtold.setBackgroundResource(R.color.color1);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderClass extends RecyclerView.ViewHolder {


        ImageView iv1, iv_inc, iv_dec;
        TextView txtwarning, txtname, txtcolor, txtcompany, txt_price, txt_qty, txt_rem, txt_mov, txt_offer1_percent, txtstrike, txt_offer_2_or_3, txtsize, txt_total;
        LinearLayout ll_inc, ll_dec, ll_offer1;


        public ViewHolderClass(View itemView) {
            super(itemView);


            iv1 = itemView.findViewById(R.id.iv1);
            iv_inc = itemView.findViewById(R.id.iv_inc);
            iv_dec = itemView.findViewById(R.id.iv_dec);
            txtname = itemView.findViewById(R.id.txtname);
            txtcolor = itemView.findViewById(R.id.txtcolor);
            txtcompany = itemView.findViewById(R.id.txtcompany);


            txt_price = itemView.findViewById(R.id.txt_price);
            txt_qty = itemView.findViewById(R.id.txt_qty);
            ll_inc = itemView.findViewById(R.id.ll_inc);
            ll_dec = itemView.findViewById(R.id.ll_dec);

            txt_rem = itemView.findViewById(R.id.txt_rem);
            txt_mov = itemView.findViewById(R.id.txt_mov);


            ll_offer1 = itemView.findViewById(R.id.ll_offer1);
            txt_offer1_percent = itemView.findViewById(R.id.txt_offer1_percent);
            txtstrike = itemView.findViewById(R.id.txtstrike);


            txt_offer_2_or_3 = itemView.findViewById(R.id.txt_offer_2_or_3);
            txtsize = itemView.findViewById(R.id.txtsize);
            txt_total = itemView.findViewById(R.id.txt_total);
            txtwarning = itemView.findViewById(R.id.txtwarning);


        }
    }


    private void remove_item_from_list(String stockid) {
        for (int jj = 0; jj < list_stock_prob_items.size(); jj++) {
            if (list_stock_prob_items.get(jj).getAvbStockId().equals(stockid)) {
                list_stock_prob_items.remove(jj);
                notifyDataSetChanged();
                break;
            }

        }


    }


}
