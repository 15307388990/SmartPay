package com.ming.smartpay.activity;

import android.os.Bundle;

import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.MvpActivity;
import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;
import com.ming.smartpay.presenter.ChangeLoginPasswordPresenter;
import com.ming.smartpay.presenter.ChangePayPasswordPresenter;
import com.ming.smartpay.view.modelview.ChangeLoginPasswordView;
import com.ming.smartpay.view.modelview.ChangePayPasswordView;

/**
 * @author luoming
 * created at 2019/8/19 9:22 AM
 * 修改支付密码
 */
public class ChangePayPasswordActivity extends MvpActivity<ChangePayPasswordView, ChangePayPasswordPresenter> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_password,"修改支付密码");
        StatusBarUtil.setColor(this, AppUtils.getColor(R.color.white));
        onClick();


    }

    private void onClick() {
    }

    @Override
    protected ChangePayPasswordPresenter createPresenter() {
        return new ChangePayPasswordPresenter();
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

}
