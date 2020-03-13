package com.ming.smartpay.dialogfrment;

import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.ming.smartpay.R;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.databinding.DialogCoreImgBinding;
import com.ming.smartpay.databinding.DialogOrderBinding;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * @author luoming
 * created at 2019-09-09 15:46
 * 二维码弹框
 */
public class CoreImgeDialog extends CenterDialog {
    private DialogCoreImgBinding binding;


    @Override
    public int getLayoutId() {
        return R.layout.dialog_core_img;
    }

    public static CoreImgeDialog newInstance(String qrurl) {
        //"order_no":"订单号","amount":"订单金额","payment_name":"支付名称","created_time":"创建时间"}
        CoreImgeDialog dialog = new CoreImgeDialog();
        Bundle bundle = new Bundle();
        bundle.putString("qrurl", qrurl);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void initView(ViewDataBinding dataBinding) {
        binding = (DialogCoreImgBinding) dataBinding;
        final Bundle bundle = getArguments();
        binding.tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Bitmap bitmap = CodeUtils.createImage(bundle.getString("qrurl"), 200, 200, null);
        binding.ivImg.setImageBitmap(bitmap);

    }
}
