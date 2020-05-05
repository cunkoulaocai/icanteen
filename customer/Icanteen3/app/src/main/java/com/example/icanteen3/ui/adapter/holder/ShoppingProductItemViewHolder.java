package com.example.icanteen3.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.example.icanteen3.R;
import com.example.icanteen3.base.BaseViewHolder;
import com.example.icanteen3.model.bean.ShoppingEntity;
import com.example.icanteen3.util.StringFetcher;

import butterknife.BindView;


public class ShoppingProductItemViewHolder extends BaseViewHolder<ShoppingEntity> {

    @BindView(R.id.txt_name)
    TextView nameTxt;

    @BindView(R.id.txt_quantity)
    TextView quantityTxt;

    @BindView(R.id.txt_price)
    TextView priceTxt;

    public ShoppingProductItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(ShoppingEntity item) {
        nameTxt.setText(item.getName());
        quantityTxt.setText(StringFetcher.getString(R.string.label_quantity,
                item.getQuantity()));
        priceTxt.setText(StringFetcher.getString(R.string.label_price,
                item.getTotalPrice()));
    }
}
