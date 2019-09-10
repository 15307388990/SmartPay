package com.ming.smartpay.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * @author luoming
 * created at 2019-09-09 14:26
 * 图片表
 */
public class ImgTab extends BmobObject {
    private int id;
    private String imgUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgurl() {
        return imgUrl;
    }

    public void setImgurl(String imgurl) {
        this.imgUrl = imgurl;
    }
}
