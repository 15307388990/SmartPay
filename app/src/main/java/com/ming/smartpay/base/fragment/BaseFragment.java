package com.ming.smartpay.base.fragment;


import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.ming.smartpay.activity.LoginActivity;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author 罗富贵 Activity 基类
 */

public abstract class BaseFragment extends BaseV4Fragment implements Listener<String>, ErrorListener {



    @Override
    public void onErrorResponse(VolleyError error) {
        dismissDialog();
        ToastShow.s("网络连接异常");
    }

    @Override
    public void onResponse(String response, String url) {
        dismissDialog();
        try {
            JSONObject json = new JSONObject(response);
            int stauts = json.optInt("code");
            String msg = json.optString("msg");
            String data = json.optString("data");
            if (stauts == 200) {
                returnData(response, url);
            } else if (stauts==203){
                ToastShow.s(msg);
                mSavePreferencesData.putStringData("token","");
                Tools.jump(getActivity(), LoginActivity.class,true);
            }else {
                ToastShow.s(msg);
                returnMsg(response, url);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ToastShow.s("数据格式不对");
        }
    }

    public void returnData(String data, String url) {
    }

    protected void returnMsg(String data, String url) {
    }

}
