package com.daoguang.dopemaster.ui.fragment.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.support.utils.ViewFinder;
import com.daoguang.dopemaster.ui.view.TopBar;

/**
 * 首页Fragment
 * Created by joker on 2015/2/1.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private LinearLayout mAnnounceOrderLayout ;
    private LinearLayout mFindTeamLayout ;
    private LinearLayout mQuestionAnswerLayout ;
    private LinearLayout mMaterialRecommendatonLayout ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeLayout =inflater.inflate(R.layout.home_layout,container,false);
        ViewFinder finder = new ViewFinder(homeLayout);

        TopBar topBar = finder.find(R.id.topBar);
        topBar.setOnTopBarClickListener(new TopBar.TopBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(getActivity(),"left",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(getActivity(),"right",Toast.LENGTH_SHORT).show();
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

        return homeLayout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.announce_order:
                Toast.makeText(getActivity(), "发布装修单", Toast.LENGTH_SHORT).show();
                break;

            case R.id.find_team:
                Toast.makeText(getActivity(), "寻找装修团队", Toast.LENGTH_SHORT).show();
                break;

            case R.id.question_answer:
                Toast.makeText(getActivity(), "装修问答专栏", Toast.LENGTH_SHORT).show();
                break;

            case R.id.material_recommendation:
                Toast.makeText(getActivity(), "装修材料推荐", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
