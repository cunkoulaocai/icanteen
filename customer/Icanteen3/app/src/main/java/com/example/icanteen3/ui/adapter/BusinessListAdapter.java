package com.example.icanteen3.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.icanteen3.R;
import com.example.icanteen3.base.BaseAdapter;
import com.example.icanteen3.model.bean.Business;
import com.example.icanteen3.ui.adapter.holder.BusinessItemViewHolder;


public class BusinessListAdapter extends BaseAdapter<Business> {

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.adapter_business_item;
    }

    @Override
    public BusinessItemViewHolder createViewHolder(View view, int viewType) {
        return new BusinessItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder holder, Business business, int position) {
        if (holder instanceof BusinessItemViewHolder) {
            ((BusinessItemViewHolder) holder).bind(business);
        }
    }
}
