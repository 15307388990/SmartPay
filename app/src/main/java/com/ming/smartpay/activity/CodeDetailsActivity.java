package com.ming.smartpay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.BaseActivity;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.CoreDetailsBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.ActivityAddCodeBinding;
import com.ming.smartpay.databinding.ActivityCodeDetailsBinding;
import com.ming.smartpay.dialogfrment.CancelTheDealDialog;
import com.ming.smartpay.dialogfrment.CoreImgeDialog;
import com.ming.smartpay.dialogfrment.PaymentTypeDialog;
import com.ming.smartpay.utils.ParamTools;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * @author luoming
 * created at 2020-03-08 09:38
 * 二维码详情
 */
public class CodeDetailsActivity extends BaseActivity {

    ActivityCodeDetailsBinding binding;
    private String id;
    private String qrurl;
    private String userName;

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_code_details);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        id = getIntent().getStringExtra("id");
        initTitle();
        title.setText("二维码详情");
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelTheDealDialog.newInstance().setTitle("删除二维码").setContext("是否确认删除：" + userName).setOktext("确定").setOnClickListener(new CancelTheDealDialog.OnClickListener() {
                    @Override
                    public void successful() {
                        deleteqr();
                    }
                }).show(CodeDetailsActivity.this);
            }
        });
        binding.llCore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoreImgeDialog.newInstance(qrurl).show(CodeDetailsActivity.this);
            }
        });

    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(id))
            getqrinfo();

    }

    // 启动(关闭)二维码状态
    public void updataqrstatus(String status) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("status", status);
        mQueue.add(ParamTools.packParam(MyConst.updataqrstatus, CodeDetailsActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    @Override
    protected void returnData(String data, String url) {
        if (url.contains(MyConst.getqrinfo)) {
            CoreDetailsBean bean = JSON.parseObject(data, CoreDetailsBean.class);
            binding.tvId.setText(bean.getData().getId() + "");
            binding.tvPaymentname.setText(bean.getData().getPaymentname());
            binding.tvAmount.setText(bean.getData().getAmount());
            binding.tvName.setText(bean.getData().getQr_username());
            binding.swith.setChecked(bean.getData().getStatus() == 1);
            // 1,审核状态 【0.审核中 1.正常 2.禁用(审核失败)】 <number>
            if (bean.getData().getExamine() == 0) {
                binding.tvExamine.setText("审核中");
            } else if (bean.getData().getExamine() == 1) {
                binding.tvExamine.setText("正常");
            } else {
                binding.tvExamine.setText("禁用");
            }
            qrurl = bean.getData().getAccount_url();
            userName = bean.getData().getQr_username();
            binding.swith.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(!buttonView.isPressed())
                        return;
                    updataqrstatus(isChecked ? "1" : "0");
                }
            });
        } else if (url.contains(MyConst.updataqrstatus)) {
            getqrinfo();
        }

    }

    /* 获取二维码详情 */
    public void getqrinfo() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        mQueue.add(ParamTools.packParam(MyConst.getqrinfo, CodeDetailsActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    /* 删除二维码 */
    public void deleteqr() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        mQueue.add(ParamTools.packParam(MyConst.deleteqr, CodeDetailsActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }


}
