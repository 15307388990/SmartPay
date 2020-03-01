package com.ming.smartpay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.MvpActivity;
import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;
import com.ming.smartpay.databinding.ActivityLoginBinding;
import com.ming.smartpay.presenter.MainPresenter;
import com.ming.smartpay.view.modelview.MianView;

import butterknife.BindView;

/**
 * @author luoming
 * created at 2019/8/19 9:22 AM
 * 登录
 */
public class LoginActivity extends MvpActivity<MianView, MainPresenter> {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_login);
        StatusBarUtil.setColor(this, AppUtils.getColor(R.color.SM_6277FF));
        onClick();


    }

    private void onClick() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
