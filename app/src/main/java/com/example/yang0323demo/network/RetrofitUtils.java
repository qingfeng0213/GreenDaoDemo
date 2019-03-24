package com.example.yang0323demo.network;

import com.example.yang0323demo.Api.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private Retrofit retrofit;
    //单例模式
    public static final class SINGLE_INSTANCE{
        public static final RetrofitUtils _INSTANCE = new RetrofitUtils();
    }
    public static RetrofitUtils getInstance(){
        return SINGLE_INSTANCE._INSTANCE;
    }
    //网络请求
    private RetrofitUtils(){
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(buildOkhttpClient())
                .build();
    }
    //设置读写超时
    private OkHttpClient buildOkhttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//打印请求参数
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .writeTimeout(3000,TimeUnit.MILLISECONDS)
                .readTimeout(3000,TimeUnit.MILLISECONDS)
                .build();
    }
    public Retrofit getRetrofit(){
        return retrofit;
    }
    //自定义泛型
    public <T> T  create(Class<T> clazz){
        return retrofit.create(clazz);
    }
}
