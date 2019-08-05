package com.example.shopprotucttest;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyApplication extends Application {
    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();

        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }

    public static MyApplication getInstance() {
        return INSTANCE;
    }

    public static boolean hasNetwork(){
        return INSTANCE.isNetworkConnected();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
