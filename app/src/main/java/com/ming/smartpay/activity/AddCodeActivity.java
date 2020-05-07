package com.ming.smartpay.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.toolbox.HurlStack;
import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.BaseActivity;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.LoginBean;
import com.ming.smartpay.bean.PaymentsListBean;
import com.ming.smartpay.config.MyConst;
import com.ming.smartpay.databinding.ActivityAddCodeBinding;
import com.ming.smartpay.databinding.ActivityLoginBinding;
import com.ming.smartpay.dialogfrment.PaymentTypeDialog;
import com.ming.smartpay.utils.ImageUtil;
import com.ming.smartpay.utils.MD5Util;
import com.ming.smartpay.utils.ParamTools;
import com.ming.smartpay.utils.Tools;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * @author luoming
 * created at 2020-03-08 09:38
 * 添加二维码
 */
public class AddCodeActivity extends BaseActivity {

    ActivityAddCodeBinding binding;
    private String qrurl;
    public String mScrolled;
    private String payment_id;
    private PaymentsListBean bean;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View getLayoutRes() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_code);
        return binding.getRoot();
    }

    @Override
    public void initEvent() {
        initTitle();
        title.setText("添加二维码");
        binding.ivImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPermission();
            }
        });
        binding.tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentTypeDialog.newInstance(list).setOnClickListener(new PaymentTypeDialog.OnClickListener() {
                    @Override
                    public void Scrolled(String scrolled) {
                        mScrolled = scrolled;
                        binding.tvType.setText(mScrolled);
                        if (mScrolled.equals("支付宝")) {
                            binding.tvName.setText("姓名");
                            binding.llPid.setVisibility(View.VISIBLE);
                        } else {
                            binding.tvName.setText("昵称");
                            binding.llPid.setVisibility(View.GONE);
                        }
                        for (PaymentsListBean.DataBean.ListBean listBean : bean.getData().getList()) {
                            if (listBean.getPaymentname().equals(mScrolled))
                                payment_id = listBean.getId() + "";
                        }

                    }
                }).show(AddCodeActivity.this);
            }
        });
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.etNickName.getText().toString().trim())) {
                    ToastShow.s("请填写昵称");
                    return;
                }
                if (TextUtils.isEmpty(qrurl)) {
                    ToastShow.s("请上传正确的二维码");
                    return;
                }
                if (TextUtils.isEmpty(payment_id)) {
                    ToastShow.s("请选择二维码类型");
                    return;
                }
                qrupload();

            }
        });
    }

    @Override
    public void initData() {
        getpaymentslist();

    }

    @Override
    protected void returnData(String data, String url) {
        if (url.contains(MyConst.getpaymentslist)) {
            bean = JSON.parseObject(data, PaymentsListBean.class);
            list = new ArrayList<>();
            for (PaymentsListBean.DataBean.ListBean listBean : bean.getData().getList()) {
                list.add(listBean.getPaymentname());
            }
        } else if (url.contains(MyConst.qrupload)) {
            ToastShow.s("上传成功等待审核");
            finish();
        }

    }

    public void qrupload() {
        if (TextUtils.isEmpty(payment_id)) {
            ToastShow.s("二维码类型没选择");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("payment_id", payment_id);
        map.put("qrurl", qrurl);
        map.put("alipay_name", binding.etNickName.getText().toString().trim());
        map.put("alipay_account", binding.etAccount.getText().toString().trim());
        map.put("alipay_pid", binding.etPid.getText().toString().trim());
        mQueue.add(ParamTools.packParam(MyConst.qrupload, AddCodeActivity.this, this, this, map, Request.Method.POST));
        showLoadDialog();
    }

    /* 获取通道列表 */
    public void getpaymentslist() {
        Map<String, String> map = new HashMap<>();
        mQueue.add(ParamTools.packParam(MyConst.getpaymentslist, AddCodeActivity.this, this, this, map, Request.Method.GET));
        showLoadDialog();
    }

    /**
     * 获取相册权限
     */
    private void initPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.READ_EXTERNAL_STORAGE).
                onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Intent intent = new Intent(AddCodeActivity.this,
                                MultiImageSelectorActivity.class);
                        intent.putExtra("isUploadIcon", true);
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA,
                                false);
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT,
                                1);
                        startActivityForResult(intent, 1);
                    }
                }).
                onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        ToastShow.s("需要获取相册权限");
                    }
                }).
                start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                ArrayList<String> resultList = data.getExtras().getStringArrayList("select_result");

                parseInfoFromBitmap(resultList.get(0));


            }
        }
    }

    public void parseInfoFromBitmap(String path) {
        try {
            CodeUtils.analyzeBitmap(path, new CodeUtils.AnalyzeCallback() {
                @Override
                public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                    qrurl = result;
                    binding.ivImg.setImageBitmap(mBitmap);
                }

                @Override
                public void onAnalyzeFailed() {
                    ToastShow.s("无法解析图片二维码，请重新选择");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
