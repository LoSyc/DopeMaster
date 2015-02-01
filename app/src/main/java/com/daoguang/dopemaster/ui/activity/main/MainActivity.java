package com.daoguang.dopemaster.ui.activity.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daoguang.dopemaster.R;

import com.daoguang.dopemaster.base.BaseActivity;
import com.daoguang.dopemaster.ui.fragment.HomeFragment;
import com.daoguang.dopemaster.ui.fragment.MyFragment;
import com.daoguang.dopemaster.ui.fragment.OrderFragment;
import com.daoguang.dopemaster.ui.fragment.ShowFragment;


public class MainActivity extends BaseActivity {

    private HomeFragment homeFragment;
    private OrderFragment orderFragment;
    private ShowFragment showFragment;
    private MyFragment myFragment;

    private View homeLayout;
    private View orderLayout;
    private View showLayout;
    private View myLayout;

    private ImageView homeImage;
    private ImageView orderImage;
    private ImageView showImage;
    private ImageView myImage;

    private TextView homeText;
    private TextView orderText;
    private TextView showText;
    private TextView myText;

    private FragmentManager fragmentManager;

    private int fragmentIndex ;

    public <T extends View> T getView(int id) {
            return (T)super.findViewById(id);
    }

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_main);

        homeLayout = getView(R.id.home_layout);
        orderLayout = getView(R.id.order_layout);
        showLayout = getView(R.id.show_layout);
        myLayout = getView(R.id.my_layout);

        homeImage = getView(R.id.home_image);
        orderImage = getView(R.id.order_image);
        showImage = getView(R.id.show_image);
        myImage = getView(R.id.my_image);

        homeText = getView(R.id.home_text);
        orderText = getView(R.id.order_text);
        showText = getView(R.id.show_text);
        myText = getView(R.id.my_text);

        homeLayout.setOnClickListener(this);
        orderLayout.setOnClickListener(this);
        showLayout.setOnClickListener(this);
        myLayout.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch(v.getId()){
            case R.id.home_layout:
                //当点击了消息tab时 选中第一个Tab
                fragmentIndex = 0;
                setTabSelection(0);
                break;

            case R.id.order_layout:
                fragmentIndex = 1;
                setTabSelection(1);
                break;

            case R.id.show_layout:
                fragmentIndex = 2;
                setTabSelection(2);
                break;

            case R.id.my_layout:
                fragmentIndex = 3;
                setTabSelection(3);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
        //第一次启动时选中第0个tab
        setTabSelection(0);
    }

    /**
     * 根据传入的index参数来设置选中的tab页
     * @param index
     * 每个tab页对应的下标,0表示首页，1表示订单，2表示展示，3表示我的。
     */
    private void setTabSelection(int index){
        //每次选中之前先清除掉上次的选中状态
        clearSelection();
        //开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //先隐藏掉所有的Fragment 以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index){
            case 0:
                // 当点击了首页tab时，改变控件的图片和文字颜色
                homeImage.setImageResource(R.drawable.home_selected);
                homeText.setTextColor(Color.parseColor("#5F97FA"));
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;

            case 1:
                orderImage.setImageResource(R.drawable.order_selected);
                orderText.setTextColor(Color.parseColor("#5F97FA"));
                if(orderFragment == null){
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.content,orderFragment);
                }else{
                    transaction.show(orderFragment);
                }
                break;

            case 2:
                showImage.setImageResource(R.drawable.show_selected);
                showText.setTextColor(Color.parseColor("#5F97FA"));
                if(showFragment == null){
                    showFragment = new ShowFragment();
                    transaction.add(R.id.content,showFragment);
                }else{
                    transaction.show(showFragment);
                }
                break;

            case 3:
                myImage.setImageResource(R.drawable.my_selected);
                myText.setTextColor(Color.parseColor("#5F97FA"));
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
     * 清除掉所有的选中状态
     */
    private void clearSelection(){
        homeImage.setImageResource(R.drawable.home);
        homeText.setTextColor(Color.parseColor("#999999"));
        orderImage.setImageResource(R.drawable.order);
        orderText.setTextColor(Color.parseColor("#999999"));
        showImage.setImageResource(R.drawable.show);
        showText.setTextColor(Color.parseColor("#999999"));
        myImage.setImageResource(R.drawable.my);
        myText.setTextColor(Color.parseColor("#999999"));
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
