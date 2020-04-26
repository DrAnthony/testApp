package com.example.testapp.utils.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtils {
    private static HttpUtils instance=null;
    private HttpUtils(){};
    private final static String ROOT_URL="http://192.168.0.104:8080/";
    public static HttpUtils getInstance(){
        if(instance==null){
            instance=new HttpUtils();
        }
        return instance;
    }
    public <T> T getHttpClient(Class<T> cls){
        return (T)getRetrofit(ROOT_URL).create(cls);
    }
    private Retrofit getRetrofit(String url){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
