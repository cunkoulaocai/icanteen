/*package com.example.icanteen3.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class OrderReportView extends FrameLayout {


    public OrderReportView(Context context) {
        this(context, null);
    }

    public OrderReportView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_order_report_view, this);
      
        initViews();
    }

    private void initViews() {

    }

    public void setupContent() {
        // 价格
        mOriginPriceTxt.setText(StringFetcher.getString(R.string.label_price, cartInfo.getOriginPrice()));
        mDiscountPriceTxt.setText(StringFetcher.getString(R.string.label_price, cartInfo.getDiscountPrice()));
        mTotalPriceTxt.setText(StringFetcher.getString(R.string.label_price, cartInfo.getTotalPrice()));
        // 商家名称
        final Business businessInfo = cartInfo.getBusiness();
        if (businessInfo != null) {
            mBusinessNameTxt.setText(businessInfo.getName());
            mBusinessNameTxt.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mNameClickHandle != null) {
                        mNameClickHandle.onBusinessNameClick(businessInfo);
                    }
                }
            });
        }
        // 选购的商品
        List<ShoppingEntity> shoppingProducts = cartInfo.getShoppingProducts();
        mShoppingProductAdapter.setItems(shoppingProducts);
        mShoppingProductDivider.setVisibility(CollectionUtil.isEmpty(shoppingProducts) ? View.GONE : View.VISIBLE);
        // 商家额外费用
        List<CartInfo.ExtraFee> extraFees = cartInfo.getExtraFees();
        mExtraFeeListAdapter.setItems(extraFees);
        mExtraFeeDivider.setVisibility(CollectionUtil.isEmpty(extraFees) ? View.GONE : View.VISIBLE);
        // 商家活动优惠
        List<CartInfo.DiscountInfo> discountInfos = cartInfo.getDiscountInfos();
        mDiscountInfoAdapter.setItems(discountInfos);
        mDiscountListDivider.setVisibility(CollectionUtil.isEmpty(discountInfos) ? View.GONE : View.VISIBLE);
    }

    public void setNameClickHandle(BusinessNameClickHandle nameClickHandle) {
        if (nameClickHandle != null) {
            mNameClickHandle = nameClickHandle;
            mArrowImg.setVisibility(View.VISIBLE);
        }
    }
}
*/