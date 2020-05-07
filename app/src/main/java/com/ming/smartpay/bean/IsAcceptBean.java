package com.ming.smartpay.bean;

public class IsAcceptBean extends BaseBean {


    /**
     * data : {"is_accept":true}
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
         * is_accept : true
         */

        private boolean is_accept;

        public boolean isIs_accept() {
            return is_accept;
        }

        public void setIs_accept(boolean is_accept) {
            this.is_accept = is_accept;
        }
    }
}
