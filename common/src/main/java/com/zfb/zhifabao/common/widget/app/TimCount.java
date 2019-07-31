package com.zfb.zhifabao.common.widget.app;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class TimCount extends CountDownTimer {
    private Button view;
    private long millisInFuture;
    private long  countDownInterval;
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.millisInFuture = millisInFuture;
        this.countDownInterval =countDownInterval;
    }

    public void setView( Button view){
       this.view = view;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        view.setClickable(false);
        view.setText(millisUntilFinished/1000+"s后重发");
    }

    @Override
    public void onFinish() {
         view.setText("重新获取");
         view.setClickable(true);
    }
}
