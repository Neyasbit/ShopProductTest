package com.example.shopprotucttest.api;

import com.example.shopprotucttest.model.ShopData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("shop-item")
    Call<ShopData> getData(@Query("page") int page);

    @GET("shop-item")
    Call<ShopData> getCategories(@Query("shop_item_type") String type);

    @GET("shop-item")
    Call<ShopData> getDetailCategories(@Query("shop_item_type") String type, @Query("book_category_id") int category_id);

    @GET("shop-item")
    Call<ShopData> getRefreshDataList(@Query("shop_item") String par);
}
