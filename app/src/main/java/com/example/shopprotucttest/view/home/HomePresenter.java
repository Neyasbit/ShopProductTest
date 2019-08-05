package com.example.shopprotucttest.view.home;

import com.example.shopprotucttest.Utils;
import com.example.shopprotucttest.model.MenuModel;
import com.example.shopprotucttest.model.ShopData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private HomeView view;
    private static final String TAG = HomePresenter.class.getSimpleName();

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    void getData(int page) {
        Call<ShopData> shopDataCall = Utils.getApi().getData(page);
        shopDataCall.enqueue(new Callback<ShopData>() {
            @Override
            public void onResponse(Call<ShopData> call, Response<ShopData> response) {
                HomeActivity.setIsLoading(true);
                if (response.isSuccessful() && response.body() != null) {
                    view.setDataList(response.body().getData());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }
            @Override
            public void onFailure(Call<ShopData> call, Throwable t) {
                HomeActivity.setIsLoading(true);
                /*Log.e(TAG, "onFailure: ", t );*/
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
    void getCategories(String type) {
        Call<ShopData> shopDataCall = Utils.getApi().getCategories(type);
        shopDataCall.enqueue(new Callback<ShopData>() {
            @Override
            public void onResponse(Call<ShopData> call, Response<ShopData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.setDataList(response.body().getData());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<ShopData> call, Throwable t) {
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

    void getDetailCategories(String type, int category_id) {
        Call<ShopData> shopDataCall = Utils.getApi().getDetailCategories(type, category_id);
        shopDataCall.enqueue(new Callback<ShopData>() {
            @Override
            public void onResponse(Call<ShopData> call, Response<ShopData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.setDataList(response.body().getData());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }
            @Override
            public void onFailure(Call<ShopData> call, Throwable t) {
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
    void getDataListMenu() {
        MenuModel menuModel = new MenuModel();
        view.setListDataMenu(menuModel.getListDataHeader(), menuModel.getListDataChild());
    }
}
