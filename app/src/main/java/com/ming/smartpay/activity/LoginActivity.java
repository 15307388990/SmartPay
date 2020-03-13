package com.ming.smartpay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.BaseActivity;
import com.ming.smartpay.bean.LoginBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.ActivityLoginBinding;
import com.ming.smartpay.utils.MD5Util;
import com.ming.smartpay.utils.ParamTools;
import com.ming.smartpay.utils.Tools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luoming
 * created at 2019/8/19 9:22 AM
 * 登录
 */
public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin(binding.etPhone.getText().toString().trim(), binding.etPwd.getText().toString().trim());
            }
        });
        binding.cbPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //隐藏
                    binding.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //显示
                    binding.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(mSavePreferencesData.getStringData("token"))) {
            Tools.jump(LoginActivity.this, MainActivity.class, true);
        }


    }

    @Override
    protected void returnData(String data, String url) {
        super.returnData(data, url);
        LoginBean bean = JSON.parseObject(data, LoginBean.class);
        mSavePreferencesData.putStringData("token", bean.getData().getToken());
        Tools.jump(LoginActivity.this, MainActivity.class, true);

    }

    /* 执行登录操作 */
    public void toLogin(String name, String pwd) {
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        pwd = MD5Util.getMD5String(pwd);
        map.put("password", pwd);
        map.put("os", "android");
        map.put("version", Tools.packageCode(this) + "");
        if (!TextUtils.isEmpty(Tools.getUUID())) {
            map.put("device", Tools.getUUID());//设备唯一ID
        }
        mQueue.add(ParamTools.packParam(MyConst.dologin, LoginActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }


}
