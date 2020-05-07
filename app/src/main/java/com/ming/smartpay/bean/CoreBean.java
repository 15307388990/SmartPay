package com.ming.smartpay.bean;

import java.util.List;

public class CoreBean extends BaseBean {


    /**
     * data : {"list":[{"id":5,"qr_name":"二维码昵称123","paymentname":"微信","created_time":"2020-03-01 02:40:54","examine":0,"status":0},{"id":4,"qr_name":"二维码昵称123","paymentname":"支付宝","created_time":"2020-03-01 02:29:00","examine":0,"status":0},{"id":2,"qr_name":"微信名称","paymentname":"微信","created_time":"2020-02-12 02:02:50","examine":1,"status":0}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 5
             * qr_name : 二维码昵称123
             * paymentname : 微信
             * created_time : 2020-03-01 02:40:54
             * examine : 0
             * status : 0
             */

            private int id;
            private String qr_name;
            private String qr_pid;
            private String qr_account;
            private String paymentname;
            private String created_time;
            private int examine;
            private boolean status;

            public String getQr_pid() {
                return qr_pid;
            }

            public void setQr_pid(String qr_pid) {
                this.qr_pid = qr_pid;
            }

            public String getQr_account() {
                return qr_account;
            }

            public void setQr_account(String qr_account) {
                this.qr_account = qr_account;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getQr_name() {
                return qr_name;
            }

            public void setQr_name(String qr_name) {
                this.qr_name = qr_name;
            }

            public String getPaymentname() {
                return paymentname;
            }

            public void setPaymentname(String paymentname) {
                this.paymentname = paymentname;
            }

            public String getCreated_time() {
                return created_time;
            }

            public void setCreated_time(String created_time) {
                this.created_time = created_time;
            }

            public int getExamine() {
                return examine;
            }

            public void setExamine(int examine) {
                this.examine = examine;
            }

            public boolean getStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
            }
        }
    }
}
