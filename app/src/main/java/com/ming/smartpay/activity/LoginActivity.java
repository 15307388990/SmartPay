package com.ming.smartpay.activity;

import android.os.Bundle;

import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.MvpActivity;
import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;
import com.ming.smartpay.presenter.MainPresenter;
import com.ming.smartpay.view.MianView;

/**
 * @author luoming
 *created at 2019/8/19 9:22 AM
 * 登录
*/
public class LoginActivity extends MvpActivity<MianView, MainPresenter> {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatusBarUtil.setColor(this, AppUtils.getColor(R.color.SM_6277FF));


    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
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
