package com.daoguang.dopemaster.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoguang.dopemaster.R;

/**
 * Created by joker on 2015/2/24.
 */
public class LeftHomeFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_left_layout,container,false);
        return view;
    }
}
