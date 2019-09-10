package com.ming.smartpay.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ming.smartpay.R;
import com.ming.smartpay.bean.ImgTab;
import com.ming.smartpay.bean.ProjectTab;
import com.ming.smartpay.view.WiperSwitch;

import java.util.List;

/**
 * @author luoming
 * created at 2019/8/27 6:09 PM
 */

public class HomeAdapter extends BaseQuickAdapter<ProjectTab, BaseViewHolder> {
    private OnChangedListener listener;

    public HomeAdapter(List<ProjectTab> data, OnChangedListener listener) {
        super(R.layout.home_item, data);
        this.listener = listener;
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, ProjectTab projectTab) {
        baseViewHolder
                .setText(R.id.tv_name, projectTab.getPrijectName()).setText(R.id.tv_time, projectTab.getCreatedAt());
        WiperSwitch wiperSwitch = baseViewHolder.getView(R.id.wiperswitch);
        wiperSwitch.setChecked(projectTab.isEnable());
        wiperSwitch.setOnChangedListener(new WiperSwitch.OnChangedListener() {
            @Override
            public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
                listener.OnChanged(wiperSwitch, checkState, baseViewHolder.getLayoutPosition());
            }
        });

    }


    /**
     * 回调接口
     */
    public interface OnChangedListener {
        void OnChanged(WiperSwitch wiperSwitch, boolean checkState, int position);
    }

}
