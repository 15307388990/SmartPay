package com.ming.smartpay.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.ming.smartpay.view.modelview.TimerOnback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyCountTimer extends CountDownTimer {
    private TextView btn;
    private int timingColor;// 未计时的文字颜色，计时期间的文字颜色
    private Context mContext;
    private TimerOnback timerOnback;

    /**
     * 参数 millisInFuture 倒计时总时间（如60S，120s等） 参数 countDownInterval 渐变时间（每次倒计1s）
     * <p>
     * 参数 btn 点击的按钮(因为Button是TextView子类，为了通用我的参数设置为TextView）
     * <p>
     * 参数 endStrRid 倒计时结束后，按钮对应显示的文字
     */
    public MyCountTimer(Context context, TextView btn, int timingColor, long timer, TimerOnback timerOnback) {
        super(timer, 1000);
        this.btn = btn;
        this.mContext = context;
        this.timingColor = timingColor;
        this.timerOnback = timerOnback;
    }


    // 计时完毕时触发
    @Override
    public void onFinish() {
        btn.setEnabled(true);
        timerOnback.onFinish();
    }

    // 计时过程显示
    @Override
    public void onTick(long millisUntilFinished) {
        if (timingColor > 0) {
            btn.setTextColor(mContext.getResources().getColor(timingColor));
        }
        btn.setEnabled(false);
        btn.setText(Tools.getDateMs((int) (millisUntilFinished / 1000)));
        timerOnback.onTick(millisUntilFinished);
    }
}
