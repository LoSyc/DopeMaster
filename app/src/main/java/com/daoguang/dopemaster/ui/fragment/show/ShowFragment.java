package com.daoguang.dopemaster.ui.fragment.show;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daoguang.dopemaster.R;

/**
 * Created by joker on 2015/2/1.
 */
public class ShowFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View showLayout =inflater.inflate(R.layout.show_layout,container,false);
        return showLayout;
    }
}