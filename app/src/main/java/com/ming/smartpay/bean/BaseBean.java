package com.ming.smartpay.bean;

public class BaseBean {

    /**
     * code : 200
     * msg : 登录成功
     * data : {"uid":1,"token":"98608707a5d51ca34e38a736291746a957697f28"}
     */

    private int code;
    private String msg;

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

}
