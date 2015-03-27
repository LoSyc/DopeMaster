package com.daoguang.dopemaster.ui.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.support.adapter.CommonAdapter;
import com.daoguang.dopemaster.support.bean.DesignInfo;
import com.daoguang.dopemaster.support.callBack.AnnounceFrCall;
import com.daoguang.dopemaster.support.utils.ViewFinder;
import com.daoguang.dopemaster.support.utils.ViewHolder;
import com.daoguang.dopemaster.ui.view.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joker on 2015/3/21.
 */
public class DesiginChooseFragment extends Fragment{
    private CommonAdapter<DesignInfo> mAdapter;
    private ListView mListView;
    private TextView styleTv;
    private ImageView imageView;

    private List<DesignInfo> mdatas = new ArrayList<DesignInfo>();

    private AnnounceFrCall call;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.home_announce_design,container,false);
        ViewFinder finder = new ViewFinder(view);
        mListView = finder.find(R.id.designLv);
        initDatas();
        mListView.setAdapter(mAdapter = new CommonAdapter<DesignInfo>(
                getActivity(),mdatas,R.layout.home_announce_design_item) {
            @Override
            public void convert(ViewHolder viewHolder, DesignInfo item) {
                viewHolder.setText(R.id.list_tv,item.getStyle());
                viewHolder.setImageResource(R.id.list_iv,item.getImagId());
            }
        });

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
        return view;

    }

    private void initDatas() {
        DesignInfo designInfo = new DesignInfo("宜家",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("现代",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("简欧",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("欧式",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("北欧",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("中式",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("简约",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("地中海",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("日式",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("美式",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("东南亚",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("田园",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("混搭",R.mipmap.city_selected);
        mdatas.add(designInfo);
        designInfo = new DesignInfo("新古典",R.mipmap.city_selected);
        mdatas.add(designInfo);
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

    // 设置返回数据
    protected void setResult(int which) {
        // 判断是否设置了targetFragment
        if (getTargetFragment() == null)
            return;

        Intent intent = new Intent();
//        intent.putExtra("g", mdatas.get(which));
        getTargetFragment().onActivityResult(1,
                Activity.RESULT_OK, intent);

    }
}
