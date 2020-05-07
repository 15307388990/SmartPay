package com.ming.smartpay.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ming.smartpay.R;
import com.ming.smartpay.activity.CodeDetailsActivity;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.CoreBean;
import com.ming.smartpay.bean.OrderBean;
import com.ming.smartpay.utils.Tools;

import java.util.List;

/**
 * @author luoming
 * created at 2019/8/27 6:09 PM
 */

public class CoreListAdapter extends BaseQuickAdapter<CoreBean.DataBean.ListBean, BaseViewHolder> {

    /**
     * 定义结果回调接口
     */
    public interface OnClickListener {
        void onCheckedChanged(boolean isChecked, String id);

    }

    private OnClickListener onClickListener;

    public CoreListAdapter(List<CoreBean.DataBean.ListBean> data, OnClickListener onClickListener) {
        super(R.layout.core_item, data);
        this.onClickListener = onClickListener;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final CoreBean.DataBean.ListBean bean) {
        baseViewHolder.setText(R.id.tv_name, "[编号：" + bean.getId() + "]" + bean.getQr_name())
                .setText(R.id.tv_created_time, "订单时间：" + bean.getCreated_time())
                .setText(R.id.tv_paymentname, "支付方式：" +
                        bean.getPaymentname()).setText(R.id.tv_account, "二维码账号：" + bean.getQr_account());
        // 1,状态 【0：审核中】 【1：正常】 【2：审核失败 禁用】 <number>
        String status = "";
        switch (bean.getExamine()) {
            case 0:
                status = "审核中";
                baseViewHolder.setTextColor(R.id.tv_examine, mContext.getResources().getColor(R.color.SM_FD2A5B));
                break;
            case 1:
                status = "正常";
                baseViewHolder.setTextColor(R.id.tv_examine, mContext.getResources().getColor(R.color.SM_626262));
                break;
            case 2:
                status = "禁用";
                baseViewHolder.setTextColor(R.id.tv_examine, mContext.getResources().getColor(R.color.SM_FD2A5B));
                break;
        }
        if (TextUtils.isEmpty(bean.getQr_pid())) {
            baseViewHolder.setGone(R.id.tv_pid, false);
        } else {
            baseViewHolder.setGone(R.id.tv_pid, true);
            baseViewHolder.setText(R.id.tv_pid, "二维码PID：" + bean.getQr_pid());
        }
        baseViewHolder.setText(R.id.tv_examine, status);
        //"status": 0状态【0：未启动】 【1：已启动】
        final Switch swith = baseViewHolder.getView(R.id.swith);
        swith.setChecked(bean.getStatus());
        baseViewHolder.getView(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CodeDetailsActivity.class);
                intent.putExtra("id", bean.getId() + "");
                mContext.startActivity(intent);
            }
        });
        swith.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!buttonView.isPressed())
                    return;
                if (bean.getExamine() == 1) {
                    onClickListener.onCheckedChanged(isChecked, bean.getId() + "");
                } else {
                    swith.setChecked(false);
                    ToastShow.s("订单状态非法，不能开启");
                }
            }
        });

    }
}
