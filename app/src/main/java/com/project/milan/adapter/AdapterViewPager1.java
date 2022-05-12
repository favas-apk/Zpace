package com.project.milan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.project.milan.R;

import java.util.List;

public class AdapterViewPager1 extends PagerAdapter {
    private Context context;
    private List<String> list;
    private  List<String> list_caption;

    public AdapterViewPager1(Context context, List<String> list, List<String> list_caption){

        this.context = context;
        this.list = list;
        this.list_caption = list_caption;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {



        return object==view;
    }





    @Override
    public Object instantiateItem(final ViewGroup container, int position) {


        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.model_view_pager,
                container, false);
        container.addView(viewGroup);
        return viewGroup;

//        Button button = new Button(container.getContext());
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        button.setLayoutParams(params);
//        button.setText(String.valueOf(position));
//
//        final int page = position;
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(container.getContext(), "You clicked: " + page + ". page.", Toast.LENGTH_SHORT).show();
//            }
//        });

//        container.addView(button);
//        return button;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        String cpr = list.get(position);
//
//        String cap = list_caption.get(position);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.model_view_pager, null);
//        TextView txt1 = (TextView) view.findViewById(R.id.txt1);
//        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
//       txt1.setText(cap);
//
//        Glide.with(context).load(cpr).fitCenter().centerCrop().into(imageView);
//      //  Picasso.with(context).load(cpr).fit().centerCrop().into(imageView);
//
//
//        return view;
//    }
}