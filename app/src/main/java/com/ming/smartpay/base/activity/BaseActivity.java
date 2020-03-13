package com.ming.smartpay.base.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.ming.smartpay.R;
import com.ming.smartpay.activity.MainActivity;
import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.LongChainBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.dialogfrment.OrderDialog;
import com.ming.smartpay.utils.ParamTools;
import com.ming.smartpay.utils.SavePreferencesData;
import com.ming.smartpay.ws.WsManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public abstract class BaseActivity extends BasePermissionAty implements Response.Listener<String>, Response.ErrorListener, WsManager.IWsManagerActivityView {

    public RequestQueue mQueue; // 请求列队
    public View rl_title_bar;
    public View ll_view_back; // 返回
    public TextView title; // 标题
    public ImageView rightView; // 更多
    public SavePreferencesData mSavePreferencesData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // android 7.0系统解决拍照的问题
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            builder.detectFileUriExposure();
        }
        WsManager.getInstance().setmListener(this);
        mQueue = Volley.newRequestQueue(this);
        mSavePreferencesData = new SavePreferencesData(this);
        StatusBarUtil.setColor(BaseActivity.this, AppUtils.getColor(R.color.white));
        StatusBarUtil.setDarkMode(this);
        initEvent();
        initData();
    }

    private void onBack() {
        mQueue.stop();
        finish();
    }


    @Override
    public void onResponse(String response, String url) {
        dismissDialog();
        try {
            JSONObject json = new JSONObject(response);
            int stauts = json.optInt("code");
            String msg = json.optString("msg");
            if (stauts == 200) {
                this.returnData(response, url);
            } else {
                ToastShow.s(msg);
                this.returnMsg(response, url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ToastShow.s("数据格式不对");
        }
    }

    protected void returnData(String data, String url) {
    }

    protected void returnMsg(String data, String url) {
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        dismissDialog();
        ToastShow.s("网络连接异常");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mQueue.stop();
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 初始化标题栏
     */
    public void initTitle() {
        rl_title_bar = findViewById(R.id.title_bar_contentView);

        ll_view_back = findViewById(R.id.title_bar_IvLeft);
        title = (TextView) findViewById(R.id.title_bar_TvTitle);
        ll_view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQueue.stop();
                finish();
            }
        });
    }

    public abstract View getLayoutRes();

    public abstract void initEvent();

    public abstract void initData();

    @Override
    public void onTextMessage(String text) {
        LongChainBean bean = JSON.parseObject(text, LongChainBean.class);
        if (bean.getType().equals("orders")) {
            OrderDialog.newInstance(bean.getId(), bean.getOrder_no(), bean.getAmount(), bean.getPayment_name(), bean.getCreated_time())
                    .setOnClickListener(new OrderDialog.OnClickListener() {
                        @Override
                        public void toAccount(String amount, String id) {
                            confirm(amount, id);
                        }
                    }).show(BaseActivity.this);
        }
    }

    //接受排队
    public void confirm(String amount, String id) {
        Map<String, String> map = new HashMap<>();
        map.put("amount", amount);
        map.put("id", id);
        mQueue.add(ParamTools.packParam(MyConst.confirm, BaseActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onConnected() {

    }

    protected boolean useEventBus() {
        return false;
    }
}
