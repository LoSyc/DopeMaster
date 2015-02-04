package com.daoguang.dopemaster.ui.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.BaseActivity;
import com.daoguang.dopemaster.support.utils.ViewFinder;
import com.daoguang.dopemaster.ui.fragment.HomeFragment;
import com.daoguang.dopemaster.ui.fragment.MyFragment;
import com.daoguang.dopemaster.ui.fragment.OrderFragment;
import com.daoguang.dopemaster.ui.fragment.ShowFragment;
import com.daoguang.dopemaster.ui.view.BottomTabView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private ShowFragment showFragment;
    private MyFragment myFragment;

    private FragmentManager fragmentManager;

    private List<BottomTabView> mTabIndicators = new ArrayList<BottomTabView>();

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_main);
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

        homeTab.setIconAlpha(1.0f);

    }

    @Override
    public void widgetClick(View v) {
       clickTab(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentMansger();

    }

    /**
     * 点击Tab按钮
     *
     * @param v
     */
    private void clickTab(View v) {
        resetOtherTabs();

        //开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先隐藏掉所有的Fragment 以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);

        switch (v.getId()){
            case R.id.home_tab:
                mTabIndicators.get(0).setIconAlpha(1.0f);
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;

            case R.id.order_tab:
                mTabIndicators.get(1).setIconAlpha(1.0f);
                if(orderFragment == null){
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.content,orderFragment);
                }else{
                    transaction.show(orderFragment);
                }
                break;

            case R.id.show_tab:
                mTabIndicators.get(2).setIconAlpha(1.0f);
                if(showFragment == null){
                    showFragment = new ShowFragment();
                    transaction.add(R.id.content,showFragment);
                }else{
                    transaction.show(showFragment);
                }
                break;

            case R.id.my_tab:
                mTabIndicators.get(3).setIconAlpha(1.0f);
                if(myFragment == null){
                    myFragment = new MyFragment();
                    transaction.add(R.id.content,myFragment);
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

}
