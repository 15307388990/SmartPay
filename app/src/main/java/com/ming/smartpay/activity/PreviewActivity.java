package com.ming.smartpay.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ming.smartpay.R;
import com.ming.smartpay.base.activity.MvpActivity;
import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;
import com.ming.smartpay.presenter.MainPresenter;
import com.ming.smartpay.view.modelview.MianView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author luoming
 * created at 2019/8/19 9:22 AM
 * 图片预览
 */
public class PreviewActivity extends MvpActivity<MianView, MainPresenter> {


    @BindView(R.id.iv_img)
    ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prcture);
        StatusBarUtil.setColor(this, AppUtils.getColor(R.color.white));
        StatusBarUtil.setDarkMode(this);
        onClick();


    }

    private void onClick() {
        String imgUrl = getIntent().getStringExtra("imgurl");
        Glide.with(getApplicationContext()).load(imgUrl).into(ivImg);

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
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
