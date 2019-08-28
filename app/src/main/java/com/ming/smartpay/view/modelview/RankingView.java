package com.ming.smartpay.view.modelview;

import com.ming.smartpay.base.view.MvpView;
import com.ming.smartpay.bean.RankingBean;

import java.util.List;

public interface RankingView extends MvpView {
    void setData(List<RankingBean> rankingBeans);
}
