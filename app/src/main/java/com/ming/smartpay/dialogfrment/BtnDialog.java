package com.ming.smartpay.dialogfrment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.ming.smartpay.R;
import com.ming.smartpay.databinding.DialogAddProjectBinding;
import com.ming.smartpay.databinding.DialogBtnBinding;

/**
 * @author luoming
 * created at 2019-09-09 15:46
 * 按钮选择弹框
 */
public class BtnDialog extends CenterDialog {
    private DialogBtnBinding binding;
    private OnClickListener onClickListener;


    /**
     * 定义结果回调接口
     */
    public interface OnClickListener {
        void update();

        void delete();

    }

    public BtnDialog setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_btn;
    }

    public static BtnDialog newInstance() {
        BtnDialog dialog = new BtnDialog();
        Bundle bundle = new Bundle();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void initView(ViewDataBinding dataBinding) {
        binding = (DialogBtnBinding) dataBinding;
        binding.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.delete();
                dismiss();
            }
        });
        binding.tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.update();
                dismiss();
            }
        });

    }
}
