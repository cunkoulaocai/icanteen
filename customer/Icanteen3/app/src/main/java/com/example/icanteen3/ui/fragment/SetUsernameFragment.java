package com.example.icanteen3.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icanteen3.R;
import com.example.icanteen3.base.BaseController;
import com.example.icanteen3.ui.Display;
import com.example.icanteen3.util.ContentView;
import com.example.icanteen3.util.StringUtil;
import com.example.icanteen3.util.SystemUtil;
import com.example.icanteen3.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;


@ContentView(R.layout.fragment_set_username)
public class SetUsernameFragment extends BaseFragment<UserController.UserUiCallbacks>
        implements UserController.UserNameUi {

    @BindView(R.id.edit_content)
    EditText mContentEdit;

    @BindView(R.id.btn_clear)
    ImageView mClearBtn;

    @BindView(R.id.btn_save)
    TextView mSaveBtn;

    @Override
    protected BaseController getController() {
        return AppContext.getContext().getMainController().getUserController();
    }

    @Override
    protected String getTitle() {
        return getString(R.string.title_set_username);
    }

    @OnTextChanged(R.id.edit_content)
    public void onContentTextChange(CharSequence s) {
        int visible = StringUtil.isEmpty(s.toString()) ? View.GONE : View.VISIBLE;
        mClearBtn.setVisibility(visible);
    }

    @Override
    public void onSetUsernameFinish() {
        cancelLoading();
        ToastUtil.showToast(R.string.toast_success_set_username);
        // 关闭当前页面
        Display display = getDisplay();
        if (display != null) {
            display.popTopFragmentBackStack();
        }
    }

    @Override
    public void onResponseError(ResponseError error) {
        cancelLoading();
        ToastUtil.showToast(error);
    }

    @OnClick({R.id.btn_clear, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                mContentEdit.setText("");
                break;
            case R.id.btn_save:
                saveUserName();
                break;
        }
    }

    /**
     * 执行保存用户名的操作
     */
    private void saveUserName() {
        // 隐藏软键盘
        SystemUtil.hideKeyBoard(getContext());

        // 验证用户名是否为空
        final String username = mContentEdit.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            ToastUtil.showToast(R.string.toast_error_empty_username);
            return;
        }

        // 禁用保存按钮,避免重复点击
        mSaveBtn.setEnabled(false);
        // 显示提示对话框
        showLoading(R.string.label_being_something);
        // 发起设置用户名的网络请求
        getCallbacks().setUsername(username);
    }
}
