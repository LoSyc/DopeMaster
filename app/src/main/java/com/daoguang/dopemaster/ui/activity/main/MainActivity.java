package com.daoguang.dopemaster.ui.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.BaseActivity;
import com.daoguang.dopemaster.support.callBack.ShowSlidingMenuListener;
import com.daoguang.dopemaster.support.utils.ViewFinder;
import com.daoguang.dopemaster.ui.fragment.home.HomeFragment;
import com.daoguang.dopemaster.ui.fragment.home.LeftHomeFragment;
import com.daoguang.dopemaster.ui.fragment.my.MyFragment;
import com.daoguang.dopemaster.ui.fragment.order.OrderFragment;
import com.daoguang.dopemaster.ui.fragment.show.ShowFragment;
import com.daoguang.dopemaster.ui.view.BottomTabView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements ShowSlidingMenuListener {

    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private ShowFragment showFragment;
    private MyFragment myFragment;

    private FragmentManager fragmentManager;

    private List<BottomTabView> mTabIndicators = new ArrayList<>();

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_main);
        fragmentManager = getTheFragmentManager();
        ViewFinder finder = new ViewFinder(this);

        BottomTabView homeTab = finder.find(R.id.home_tab);
        mTabIndicators.add(homeTab);

        BottomTabView orderTab = finder.find(R.id.order_tab);
        mTabIndicators.add(orderTab);

        BottomTabView showTab = finder.find(R.id.show_tab);
        mTabIndicators.add(showTab);

        BottomTabView myTab = finder.find(R.id.my_tab);
        mTabIndicators.add(myTab);

        homeTab.setOnClickListener(this);
        orderTab.setOnClickListener(this);
        showTab.setOnClickListener(this);
        myTab.setOnClickListener(this);

        initSlidingMenu();
        clickTab(homeTab);

    }

    @Override
    public void widgetClick(View v) {
       clickTab(v);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 点击Tab按钮
     *
     *
     */
    private void clickTab(View v) {
        resetOtherTabs();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);

        switch (v.getId()){
            case R.id.home_tab:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fragmentContainer,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;

            case R.id.order_tab:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                if(orderFragment == null){
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.fragmentContainer,orderFragment);
                }else{
                    transaction.show(orderFragment);
                }
                break;

            case R.id.show_tab:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                if(showFragment == null){
                    showFragment = new ShowFragment();
                    transaction.add(R.id.fragmentContainer,showFragment);
                }else{
                    transaction.show(showFragment);
                }
                break;

            case R.id.my_tab:
                mTabIndicators.get(3).setIconAlpha(1.0f);
                if(myFragment == null){
                    myFragment = new MyFragment();
                    transaction.add(R.id.fragmentContainer,myFragment);
                }else{
                    transaction.show(myFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs() {
        for(int i = 0; i < mTabIndicators.size(); i++){
            mTabIndicators.get(i).setIconAlpha(0);
        }
    }

    /**
     * 将所有的Fragment都置为隐藏状态
     * @param transaction
     * 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction){
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }

        if(orderFragment != null){
            transaction.hide(orderFragment);
        }

        if(showFragment != null){
            transaction.hide(showFragment);
        }

        if(myFragment != null){
            transaction.hide(myFragment);
        }
    }

    public void initSlidingMenu() {
        // 设置左侧滑动菜单
        setBehindContentView(R.layout.home_left_frme);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.leftHome_frame, new LeftHomeFragment()).commit();

        // 实例化滑动菜单对象
        SlidingMenu sm = getSlidingMenu();
        // 设置可以左右滑动的菜单
        sm.setMode(SlidingMenu.LEFT);
        // 设置滑动阴影的宽度
        sm.setShadowWidthRes(R.dimen.shadow_width);
        // 设置滑动菜单阴影的图像资源
        sm.setShadowDrawable(null);
        // 设置滑动菜单视图的宽度
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        sm.setFadeDegree(0.35f);
        // 设置触摸屏幕的模式,这里设置为禁止滑动
        sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        // 设置下方视图的在滚动时的缩放比例
        sm.setBehindScrollScale(0.0f);
    }


    @Override
    public void showSlidingMenu() {
        toggle();
    }
}
