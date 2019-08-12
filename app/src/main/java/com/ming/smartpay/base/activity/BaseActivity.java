package com.ming.smartpay.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ming.smartpay.R;
import com.ming.smartpay.base.cache.ACache;
import com.ming.smartpay.base.widget.TitleBar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity extends BasePermissionAty {
    public final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private LinearLayout mViewContainer;
    public TitleBar mTitleBar;
    private Unbinder mUnbinder;
    /**
     * 记录所有活动的Activity
     */
    private static final List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
    public static ACache mCache;

    //适配矢量图
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivities.add(this);
        mCache = ACache.get(this);
    }

    public void setContentView(int layoutId, String title) {
        setContentViews(layoutId, title);
    }


    public void setContentView(int layoutId) {
        setContentViews(layoutId, "");
    }

    private void setContentViews(int layoutId, String title) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout parentView = (LinearLayout) inflater.inflate(R.layout.activity_base, null);
        super.setContentView(parentView);
        setContentView(parentView);

        mContext = this;

        mViewContainer = (LinearLayout) parentView.findViewById(R.id.ll_container);
        mTitleBar = (TitleBar) parentView.findViewById(R.id.titleBar);
        if (!TextUtils.isEmpty(title)) {
            mTitleBar.setVisibility(View.VISIBLE);
            mTitleBar.setTitle(title);
        } else {
            mTitleBar.setVisibility(View.GONE);
        }
        View childView = inflater.inflate(layoutId, null);
//        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        int width = dm.widthPixels;         // 屏幕宽度（像素）
//        int height = dm.heightPixels;       // 屏幕高度（像素）
//        mViewContainer.addView(childView,width,height);
        mViewContainer.addView(childView);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        mActivities.remove(this);

    }

    /**
     * 关闭指定类的activity
     *
     * @param clazz
     */
    public static void finishActivity(Class clazz) {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            if (activity != null
                    && activity.getClass().getName().equals(clazz.getName())) {
                activity.finish();
            }
        }
    }


}
