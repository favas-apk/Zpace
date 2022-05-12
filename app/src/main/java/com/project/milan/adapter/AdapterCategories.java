package com.project.milan.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.milan.activity.HomeActivity;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.fragment.Fragment_Section;
import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.pojos.read_category_with_random_images.DetailsItem;
import com.project.milan.pojos.read_category_with_random_images.FnamesItem;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolderClass>  {



    Context context;
    List<DetailsItem> list;
    private List<String> list_banner, list_caption;



    public AdapterCategories(Context context,List<DetailsItem> list) {
        this.context = context;
        this.list = list;

      //  list_banner=new ArrayList<>();
      //  list_caption=new ArrayList<>();
//        int images[] = {R.drawable.ban1, R.drawable.ban2, R.drawable.ban3, R.drawable.ban4};
//        for (int i = 0; i < images.length; i++) {
//            list_banner.add(Utils.getURLForResource(images[i]));
//            list_caption.add("efgh" + i);
//
//        }

    }

    @Override
    public ViewHolderClass onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_style3_categoris, parent, false);
        //   view.findViewById(R.id.txt1).setMinimumHeight(560);
        // view.setMinimumHeight(160);
        //   View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
        return new ViewHolderClass(view);

    }

    @Override
    public void onBindViewHolder(ViewHolderClass holder, int position) {

        list_banner=new ArrayList<>();
        list_caption=new ArrayList<>();
        final DetailsItem cpr = list.get(position);














//
        holder.txt2.setText(cpr.getCategory());


       List<FnamesItem>list_fnames= cpr.getFnames();

       String image="";

        for (FnamesItem row :list_fnames)
        {
           String fname= row.getFname();

           if(fname.contains(","))
           {
                String[] arr= fname.split(",");
              image=  arr[0];
           }
           else
           {
               image=fname;
           }

            String pic= ApiClient.BASE_URL+"zpa/images/images/"+image +"th"+".jpeg";

            list_banner.add(pic);
            list_caption.add("");



        }
        AdapterFlipper adapter = new AdapterFlipper(context, list_banner, list_caption);

        //adding it to adapterview flipper
        holder.avf1.setAdapter(adapter);
        holder.avf1.setFlipInterval(2000);


//        if(position %2==0)
//        {
//            holder.avf1.setFlipInterval(2000);
//        }
//        else
//        {
//            holder.avf1.setFlipInterval(1500);
//        }

        holder.avf1.startFlipping();
        holder.avf1.setInAnimation(context, R.animator.left_in);
        holder.avf1.setOutAnimation(context, R.animator.right_out);
        holder.avf1.showPrevious();


        holder.rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Constants.sceen = Constants.section;

                Fragment_Section fragment = new Fragment_Section ();
                Bundle args = new Bundle();
                args.putString(Constants.Parcel1, holder.txt2.getText().toString());
                args.putString(Constants.fragment_heading, cpr.getCategory());
                fragment.setArguments(args);


//                ((HomeActivity)context).getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, fragment,"Fragment_Section" )
//                        .commit();


                ((HomeActivity) context).hide_fragment_except("Fragment_Section");

                FragmentManager fm=((HomeActivity)context).getSupportFragmentManager();

                fm.beginTransaction().add(R.id.fragment_container, fragment, "Fragment_Section").commit();









                 Constants.getHomeInterface().show_heading("section");
//
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                actionBarDrawerToggle.setDrawerIndicatorEnabled(false);


            }
        });












      //  holder.txtold.setBackgroundResource(R.color.color1);




//        holder.rl1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if(!holder.txt2.getText().toString().equals(""))
//                {
//                    Constants.getCategoriesActivityInterface().goto_section(holder.txt2.getText().toString());
//
//                }
//
//
//
//
//
//            }
//        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class ViewHolderClass extends RecyclerView.ViewHolder {


        TextView txt1,txt2;
        LinearLayout ll1;
        RelativeLayout rl1;
        AdapterViewFlipper avf1;




        public ViewHolderClass(View itemView) {
            super(itemView);



            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            ll1 = itemView.findViewById(R.id.ll1);
            rl1 = itemView.findViewById(R.id.rl1);
            avf1=itemView.findViewById(R.id.avf1);



        }
    }





}
