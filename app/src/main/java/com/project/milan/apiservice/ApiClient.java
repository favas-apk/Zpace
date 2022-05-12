package com.project.milan.apiservice;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient
{
  //  ApiClient.BASE_URL = "http://nepdata.in/";
//    public  static  String BASE_URL="https://jsonplaceholder.typicode.com";
 //   public  static String BASE_URL="https://neptonglobal.in/";
    public  static String BASE_URL="http://milantex.in/";
  //  public  static String BASE_URL="https://ded0-157-49-142-102.ngrok.io/Sowndhar/mcollect/";
    public   static Retrofit retrofit=null;





    public static Retrofit getClient() {
        if (retrofit==null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }







}
