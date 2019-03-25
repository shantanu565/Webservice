package com.shantanu.example.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/";
    private static Retrofit retrofit = null;

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RequestInterface getReqInstance(){
       return getRetrofitInstance().create(RequestInterface.class);
    }

}
