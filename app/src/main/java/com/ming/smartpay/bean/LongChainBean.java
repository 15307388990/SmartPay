package com.ming.smartpay.bean;

public class LongChainBean {


    /**
     * type : updateline
     * channel_name : 000000000b5500000001
     * qrname : 二维码名称
     * qrid : 二位码ID
     * qrline : 当前位置
     * estimatedtime : 预计时间
     */

    private String type;
    private String channel_name;
    private String qrname;
    private int qrid;
    private int qrline;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * order_no : 订单号
     * amount : 订单金额
     * payment_name : 支付名称
     * created_time : 创建时间
     */

    private String order_no;
    private String amount;
    private String payment_name;
    private String created_time;

    public int getQrid() {
        return qrid;
    }

    public void setQrid(int qrid) {
        this.qrid = qrid;
    }

    public int getQrline() {
        return qrline;
    }

    public void setQrline(int qrline) {
        this.qrline = qrline;
    }

    private String estimatedtime;
    private boolean is_promptbox;
    private String promptingtime;
    private int countdown;

    public boolean isIs_promptbox() {
        return is_promptbox;
    }

    public void setIs_promptbox(boolean is_promptbox) {
        this.is_promptbox = is_promptbox;
    }

    public String getPromptingtime() {
        return promptingtime;
    }

    public void setPromptingtime(String promptingtime) {
        this.promptingtime = promptingtime;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    /**
     * client_id : 000000000b5500000001
     */

    private String client_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getQrname() {
        return qrname;
    }

    public void setQrname(String qrname) {
        this.qrname = qrname;
    }


    public String getEstimatedtime() {
        return estimatedtime;
    }

    public void setEstimatedtime(String estimatedtime) {
        this.estimatedtime = estimatedtime;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
