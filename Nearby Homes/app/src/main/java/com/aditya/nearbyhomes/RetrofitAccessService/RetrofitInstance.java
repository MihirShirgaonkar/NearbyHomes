package com.aditya.nearbyhomes.RetrofitAccessService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitInstance {
    static RetrofitInterface retrofitInterface;



    public static Retrofit retrofit;


    public static final String BASE_URL = "http://adityajaitpal22-001-site1.etempurl.com/";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }




    public static RetrofitInterface getSeviceData() {
        retrofitInterface = RetrofitInstance.getRetrofit().create(RetrofitInterface.class);
        return retrofitInterface;
    }





}
