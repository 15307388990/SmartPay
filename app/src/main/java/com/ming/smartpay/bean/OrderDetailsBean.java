package com.ming.smartpay.bean;

public class OrderDetailsBean extends BaseBean {


    /**
     * data : {"id":43,"branchName":"中国银行","bankCardNo":"23423423","accountName":"张三","return_amount":"297.30","order_mk":"37","amount":"300.00","minersamount":"2.70","created_time":"2020-05-05 13:33:37","phone_id":4,"withdraws_status":0}
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
         * id : 43
         * branchName : 中国银行
         * bankCardNo : 23423423
         * accountName : 张三
         * return_amount : 297.30
         * order_mk : 37
         * amount : 300.00
         * minersamount : 2.70
         * created_time : 2020-05-05 13:33:37
         * phone_id : 4
         * withdraws_status : 0
         */

        private int id;
        private String branchName;
        private String bankCardNo;
        private String accountName;
        private String return_amount;
        private String order_mk;
        private String amount;
        private String minersamount;
        private String created_time;
        private int phone_id;
        private int withdraws_status;
        private String order_no;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getReturn_amount() {
            return return_amount;
        }

        public void setReturn_amount(String return_amount) {
            this.return_amount = return_amount;
        }

        public String getOrder_mk() {
            return order_mk;
        }

        public void setOrder_mk(String order_mk) {
            this.order_mk = order_mk;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getMinersamount() {
            return minersamount;
        }

        public void setMinersamount(String minersamount) {
            this.minersamount = minersamount;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public int getPhone_id() {
            return phone_id;
        }

        public void setPhone_id(int phone_id) {
            this.phone_id = phone_id;
        }

        public int getWithdraws_status() {
            return withdraws_status;
        }

        public void setWithdraws_status(int withdraws_status) {
            this.withdraws_status = withdraws_status;
        }
    }
}
