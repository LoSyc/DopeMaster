package com.daoguang.dopemaster.ui.fragment.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.daoguang.dopemaster.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 控制全部订单的页面
 * 作者:宋益聪
 * 时间：3月2号
 */
public class Order_Order_Fragment extends Fragment implements Order_Listview.IReflashListener,AdapterView.OnItemClickListener{

    SimpleAdapter msimp;

    View view;

    //模拟测试
    private Order_Listview mlv;
    private List<Map<String, Object>> list;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_tab_order, container, false);
//模拟测试
        mlv = (Order_Listview) view.findViewById(R.id.order_listview_order);
        mlv.setInterface(this);
        getData();
        msimp = new SimpleAdapter(getActivity().getApplicationContext(), list,
                R.layout.order_listview_item,
                new String[]{"order_team", "order_complete", "order_image",
                        "order_text", "order_time"},
                new int[]{R.id.order_item_team_name, R.id.order_item_team_finish, R.id.order_item_team_portrait,
                        R.id.order_item_team_address, R.id.order_item_team_time});

        mlv.setAdapter(msimp);
        mlv.setOnItemClickListener(this);
        return view;
    }



    // 加载SimpleAdapter数据集
    private List<Map<String, Object>> getData() {
        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("order_team", "负责团队：名字");
        map.put("order_complete", "订单已完成");
        map.put("order_image", R.mipmap.order_test);
        map.put("order_text","装修地址:广州市天河区华南理工大学");
        map.put("order_time", "下单时间：2015-2-12");

        list.add(map);

        map.clear();
        map.put("order_team", "负责团队：名字");
        map.put("order_complete", "订单未完成");
        map.put("order_image", R.mipmap.order_test);
        map.put("order_text","装修地址:广州市天河区华南理工大学");
        map.put("order_time", "下单时间：2015-2-12");

        list.add(map);

        map.clear();
        map.put("order_team", "负责团队：名字");
        map.put("order_complete", "订单已完成");
        map.put("order_image", R.mipmap.order_test);
        map.put("order_text","装修地址:广州市天河区华南理工大学");
        map.put("order_time", "下单时间：2015-2-12");

        list.add(map);

        map.clear();
        map.put("order_team", "负责团队：名字");
        map.put("order_complete", "订单已完成");
        map.put("order_image", R.mipmap.order_test);
        map.put("order_text","装修地址:广州市天河区华南理工大学");
        map.put("order_time", "下单时间：2015-2-12");

        list.add(map);

        return list;
    }


    /**
     * 数据刷新在此做
     */
    @Override
    public void onReflash() {
        //下面是模拟数据
        android.os.Handler handler=new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mlv.reflashComplete();
            }
        },2000);

    }

    //下面是传输数据
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(),Order_page_item.class);
        startActivity(intent);
    }
}
