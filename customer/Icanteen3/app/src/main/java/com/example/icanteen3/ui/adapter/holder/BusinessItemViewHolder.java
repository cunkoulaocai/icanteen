package com.example.icanteen3.ui.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.example.icanteen3.R;
import com.example.icanteen3.base.BaseViewHolder;
import com.example.icanteen3.model.ShoppingCart;
import com.example.icanteen3.model.bean.Business;
import com.example.icanteen3.util.StringFetcher;
import com.example.icanteen3.widget.PicassoImageView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.badgeview.BGABadgeFrameLayout;

import static com.example.icanteen3.util.Constants.ClickType.CLICK_TYPE_BUSINESS_CLICKED;


public class BusinessItemViewHolder extends BaseViewHolder<Business> {

    @BindView(R.id.list_item)
    View mListItem;

    @BindView(R.id.badge_view)
    BGABadgeFrameLayout mBadgeView;

    @BindView(R.id.img_photo)
    PicassoImageView mPhotoImg;

    @BindView(R.id.txt_name)
    TextView mNameTxt;

    @BindView(R.id.txt_month_sales)
    TextView mMonthSalesTxt;

    @BindView(R.id.txt_content)
    TextView mMultiContentTxt;

    public BusinessItemViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(Business business) {
        mPhotoImg.loadBusinessPhoto(business);
        mNameTxt.setText(business.getName());
        mMonthSalesTxt.setText(StringFetcher.getString(R.string.label_month_sales,
                business.getMonthSales()));
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        int count = shoppingCart.getTotalQuantity();
        if (business.getId().equals(shoppingCart.getBusinessId())
                && count > 0) {
            mBadgeView.showTextBadge(String.valueOf(count));
        } else {
            mBadgeView.hiddenBadge();
        }
    }

    @OnClick(R.id.list_item)
    public void onClick(View view) {
        notifyItemAction(CLICK_TYPE_BUSINESS_CLICKED);
    }
}
