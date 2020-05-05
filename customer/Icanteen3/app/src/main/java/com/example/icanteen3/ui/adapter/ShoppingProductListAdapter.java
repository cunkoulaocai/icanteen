package com.example.icanteen3.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.icanteen3.R;
import com.example.icanteen3.base.BaseAdapter;
import com.example.icanteen3.model.bean.ShoppingEntity;
import com.example.icanteen3.ui.adapter.holder.ShoppingProductItemViewHolder;


public class ShoppingProductListAdapter extends BaseAdapter<ShoppingEntity> {

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.adapter_shopping_product_item;
    }

    @Override
    public RecyclerView.ViewHolder createViewHolder(View view, int viewType) {
        return new ShoppingProductItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, ShoppingEntity entity, int position) {
        if (holder instanceof ShoppingProductItemViewHolder) {
            ((ShoppingProductItemViewHolder) holder).bind(entity);
        }
    }
}
