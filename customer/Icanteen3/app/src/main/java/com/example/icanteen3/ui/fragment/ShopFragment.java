package com.example.icanteen3.ui.fragment;

import com.example.icanteen3.R;
import com.example.icanteen3.base.BaseController;
import com.example.icanteen3.model.bean.Business;
import com.example.icanteen3.ui.adapter.BusinessListAdapter;

import static com.example.icanteen3.util.Constants.ClickType.CLICK_TYPE_BUSINESS_CLICKED;


public class ShopFragment extends BaseListFragment<Business, BusinessController.BusinessUiCallbacks>
        implements BusinessController.BusinessListUi {

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getBusinessController();
    }

    @Override
    public Business getRequestParameter() {
        return null;
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_shop);
    }

    @Override
    protected boolean isShowBack() {
        return false;
    }

    @Override
    protected BusinessListAdapter getAdapter() {
        return new BusinessListAdapter();
    }

    @Override
    protected void refreshPage() {
        getCallbacks().refresh();
    }

    @Override
    protected void nextPage() {
        getCallbacks().nextPage();
    }

    @Override
    protected void onItemClick(int actionId, Business business) {
        switch (actionId) {
            case CLICK_TYPE_BUSINESS_CLICKED:
                getCallbacks().showBusiness(business);
                break;
        }
    }
}
