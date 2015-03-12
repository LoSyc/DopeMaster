package com.daoguang.dopemaster.ui.fragment.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.daoguang.dopemaster.R;

/**
 * Created by Administrator on 2015/3/11.
 */
public class MyAddrFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "fragment.my.CRIME_ID";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createaddr, container, false);
        return view;
    }
}
