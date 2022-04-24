package com.project.zpace.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.adapter.AdapterOrders;
import com.project.zpace.adapter.AdapterSimilarProduct;
import com.project.zpace.apiservice.ApiClient;
import com.project.zpace.apiservice.Endpoint;
import com.project.zpace.pojos.read_orders.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class Fragment_Orders extends Fragment {
    private RecyclerView rcv_order;
    private View view;
    private List<String> list_order;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {





        view= inflater.inflate(R.layout.fragment_orders,container,false);

       init();

        show_orders();





      return view;




    }



    @Override
    public void onResume() {
        super.onResume();
      //  read();
    }

    private void init()
    {
        rcv_order=view.findViewById(R.id.rcv_order);
    }




    private void show_orders() {

        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);

        Call<Response> call = apiService.read_orders(Constants.api_key, "22");

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful())
                {
                    if(response.body().getResult().equals("1"))
                    {
                        if(Constants.sceen.equals(Constants.order)) {


                            list_order = new ArrayList<>();

                            list_order.add("Luxor assoted compo pack");
                            list_order.add("Camlin kokuyo Fabrica");
                            list_order.add("Doll house");

                            list_order.add("Woodland shoe");
                            list_order.add("Luxor assoted compo pack");
                            list_order.add("Camlin kokuyo Fabrica");
                            list_order.add("Doll house");

                            list_order.add("Woodland shoe");

                            AdapterOrders adp = new AdapterOrders(getActivity(), list_order);

                            LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            rcv_order.setLayoutManager(lm);
                            rcv_order.setAdapter(adp);

                            Constants.setFragment_order_done_flag(1);

                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });



        //call api to read the last 100 orders





//
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {


            show_orders();

        }

    }
}
