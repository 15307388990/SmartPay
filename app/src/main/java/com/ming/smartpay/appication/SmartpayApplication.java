package com.ming.smartpay.appication;


import com.ming.smartpay.base.utils.AppUtils;


/**
 * @author luoming
 * created at 2019/8/6 10:57 AM
 */
public class SmartpayApplication extends BaseApplication {
    private static SmartpayApplication mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //颜色
        AppUtils.init(this);

    }

    public static SmartpayApplication getInstance() {
        return mInstance;
    }

}