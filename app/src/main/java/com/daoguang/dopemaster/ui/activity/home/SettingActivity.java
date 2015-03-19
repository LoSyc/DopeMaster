package com.daoguang.dopemaster.ui.activity.home;

import android.os.Bundle;
import android.view.View;

import com.daoguang.dopemaster.R;
import com.daoguang.dopemaster.base.BaseActivity;
import com.daoguang.dopemaster.support.utils.ViewFinder;
import com.daoguang.dopemaster.ui.view.TopBar;

/**
 * Created by joker on 2015/3/15.
 */
public class SettingActivity extends BaseActivity {

    @Override
    public void initWidget() {
        setContentView(R.layout.home_setting);
        ViewFinder finder = new ViewFinder(this);
        TopBar topBar = finder.find(R.id.topBar);
        topBar.setOnTopBarClickListener(new TopBar.TopBarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {

            }
        });

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
