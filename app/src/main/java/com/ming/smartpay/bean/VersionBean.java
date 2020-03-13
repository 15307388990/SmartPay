package com.ming.smartpay.bean;

import java.io.Serializable;

public class VersionBean extends BaseBean implements Serializable {


    /**
     * data : {"android_url":"http://www.baidu.com/abc.apk"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * android_url : http://www.baidu.com/abc.apk
         */

        private String android_url;

        public String getAndroid_url() {
            return android_url;
        }

        public void setAndroid_url(String android_url) {
            this.android_url = android_url;
        }
    }
}
