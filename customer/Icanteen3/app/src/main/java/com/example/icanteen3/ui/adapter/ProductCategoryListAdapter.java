package com.example.icanteen3.ui.adapter;

import android.view.View;

import com.example.icanteen3.R;
import com.example.icanteen3.model.bean.ProductCategory;
import com.example.icanteen3.ui.adapter.holder.ProductCategoryItemViewHolder;

public class ProductCategoryListAdapter extends BaseListAdapter<ProductCategory, ProductCategoryItemViewHolder> {

    @Override
    protected int getViewLayoutId() {
        return R.layout.layout_product_category_item;
    }

    @Override
    protected ProductCategoryItemViewHolder createViewHolder(View view) {
        return new ProductCategoryItemViewHolder(view);
    }

    @Override
    protected void showData(ProductCategoryItemViewHolder holder, int position) {
        ProductCategory category = getItem(position);
        holder.bind(category);
    }
}
