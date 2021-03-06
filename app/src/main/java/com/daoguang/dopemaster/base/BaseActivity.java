package com.daoguang.dopemaster.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;

import com.daoguang.dopemaster.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by joker on 2015/2/1.
 */
public abstract class BaseActivity extends SlidingFragmentActivity implements
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

    public FragmentManager getTheFragmentManager() {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppLog.debug(this.getClass() + "---------onCreat ");
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
        }
        AppManager.getAppManager().addActivity(this);
        // 设置左侧滑动菜单
        setBehindContentView(R.layout.home_left_frme);
        // 实例化滑动菜单对象
        SlidingMenu sm = getSlidingMenu();
        // 设置触摸屏幕的模式,这里设置为禁止滑动
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
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
        super.onStop();
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


}
