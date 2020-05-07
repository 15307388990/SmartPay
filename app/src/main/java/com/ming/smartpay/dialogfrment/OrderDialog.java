package com.ming.smartpay.dialogfrment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.ming.smartpay.R;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.databinding.DialogBtnBinding;
import com.ming.smartpay.databinding.DialogOrderBinding;

/**
 * @author luoming
 * created at 2019-09-09 15:46
 * 订单提示框
 */
public class OrderDialog extends CenterDialog {
    private DialogOrderBinding binding;
    private OnClickListener onClickListener;


    /**
     * 定义结果回调接口
     */
    public interface OnClickListener {
        void toAccount(String amount, String order_no);


    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.DialogStyle);
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

    public OrderDialog setOnClickListener(OnClickListener onClickListener) {

        this.onClickListener = onClickListener;
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_order;
    }

    public static OrderDialog newInstance(String id, String order_no, String amount, String payment_name, String created_time) {
        //"order_no":"订单号","amount":"订单金额","payment_name":"支付名称","created_time":"创建时间"}
        OrderDialog dialog = new OrderDialog();
        Bundle bundle = new Bundle();
        bundle.putString("order_no", order_no);
        bundle.putString("amount", amount);
        bundle.putString("payment_name", payment_name);
        bundle.putString("created_time", created_time);
        bundle.putString("id", id);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void initView(ViewDataBinding dataBinding) {
        binding = (DialogOrderBinding) dataBinding;
        final Bundle bundle = getArguments();
        binding.tvAmount.setText("￥ " + bundle.get("amount"));
        binding.tvCreatedTime.setText("支付时间： " + bundle.get("created_time"));
        binding.tvOrderNo.setText("订单号： " + bundle.get("order_no"));
        binding.tvPaymentname.setText("支付方式： " + bundle.get("payment_name"));
        binding.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(binding.etAmount.getText().toString().trim())) {
                    onClickListener.toAccount(binding.etAmount.getText().toString().trim(), bundle.getString("order_no"));
                    dismiss();
                } else {
                    ToastShow.s("请输入金额");
                }
            }
        });
        binding.tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
