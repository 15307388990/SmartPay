package com.ming.smartpay.fragment.mian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ming.smartpay.R;
import com.ming.smartpay.activity.ChangeLoginPasswordActivity;
import com.ming.smartpay.activity.ChangePayPasswordActivity;
import com.ming.smartpay.base.fragment.MvpFragment;
import com.ming.smartpay.dialogfrment.VersionUpgradeDialog;
import com.ming.smartpay.presenter.HomePresenter;
import com.ming.smartpay.view.modelview.HomeView;

import butterknife.BindView;

public class MyFragemt extends MvpFragment<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.ll_check_update)
    LinearLayout llCheckUpdate;
    @BindView(R.id.ll_change_login)
    LinearLayout llChangeLogin;
    @BindView(R.id.ll_change_pay)
    LinearLayout llChangePay;

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
        llChangeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeLoginPasswordActivity.class);
                startActivity(intent);
            }
        });
        llChangePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(getActivity(), ChangePayPasswordActivity.class);
                 startActivity(intent);
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
