package com.ming.smartpay.bean;

import java.util.List;

public class PaymentsListBean extends BaseBean {


    /**
     * data : {"list":[{"id":1,"paymentname":"支付宝"},{"id":2,"paymentname":"微信"}]}
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
             * id : 1
             * paymentname : 支付宝
             */

            private int id;
            private String paymentname;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPaymentname() {
                return paymentname;
            }

            public void setPaymentname(String paymentname) {
                this.paymentname = paymentname;
            }
        }
    }
}
