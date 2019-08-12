package com.ming.smartpay.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ming.smartpay.R;
import com.ming.smartpay.base.presenter.MvpPresenter;
import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;
import com.ming.smartpay.base.view.MvpView;

import org.greenrobot.eventbus.EventBus;


/**
 * @author luoming
 *created at 2019/8/8 11:40 AM
 * MVP模式Activity基类
*/
public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends BaseActivity implements MvpView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
        StatusBarUtil.setColor(this, AppUtils.getColor(R.color.white));
        StatusBarUtil.setDarkMode(this);
        // 初始化presenter并绑定view
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }

        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        super.onDestroy();
    }


    protected boolean useEventBus() {
        return false;
    }


    protected boolean userButterKnife() {
        return true;
    }

    protected abstract P createPresenter();


}
