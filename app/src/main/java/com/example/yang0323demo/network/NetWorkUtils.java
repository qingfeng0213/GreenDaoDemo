package com.example.yang0323demo.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetWorkUtils {
    public static boolean getNetWork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return info.isAvailable();
        }
        return false;
    }
}
