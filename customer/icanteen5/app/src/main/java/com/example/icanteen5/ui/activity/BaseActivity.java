package com.example.icanteen5.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.icanteen5.dagger2.component.DaggerCommonComponent;
import com.example.icanteen5.dagger2.module.PresenterModule;
import com.example.icanteen5.presenter.activity.OrderPresenter;
import com.example.icanteen5.presenter.activity.PaymentPresenter;
import com.example.icanteen5.presenter.activity.ReceiptAddressPresenter;
import com.example.icanteen5.ui.IView;
import com.umeng.analytics.MobclickAgent;

import javax.inject.Inject;



public class BaseActivity extends AppCompatActivity implements IView {
    @Inject
    ReceiptAddressPresenter mAddressPresenter;

    @Inject
    OrderPresenter mOrderPresenter;

    @Inject
    PaymentPresenter mPaymentPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCommonComponent
                .builder()
                .presenterModule(new PresenterModule(this))
                .build()
                .in(this);
    }

    @Override
    public void success(Object o) {

    }

    @Override
    public void failed(String msg) {

    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

}
