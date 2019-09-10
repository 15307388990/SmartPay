package com.ming.smartpay.dialogfrment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;

import com.ming.smartpay.R;
import com.ming.smartpay.databinding.DialogAddProjectBinding;

/**
 * @author luoming
 * created at 2019-09-09 15:46
 */
public class AddProjectDialog extends CenterDialog {
    private DialogAddProjectBinding binding;
    private OnClickListener onClickListener;


    /**
     * 定义结果回调接口
     */
    public interface OnClickListener {
        void successful(String name);

    }

    public AddProjectDialog setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_add_project;
    }

    public static AddProjectDialog newInstance() {
        AddProjectDialog dialog = new AddProjectDialog();
        Bundle bundle = new Bundle();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void initView(ViewDataBinding dataBinding) {
        binding = (DialogAddProjectBinding) dataBinding;
        binding.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.successful(binding.etEdit.getText().toString().trim());
                dismiss();
            }
        });

    }
}
