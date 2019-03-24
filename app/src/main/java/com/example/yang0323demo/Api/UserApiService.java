package com.example.yang0323demo.Api;

import com.example.yang0323demo.bean.BannerBean;
import com.example.yang0323demo.bean.HomeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

public interface UserApiService {
    @GET
    Observable<BannerBean> getBannerData(@Url String url);
    @GET
    Observable<HomeBean> getHomeData(@Url String url);
}
