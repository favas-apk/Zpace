package com.project.milan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.milan.R;
import com.project.milan.apiservice.ApiClient;

import java.util.ArrayList;
import java.util.List;

public class VPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> list = new ArrayList<>();
    // private Integer [] images = {R.drawable.noimg,R.drawable.noimg,R.drawable.noimg};

    public VPagerAdapter(Context context) {
        this.context = context;
    }

    public VPagerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {

        if (list.size() > 0) {
            return list.size();
        } else {
            return 5;
        }


    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.model_zoom, null);
      //  ImageView iv1 = (ImageView) view.findViewById(R.id.iv1);
       ImageView ziv=view.findViewById(R.id.ziv);


        if (list.size() > 0) {
            final String cpr = list.get(position);


            String profilepic = ApiClient.BASE_URL + "zpa/images/images/" + cpr + "th" + ".jpeg";

            try
            {
                Glide.with(context).load(profilepic)
                        .sizeMultiplier(1.0f)

                        .placeholder(R.drawable.blanc_pic)
                        .error(R.drawable.blanc_pic)
                        .fallback(R.drawable.blanc_pic)
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(ziv.getWidth(),ziv.getHeight()) // Overrides size of downloaded image and converts it's bitmaps to your desired image size;
                        .into(ziv);
            }
            catch (Exception e)
            {

            }



        //    Glide.with(context).load(profilepic).into(iv1);
//            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.banner));
        } else {
            view = layoutInflater.inflate(R.layout.item_config_banner_shimmer, null);

        }


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }


    @Override
    public float getPageWidth(int position) {
        return 1.0f;
    }
}