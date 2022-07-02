package com.project.milan.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.project.milan.Constants;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.database.appdb.Appdb;
import com.project.milan.database.entities.WishEntity;
import com.project.milan.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterSingleView extends SliderViewAdapter<AdapterSingleView.SliderAdapterVH> {

    private Context context;
    private List<String> list = new ArrayList<>();
    String stockid;
    private Appdb db;
    private String itemname;
    private String rate;
    private String brand;

    String offer_percent;

    public AdapterSingleView(Context context, List<String> list, String stocckid, String itemname, String rate, String offer_percent) {
        this.context = context;
        this.list = list;
        this.stockid = stocckid;
        db = Appdb.getDb_instance(context);
        this.itemname = itemname;
        this.rate = rate;
        this.offer_percent = offer_percent;


    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_view_pager2, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH holder, final int position) {

        final String cpr = list.get(position);

        //Glide.with(context).load(cpr).fitCenter().centerCrop().into(holder.imageView);

        String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + cpr + "th.jpeg";
        Glide.with(context).load(profilepic)
                .sizeMultiplier(1.0f)

                .placeholder(R.drawable.blanc_pic)
                .error(R.drawable.blanc_pic)
                .fallback(R.drawable.blanc_pic)
                .dontAnimate()
                .into(holder.imageView);

        holder.iv_off.setText(offer_percent);
        if(!offer_percent.trim().equals(""))
        {
            holder.iv_off_back.setVisibility(View.VISIBLE);

        }
       else
        {

            holder.iv_off_back.setVisibility(View.INVISIBLE);
        }


        holder.iv_like_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //             Toast.makeText(context,"df",Toast.LENGTH_LONG).show();

                if (db.getWishEntityDao().get_count(stockid) > 0) {
                    db.getWishEntityDao().del(stockid);
                    notifyDataSetChanged();
                    //      holder.iv_heart.setImageResource(R.drawable.ic_heart_empty);

                    holder.iv_like.setImageResource(R.drawable.ic_heart_empty);
                } else {
                    db.getWishEntityDao().insert_wish_details(new WishEntity(0, stockid, cpr, itemname, rate, "", ""));


                    notifyDataSetChanged();
                    //      holder.iv_heart.setImageResource(R.drawable.ic_heart);
                    holder.iv_like.setImageResource(R.drawable.ic_heart_2);

                }

            }
        });


        if (db.getWishEntityDao().get_count(stockid) > 0) {
            holder.iv_like.setImageResource(R.drawable.ic_heart_2);
        } else {
            holder.iv_like.setImageResource(R.drawable.ic_heart_empty);
        }


//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
//            }
//        });

        holder.iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//
//                PackageManager pm = context.getPackageManager();
//                boolean isInstalled1 = isPackageInstalled("com.whatsapp", pm);
//                boolean isInstalled2 = isPackageInstalled("com.whatsapp.w4b", pm);
//
////                URL url = null;
//                  Uri imgUri = null;
//                try {
//                    url = new URL(profilepic);
//                     imgUri =  Uri.parse( url.toURI().toString());
//                } catch (MalformedURLException | URISyntaxException e) {
//                    e.printStackTrace();
//                }
//
//                //   Uri imgUri = Uri.parse(profilepic);
//
//                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
//                whatsappIntent.setType("text/plain");
//                whatsappIntent.setPackage("com.whatsapp.w4b");
//                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
//                whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
//                whatsappIntent.setType("image/jpeg");
//                whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//                try {
//
//
//
//                    ((HomeActivity) context).startActivity(whatsappIntent);
//                } catch (android.content.ActivityNotFoundException ex) {
//
//                    Toast.makeText(context,"Whatsapp have not been installed.",Toast.LENGTH_LONG).show();
//
//                }


                Glide.with(context)
                        .load(profilepic)
                        .asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)

                        .into(new SimpleTarget<Bitmap>(500, 500) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {


                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.putExtra(Intent.EXTRA_TEXT, "https://com.project.zpace?data=" + stockid);
                                String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), resource, "", null);


                                Uri screenshotUri = Uri.parse(path);


                                intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
                                intent.setType("image/*");

                                context.startActivity(Intent.createChooser(intent, "Share image via..."));
                            }

                            @Override
                            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();


                                super.onLoadFailed(e, errorDrawable);
                            }

                            @Override
                            public void onLoadStarted(Drawable placeholder) {
                                Toast.makeText(context, "Starting", Toast.LENGTH_SHORT).show();

                                super.onLoadStarted(placeholder);
                            }
                        });


            }
        });

                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Constants.getFr_single_interface().jump_to_zoom_page();
                     //   Toast.makeText(context, "This is item in position " , Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return list.size();
    }


    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageView, iv_share, iv_like;
        de.hdodenhof.circleimageview.CircleImageView iv_like_back,iv_off_back;
        TextView iv_off;


        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            iv_share = itemView.findViewById(R.id.iv_share);
            iv_like_back = itemView.findViewById(R.id.iv_like_back);
            iv_like = itemView.findViewById(R.id.iv_like);
            iv_off_back= itemView.findViewById(R.id.iv_off_back);
            iv_off = itemView.findViewById(R.id.iv_off);

            this.itemView = itemView;
        }
    }


}