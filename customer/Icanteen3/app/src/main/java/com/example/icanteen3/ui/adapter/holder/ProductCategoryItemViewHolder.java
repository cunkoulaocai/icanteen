package com.example.icanteen3.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.example.icanteen3.R;
import com.example.icanteen3.base.BaseViewHolder;
import com.example.icanteen3.model.ShoppingCart;
import com.example.icanteen3.model.bean.ProductCategory;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeFrameLayout;


public class ProductCategoryItemViewHolder extends BaseViewHolder<ProductCategory> {

    @BindView(R.id.txt_name)
    TextView mNameTxt;

    @BindView(R.id.badge_view)
    BGABadgeFrameLayout mBadgeView;

    public ProductCategoryItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(ProductCategory category) {
        mNameTxt.setText(category.getName());

        int count = ShoppingCart.getInstance().getQuantityForCategory(category);
        if (count > 0) {
            mBadgeView.showTextBadge(String.valueOf(count));
        } else {
            mBadgeView.hiddenBadge();
        }
    }
}
