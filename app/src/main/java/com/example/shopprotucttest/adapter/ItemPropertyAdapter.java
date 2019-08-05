package com.example.shopprotucttest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopprotucttest.R;
import com.example.shopprotucttest.model.ShopData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemPropertyAdapter extends RecyclerView.Adapter<ItemPropertyAdapter.ViewHolder> {

    private List<ShopData.Item.Property> propertyList;
    private Context context;

    public ItemPropertyAdapter(List<ShopData.Item.Property> propertyList, Context context) {
        this.propertyList = propertyList;
        this.context = context;
    }

    public void setPropertyList(List<ShopData.Item.Property> propertyList) {
        this.propertyList = propertyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_property, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        for (ShopData.Item.Property item : propertyList) {
            switch (item.getName()) {
                case "cd_type":
                    holder.property_name_1.setText("Тип диска");
                    holder.property_value_1.setText(item.getValue());
                    break;
                case "cd_content":
                    holder.property_name_2.setText("Контент");
                    holder.property_value_2.setText(item.getValue());
                    break;
                case "pages_count":
                    holder.property_name_1.setText("Количество страниц");
                    holder.property_value_1.setText(item.getValue());
                    break;
                case "programming_language":
                    holder.property_name_2.setText("Язык программирования");
                    holder.property_value_2.setText(item.getValue());
                    break;
                case "reader_min_age":
                    holder.property_name_2.setText("Минимальный возраст читателя");
                    holder.property_value_2.setText(item.getValue());
                    break;
                case "main_ingredient":
                    holder.property_name_2.setText("Основной ингредиент");
                    holder.property_value_2.setText(item.getValue());
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_property_name_1)
        TextView property_name_1;
        @BindView(R.id.tv_property_name_2)
        TextView property_name_2;
        @BindView(R.id.tv_property_value_1)
        TextView property_value_1;
        @BindView(R.id.tv_property_value_2)
        TextView property_value_2;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
