package com.ming.smartpay.Fragment.mian;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ming.smartpay.R;
import com.ming.smartpay.base.fragment.MvpFragment;
import com.ming.smartpay.base.utils.FileUtil;
import com.ming.smartpay.presenter.HomePresenter;
import com.ming.smartpay.view.HomeView;

import java.io.File;

import butterknife.BindView;

public class HomeFragemt extends MvpFragment<HomeView, HomePresenter> implements HomeView {

    @BindView(R.id.button)
    Button button;
    private static String SRC = "system/extMedia/FujiApp/content/movie/";
    // 目的路径 当然也可以通过获取调用API的方式来获取 这里我就直接定义了
    private static String DST = FileUtil.getSDCardPath() + "/FujiApp/content/movie/";
    private static String FLAG = "extMedia";

    public static HomeFragemt newInstance() {
        return new HomeFragemt();
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
        setContentView(R.layout.fragemt_home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSharedPreferences(FLAG, Context.MODE_PRIVATE);
                new Thread() {
                    @Override
                    public void run() {
                        copyMedia(SRC, DST);
                    }
                }.start();
            }
        });
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

    private boolean copyMedia(String src, String dst) {
        if (!FileUtil.copyFile(new File(src), new File(dst))) {
            return false;
        }
        return true;
    }
}
