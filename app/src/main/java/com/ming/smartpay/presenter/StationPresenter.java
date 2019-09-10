package com.ming.smartpay.presenter;

import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ming.smartpay.base.presenter.MvpPresenter;
import com.ming.smartpay.base.utils.TextUtil;
import com.ming.smartpay.base.widget.ToastShow;
import com.ming.smartpay.bean.StationTab;
import com.ming.smartpay.utils.Tools;
import com.ming.smartpay.view.modelview.StationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class StationPresenter extends MvpPresenter<StationView> {
    /**
     * 获取工位列表
     */
    public void showDate(String projectId) {
        BmobQuery<StationTab> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("projectId", projectId);
        bmobQuery.findObjects(new FindListener<StationTab>() {
            @Override
            public void done(List<StationTab> list, BmobException e) {
                getView().showDate(list);
            }
        });

    }

    /**
     * 新增工位
     */
    public void save(String name, String device, final String projectId, String str) {
        if (TextUtil.isEmpty(name)) {
            ToastShow.s("工位名称不能为空");
            return;
        }
        if (TextUtil.isEmpty(device)) {
            ToastShow.s("设备序列号不能为空");
            return;
        }
        if (TextUtil.isEmpty(str)) {
            ToastShow.s("图片没有上传");
        }
        String imgUrl = changejson(str);
        BmobQuery<StationTab> bmobQuery = new BmobQuery<>();
        StationTab stationTab = new StationTab();
        stationTab.setDeviceId(device);
        stationTab.setProjectId(projectId);
        stationTab.setStationName(name);
        stationTab.setImgUrl(imgUrl);
        stationTab.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    ToastShow.s("添加数据成功");
                    //更新数据
                    showDate(projectId);
                } else {
                    ToastShow.s("创建数据失败：" + e.getMessage());
                }
            }
        });


    }

    private String changejson(String str) {
        try {
            JSONObject json = new JSONObject(str);
            int stauts = json.optInt("code");
            String msg = json.optString("msg");
            if (stauts == 1) {
                String data = json.optString("data");
                List<String> list = JSON.parseArray(data, String.class);
                return "http://111.230.242.115:8888/static/" + list.get(0);
            } else {
                ToastShow.s(msg);
                return "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ToastShow.s("数据格式不对");
            return "";
        }

    }
}
