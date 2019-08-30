package com.ming.smartpay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.MvpActivity;
import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;
import com.ming.smartpay.presenter.ChangeLoginPasswordPresenter;
import com.ming.smartpay.presenter.MainPresenter;
import com.ming.smartpay.view.modelview.ChangeLoginPasswordView;
import com.ming.smartpay.view.modelview.MianView;

import butterknife.BindView;

/**
 * @author luoming
 * created at 2019/8/19 9:22 AM
 * 修改登录密码
 */
public class ChangeLoginPasswordActivity extends MvpActivity<ChangeLoginPasswordView, ChangeLoginPasswordPresenter> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        StatusBarUtil.setColor(this, AppUtils.getColor(R.color.white));
        onClick();


    }

    private void onClick() {
    }

    @Override
    protected ChangeLoginPasswordPresenter createPresenter() {
        return new ChangeLoginPasswordPresenter();
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
