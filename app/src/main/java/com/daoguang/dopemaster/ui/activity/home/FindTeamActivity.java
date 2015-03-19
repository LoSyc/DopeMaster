package com.daoguang.dopemaster.ui.activity.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.BaseActivity;
import com.daoguang.dopemaster.ui.fragment.home.HomePopularityFragment;
import com.daoguang.dopemaster.ui.fragment.home.HomePriceFragment;
import com.daoguang.dopemaster.ui.view.TopBar;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/14.
 */
public class FindTeamActivity extends BaseActivity implements TopBar.TopBarClickListener {

    private TopBar mTopBar;
    private ViewPager mViewPager;
    private ImageView mTabLine;
    private List<Fragment> mFragmentList;
    private FragmentPagerAdapter mAdapter;

    private int mScreen1_2;
    private int mCurrentPageIndex;

    //切换Tab
    private RelativeLayout mPrice;
    private RelativeLayout mPopularity;
    private TextView mPriceTV;
    private TextView mPopularityTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidget() {
        setContentView(R.layout.home_find_team);

        mTabLine = (ImageView)findViewById(R.id.home_tabline);
        initTabLine(mTabLine);

        mTopBar = (TopBar)findViewById(R.id.topBar);
        mTopBar.setLeftIsVisable(true);
        mTopBar.setrightIsVisable(false);
        mTopBar.setOnTopBarClickListener(this);

        mPrice = (RelativeLayout)findViewById(R.id.home_find_team_price);
        mPopularity = (RelativeLayout)findViewById(R.id.home_find_team_popularity);
        mPriceTV = (TextView)findViewById(R.id.home_price_textview);
        mPopularityTV = (TextView)findViewById(R.id.home_popularity_textview);

        mPrice.setOnClickListener(this);
        mPopularity.setOnClickListener(this);

        //数据源
        HomePriceFragment homePriceFragment = new HomePriceFragment();
        HomePopularityFragment homePopularityFragment = new HomePopularityFragment();
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(homePriceFragment);
        mFragmentList.add(homePopularityFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        };

        mViewPager = (ViewPager)findViewById(R.id.home_view_pager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine
                        .getLayoutParams();

                // 0->1
                if (mCurrentPageIndex == 0 && position == 0) {
                    lp.leftMargin = (int) (positionOffset * mScreen1_2 + mCurrentPageIndex
                            * mScreen1_2);
                } else if (mCurrentPageIndex == 1 && position == 0){
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_2 + (positionOffset - 1)
                            * mScreen1_2);
                }// 1->0

                mTabLine.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();

                switch (position) {
                    case 0:
                        mPriceTV.setTextColor(Color.parseColor("#5f97fa"));
                        break;

                    case 1:
                        mPopularityTV.setTextColor(Color.parseColor("#5f97fa"));
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.topBar :
                leftClick();
                break;

            case R.id.home_find_team_price:
                new HomePriceFragment();
                break;

            case R.id.home_find_team_popularity:
                new HomePopularityFragment();
                break;

            default:
                break;

        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }



    /**
     * 初始化Tab下划线
     * @param mTabLine
     */
    public void initTabLine(ImageView mTabLine) {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        mScreen1_2 = metrics.widthPixels / 2;
        ViewGroup.LayoutParams lp = mTabLine.getLayoutParams();
        lp.width = mScreen1_2;
        mTabLine.setLayoutParams(lp);
    }

    public void resetTextView() {
        mPriceTV.setTextColor(Color.parseColor("#000000"));
        mPopularityTV.setTextColor(Color.parseColor("#000000"));
    }
}
