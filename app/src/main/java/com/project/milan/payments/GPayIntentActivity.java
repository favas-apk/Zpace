package com.project.milan.payments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.milan.R;

import java.util.Random;

public class GPayIntentActivity extends AppCompatActivity {

    EditText edt1,edt2,edt3,edt4;
    Button btn1;
    final int UPI_PAYMENT=0;
    int random ;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_googlepay);


        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        edt3=findViewById(R.id.edt3);
        edt4=findViewById(R.id.edt4);

        btn1=findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!edt1.getText().toString().equals("")  &&  !edt2.getText().toString().equals("") && !edt3.getText().toString().equals("") && !edt4.getText().toString().equals("") )
                {

                  random=  new Random().nextInt(61) + 20;
                    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
                    int GOOGLE_PAY_REQUEST_CODE = 123;

                    Uri uri =
                            new Uri.Builder()
                                    .scheme("upi")
                                    .authority("pay")
                                    .appendQueryParameter("pa", "mufeedamhdali-1@okaxis")
                                    .appendQueryParameter("pn", "Mufeeda ck")
                                  //  .appendQueryParameter("pa", "mufeedamhdali-1@okaxis")
                               //       .appendQueryParameter("pn", "mufeeda ck")
                                    .appendQueryParameter("mc", "")
                                    .appendQueryParameter("tr", ""+random)
                                    .appendQueryParameter("tn", "Test")
                                    .appendQueryParameter("am", "1")
                                    .appendQueryParameter("cu", "INR")
                                   // .appendQueryParameter("url", "your-transaction-url")
                                    .build();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(uri);
                 //   intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
                   // startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);

                    Intent chooser = Intent.createChooser(intent,"Pay with");
                    if(chooser.resolveActivity(getPackageManager())!=null)
                    {
                        startActivityForResult(chooser,UPI_PAYMENT);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All fields are mandatory", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==123)
        {

            if(resultCode==-1)
            {
                data.getStringExtra("response");
            }

        }

        Toast.makeText(getApplicationContext(),""+data.getStringExtra("response"), Toast.LENGTH_SHORT).show();

    }
}
