package com.ming.smartpay.dialogfrment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.ming.smartpay.R;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.databinding.DialogOrderBinding;
import com.ming.smartpay.databinding.DialogPaymentTypeBinding;
import com.ming.smartpay.provider.DefaultItemViewProvider;
import com.ming.smartpay.view.ScrollPickerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoming
 * created at 2019-09-09 15:46
 * 支付类型选择
 */
public class PaymentTypeDialog extends CenterDialog {
    private DialogPaymentTypeBinding binding;
    private OnClickListener onClickListener;
    private String scrolled;


    /**
     * 定义结果回调接口
     */
    public interface OnClickListener {
        void Scrolled(String scrolled);


    }

    public PaymentTypeDialog setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_payment_type;
    }

    public static PaymentTypeDialog newInstance(ArrayList<String> list) {
        PaymentTypeDialog dialog = new PaymentTypeDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("list", list);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void initView(ViewDataBinding dataBinding) {
        binding = (DialogPaymentTypeBinding) dataBinding;
        binding.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.Scrolled(scrolled);
                dismiss();
            }
        });
        binding.tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ScrollPickerAdapter.ScrollPickerAdapterBuilder<String> builder =
                new ScrollPickerAdapter.ScrollPickerAdapterBuilder<String>(getActivity())
                        .setDataList(getArguments().getStringArrayList("list"))
                        .selectedItemOffset(1)
                        .visibleItemNumber(2)
                        .setDivideLineColor("#D8D8D8")
                        .setItemViewProvider(new DefaultItemViewProvider())
                        .setOnScrolledListener(new ScrollPickerAdapter.OnScrollListener() {
                            @Override
                            public void onScrolled(View currentItemView) {
                                scrolled = currentItemView.getTag().toString();
                            }
                        });
        ScrollPickerAdapter mScrollPickerAdapter = builder.build();
        binding.scroll.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.scroll.setAdapter(mScrollPickerAdapter);

    }

    @Override
    public boolean isBottom() {
        return true;
    }
}
