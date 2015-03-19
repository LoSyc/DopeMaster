package com.daoguang.dopemaster.ui.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.support.adapter.CommonAdapter;
import com.daoguang.dopemaster.support.bean.PriceSort;
import com.daoguang.dopemaster.support.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/14.
 */
public class HomePriceFragment extends Fragment{

    private List<PriceSort> mList = new ArrayList<PriceSort>();

    private ListView mListView;
    private CommonAdapter<PriceSort> mAdapter;
    private ImageView mImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_team_price_listview, container, false);

        mImageView = (ImageView)view.findViewById(R.id.home_head_iamge);
        mListView = (ListView)view.findViewById(R.id.home_team_price_listview);

        getData();
        mListView.setAdapter(mAdapter = new CommonAdapter<PriceSort>(getActivity(), mList,
                R.layout.home_team_listview_item) {
            @Override
            public void convert(ViewHolder viewHolder, PriceSort item) {
                viewHolder.setImageResource(R.id.home_head_iamge, item.getImageId());
                viewHolder.setText(R.id.home_text_view_master, item.getMasterName());
                viewHolder.setText(R.id.home_text_view_number, item.getOrderNumber());
            }
        });
        return view;
    }

    //模拟数据
    public void getData() {
        PriceSort masterFirst = new PriceSort(R.mipmap.head_image, "徐师傅", "接单96次");
        mList.add(masterFirst);

        PriceSort masterSecond = new PriceSort(R.mipmap.head_image, "王师傅", "接单120次");
        mList.add(masterSecond);

        PriceSort masterThird = new PriceSort(R.mipmap.head_image, "李师傅", "接单56次");
        mList.add(masterThird);

        PriceSort masterFourth = new PriceSort(R.mipmap.head_image, "张师傅", "接单66次");
        mList.add(masterFourth);

        PriceSort masterFifth = new PriceSort(R.mipmap.head_image, "麦钜堂", "接单200次");
        mList.add(masterFifth);

        PriceSort masterSix = new PriceSort(R.mipmap.head_image, "黄师傅", "接单26次");
        mList.add(masterSix);

        PriceSort masterSeven = new PriceSort(R.mipmap.head_image, "叶师傅", "接单110次");
        mList.add(masterSeven);

        PriceSort masterEight = new PriceSort(R.mipmap.head_image, "许师傅", "接单66次");
        mList.add(masterEight);

        PriceSort masterNine = new PriceSort(R.mipmap.head_image, "方师傅", "接单76次");
        mList.add(masterFourth);

        PriceSort master_1 = new PriceSort(R.mipmap.head_image, "方师傅", "接单45次");
        mList.add(master_1);

        PriceSort master_2 = new PriceSort(R.mipmap.head_image, "刘师傅", "接单58次");
        mList.add(master_2);

        PriceSort master_3 = new PriceSort(R.mipmap.head_image, "庄师傅", "接单48次");
        mList.add(master_3);

        PriceSort master_4 = new PriceSort(R.mipmap.head_image, "庄师傅", "接单49次");
        mList.add(master_4);

        PriceSort master_5 = new PriceSort(R.mipmap.head_image, "蔡师傅", "接单58次");
        mList.add(master_5);
    }
}
