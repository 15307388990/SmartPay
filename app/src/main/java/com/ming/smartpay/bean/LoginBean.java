package com.ming.smartpay.bean;

public class LoginBean extends BaseBean {

    /**
     * data : {"uid":1,"token":"98608707a5d51ca34e38a736291746a957697f28"}
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
         * uid : 1
         * token : 98608707a5d51ca34e38a736291746a957697f28
         */

        private int uid;
        private String token;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
