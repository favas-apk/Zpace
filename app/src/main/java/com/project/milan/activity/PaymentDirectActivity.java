package com.project.milan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.milan.R;
import com.project.milan.apiservice.ApiClient;
import com.project.milan.apiservice.Endpoint;
import com.project.milan.database.appdb.Appdb;
import com.project.milan.Constants;

import com.project.milan.pojos.base.Pojomodelbase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDirectActivity extends AppCompatActivity implements  PaymentResultListener {

    private WebView web_view;
    private Appdb db;
    private  String app_order_no="",order_id="",total="",custid="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Checkout.preload(getApplicationContext());




        init();


        Bundle extras = getIntent().getExtras();
       // if (extras != null) {
        //must get below 3 params
             app_order_no = extras.getString("o");
            order_id = extras.getString("i");
            total = extras.getString("t");


       // }

     //   web_view.loadUrl("http://www.milantex.in/zpa/do_payment.php");

        db = Appdb.getDb_instance(getApplicationContext());

        custid=db.getLoginEntityDao().get_customer_id().get(0).trim();


     if(!app_order_no.trim().equals("")  && !order_id.trim().equals("")  && !total.trim().equals("")  && !custid.trim().equals("")   )
     {
         startPayment();

     }



    }


    private void init()
    {
//        web_view=findViewById(R.id.web_view);

//        web_view.setWebChromeClient(new WebChromeClient());
//        web_view.setWebViewClient(new  WebViewClient());
//        web_view.clearCache(true);
//        web_view.clearHistory();
//        web_view.getSettings().setJavaScriptEnabled(true);
//        web_view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        web_view.addJavascriptInterface(new WebAppInterface(this), "Android");





    }


    public void startPayment() {


        

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_HMXJUR4J9lIMYh");


        checkout.setImage(R.drawable.ic_add);

        /**
         * Reference to current activity
         */
        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();

            options.put("name", "Milan Readymades");
            options.put("description", "Reference No. "+app_order_no);
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", order_id);//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", ""+total);//pass amount in currency subunits
            options.put("prefill.email", ""+db.getLoginEntityDao().get_all_datas().get(0).getEmail());
            options.put("prefill.contact",""+db.getLoginEntityDao().get_all_datas().get(0).getMobile());
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
           // Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }




    @Override
    public void onPaymentSuccess(String payment_id) {
        Toast.makeText(getApplicationContext(),payment_id,Toast.LENGTH_LONG).show();
        db.getOrderEntityDao().del_all();
        db.getCartEntityDao().del_all();


        Endpoint apiService = ApiClient.getClient().create(Endpoint.class);
        Call<Pojomodelbase> call = apiService.save_payment_id(Constants.api_key, app_order_no,  db.getLoginEntityDao().get_customer_id().get(0).toString().trim(),order_id,payment_id);

        call.enqueue(new Callback<Pojomodelbase>() {
            @Override
            public void onResponse(Call<Pojomodelbase> call, Response<Pojomodelbase> response) {

                Intent in = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(in);
                finish();

            }

            @Override
            public void onFailure(Call<Pojomodelbase> call, Throwable t) {

            }
        });


    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}
