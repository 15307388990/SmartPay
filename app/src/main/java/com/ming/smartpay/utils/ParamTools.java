package com.ming.smartpay.utils;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.ming.smartpay.config.MyConst;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuJianHua 参数配置工具类
 */
public class ParamTools {
    /**
     * 生成参数
     */
    public static StringRequest packParam(String url, final Context context,
                                          Listener<String> listener, ErrorListener errorListener,
                                          final Map<String, String> map,int method) {
        final SavePreferencesData savePreferencesData = new SavePreferencesData(context);
        if (!url.contains("http")) {
            url = MyConst.BASE_URL + url;
        }

        StringRequest stringRequest = new StringRequest(method, url,
                listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
//                map.put("os", "android");
                map.put("token", savePreferencesData.getStringData("token"));
//                map.put("version", Tools.packageCode(context) + "");
//                if (!TextUtils.isEmpty(Tools.getUUID())) {
//                    map.put("device", Tools.getUUID());//设备唯一ID
//                }
                return map;
            }
        };
        stringRequest.setCharset("UTF-8");
        stringRequest
                .setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 1, 1.0f));
        return stringRequest;
    }


}
