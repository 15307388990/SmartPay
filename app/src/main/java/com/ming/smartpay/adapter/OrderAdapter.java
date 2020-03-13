package com.ming.smartpay.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ming.smartpay.R;
import com.ming.smartpay.bean.OrderBean;

import java.util.List;

/**
 * @author luoming
 * created at 2019/8/27 6:09 PM
 */

public class OrderAdapter extends BaseQuickAdapter<OrderBean, BaseViewHolder> {


    public OrderAdapter(List<OrderBean> data) {
        super(R.layout.order_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OrderBean orderBean) {
        baseViewHolder.setText(R.id.tv_amount, "￥" + orderBean.getAmount())
                .setText(R.id.tv_order_no, "订单号：" + orderBean.getOrder_no())
                .setText(R.id.tv_created_time, "订单时间：" + orderBean.getCreated_time())
                .setText(R.id.tv_paymentname, "支付方式：" + orderBean.getPaymentname());
        //【0：未返款 1：已返款待审核 2.已返款异常 3.已返款成功】
        String status = "";
        switch (orderBean.getWithdraws_status()) {
            case 0:
                status = "未返款";
                break;
            case 1:
                status = "已返款待审核";
                break;
            case 2:
                status = "已返款异常";
                break;
            case 3:
                status = "已返款成功";
                break;
        }
        baseViewHolder.setText(R.id.tv_status, "订单状态：" + status);
        //订单状态 1:支付中 2:支付失败 3:支付成功
        String btntext = "";
        switch (orderBean.getStatus()) {
            case 1:
                btntext = "确认收款";
                baseViewHolder.setImageResource(R.id.iv_img, R.mipmap.home_icon_l);
                break;
            case 2:
                btntext = "支付失败";
                baseViewHolder.setImageResource(R.id.iv_img, R.mipmap.home_icon_d);
                break;
            case 3:
                btntext = "查看订单详情";
                baseViewHolder.setImageResource(R.id.iv_img, R.mipmap.home_icon_k);
                break;
        }
        baseViewHolder.setText(R.id.btn, btntext);

    }
}
