package com.example.yang0323demo.mvp.view;

import com.example.yang0323demo.bean.BannerBean;
import com.example.yang0323demo.bean.HomeBean;

public interface IView {
    void getPreBanner(BannerBean bannerBean);
    void getPreHome(HomeBean homeBean);
}
