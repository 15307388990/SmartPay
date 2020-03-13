package com.ming.smartpay.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.ming.smartpay.R;
import com.ming.smartpay.adapter.OrderAdapter;
import com.ming.smartpay.base.activity.BaseActivity;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.MianOrderBean;
import com.ming.smartpay.bean.OrderBean;
import com.ming.smartpay.bean.PaymentsListBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.ActivityAddCodeBinding;
import com.ming.smartpay.databinding.ActivitySearchOrderBinding;
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
 * 搜索订单
 */
public class SearchOrderActivity extends BaseActivity {

    ActivitySearchOrderBinding binding;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_order);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        binding.tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftKeyboard(SearchOrderActivity.this);
                    searchorder();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    protected void returnData(String data, String url) {
        if (url.contains(MyConst.searchorder)) {
            MianOrderBean orderBean = JSON.parseObject(data, MianOrderBean.class);
            if (orderBean.getData() == null) {
                binding.tvEmpty.setVisibility(View.VISIBLE);
                binding.recycleView.setVisibility(View.GONE);
            } else {
                binding.tvEmpty.setVisibility(View.GONE);
                binding.recycleView.setVisibility(View.VISIBLE);
                if (orderAdapter == null) {
                    orderAdapter = new OrderAdapter(orderBean.getData().getList());
                    binding.recycleView.setLayoutManager(new LinearLayoutManager(SearchOrderActivity.this));
                    binding.recycleView.setAdapter(orderAdapter);
                } else {
                    orderAdapter.setNewData(orderBean.getData().getList());
                }
            }
        }

    }

    /* 执行登录操作 */
    public void searchorder() {
        Map<String, String> map = new HashMap<>();
        map.put("order_no", binding.etSearch.getText().toString().trim());
        mQueue.add(ParamTools.packParam(MyConst.searchorder, SearchOrderActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    /**
     * 隐藏软键盘(无输入框或者说无法获取输入框。比如，微信支付时处于未登录状态，此时输入框
     * 是微信的，返回再隐藏键盘)
     *
     * @param context
     */
    public static void hideSoftKeyboard(@NonNull Activity context) {
        View view = context.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
