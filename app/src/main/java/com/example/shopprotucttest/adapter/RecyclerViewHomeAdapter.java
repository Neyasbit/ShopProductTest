package com.example.shopprotucttest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopprotucttest.R;
import com.example.shopprotucttest.model.ShopData;

import java.text.NumberFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.RecyclerViewHolder> {

    private List<ShopData.Item> itemList;
    private Context context;
    private ListItemClickListener itemClickListener;

    public void setItemClickListener(ListItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public RecyclerViewHomeAdapter(List<ShopData.Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public void setItemList(List<ShopData.Item> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ShopData.Item item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface ListItemClickListener{
        void itemOnClick(View view, int position);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.tv_title)
        TextView item_title;
        @BindView(R.id.tv_barcode)
        TextView barcode;
        @BindView(R.id.tv_category)
        TextView item_category;
        @BindView(R.id.tv_price)
        TextView item_price;
        @BindView(R.id.card_item)
        CardView cardView_item;
        @BindView(R.id.img_icon)
        ImageView image_icon;

        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardView_item.setOnClickListener(this);
        }

        void bind(ShopData.Item type) {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            item_title.setText(type.getName());
            barcode.setText(type.getBarCode());
            item_category.setText(type.getType());
            item_price.setText(currencyFormat.format(type.getPrice()));

            switch (type.getType()) {
                case "book":
                    image_icon.setImageResource(R.drawable.book_icon);
                    break;
                case "cd":
                    image_icon.setImageResource(R.drawable.cd_icon);
                    break;
            }
        }

        @Override
        public void onClick(View view) {
            itemClickListener.itemOnClick(view, getAdapterPosition());
        }
    }
}
