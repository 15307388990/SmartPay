package com.ming.smartpay.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ming.smartpay.base.cache.ACache;
import com.ming.smartpay.base.manager.LoadManager;
import com.ming.smartpay.base.utils.TextUtil;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.utils.SavePreferencesData;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;

public abstract class BaseV4Fragment extends Fragment {

    protected LayoutInflater inflater;
    protected Context mContext;
    protected ViewGroup container;
    protected String TAG = getClass().getName();
    public RequestQueue mQueue; // 请求列队
    public SavePreferencesData mSavePreferencesData;

    private LoadManager mLoadManager;

    public static ACache mCache;

    //适配矢量图
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    protected void le(String msg) {
        Logger.t(TAG).e(msg);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mCache = ACache.get(mContext);
        mSavePreferencesData = new SavePreferencesData(mContext);
        mQueue = Volley.newRequestQueue(mContext);
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
        initLoadManager();
    }

    public void showTips(String msg) {
        if (!TextUtil.isEmpty(msg))
            ToastShow.s(msg);
    }

    public void showTips(@StringRes int msg) {
        ToastShow.s(msg);
    }

    private void initLoadManager() {
        mLoadManager = new LoadManager(getActivity());
    }

    public void showLoadDialog() {
        if (mLoadManager != null)
            mLoadManager.showLoadDialog();
    }


    public void dismissDialog() {
        if (mLoadManager != null)
            mLoadManager.dismissDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        onCreateView(savedInstanceState);
        if (getLayoutRes() == null)
            return super.onCreateView(inflater, container, savedInstanceState);
        initData();
        return getLayoutRes();
    }

    protected void onCreateView(Bundle savedInstanceState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvent();
    }
    protected boolean useEventBus() {
        return false;
    }


    @Override
    public void onDestroyView() {
//		NetRequest.getRequestQueue().cancelAll(this);//activity销毁时取消请求
        super.onDestroyView();
        container = null;
        inflater = null;
        if (mLoadManager != null) {
            mLoadManager.onDestroy();
        }
    }

    public Context getApplicationContext() {
        return mContext.getApplicationContext();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract View getLayoutRes();

    public abstract void initEvent();

    public abstract void initData();


}
