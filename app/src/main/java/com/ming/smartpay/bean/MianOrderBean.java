package com.ming.smartpay.bean;

import java.util.List;

public class MianOrderBean extends BaseBean {


    /**
     * data : {"today_total":0,"today_amount":"0.00","today_income":"0.00","list":[{"id":3,"paymentname":"支付宝","amount":"100.00","withdraws_status":0,"status":0,"order_no":"100001","created_time":"2020-02-29 19:30:47"},{"id":2,"paymentname":"支付宝","amount":"100.00","withdraws_status":0,"status":0,"order_no":"100001","created_time":"2020-02-29 19:30:47"},{"id":1,"paymentname":"支付宝","amount":"0.00","withdraws_status":3,"status":3,"order_no":"100000","created_time":"2020-02-29 14:47:47"}]}
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
         * today_total : 0
         * today_amount : 0.00
         * today_income : 0.00
         * list : [{"id":3,"paymentname":"支付宝","amount":"100.00","withdraws_status":0,"status":0,"order_no":"100001","created_time":"2020-02-29 19:30:47"},{"id":2,"paymentname":"支付宝","amount":"100.00","withdraws_status":0,"status":0,"order_no":"100001","created_time":"2020-02-29 19:30:47"},{"id":1,"paymentname":"支付宝","amount":"0.00","withdraws_status":3,"status":3,"order_no":"100000","created_time":"2020-02-29 14:47:47"}]
         */

        private int today_total;
        private String today_amount;
        private String today_income;
        private List<OrderBean> list;

        public int getToday_total() {
            return today_total;
        }

        public void setToday_total(int today_total) {
            this.today_total = today_total;
        }

        public String getToday_amount() {
            return today_amount;
        }

        public void setToday_amount(String today_amount) {
            this.today_amount = today_amount;
        }

        public String getToday_income() {
            return today_income;
        }

        public void setToday_income(String today_income) {
            this.today_income = today_income;
        }

        public List<OrderBean> getList() {
            return list;
        }

        public void setList(List<OrderBean> list) {
            this.list = list;
        }

    }
}
