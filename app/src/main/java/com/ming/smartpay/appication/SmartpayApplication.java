package com.ming.smartpay.appication;


import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.ming.smartpay.base.utils.AppUtils;
import com.ming.smartpay.base.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * @author luoming
 *created at 2019/8/6 10:57 AM
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