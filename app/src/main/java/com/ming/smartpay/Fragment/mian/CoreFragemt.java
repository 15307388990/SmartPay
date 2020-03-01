package com.ming.smartpay.fragment.mian;

import android.os.Bundle;

import com.ming.smartpay.R;
import com.ming.smartpay.base.fragment.MvpFragment;
import com.ming.smartpay.presenter.HomePresenter;
import com.ming.smartpay.view.modelview.HomeView;

public class CoreFragemt extends MvpFragment<HomeView, HomePresenter> implements HomeView {

    public static CoreFragemt newInstance() {
        return new CoreFragemt();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragemt_code);

    }

    @Override
    public void onResume() {
        super.onResume();
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
