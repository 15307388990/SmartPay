package com.ming.smartpay.presenter;

import com.ming.smartpay.base.presenter.MvpPresenter;
import com.ming.smartpay.bean.RankingBean;
import com.ming.smartpay.view.modelview.HomeView;
import com.ming.smartpay.view.modelview.RankingView;

import java.util.ArrayList;
import java.util.List;

public class RankingPresenter extends MvpPresenter<RankingView> {
    public void showDate() {
        List<RankingBean> beans = new ArrayList<>();
        RankingBean rankingBean = new RankingBean();
        rankingBean.setId("尖叫鸡");
        rankingBean.setResults("10w");
        rankingBean.setRanking("2");
        rankingBean.setImgurl("https://c-ssl.duitang.com/uploads/blog/201512/19/20151219212115_sxind.thumb.700_0.jpeg");

        RankingBean rankingBean2 = new RankingBean();
        rankingBean2.setId("尖叫鸡2");
        rankingBean2.setResults("120w");
        rankingBean2.setRanking("3");
        rankingBean2.setImgurl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3276179142,1686381254&fm=26&gp=0.jpg");
        beans.add(rankingBean);
        beans.add(rankingBean2);
        getView().setData(beans);
    }

}
