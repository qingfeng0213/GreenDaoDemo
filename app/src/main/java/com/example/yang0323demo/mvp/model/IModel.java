package com.example.yang0323demo.mvp.model;

import java.util.HashMap;

public interface IModel {
    void getDate(String url,  ModelCallBack callBack);
    void getBannerDate(String url, ModelCallBack callBack);

    interface  ModelCallBack{
        void  onSuccess(Object obj);
        void  onFail(Throwable e);
    }
}
