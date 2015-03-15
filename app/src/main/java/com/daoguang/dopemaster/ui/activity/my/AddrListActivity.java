package com.daoguang.dopemaster.ui.activity.my;

import android.support.v4.app.Fragment;
import com.daoguang.dopemaster.ui.fragment.my.MyAddrListFragment;

/**
 * 托管地址列表类
 * 用于往Fragment中传递参数
 * Created by 麦均贤 on 2015/3/11.
 */
public class AddrListActivity extends SingleFragmentActivity {
    private static final String TAG = "fragment.my.AddrListActivity";

    @Override
    protected Fragment createFragment() {
        return new MyAddrListFragment();
    }
}
