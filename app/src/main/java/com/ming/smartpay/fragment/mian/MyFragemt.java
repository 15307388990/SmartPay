package com.ming.smartpay.fragment.mian;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ming.smartpay.R;
import com.ming.smartpay.base.fragment.MvpFragment;
import com.ming.smartpay.dialogfrment.VersionUpgradeDialog;
import com.ming.smartpay.presenter.HomePresenter;
import com.ming.smartpay.view.modelview.HomeView;

import butterknife.BindView;

public class MyFragemt extends MvpFragment<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.ll_check_update)
    LinearLayout llCheckUpdate;

    public static MyFragemt newInstance() {
        return new MyFragemt();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragemt_my);
        onClick();

    }

    private void onClick() {
        llCheckUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VersionUpgradeDialog.newInstance().show(getActivity());
            }
        });
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
