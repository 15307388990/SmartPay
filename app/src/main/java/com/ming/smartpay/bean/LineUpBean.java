package com.ming.smartpay.bean;

public class LineUpBean extends BaseBean {


    /**
     * data : {"channel_name":"alipay","qrname":"支付宝名称","qrid":1,"qrline":1,"estimatedtime":"00:01:00","is_promptbox":true,"promptingtime":"00:05:00","countdown":120}
     */

    private LongChainBean data;

    public LongChainBean getData() {
        return data;
    }

    public void setData(LongChainBean data) {
        this.data = data;
    }

}
