package com.ming.smartpay.bean;

import java.util.List;

public class UserInfoBean {


    /**
     * code : 200
     * msg : 获取成功
     * data : {"name":"小怪兽","handlingFeeBalance":"10000.00","list":[{"paymentname":"支付宝","rate":"0.90"},{"paymentname":"微信","rate":"1.00"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 小怪兽
         * handlingFeeBalance : 10000.00
         * list : [{"paymentname":"支付宝","rate":"0.90"},{"paymentname":"微信","rate":"1.00"}]
         */

        private String name;
        private String handlingFeeBalance;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHandlingFeeBalance() {
            return handlingFeeBalance;
        }

        public void setHandlingFeeBalance(String handlingFeeBalance) {
            this.handlingFeeBalance = handlingFeeBalance;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * paymentname : 支付宝
             * rate : 0.90
             */

            private String paymentname;
            private String rate;

            public String getPaymentname() {
                return paymentname;
            }

            public void setPaymentname(String paymentname) {
                this.paymentname = paymentname;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }
        }
    }
}
