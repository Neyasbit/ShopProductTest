package com.example.shopprotucttest.model;

import com.example.shopprotucttest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuModel {
    private List<ExpandedMenuModel> listDataHeader = new ArrayList<>();
    private HashMap<ExpandedMenuModel, List<String>> listDataChild = new HashMap<>();

    public MenuModel() {
        initData();
    }

    private void initData(){
        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName("Книги");
        item1.setIconImg(R.drawable.book_icon);
        // Adding data header
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName("Диски");
        item2.setIconImg(R.drawable.cd_icon);
        listDataHeader.add(item2);

        // Adding child data
        List<String> heading1 = new ArrayList<String>();
        heading1.add("Программирование");
        heading1.add("Кулинария");
        heading1.add("Эзотерика");

        List<String> heading2 = new ArrayList<String>();
        heading2.add("Музыка");
        heading2.add("Видео");
        heading2.add("ПО");

        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), heading2);
    }

    public List<ExpandedMenuModel> getListDataHeader() {
        return listDataHeader;
    }

    public HashMap<ExpandedMenuModel, List<String>> getListDataChild() {
        return listDataChild;
    }
}
