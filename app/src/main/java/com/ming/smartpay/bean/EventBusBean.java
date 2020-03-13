package com.ming.smartpay.bean;

public class EventBusBean {

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    private String eventName;
    private LongChainBean data;

    public LongChainBean getData() {
        return data;
    }

    public void setData(LongChainBean data) {
        this.data = data;
    }

    public EventBusBean(String eventName) {
        this.eventName = eventName;
    }

    public EventBusBean(String eventName, LongChainBean longChainBean) {
        this.eventName = eventName;
        this.data = longChainBean;
    }
}
