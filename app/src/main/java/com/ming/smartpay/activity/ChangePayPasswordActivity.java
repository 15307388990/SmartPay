package com.ming.smartpay.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.BaseActivity;
import com.ming.smartpay.databinding.ActivityPayPasswordBinding;

/**
 * @author luoming
 * created at 2019/8/19 9:22 AM
 * 修改支付密码
 */
public class ChangePayPasswordActivity extends BaseActivity {
    private ActivityPayPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pay_password);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        initTitle();
        title.setText("修改支付密码");
    }

    @Override
    public void initData() {

    }



}
