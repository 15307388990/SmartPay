package com.ming.smartpay.Fragment.mian;

import android.os.Bundle;

import com.ming.smartpay.R;
import com.ming.smartpay.base.fragment.MvpFragment;
import com.ming.smartpay.presenter.HomePresenter;
import com.ming.smartpay.view.HomeView;

public class TwoFragemt extends MvpFragment<HomeView, HomePresenter> implements HomeView {

    public static TwoFragemt newInstance() {
        return new TwoFragemt();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragemt_two);
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
