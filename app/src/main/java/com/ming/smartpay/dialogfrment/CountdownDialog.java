package com.ming.smartpay.dialogfrment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;

import com.ming.smartpay.R;
import com.ming.smartpay.bean.LineUpBean;
import com.ming.smartpay.databinding.DialogAddProjectBinding;
import com.ming.smartpay.utils.MyCountTimer;
import com.ming.smartpay.view.modelview.TimerOnback;

import java.math.BigDecimal;

/**
 * @author luoming
 * created at 2019-09-09 15:46
 */
public class CountdownDialog extends CenterDialog {
    private DialogAddProjectBinding binding;
    private OnClickListener onClickListener;
    private MyCountTimer myCountTimer;
    public static String LENGTH = "length";
    Dialog dialog;

    /**
     * 定义结果回调接口
     */
    public interface OnClickListener {
        void successful();

        void onFinish();

        void cancel();

    }

    public CountdownDialog setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.DialogStyle);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setDimAmount(0.65f);
        }
        ViewDataBinding binding = getLayoutBind();
        View view = binding.getRoot();
        dialog.setContentView(view);
        initView(binding);
        initWindowParams(dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return false;
            }
        });
        return dialog;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_add_project;
    }

    public static CountdownDialog newInstance(long timer) {
        CountdownDialog dialog = new CountdownDialog();
        Bundle bundle = new Bundle();
        bundle.putLong(LENGTH, timer);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void initView(ViewDataBinding dataBinding) {
        binding = (DialogAddProjectBinding) dataBinding;
        myCountTimer = new MyCountTimer(getActivity(), binding.tvTimer, R.color.SM_6C42F5, getArguments().getLong(LENGTH), new TimerOnback() {
            @Override
            public void onFinish() {
                myCountTimer.cancel();
                dismiss();
                onClickListener.onFinish();
            }

            @Override
            public void onTick(long millisUntilFinished) {
                int s = (int) (millisUntilFinished / 1000);
                int e = (int) (getArguments().getLong(LENGTH) / 1000);
                float fx = 0;
                try {
                    fx = new BigDecimal((float) s / e).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                binding.circlePercentProgress.setPercentage(fx * 100);
            }
        });
        myCountTimer.start();
        binding.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (myCountTimer != null) {
                    myCountTimer.cancel();
                }
                onClickListener.successful();
            }
        });
        binding.tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (myCountTimer != null) {
                    myCountTimer.cancel();
                }
                onClickListener.cancel();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myCountTimer != null) {
            myCountTimer.cancel();
        }
    }
}
