package com.example.icanteen3.ui.fragment;

import android.os.Bundle;

import com.example.icanteen3.R;
import com.example.icanteen3.base.BaseController;
import com.example.icanteen3.model.bean.Business;
import com.example.icanteen3.ui.Display;
import com.example.icanteen3.util.ContentView;

@ContentView(R.layout.fragment_business_detail)
public class BusinessDetailFragment extends BaseFragment<BusinessController.BusinessUiCallbacks>
        implements BusinessController.BusinessProfileUi {

    private Business mBusiness;

    public static BusinessDetailFragment create(Business business) {
        BusinessDetailFragment fragment = new BusinessDetailFragment();
        if (business != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Display.PARAM_OBJ, business);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getBusinessController();
    }

    @Override
    protected void handleArguments(Bundle arguments) {
        if (arguments != null) {
            mBusiness = (Business) arguments.getSerializable(Display.PARAM_OBJ);
        }
    }

    @Override
    public Business getRequestParameter() {
        return mBusiness;
    }
}