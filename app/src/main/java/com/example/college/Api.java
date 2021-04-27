package com.example.college;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit=null;

    public static Retrofit getClient()
    {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder()
                .baseUrl("https://eceianstech.com/")
                .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
                .build();

        return retrofit;
    }

}

