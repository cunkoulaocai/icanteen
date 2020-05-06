package com.example.icanteen5.ui.fragment;

import android.support.v4.app.Fragment;

import com.umeng.analytics.MobclickAgent;



public class BaseFragment extends Fragment {

    //添加友盟统计
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this.getContext());
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this.getContext());
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }
}
