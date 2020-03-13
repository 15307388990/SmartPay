package com.ming.smartpay.bean;

public class CoreDetailsBean extends BaseBean {


    /**
     * data : {"id":2,"status":0,"examine":1,"qr_username":"微信名称","paymentname":"微信","amount":"0.00","account_url":"http://www.alipay.com"}
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
         * id : 2
         * status : 0
         * examine : 1
         * qr_username : 微信名称
         * paymentname : 微信
         * amount : 0.00
         * account_url : http://www.alipay.com
         */

        private int id;
        private int status;
        private int examine;
        private String qr_username;
        private String paymentname;
        private String amount;
        private String account_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getExamine() {
            return examine;
        }

        public void setExamine(int examine) {
            this.examine = examine;
        }

        public String getQr_username() {
            return qr_username;
        }

        public void setQr_username(String qr_username) {
            this.qr_username = qr_username;
        }

        public String getPaymentname() {
            return paymentname;
        }

        public void setPaymentname(String paymentname) {
            this.paymentname = paymentname;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getAccount_url() {
            return account_url;
        }

        public void setAccount_url(String account_url) {
            this.account_url = account_url;
        }
    }
}
