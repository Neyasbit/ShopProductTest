package com.example.shopprotucttest.view.home;

import com.example.shopprotucttest.model.ExpandedMenuModel;
import com.example.shopprotucttest.model.ShopData;

import java.util.HashMap;
import java.util.List;

public interface HomeView {
    void setDataList(List<ShopData.Item> itemList);
    void onErrorLoading(String message);
    void setListDataMenu(List<ExpandedMenuModel> listDataHeader, HashMap<ExpandedMenuModel, List<String>> listDataChild);
}
