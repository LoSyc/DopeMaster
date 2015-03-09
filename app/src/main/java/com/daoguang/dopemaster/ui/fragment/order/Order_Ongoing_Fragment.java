package com.daoguang.dopemaster.ui.fragment.order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoguang.dopemaster.R;


/**
 * 控制正在进行的页面
 与Order_Order_Fragment类是，不模拟
 * 作者:宋益聪
 * 时间：3月2号
 */
public class Order_Ongoing_Fragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.order_tab_ongoing,container,false);

        return view;
    }
}
