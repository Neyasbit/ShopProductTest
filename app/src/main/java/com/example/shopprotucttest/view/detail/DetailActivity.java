package com.example.shopprotucttest.view.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopprotucttest.R;
import com.example.shopprotucttest.adapter.ItemPropertyAdapter;
import com.example.shopprotucttest.model.ShopData;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.tv_detail_name)
    TextView detail_name;
    @BindView(R.id.tv_detail_price)
    TextView detail_price;
    @BindView(R.id.tv_detail_barcode)
    TextView detail_barcode;
    @BindView(R.id.recyclerView_property)
    RecyclerView recyclerView;
    @BindView(R.id.iv_detail)
    ImageView iv_detail;
    @BindView(R.id.tv_detail_lang)
    TextView book_category;

    private List<ShopData.Item.Property> propertyList = new ArrayList<>();
    private ItemPropertyAdapter propertyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);
        ShopData.Item item = (ShopData.Item)getIntent().getSerializableExtra("item");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        detail_name.setText(item.getName());
        detail_price.setText(currencyFormat.format(item.getPrice()));
        detail_barcode.setText("штрих-код: "+item.getBarCode());
        switch (item.getType()) {
            case "book":
                iv_detail.setImageResource(R.drawable.book_icon);
                book_category.setText(item.getBookCategory().getName());
                break;
            case "cd":
                iv_detail.setImageResource(R.drawable.cd_icon);
                book_category.setVisibility(View.GONE);
                break;
        }
        propertyAdapter = new ItemPropertyAdapter(propertyList, this);
        propertyAdapter.setPropertyList(item.getProperties());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(propertyAdapter);

    }
}
