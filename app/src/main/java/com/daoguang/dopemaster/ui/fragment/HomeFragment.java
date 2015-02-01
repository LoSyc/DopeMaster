package com.daoguang.dopemaster.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoguang.dopemaster.R;

/**
 * Created by joker on 2015/2/1.
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View homeLayout =inflater.inflate(R.layout.home_layout,container,false);
        return homeLayout;
    }
}
