package com.ming.smartpay.Fragment.mian;

import android.os.Bundle;

import com.ming.smartpay.R;
import com.ming.smartpay.base.fragment.MvpFragment;
import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;
import com.ming.smartpay.presenter.HomePresenter;
import com.ming.smartpay.view.HomeView;

public class RankingFragemt extends MvpFragment<HomeView, HomePresenter> implements HomeView {

    public static RankingFragemt newInstance() {
        return new RankingFragemt();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragemt_there);
        StatusBarUtil.setColor(getActivity(), AppUtils.getColor(R.color.white));
        StatusBarUtil.setDarkMode(getActivity());
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }
}
