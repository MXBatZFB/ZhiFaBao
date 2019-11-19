package com.zfb.zhifabao.common.widget.app;

import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

/**
 * 作者：Maodelong
 * 邮箱：mdl_android@163.com
 */
public class ScrollBindHelper implements SeekBar.OnSeekBarChangeListener, ObservableScrollView.ScrollViewListener, View.OnTouchListener {

    /*动画*/
    public static final long DEFAULT_TIME_OUT = 2000L;
    private static ScrollBindHelper helper;
    private final VerticalSeekBar seekBar;
    private final ObservableScrollView scrollView;
    private final View scrollContent;
    //用户是否正在拖动SeekBar的标志
    private boolean isUserSeeking;
    private VisibleHandler handler = new VisibleHandler(this);

    //设置全局属性
    private ScrollBindHelper(VerticalSeekBar seekBar, ObservableScrollView scrollView) {
        this.seekBar = seekBar;
        this.scrollView = scrollView;
        //获取scrollview的第一个孩子的高度，在这里第一个孩子就是就是TextView
        this.scrollContent = scrollView.getChildAt(0);
    }

    /**
     * 使用静态方法来绑定逻辑，代码可读性更高。
     */
    public static ScrollBindHelper bind(VerticalSeekBar seekBar, ObservableScrollView scrollView) {
        //初始化工具类
        //封装好的获取屏幕工具类  进行初始化
        ViewUtil.init(seekBar.getContext().getApplicationContext());
        helper = new ScrollBindHelper(seekBar, scrollView);
        seekBar.setOnSeekBarChangeListener(helper);
        seekBar.setOnTouchListener(helper);
        seekBar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("delong", "hasFocus>>>>>>>>>" + hasFocus);
            }
        });
        scrollView.setScrollViewListener(helper);
        return helper;
    }

    //获取TextView的高度
    private int getContentRange() {
        return scrollContent.getHeight();
    }

    //获取滚动范围
    private int getScrollRange() {
        //换句话说就是TextView的高度 -  Scrollview的高度
        return scrollContent.getHeight() - scrollView.getHeight();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        showScroll();
        if (!isUserSeeking) {
            handler.reset();
            return;
        }
        scrollView.scrollTo(0, progress * getScrollRange() / 100);
    }

    //SeekBar的拖动事件
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isUserSeeking = true;
        handler.clearAll();
    }

    //SeekBar的停止拖动事件
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isUserSeeking = false;
        handler.reset();
    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
        Log.e("delong", "isUserSeeking>>>>>>>>>" + isUserSeeking);
        //用户触控时不触发
        if (isUserSeeking) {
            return;
        } else if (getContentRange() < ViewUtil.getScreenHeightPx()) {//宽度小于三个屏幕不做处理
            return;
        }

        int range = getScrollRange();
        seekBar.setProgress(range != 0 ? y * 100 / range : 0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                isUserSeeking = false;
                handler.reset();
                break;
            case MotionEvent.ACTION_DOWN:
                isUserSeeking = true;
                handler.clearAll();
                break;
        }
        return false;
    }

    //隐藏SeekBar
    private void hideScroll() {
        seekBar.setVisibility(View.GONE);
    }

    //显示SeekBar
    private void showScroll() {
        seekBar.setVisibility(View.VISIBLE);
    }

    private static class VisibleHandler extends LastMsgHandler {

        private ScrollBindHelper helper;

        public VisibleHandler(ScrollBindHelper helper) {
            this.helper = helper;
        }

        public void reset() {
            sendMsgDelayed(DEFAULT_TIME_OUT);
        }

        @Override
        protected void handleLastMessage(Message msg) {
            helper.hideScroll();
        }
    }
}