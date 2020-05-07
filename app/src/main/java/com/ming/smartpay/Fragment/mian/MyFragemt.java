package com.ming.smartpay.fragment.mian;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.ming.smartpay.R;
import com.ming.smartpay.activity.ChangeLoginPasswordActivity;
import com.ming.smartpay.activity.ChangePayPasswordActivity;
import com.ming.smartpay.activity.LoginActivity;
import com.ming.smartpay.adapter.MyAdapter;
import com.ming.smartpay.base.fragment.BaseFragment;
import com.ming.smartpay.bean.UserInfoBean;
import com.ming.smartpay.bean.VersionBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.FragemtMyBinding;
import com.ming.smartpay.dialogfrment.VersionUpgradeDialog;
import com.ming.smartpay.utils.MD5Util;
import com.ming.smartpay.utils.ParamTools;
import com.ming.smartpay.utils.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class MyFragemt extends BaseFragment {
    private FragemtMyBinding binding;
    private MyAdapter myAdapter;


    public static MyFragemt newInstance() {
        return new MyFragemt();
    }


    private void onClick() {
        binding.llCheckUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                version();

            }
        });
        binding.llChangeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeLoginPasswordActivity.class);
                startActivity(intent);
            }
        });
        binding.llOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outuser();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragemt_my, null, false);
        return binding.getRoot();
    }

    /* 获取用户信息 */
    public void getuserinfo() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.getuserinfo, getActivity(), this, this, map, Request.Method.GET));
        showLoadDialog();
    }

    /* 退出登录 */
    public void outuser() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.outuser, getActivity(), this, this, map, Request.Method.GET));
        showLoadDialog();
    }

    /* 退出登录 */
    public void version() {
        Map<String, String> map = new HashMap<>();
        map.put("version", Tools.packageCode(getActivity()) + "");
        mQueue.add(ParamTools.packParam(MyConst.version, getActivity(), this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    @Override
    public void initEvent() {
        onClick();

    }

    @Override
    public void initData() {
        getuserinfo();
    }

    @Override
    public void returnData(String data, String url) {
        if (url.contains(MyConst.getuserinfo)) {
            UserInfoBean bean = JSON.parseObject(data, UserInfoBean.class);
            binding.tvName.setText(bean.getData().getName());
            binding.handlingFeeBalance.setText("额度：" + bean.getData().getHandlingFeeBalance());
            ArrayList<String> strings = new ArrayList<>();
            for (UserInfoBean.DataBean.ListBean listBean : bean.getData().getList()) {
                strings.add(listBean.getPaymentname() + "：" + Double.valueOf(listBean.getRate()) + "%");
            }
            if (myAdapter == null) {
                myAdapter = new MyAdapter(strings);
                binding.recyclerview.setAdapter(myAdapter);
                binding.recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            } else {
                myAdapter.setNewData(strings);
            }
        } else if (url.contains(MyConst.outuser)) {
            mSavePreferencesData.putStringData("token", "");
            Tools.jump(getActivity(), LoginActivity.class, true);

        } else if (url.contains(MyConst.version)) {
            VersionBean bean = JSON.parseObject(data, VersionBean.class);
            VersionUpgradeDialog.newInstance(bean).show(getActivity());
        }
    }
}
