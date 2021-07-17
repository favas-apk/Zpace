package com.project.zpace.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.zpace.Constants;
import com.project.zpace.R;
import com.project.zpace.database.appdb.Appdb;
import com.project.zpace.interfac.PaymentActivityInterface;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PaymentActivity extends AppCompatActivity implements PaymentActivityInterface {

    private WebView web_view;
    private Appdb db;
    private  String app_order_no="",order_id="",total="",custid="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Constants.setPaymentActivityInterface(this);

        init();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             app_order_no = extras.getString("o");
            order_id = extras.getString("i");
            total = extras.getString("t");


        }

     //   web_view.loadUrl("http://www.milantex.in/zpa/do_payment.php");

        db = Appdb.getDb_instance(getApplicationContext());

        custid=db.getLoginEntityDao().get_customer_id().get(0).trim();


     if(!app_order_no.trim().equals("")  && !order_id.trim().equals("")  && !total.trim().equals("")  && !custid.trim().equals("")   )
     {

      //   Toast.makeText(getApplicationContext(),order_id,Toast.LENGTH_LONG).show();
         String url="http://www.milantex.in/zpa/do_pay2.php";
         try {
           //  String post_data="custid="+ URLEncoder.encode(custid,"UTF-8") +"&app_order_no="+ URLEncoder.encode(app_order_no,"UTF-8")+"&order_id="+ order_id;
             String post_data="custid="+custid+"&app_order_no="+app_order_no+"&order_id="+ order_id+"&apikey="+ Constants.api_key;

             web_view.postUrl(url,post_data.getBytes());
         } catch (Exception e) {
             e.printStackTrace();
         }
     }



    }


    private void init()
    {
        web_view=findViewById(R.id.web_view);

        web_view.setWebChromeClient(new WebChromeClient());
        web_view.setWebViewClient(new  WebViewClient());
        web_view.clearCache(true);
        web_view.clearHistory();
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web_view.addJavascriptInterface(new WebAppInterface(this), "Android");





    }


    @Override
    public void jump_to_home_activity() {

        // Constants.getHomeInterface().Switch_to_fragment("Fragment_Dashboard");\
        Intent in=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(in);
        finish();
    }
}
