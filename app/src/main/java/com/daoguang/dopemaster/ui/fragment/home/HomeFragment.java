package com.daoguang.dopemaster.ui.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.support.bean.PageInfo;
import com.daoguang.dopemaster.support.callBack.ShowSlidingMenuListener;
import com.daoguang.dopemaster.support.utils.ViewFinder;
import com.daoguang.dopemaster.ui.activity.RegisterActivity;
import com.daoguang.dopemaster.ui.activity.home.AnnounceOrderActivity;
import com.daoguang.dopemaster.ui.activity.home.FindTeamActivity;
import com.daoguang.dopemaster.ui.activity.home.MaterialRecommendationActivity;
import com.daoguang.dopemaster.ui.activity.home.SettingActivity;
import com.daoguang.dopemaster.ui.view.TopBar;

import java.util.ArrayList;

import cn.lightsky.infiniteindicator.InfiniteIndicatorLayout;
import cn.lightsky.infiniteindicator.slideview.BaseSliderView;
import cn.lightsky.infiniteindicator.slideview.DefaultSliderView;

/**
 * 首页Fragment
 * Created by joker on 2015/2/1.
 */
public class HomeFragment extends Fragment implements View.OnClickListener,BaseSliderView.OnSliderClickListener{

    private LinearLayout mAnnounceOrderLayout ;
    private LinearLayout mFindTeamLayout ;
    private LinearLayout mQuestionAnswerLayout ;
    private LinearLayout mMaterialRecommendatonLayout ;

    private ArrayList<PageInfo> viewInfos;
    private InfiniteIndicatorLayout mAnimCircleIndicator;

    private ShowSlidingMenuListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeLayout = inflater.inflate(R.layout.home_layout,container,false);
        ViewFinder finder = new ViewFinder(homeLayout);


        TopBar topBar = finder.find(R.id.topBar);
        topBar.setOnTopBarClickListener(new TopBar.TopBarClickListener() {
            @Override
            public void leftClick() {
                listener.showSlidingMenu();
            }

            @Override
            public void rightClick() {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, R.anim.alpha_out);
            }
        });

        mAnnounceOrderLayout = finder.find(R.id.announce_order);
        mFindTeamLayout = finder.find(R.id.find_team);
        mQuestionAnswerLayout = finder.find(R.id.question_answer);
        mMaterialRecommendatonLayout = finder.find(R.id.material_recommendation);

        mAnnounceOrderLayout.setOnClickListener(this);
        mFindTeamLayout.setOnClickListener(this);
        mQuestionAnswerLayout.setOnClickListener(this);
        mMaterialRecommendatonLayout.setOnClickListener(this);

        viewInfos = new ArrayList<PageInfo>();
        viewInfos.add(new PageInfo("Page A", R.mipmap.advertising));
        viewInfos.add(new PageInfo("Page B", R.mipmap.advertising));
        viewInfos.add(new PageInfo("Page C", R.mipmap.advertising));
        viewInfos.add(new PageInfo("Page D", R.mipmap.advertising));
        viewInfos.add(new PageInfo("Page E", R.mipmap.advertising));

        testAnimCircleIndicator(homeLayout);

        return homeLayout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.announce_order:
                Intent intent = new Intent(getActivity(), AnnounceOrderActivity.class);
                startActivity(intent);
                break;

            case R.id.find_team:
                Intent intentFind = new Intent(getActivity(), FindTeamActivity.class);
                startActivity(intentFind);
                break;

            case R.id.question_answer:
                intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.material_recommendation:
                Intent intentMaterial = new Intent(getActivity(), MaterialRecommendationActivity.class);
                startActivity(intentMaterial);
                break;

        }
    }

    //To avoid memory leak ,you should release the res
    @Override
    public void onPause() {
        super.onPause();
        mAnimCircleIndicator.stopAutoScroll();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ShowSlidingMenuListener) {
            listener = (ShowSlidingMenuListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implement ShowSlidingMenuListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAnimCircleIndicator.startAutoScroll();
    }

    private void testAnimCircleIndicator(View v) {
        mAnimCircleIndicator = (InfiniteIndicatorLayout)(v.findViewById(R.id.infinite_anim_circle));
        for(PageInfo name : viewInfos){
            DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
            textSliderView
                    .image(name.getDrawableRes())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.getBundle()
                    .putString("extra", name.getData());
            mAnimCircleIndicator.addSlider(textSliderView);
        }
        mAnimCircleIndicator.setIndicatorPosition(InfiniteIndicatorLayout.IndicatorPosition.Center_Bottom);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

}
