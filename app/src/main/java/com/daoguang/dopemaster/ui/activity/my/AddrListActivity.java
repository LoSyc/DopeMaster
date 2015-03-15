package com.daoguang.dopemaster.ui.activity.my;

import android.support.v4.app.Fragment;
import com.daoguang.dopemaster.ui.fragment.my.MyAddrListFragment;

/**
 * Created by Administrator on 2015/3/12.
 */
public class AddrListActivity extends SingleFragmentActivity {
    private static final String TAG = "fragment.my.AddrListActivity";

    @Override
    protected Fragment createFragment() {
        return new MyAddrListFragment();
    }
}
