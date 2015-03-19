package com.daoguang.dopemaster.ui.fragment.show;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.daoguang.dopemaster.R;

/**
 * Created by joker on 2015/2/1.
 */
public class ShowFragment extends Fragment {


    private GridView gridView;
    //图片的文字标题
    private String[] titles = new String[]
            { "干净整洁，时尚豪华", "高端的设备，不一样的选择", "温馨之作，打造非凡作品", "简单时尚，你还在等什么",
                    "明亮而鲜艳，成就非凡的你", "简简单单，蕴含多样芬芳", "高贵不失高雅，最好的选择",
                    "那般的美，又那般的舒适"};
    //图片ID数组
    private int[] images = new int[]{
            R.mipmap.show_pic1, R.mipmap.show_pic2, R.mipmap.show_pic3,
            R.mipmap.show_pic4, R.mipmap.show_pic5, R.mipmap.show_pic6,
            R.mipmap.show_pic7, R.mipmap.show_pic8

    };
    private String[] prices=new String[]{
            "¥1200","¥2500","¥1820","¥2999","¥10999","¥1800","¥3220","¥3120"
    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // View showLayout =inflater.inflate(R.layout.show_layout,container,false);
       // ViewFinder finder = new ViewFinder(showLayout);
        //TopBar topBar = finder.find(R.id.topBar);
        View view=inflater.inflate(R.layout.show_layout,container,false);
        gridView = (GridView)view.findViewById(R.id.gridview);
        Show_PictureAdapter adapter = new Show_PictureAdapter(titles, images,prices, getActivity());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                Toast.makeText(getActivity(), "pic" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
        return view;


    }





}
