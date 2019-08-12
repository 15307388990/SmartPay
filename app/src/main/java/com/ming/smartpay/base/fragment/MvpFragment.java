package com.ming.smartpay.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ming.smartpay.base.presenter.MvpPresenter;
import com.ming.smartpay.base.view.MvpView;

/**
 * MVP模式Fragment基类
 *
 * @author linjie
 * @date 2017/10/15
 */
public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends BaseFragment
        implements MvpView {
    protected P mPresenter;

    protected abstract P createPresenter();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);


    }



    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }

        super.onDestroyView();
    }


}