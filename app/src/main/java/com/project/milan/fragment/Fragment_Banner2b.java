package com.project.milan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.project.milan.R;


public class Fragment_Banner2b extends Fragment {
   // private GridView gridview;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {





        view= inflater.inflate(R.layout.model_view_pager,container,false);

       init();

    //   read();

      return view;




    }

    @Override
    public void onResume() {
        super.onResume();
      //  read();
    }

    private void init()
    {
        //gridview=view.findViewById(R.id.gridview);
    }


   /* private void read()
    {
        list=new ArrayList<>();
        gridview.setAdapter(null);

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<PojomodelReadLeaders> call = apiService.read_leaders("");

        call.enqueue(new Callback<PojomodelReadLeaders>() {
            @Override
            public void onResponse(Call<PojomodelReadLeaders> call, Response<PojomodelReadLeaders> response) {
                if(response.body().getResult().equals("1"))
                {
                   // ((HomeActivity)getActivity()).showSnack_E(response.body().getMessage());
                    list.addAll(response.body().getDetails());

                    gridview.setAdapter(new AdapterLeaders(getActivity(), list));
                }
                else{
                    ((HomeActivity)getActivity()).showSnack_E(response.body().getMessage());
                }


            }

            @Override
            public void onFailure(Call<PojomodelReadLeaders> call, Throwable t) {
                ((HomeActivity)getActivity()).showSnack_E(t.getLocalizedMessage());
            }
        });



    }

    */

}
