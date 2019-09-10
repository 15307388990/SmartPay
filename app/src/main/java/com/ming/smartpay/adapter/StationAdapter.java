package com.ming.smartpay.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ming.smartpay.R;
import com.ming.smartpay.bean.ProjectTab;
import com.ming.smartpay.bean.StationTab;

import java.util.List;

/**
 * @author luoming
 * created at 2019/8/27 6:09 PM
 */

public class StationAdapter extends BaseQuickAdapter<StationTab, BaseViewHolder> {


    public StationAdapter( @Nullable List<StationTab> data) {
        super(R.layout.station_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, StationTab stationTab) {
        baseViewHolder
                .setText(R.id.tv_name, stationTab.getStationName())
                .setText(R.id.tv_time, stationTab.getCreatedAt())
                .setText(R.id.tv_device, stationTab.getDeviceId());
        baseViewHolder.addOnClickListener(R.id.tv_id);

    }
}
