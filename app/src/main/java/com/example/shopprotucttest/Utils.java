package com.example.shopprotucttest;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import com.example.shopprotucttest.api.ApiService;
import com.example.shopprotucttest.api.ShopClient;

public class Utils {

    public static ApiService getApi() {
        return ShopClient.retrofitBuilder().create(ApiService.class);
    }
    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }

}
