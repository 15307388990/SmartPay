package com.ming.smartpay.fragment.mian;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.ming.smartpay.R;
import com.ming.smartpay.activity.AddCodeActivity;
import com.ming.smartpay.adapter.CoreListAdapter;
import com.ming.smartpay.base.fragment.BaseFragment;
import com.ming.smartpay.bean.CoreBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.FragemtCodeBinding;
import com.ming.smartpay.utils.ParamTools;
import com.ming.smartpay.utils.Tools;

import java.util.HashMap;
import java.util.Map;

public class CoreFragemt extends BaseFragment {
    private FragemtCodeBinding binding;
    private CoreListAdapter coreListAdapter;

    public static CoreFragemt newInstance() {
        return new CoreFragemt();
    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragemt_code, null, false);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        binding.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.jump(getActivity(), AddCodeActivity.class, false);
            }
        });
        binding.tvAddcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.jump(getActivity(), AddCodeActivity.class, false);
            }
        });

    }

    @Override
    public void initData() {

    }

    //获取二维码列表
    public void getqrlist() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.getqrlist, getActivity(), this, this, map, Request.Method.GET));
        showLoadDialog();
    }

    // 启动(关闭)二维码状态
    public void updataqrstatus(String status, String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("status", status);
        mQueue.add(ParamTools.packParam(MyConst.updataqrstatus, getActivity(), this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    @Override
    public void returnData(String data, String url) {
        if (url.contains(MyConst.getqrlist)) {
            CoreBean coreBean = JSON.parseObject(data, CoreBean.class);
            if (coreBean.getData() != null) {
                binding.llList.setVisibility(View.VISIBLE);
                binding.llEmpty.setVisibility(View.GONE);
                if (coreListAdapter == null) {
                    coreListAdapter = new CoreListAdapter(coreBean.getData().getList(), new CoreListAdapter.OnClickListener() {
                        @Override
                        public void onCheckedChanged(boolean isChecked, String id) {
                            updataqrstatus(isChecked ? "1" : "0", id);
                        }
                    });
                    binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                    binding.recyclerview.setAdapter(coreListAdapter);
                } else {
                    coreListAdapter.setNewData(coreBean.getData().getList());
                }
            } else {
                binding.llList.setVisibility(View.GONE);
                binding.llEmpty.setVisibility(View.VISIBLE);
            }
        } else if (url.contains(MyConst.updataqrstatus)) {
            getqrlist();
        }
    }

    @Override
    public void onResume() {
        getqrlist();
        super.onResume();
    }

}
