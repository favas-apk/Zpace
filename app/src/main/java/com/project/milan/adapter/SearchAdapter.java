package com.project.milan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project.milan.Constants;
import com.project.milan.R;
import com.project.milan.pojos.search_box.DetailsItem;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter<DetailsItem> {

    private Context context;
    private List<DetailsItem> listfull;

    public SearchAdapter(@NonNull Context context, List<DetailsItem> list) {
        super(context, 0, list);

        listfull = new ArrayList<>(list);
    }


    @NonNull
    @Override
    public Filter getFilter() {
        return SearchFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.autocomplete_custom_layout, parent, false);

        }

        TextView txt1 = convertView.findViewById(R.id.txt1);
        ImageView iv_delete = convertView.findViewById(R.id.iv_delete);
        LinearLayout ll_delete = convertView.findViewById(R.id.ll_delete);


        DetailsItem cpr = getItem(position);

        if (!cpr.getItemname().equals("")) {
            txt1.setText(cpr.getItemname());
        }

        if (cpr.isHis_flag()) {
            iv_delete.setVisibility(View.VISIBLE);

        } else {
            iv_delete.setVisibility(View.INVISIBLE);

        }



        ll_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.getFr_searchBefore_interface().delete(txt1.getText().toString());

            }
        });
//
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constants.getFr_searchBefore_interface().search(txt1.getText().toString());

            }
        });




        return convertView;
    }

    private Filter SearchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            FilterResults results = new FilterResults();
            List<DetailsItem> suggestion = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestion.addAll(listfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DetailsItem item : listfull) {
                    if (item.getItemname().toLowerCase().contains(filterPattern)) {
                        suggestion.add(item);
                    }
                }
            }


            results.values = suggestion;
            results.count = suggestion.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            clear();
            addAll((List) results.values);

            notifyDataSetChanged();

        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((DetailsItem) resultValue).getItemname();
        }
    };


}
