package com.ming.smartpay.view.modelview;

import com.ming.smartpay.base.view.MvpView;
import com.ming.smartpay.bean.StationTab;

import java.util.List;

public interface StationView extends MvpView {
    void showDate(List<StationTab> stationTabs);
}
