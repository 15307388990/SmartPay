package com.ming.smartpay.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.BaseActivity;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.ActivityChangePasswordBinding;
import com.ming.smartpay.utils.MD5Util;
import com.ming.smartpay.utils.ParamTools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luoming
 * created at 2019/8/19 9:22 AM
 * 修改登录密码
 */
public class ChangeLoginPasswordActivity extends BaseActivity {

    private ActivityChangePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        initTitle();
        title.setText("修改登录密码");
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatepassword();
            }
        });

    }

    /* 修改密码 */
    public void updatepassword() {
        Map<String, String> map = new HashMap<>();
        map.put("password", MD5Util.getMD5String(binding.etOld.getText().toString().trim()));
        map.put("new_password", MD5Util.getMD5String(binding.etNew.getText().toString().trim()));
        map.put("new_password_confirm", MD5Util.getMD5String(binding.etNew2.toString().trim()));
        mQueue.add(ParamTools.packParam(MyConst.updatepassword, ChangeLoginPasswordActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    @Override
    public void initData() {

    }


}
