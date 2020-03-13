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

public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public MyAdapter(List<String> data) {
        super(R.layout.my_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String orderBean) {
        baseViewHolder.setText(R.id.tv_text, orderBean);

    }
}
