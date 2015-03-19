package com.daoguang.dopemaster.ui.fragment.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.support.adapter.CommonAdapter;
import com.daoguang.dopemaster.support.callBack.AnnounceFrCall;
import com.daoguang.dopemaster.support.utils.ViewFinder;
import com.daoguang.dopemaster.support.utils.ViewHolder;
import com.daoguang.dopemaster.ui.view.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joker on 2015/3/17.
 */
public class HuXingChooseFragment extends Fragment{

    private GridView huxingGv;
    private CommonAdapter<String> mAdapter;
    private List<String> mDatas = new ArrayList<String>();

    private AnnounceFrCall call;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View huxingLayout = inflater.inflate(R.layout.home_announce_huxing,container,false);
        ViewFinder finder = new ViewFinder(huxingLayout);

        initDatas();

        huxingGv = finder.find(R.id.huxingGv);
        huxingGv.setAdapter(mAdapter = new CommonAdapter<String>(
                getActivity(), mDatas, R.layout.home_announce_huxing_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                 helper.setButtonText(R.id.huxingBt,item);
            }

        } );

        TopBar topBar = finder.find(R.id.topBar);
        topBar.setOnTopBarClickListener(new TopBar.TopBarClickListener() {
            @Override
            public void leftClick() {
                call.hideFragment();
            }

            @Override
            public void rightClick() {

            }
        });
        return huxingLayout;
    }

    private void initDatas() {
        String item = "一居";
        mDatas.add(item);
        item = "二居";
        mDatas.add(item);
        item = "三居";
        mDatas.add(item);
        item = "别墅";
        mDatas.add(item);
        item = "四居";
        mDatas.add(item);
        item = "公寓";
        mDatas.add(item);
        item = "复式";
        mDatas.add(item);
        item = "小户型";
        mDatas.add(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof AnnounceFrCall) {
            call = (AnnounceFrCall) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implements AnnounceFrCall");
        }
    }
}
