package com.ming.smartpay.bean;

public class StatisticsBean extends BaseBean {


    /**
     * data : {"number_count":0,"success_count":0,"success_rate":0}
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
         * number_count : 0
         * success_count : 0
         * success_rate : 0
         */

        private int number_count;
        private int success_count;
        private int success_rate;

        public int getNumber_count() {
            return number_count;
        }

        public void setNumber_count(int number_count) {
            this.number_count = number_count;
        }

        public int getSuccess_count() {
            return success_count;
        }

        public void setSuccess_count(int success_count) {
            this.success_count = success_count;
        }

        public int getSuccess_rate() {
            return success_rate;
        }

        public void setSuccess_rate(int success_rate) {
            this.success_rate = success_rate;
        }
    }
}
