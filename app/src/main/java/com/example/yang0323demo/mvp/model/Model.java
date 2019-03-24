package com.example.yang0323demo.mvp.model;

import com.example.yang0323demo.Api.UserApiService;
import com.example.yang0323demo.bean.BannerBean;
import com.example.yang0323demo.bean.HomeBean;
import com.example.yang0323demo.network.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Model implements IModel {
    @Override
    public void getDate(String url, final ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getHomeData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<HomeBean>() {
                    @Override
                    public void onNext(HomeBean value) {
                        callBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getBannerDate(String url, final ModelCallBack callBack) {
        UserApiService userApiService = RetrofitUtils.getInstance().create(UserApiService.class);
        userApiService.getBannerData(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<BannerBean>() {
                    @Override
                    public void onNext(BannerBean value) {
                        callBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
