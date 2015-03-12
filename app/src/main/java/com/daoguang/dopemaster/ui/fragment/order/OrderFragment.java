package com.daoguang.dopemaster.ui.fragment.order;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daoguang.dopemaster.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单页面Fragment
 * 作者：宋益聪
 * 时间：三月一号
 * 修改：三月十一号，增加Tab按钮点击事件
 */
public class OrderFragment extends Fragment implements View.OnClickListener {


    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;

    //order页面上面的TextView
    private TextView mOrder;
    private TextView mOngoing;
    private TextView mEvaluate;
    private ImageView mTabline;

    //order页面上面的LinearLayout布局
    private LinearLayout mOrderLinearLayout;
    private LinearLayout mOngoingLinearLayout;
    private LinearLayout mEvaluateLinearLayout;

    private int mScreen1_3;
    private int mCurrentPageIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View orderLayout = inflater.inflate(R.layout.order_layout, container, false);

        initTabLine(orderLayout);
        initView(orderLayout);

        return orderLayout;
    }


    /**
     * 处理顶部切换按钮
     *
     * @param orderLayout
     */
    private void initView(View orderLayout) {

        mViewPager = (ViewPager) orderLayout.findViewById(R.id.order_viewpager);
        mOrder = (TextView) orderLayout.findViewById(R.id.order_tab_order);
        mOngoing = (TextView) orderLayout.findViewById(R.id.order_tab_ongoing);
        mEvaluate = (TextView) orderLayout.findViewById(R.id.order_tab_evaluate);

        mOrderLinearLayout=(LinearLayout)orderLayout.findViewById(R.id.order_order_button);
        mOngoingLinearLayout=(LinearLayout)orderLayout.findViewById(R.id.order_ongoing_button);
        mEvaluateLinearLayout=(LinearLayout)orderLayout.findViewById(R.id.order_evaluate_button);

        //设置顶部点击事件
        mOngoingLinearLayout.setOnClickListener(this);
        mOrderLinearLayout.setOnClickListener(this);
        mEvaluateLinearLayout.setOnClickListener(this);

        //Order切换Fragment页面
        Order_Order_Fragment order_order_fragment = new Order_Order_Fragment();
        Order_Ongoing_Fragment order_ongoing_fragment = new Order_Ongoing_Fragment();
        Order_Evaluate_Fragment order_evaluate_fragment = new Order_Evaluate_Fragment();

        mDatas = new ArrayList<Fragment>();

        mDatas.add(order_order_fragment);
        mDatas.add(order_ongoing_fragment);
        mDatas.add(order_evaluate_fragment);

        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mDatas.get(i);
            }

            @Override
            public int getCount() {
                return mDatas.size();
            }
        };

        mViewPager.setAdapter(mAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();

                if (mCurrentPageIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 0)// 1->0
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1)
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex
                            * mScreen1_3);
                } else if (mCurrentPageIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1)
                            * mScreen1_3);
                }
                mTabline.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int i) {
                resetTextView();
                switch (i) {
                    case 0:
                        mOrder.setTextColor(Color.parseColor("#5f97fa"));
                        break;
                    case 1:
                        mOngoing.setTextColor(Color.parseColor("#5f97fa"));
                        break;
                    case 2:
                        mEvaluate.setTextColor(Color.parseColor("#5f97fa"));
                        break;

                }
                mCurrentPageIndex = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    /**
     * 恢复界面
     */
    protected void resetTextView() {
        mOrder.setTextColor(Color.parseColor("#000000"));
        mOngoing.setTextColor(Color.parseColor("#000000"));
        mEvaluate.setTextColor(Color.parseColor("#000000"));
    }

    /**
     * 初始化下划线
     *
     * @param orderLayout
     */
    private void initTabLine(View orderLayout) {
        mTabline = (ImageView) orderLayout.findViewById(R.id.order_tabline);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 3;
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);
    }


    /**
     * 处理点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabline
                .getLayoutParams();
        switch (v.getId()) {
            case R.id.order_order_button:
                mViewPager.setCurrentItem(0);
                setColortoText(0);
			lp.leftMargin = (int) (0 * mScreen1_3);
                break;
            case R.id.order_ongoing_button:
                mViewPager.setCurrentItem(1);
                setColortoText(1);
    			lp.leftMargin = (int) (1 * mScreen1_3);
                break;
            case R.id.order_evaluate_button:
                mViewPager.setCurrentItem(2);
                setColortoText(2);
			lp.leftMargin = (int) (2 * mScreen1_3);
                break;
        }
        mTabline.setLayoutParams(lp);
    }

    private void setColortoText(int position){
        resetTextView();
        switch (position) {
            case 0:
                mOrder.setTextColor(Color.parseColor("#5f97fa"));
                break;
            case 1:
                mOngoing.setTextColor(Color.parseColor("#5f97fa"));
                break;
            case 2:
                mEvaluate.setTextColor(Color.parseColor("#5f97fa"));
                break;
        }

        mCurrentPageIndex = position;
    }
}
