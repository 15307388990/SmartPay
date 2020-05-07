package com.ming.smartpay.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.BaseActivity;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.OrderDetailsBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.ActivityOrderDetailsBinding;
import com.ming.smartpay.utils.ParamTools;

import java.util.HashMap;
import java.util.Map;


/**
 * @author luoming
 * created at 2020-03-08 09:38
 * 订单详情
 */
public class OrderDetailsActivity extends BaseActivity {

    ActivityOrderDetailsBinding binding;
    private String id;
    private ClipboardManager cmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_details);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        initTitle();
        title.setText("订单详情");
        id = getIntent().getStringExtra("id");
        cmb = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);

    }

    @Override
    public void initData() {
        getdetails();
    }

    @Override
    protected void returnData(String data, String url) {
        if (url.contains(MyConst.getdetails)) {
            OrderDetailsBean bean = JSON.parseObject(data, OrderDetailsBean.class);
            initView(bean);
        } else if (url.contains(MyConst.confirmrefunds)) {
            getdetails();
        }

    }

    private void initView(OrderDetailsBean bean) {
        binding.tvOrderNo.setText("订单号：" + bean.getData().getOrder_no());
        binding.tvBranchName.setText(bean.getData().getBranchName());
        binding.tvBankCardNo.setText(bean.getData().getBankCardNo());
        binding.tvName.setText(bean.getData().getAccountName());
        binding.tvReturnAmount.setText(bean.getData().getReturn_amount());
        binding.tvOrderMk.setText(bean.getData().getOrder_mk());
        binding.tvAmount.setText("到账：" + bean.getData().getAmount());
        binding.tvMinersamount.setText("收入：" + bean.getData().getMinersamount());
        binding.tvTimer.setText("时间：" + bean.getData().getCreated_time());
        binding.tvPhoneId.setText("码号：" + bean.getData().getPhone_id());
        if (bean.getData().getWithdraws_status() == 0) {
            binding.btnOk.setVisibility(View.VISIBLE);
        } else {
            binding.btnOk.setVisibility(View.GONE);
        }
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmrefunds();
            }
        });
        //复制开户行
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmb.setText(binding.tvBranchName.getText().toString().trim());
                ToastShow.s("复制开户行成功");
            }
        });
        //复制卡号
        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmb.setText(binding.tvBankCardNo.getText().toString().trim());
                ToastShow.s("复制卡号成功");
            }
        });

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmb.setText(binding.tvName.getText().toString().trim());
                ToastShow.s("复制姓名成功");
            }
        });
        binding.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmb.setText(binding.tvReturnAmount.getText().toString().trim());
                ToastShow.s("复制转账金额成功");
            }
        });
        binding.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmb.setText(binding.tvOrderMk.getText().toString().trim());
                ToastShow.s("复制备注信息成功");
            }
        });


    }

    public void getdetails() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        mQueue.add(ParamTools.packParam(MyConst.getdetails, OrderDetailsActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    /* 确认返款 */
    public void confirmrefunds() {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        mQueue.add(ParamTools.packParam(MyConst.confirmrefunds, OrderDetailsActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }


}
