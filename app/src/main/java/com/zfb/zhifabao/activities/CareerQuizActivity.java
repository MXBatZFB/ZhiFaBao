package com.zfb.zhifabao.activities;

import android.content.Context;
import android.content.Intent;

import com.zfb.zhifabao.R;
import com.zfb.zhifabao.common.app.Activity;

public class CareerQuizActivity extends Activity {

    public static void show(Context context) {
        context.startActivity(new Intent(context, CareerQuizActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_career_quiz;
    }

}
