package com.daoguang.dopemaster.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;

/**
 * Created by joker on 2015/2/1.
 */
public abstract class BaseActivity extends FragmentActivity implements
        View.OnClickListener {
    private static final int ACTIVITY_RESUME = 0;
    private static final int ACTIVITY_STOP = 1;
    private static final int ACTIVITY_PAUSE = 2;
    private static final int ACTIVITY_DESTROY = 3;

    public int activityState;

    private FragmentManager fragmentManager ;

    // 是否允许全屏
    private boolean mAllowFullScreen = true;

//    初始化Activity控件
    public abstract void initWidget();

    public abstract void widgetClick(View v);

    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    public FragmentManager getFragmentMansger() {
        fragmentManager = getSupportFragmentManager();
        return fragmentManager;
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    /***************************************************************************
     *
     * 打印Activity生命周期
     *
     ***************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppLog.debug(this.getClass() + "---------onCreat ");
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }
        AppManager.getAppManager().addActivity(this);
        initWidget();
    }

    @Override
    protected void onStart() {
        super.onStart();
        AppLog.state(this.getClass().getSimpleName(), "---------onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityState = ACTIVITY_RESUME;
        AppLog.state(this.getClass().getSimpleName(), "---------onResume ");
    }

    @Override
    protected void onStop() {
        super.onResume();
        activityState = ACTIVITY_STOP;
        AppLog.state(this.getClass().getSimpleName(), "---------onStop ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityState = ACTIVITY_PAUSE;
        AppLog.state(this.getClass().getSimpleName(), "---------onPause ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        AppLog.state(this.getClass().getSimpleName(), "---------onRestart ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityState = ACTIVITY_DESTROY;
        AppLog.state(this.getClass().getSimpleName(), "---------onDestroy ");
        AppManager.getAppManager().finishActivity(this);
    }

    // 常用方法  :适配
    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale * dipValue + 0.5f);
    }
}
