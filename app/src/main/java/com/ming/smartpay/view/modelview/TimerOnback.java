package com.ming.smartpay.view.modelview;

public interface TimerOnback {
    void onFinish();

    void onTick(long millisUntilFinished);
}
