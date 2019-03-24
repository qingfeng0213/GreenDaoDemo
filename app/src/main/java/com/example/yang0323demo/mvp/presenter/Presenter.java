package com.example.yang0323demo.mvp.presenter;



import com.example.yang0323demo.Api.ApiService;
import com.example.yang0323demo.MainActivity;
import com.example.yang0323demo.bean.BannerBean;
import com.example.yang0323demo.bean.HomeBean;
import com.example.yang0323demo.mvp.model.IModel;
import com.example.yang0323demo.mvp.model.Model;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class Presenter<T> implements IPresenter {
    MainActivity mainActivity;
    private final Model model;
    private Reference reference;

    public Presenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        model = new Model();
    }
    public void attach(T t){
        reference = new WeakReference<>(t);
    }
    public void detach(){
        if (reference.get()!= null) {
            reference.clear();
            reference=null;
        }
    }
    @Override
    public void getBanner() {
        model.getBannerDate(ApiService.BANNER_URL,new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                BannerBean bannerBean = (BannerBean) obj;
                mainActivity.getPreBanner(bannerBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }

    @Override
    public void getHome() {
        model.getDate(ApiService.HOME_URL, new IModel.ModelCallBack() {
            @Override
            public void onSuccess(Object obj) {
                HomeBean homeBean = (HomeBean) obj;
                mainActivity.getPreHome(homeBean);
            }

            @Override
            public void onFail(Throwable e) {

            }
        });
    }
}
