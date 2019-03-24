package com.example.yang0323demo.app;

import android.app.Application;

import com.example.yang0323demo.network.GreenDaoManager;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        GreenDaoManager.getInstance().setDataBase(this);
    }
}
