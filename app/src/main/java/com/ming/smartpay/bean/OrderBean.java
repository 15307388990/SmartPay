package com.ming.smartpay.bean;

import java.util.List;

public class OrderBean {


    private int id;
    private String paymentname;
    private String amount;
    private int withdraws_status;
    private int status;
    private String order_no;
    private String created_time;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getWithdraws_status() {
        return withdraws_status;
    }

    public void setWithdraws_status(int withdraws_status) {
        this.withdraws_status = withdraws_status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
